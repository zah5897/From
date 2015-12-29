package com.haoqi.from.util;

import android.widget.Toast;

import com.haoqi.from.app.HQApplication;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class ToastUtil {
    public static void show(String message) {
        Toast.makeText(HQApplication.getApp(), message, Toast.LENGTH_SHORT).show();
    }
}
