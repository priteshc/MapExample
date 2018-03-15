package com.example.atishay.myexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

/**
 * Created by Atishay on 09-03-2018.
 */

public class Packges extends AppCompatActivity {

    private MaterialSpinner mPackage;

    private ArrayList<String> patch = new ArrayList<>();
    private ArrayList<String> area = new ArrayList<>();


    Button button;
    private Context context = Packges.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.packges);


        button = findViewById(R.id.pro);

        patch.add("Road1");
        patch.add("Road2");


        patch.add(0,"Select Area");

        area.add(0,"Select Area");

        area.add("Worli");
        area.add("Thane");

        mPackage = findViewById(R.id.operator);

        mPackage.setItems(area);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (NetworkUtil.getConnectivityStatus(Packges.this.getApplicationContext())!= 0) {


                    if (mPackage.getSelectedIndex() != 0) {

                        Intent intent = new Intent(Packges.this, DetailPackage.class);
                        intent.putExtra("Road", patch.get(mPackage.getSelectedIndex()));
                        startActivity(intent);
                    }
                    else
                        {

                        Toast.makeText(context, "Please select Area", Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    Toast.makeText(context, "Please check internet connection", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
