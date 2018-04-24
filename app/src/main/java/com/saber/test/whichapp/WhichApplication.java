package com.saber.test.whichapp;

import android.app.Application;

public class WhichApplication extends Application {

    static WhichApplication instance;

    public static WhichApplication getInstance() {
        return instance;
    }

    public void setInstance(WhichApplication instance) {
        this.instance = instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
