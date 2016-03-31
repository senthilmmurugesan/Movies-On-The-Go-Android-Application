package com.example.senthilkumar.moviesotg.Volley;

import android.app.Application;
import android.content.Context;

/**
 * Created by Senthil Kumar on 3/7/2016.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
