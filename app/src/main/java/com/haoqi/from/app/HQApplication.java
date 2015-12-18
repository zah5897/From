package com.haoqi.from.app;

import android.app.Application;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class HQApplication extends Application {
    public static HQApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static HQApplication getApp() {
        return app;
    }
}
