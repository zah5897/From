package com.haoqi.from.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoqi.from.R;
import com.haoqi.from.app.UserManager;
import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.base.BaseActivity;
import com.haoqi.from.util.ProgressDialogUtil;
import com.haoqi.from.util.ToastUtil;
import com.haoqi.from.view.EmailAutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by youxifuhuaqi on 2015/12/17.
 */
public class RegistActivity extends BaseActivity {


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

    private ProgressDialog dialog;

    @OnClick(R.id.tv_login)
    public void regist() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            ToastUtil.show("邮箱不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show("密码不能为空");
            return;
        }

        dialog = ProgressDialogUtil.show(this, "正在注册...");
        UserManager.getInstance().regist(email, password, new CallBackListener() {
            @Override
            public void onSuccess(int statusCode, Object... obj) {
                ToastUtil.show("注册成功!");
                finish();
                ProgressDialogUtil.dismiss(dialog);
            }

            @Override
            public void onFailure(int statusCode, String message) {
                if (statusCode > 0) {
                    ToastUtil.show("注册失败," + message);
                }
                ProgressDialogUtil.dismiss(dialog);

            }
        });
    }

}
