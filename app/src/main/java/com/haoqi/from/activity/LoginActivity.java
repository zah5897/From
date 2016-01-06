package com.haoqi.from.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoqi.from.R;
import com.haoqi.from.app.UserManager;
import com.haoqi.from.app.http.DefaultJsonHttpResponseHandler;
import com.haoqi.from.app.http.HttpUtils;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.base.BaseActivity;
import com.haoqi.from.util.ProgressDialogUtil;
import com.haoqi.from.util.ToastUtil;
import com.haoqi.from.view.EmailAutoCompleteTextView;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by youxifuhuaqi on 2015/12/17.
 */
public class LoginActivity extends BaseActivity {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.regist)
    TextView regist;
    @Bind(R.id.et_email)
    EmailAutoCompleteTextView etEmail;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        title.setText("用户登录");
        regist.setText("注册");
        tvLogin.setText("登录");
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.regist)
    public void toRegist() {
        startActivity(new Intent(this, RegistActivity.class));
        finish();
    }

    private ProgressDialog dialog;

    @OnClick(R.id.tv_login)
    public void login() {
        String name = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.show("账号不能为空!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("密码不能为空!");
            return;
        }
        dialog = ProgressDialogUtil.show(this, "正在登录...");
        UserManager.getInstance().login(name, password, new CallBackListener() {
            @Override
            public void onSuccess(int statusCode, Object... obj) {

                finish();
                ProgressDialogUtil.dismiss(dialog);
            }

            @Override
            public void onFailure(int statusCode, String message) {
                ProgressDialogUtil.dismiss(dialog);
                if (statusCode > 0) {
                    ToastUtil.show("登录失败！" + message);
                }
            }
        });
    }

}
