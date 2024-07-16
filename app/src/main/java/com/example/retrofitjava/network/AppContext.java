package com.example.retrofitjava.network;

import android.app.Application;
import android.content.Context;

public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance()
    {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
