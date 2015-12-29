package com.haoqi.from;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import com.haoqi.from.app.ConfigManager;
import com.haoqi.from.app.UserManager;

/**
 * Created by youxifuhuaqi on 2015/12/15.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish(); //测试提交    d
            }
        }, 2000);
        UserManager.getInstance();
    }
}
