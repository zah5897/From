package com.haoqi.from.app.listener;

import org.json.JSONObject;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public interface CallBackListener {
    public void onSuccess(int statusCode, Object... obj);

    public void onFailure(int statusCode, String message);
}
