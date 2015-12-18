package com.haoqi.from.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.haoqi.from.R;
import com.haoqi.from.activity.LoginActivity;
import com.haoqi.from.activity.RegistActivity;
import com.haoqi.from.app.ConfigManager;
import com.haoqi.from.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.right_menu)
    ImageView rightMenu;
    @InjectView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;
    @InjectView(R.id.title)
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadViewForCode();
        this.hasInit = true;
    }

    private void loadViewForCode() {
        if (hasInit) {
            return;
        }
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


        int mScreenHeight = ConfigManager.Instance().screenHeight;
        int mScreenWidth = ConfigManager.Instance().screenWidth;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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
                break;
        }
    }
}
