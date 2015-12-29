package com.haoqi.from.app;

import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.model.User;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class UserManager {

    private static UserManager userManager;

    private User user;
    private UserManager() {
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }


    public void regist(String name, String password, CallBackListener listener) {

    }
}
