package com.haoqi.from.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoqi.from.R;
import com.haoqi.from.base.BaseActivity;
import com.haoqi.from.view.EmailAutoCompleteTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by youxifuhuaqi on 2015/12/17.
 */
public class RegistActivity extends BaseActivity {


    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.regist)
    TextView regist;
    @InjectView(R.id.et_email)
    EmailAutoCompleteTextView etEmail;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);
        title.setText("用户注册");
        regist.setText("登录");
        tvLogin.setText("注册");
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.regist)
    public void toLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.tv_login)
    public void regist() {

    }

}
