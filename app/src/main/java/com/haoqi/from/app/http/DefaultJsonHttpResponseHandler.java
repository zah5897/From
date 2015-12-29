package com.haoqi.from.app.http;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class DefaultJsonHttpResponseHandler {
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
    }

    public void onFailure(int statusCode, String responseString, Throwable throwable) {
    }

}
