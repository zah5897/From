package com.haoqi.from.app.http;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haoqi.from.app.HQApplication;
import com.haoqi.from.app.UserManager;
import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.util.ToastUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HttpUtils {


    public static final int PAGE_SIZE = 20;
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

    public static void post(String url, RequestParams params, final DefaultJsonHttpResponseHandler callBackListener) {
        if (isNetworkConnected()) {

            if (params != null && UserManager.getInstance().getUser() != null) {
                params.put("userId", UserManager.getInstance().getUser().getId());
            }
            getAsyncHttpClient().post(url, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    int code = response.optInt("code");
                    if (code != 0) {
                        String msg = response.optString("msg");
                        callBackListener.onFailure(code, msg, new RuntimeException(msg));
                    } else {
                        callBackListener.onSuccess(statusCode, headers, response);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (throwable instanceof SocketTimeoutException) {
                        String message = "网络请求超时";
                        ToastUtil.show(message);
                        callBackListener.onFailure(Error.TIME_OUT, message, new NetworkErrorException());
                    } else if (throwable instanceof NetworkErrorException) {
                        String message = "网络异常";
                        ToastUtil.show(message);
                        callBackListener.onFailure(Error.NETWORK_ERROR, message, new NetworkErrorException());
                    } else {
                        ToastUtil.show("访问服务器失败");
                        callBackListener.onFailure(Error.SERVER_ERROR, throwable.getMessage(), throwable);
                    }
                }

            });
        } else {
            String message = "网络不可用";
            ToastUtil.show(message);
            callBackListener.onFailure(Error.NETWORK_NOT_AVAILABLE, message, new NetworkErrorException());
        }
    }


    public static List<?> arrayToModel(JSONArray array, Class<?> clazz) {
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