package com.haoqi.from.app;

import android.text.TextUtils;

import com.haoqi.from.app.http.DefaultJsonHttpResponseHandler;
import com.haoqi.from.app.http.HttpUtils;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.model.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class UserManager {

    public static final String USER_CFG = "login_user_info";

    private static UserManager userManager;

    private User user;

    private UserManager() {
        String userStr = ConfigManager.Instance().getString(USER_CFG);
        if (!TextUtils.isEmpty(userStr)) {
            User user = User.prase(userStr);
            if (user != null) {
                this.user = user;
            }
        }
    }

    private void setUser(User user) {
        this.user = user;
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public User getUser() {
        return user;
    }

    public void regist(String name, String password, final CallBackListener listener) {

        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("password", password);
        HttpUtils.post(Urls.URL_REGIST, params, new DefaultJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONObject userObj = response.optJSONObject("user");
                if (userObj != null) {
                    String userStr = userObj.toString();
                    User user = User.prase(userStr);
                    if (user != null) {
                        setUser(user);
                        ConfigManager.Instance().put(USER_CFG, userStr);
                        listener.onSuccess(0, user);
                    }
                }
                listener.onFailure(-1, "系统错误");
            }

            @Override
            public void onFailure(int statusCode, String responseString, Throwable throwable) {
                super.onFailure(statusCode, responseString, throwable);
                listener.onFailure(statusCode, responseString);
            }
        });
    }
}
