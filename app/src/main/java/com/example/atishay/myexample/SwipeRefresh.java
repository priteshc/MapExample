package com.example.atishay.myexample;

/**
 * Created by Atishay on 16-03-2018.
 */



import android.annotation.SuppressLint;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
        import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

public class SwipeRefresh extends AppCompatActivity {

    private JellyRefreshLayout mJellyLayout;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Jelly");*/
        mJellyLayout = (JellyRefreshLayout) findViewById(R.id.jelly_refresh);
        mJellyLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mJellyLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        View loadingView = LayoutInflater.from(this).inflate(R.layout.loading, null);
        mJellyLayout.setLoadingView(loadingView);
    }

}