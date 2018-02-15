package com.example.atishay.myexample;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Atishay on 24-01-2018.
 */

public class Splash extends AppCompatActivity {


    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.splash);

        StartAnimations();



        Thread thread = new Thread(){

            @Override
            public void run() {
                try {

                    sleep(3000);

                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {

                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }


            }

        };thread.start();
    }



    private void StartAnimations() {

        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
                R.anim.fadein);
        l.startAnimation(hyperspaceJumpAnimation);


    }




}
