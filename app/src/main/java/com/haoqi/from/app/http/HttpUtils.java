package com.haoqi.from.app.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.haoqi.from.app.HQApplication;
import com.haoqi.from.app.listener.CallBackListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HttpUtils {
    private static WeakReference<AsyncHttpClient> httpClientRef = new WeakReference<AsyncHttpClient>(
            null);

    private static WeakReference<AsyncHttpClient> getHttpClientRef() {
        return httpClientRef;
    }

    public synchronized static AsyncHttpClient getAsyncHttpClient() {
        AsyncHttpClient httpClient = httpClientRef.get();
        if (httpClient == null) {
            httpClient = new AsyncHttpClient();
            httpClient.setTimeout(10 * 2000);
            httpClient.setMaxRetriesAndTimeout(0, 500);
            httpClientRef = new WeakReference<AsyncHttpClient>(httpClient);
        }
        return httpClient;
    }

    public static void post(String url, RequestParams params, CallBackListener callBackListener) {

        getAsyncHttpClient().post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


//        if (isNetworkConnected()) {
//            getAsyncHttpClient().post(url, params,new JsonHttpResponseHandler(){
//                @Override
//                public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
//                    super.onSuccess(statusCode, headers, response);
//                }
//            });
//        } else {
//            String responseString = "网络不可用";
//            asyncHttpResponseHandler.onFailure(404, null, responseString, null);
//            ToastUtil.show(responseString);
//        }


    }


    public static List<?> arrayToModel(JSONArray array, Class clazz) {
        int len = array.length();
        List<Object> list = new ArrayList<>();
        Gson g = new Gson();
        for (int i = 0; i < len; i++) {
            list.add(g.fromJson(array.optJSONObject(i).toString(), clazz));
        }
        return list;
    }


    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) HQApplication.getApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

}