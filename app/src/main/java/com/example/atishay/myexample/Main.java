package com.example.atishay.myexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.Locale;

/**
 * Created by Atishay on 06-03-2018.
 */

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loadscreen);

        final SampleAdapter sampleAdapter = new SampleAdapter();
        sampleAdapter.addData(10);

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        final View actionB = findViewById(R.id.action_b);

        FloatingActionButton actionC = findViewById(R.id.action_a);
        actionC.setTitle("Add Benificiary");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Main.this,"Add Benificiary",Toast.LENGTH_SHORT).show();

                menuMultipleActions.collapse();
               // actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        FloatingActionButton actionb = findViewById(R.id.action_b);
        actionb.setTitle("Add New Customer");
        actionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Main.this,"Add New Customer",Toast.LENGTH_SHORT).show();

                menuMultipleActions.collapse();
                // actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final ScrollLoadRecyclerView scrollLoadRecyclerView = findViewById(R.id.scroll_load_recycler_view);

        scrollLoadRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && menuMultipleActions.getVisibility() == View.VISIBLE) {
                    menuMultipleActions.setVisibility(View.GONE);
                    menuMultipleActions.collapse();
                } else if (dy <= 0 && menuMultipleActions.getVisibility() != View.VISIBLE) {
                    menuMultipleActions.setVisibility(View.VISIBLE);
                }
            }

        });


        scrollLoadRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        scrollLoadRecyclerView.setAdapter(sampleAdapter);
        scrollLoadRecyclerView.setOnLoadListener(new ScrollLoadRecyclerView.OnLoadListener() {
            @Override
            public void onLoad() {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        sampleAdapter.addData(10);

                        scrollLoadRecyclerView.setLoading(false);
                    }
                }.execute();
            }
        });
    }

    private class SampleAdapter extends ScrollLoadRecyclerView.Adapter<SampleViewHolder> {
        int dataSie = 0;

        void addData(int dataSize) {
            this.dataSie += dataSize;
            notifyDataSetChanged();
        }

        @Override
        public SampleViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
            return new SampleViewHolder(view);
        }

        @Override
        public void onBindDataViewHolder(SampleViewHolder holder, int position) {
            holder.textView.setText(String.format(Locale.getDefault(), "Item: %d", position + 1));


            PushDownAnim.setPushDownAnimTo(holder.itemView,holder.delete)
                    .setScale(PushDownAnim.MODE_STATIC_DP, 2)
                    .setDurationPush(PushDownAnim.DEFAULT_PUSH_DURATION)
                    .setDurationRelease(PushDownAnim.DEFAULT_RELEASE_DURATION)
                    .setInterpolatorPush(PushDownAnim.DEFAULT_INTERPOLATOR)
                    .setInterpolatorRelease(PushDownAnim.DEFAULT_INTERPOLATOR);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(),"Click",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(),Details.class);
                    startActivity(intent);
                }
            });


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(),"Delete",Toast.LENGTH_SHORT).show();

                }
            });
        }

        @Override
        public int getDataItemCount() {
            return dataSie;
        }

        @Override
        public int getDataItemViewType() {
            return 0;
        }
    }

    private class SampleViewHolder extends ScrollLoadRecyclerView.ViewHolder {
        private TextView textView;
        private ImageView delete;

        SampleViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);

            delete = itemView.findViewById(R.id.delete1);

        }
    }

}
