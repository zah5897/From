package com.haoqi.from.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class ConfigManager {
    public static final String PROP_NAME = "hq_app_forly";
    public int screenWidth, screenHeight;
    public float density;
    private SharedPreferences spf;

    private ConfigManager(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        density = displayMetrics.density;
        spf = context.getSharedPreferences(PROP_NAME, Context.MODE_PRIVATE);
    }

    private static ConfigManager configManager;

    public static ConfigManager Instance(Context context) {
        if (configManager == null) {
            configManager = new ConfigManager(context);
        }
        return configManager;
    }

    public static ConfigManager Instance() {
        return configManager;
    }

    public void put(String name, String value) {
        spf.edit().putString(name, value).commit();
    }

    public void put(String name, long value) {
        spf.edit().putLong(name, value).commit();
    }

    public void put(String name, boolean value) {
        spf.edit().putBoolean(name, value).commit();
    }

    public void put(String name, int value) {
        spf.edit().putInt(name, value).commit();
    }


    public String getString(String name) {
        return spf.getString(name, "");
    }

    public long getLong(String name) {
        return spf.getLong(name, 0l);
    }

    public int getInt(String name) {
        return spf.getInt(name, 0);
    }

    public boolean getBoolean(String name) {
        return spf.getBoolean(name, false);
    }
}
