package com.haoqi.from.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.haoqi.from.R;
import com.haoqi.from.activity.EditUserPage;
import com.haoqi.from.activity.LoginActivity;
import com.haoqi.from.activity.RegistActivity;
import com.haoqi.from.app.ConfigManager;
import com.haoqi.from.app.UserManager;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.base.BaseFragment;
import com.haoqi.from.model.User;
import com.haoqi.from.util.DisplayUtil;
import com.haoqi.from.view.GlideCircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.right_menu)
    ImageView rightMenu;
    @Bind(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;
    @Bind(R.id.title)
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!this.hasInit) {
            loadViewForCode();
            this.hasInit = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setValue();
    }

    private void setValue() {
        User loginUser = UserManager.getInstance().getUser();

        TextView signature = ((TextView) scrollView.getHeaderView().findViewById(R.id.tv_user_signature));
        TextView user_name = ((TextView) scrollView.getHeaderView().findViewById(R.id.tv_user_name));
        LinearLayout extra_layout = ((LinearLayout) scrollView.getHeaderView().findViewById(R.id.extra_layout));
        ImageView iv_user_head = (ImageView) scrollView.getHeaderView().findViewById(R.id.iv_user_head);
        if (loginUser != null) {
            scrollView.getHeaderView().findViewById(R.id.ll_action_button).setVisibility(View.GONE);


            if (loginUser.getAvatar() != null) {
                int width = DisplayUtil.dip2px(getContext(), 60);
                Glide.with(this).load(Urls.URL_IMAGE_FROFIX + loginUser.getAvatar()).placeholder(R.mipmap.headlogo).error(R.mipmap.headlogo).transform(new GlideCircleTransform(getContext())).override(width, width).into(iv_user_head);
            }

            if (!TextUtils.isEmpty(loginUser.getNick_name())) {
                user_name.setText(loginUser.getNick_name());
            } else {
                user_name.setText("请编辑您的昵称");
            }
            if (!TextUtils.isEmpty(loginUser.getInfo())) {
                signature.setText(loginUser.getInfo());
            } else {
                signature.setText("快去编辑您的签名");
            }
            extra_layout.setVisibility(View.VISIBLE);
            signature.setVisibility(View.VISIBLE);
        } else {
            scrollView.getHeaderView().findViewById(R.id.ll_action_button).setVisibility(View.VISIBLE);
            extra_layout.setVisibility(View.GONE);
            signature.setVisibility(View.GONE);
            user_name.setText("您还没登录");
            iv_user_head.setImageResource(R.mipmap.headlogo);
        }
    }

    private void loadViewForCode() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);


        rightMenu.setOnClickListener(this);
        scrollView.getPullRootView().findViewById(R.id.publish).setOnClickListener(this);
        scrollView.getPullRootView().findViewById(R.id.store).setOnClickListener(this);
        scrollView.getPullRootView().findViewById(R.id.comment).setOnClickListener(this);
        scrollView.getPullRootView().findViewById(R.id.about).setOnClickListener(this);
        scrollView.getPullRootView().findViewById(R.id.exit_login).setOnClickListener(this);


        scrollView.getHeaderView().findViewById(R.id.tv_register).setOnClickListener(this);
        scrollView.getHeaderView().findViewById(R.id.tv_login).setOnClickListener(this);

        scrollView.getHeaderView().findViewById(R.id.iv_user_head).setOnClickListener(this);
        scrollView.getHeaderView().findViewById(R.id.tv_user_name).setOnClickListener(this);
        scrollView.getHeaderView().findViewById(R.id.tv_user_signature).setOnClickListener(this);


        int mScreenHeight = ConfigManager.Instance().screenHeight;
        int mScreenWidth = ConfigManager.Instance().screenWidth;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(getActivity(), RegistActivity.class));
                break;
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.iv_user_head:
            case R.id.tv_user_name:
            case R.id.tv_user_signature:
                if (UserManager.getInstance().getUser() != null) {
                    startActivity(new Intent(getActivity(), EditUserPage.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.right_menu:
                //setting
                break;
            case R.id.publish:
                //setting
                break;
            case R.id.store:
                //setting
                break;
            case R.id.comment:
                //setting
                break;
            case R.id.about:
                //setting
                break;
            case R.id.exit_login:
                //setting
                UserManager.getInstance().logout();
                setValue();
                break;
        }
    }
}
