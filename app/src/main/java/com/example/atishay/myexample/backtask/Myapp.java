package com.example.atishay.myexample.backtask;

import android.app.Application;
import android.content.Context;

/**
 * Created by Atishay on 05-02-2018.
 */

public class Myapp extends Application {

    private static Myapp instance;

    public static String BASE_URL ="https://www.atishayonline.com/android/AndroidAPI/";
    public static final String TAG = Myapp.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


    }

    public static Myapp getInstance()
    {

        return instance;
    }



    public static Context getapContext(){


        return instance.getApplicationContext();
    }



}