package com.haoqi.from.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.haoqi.from.R;
import com.haoqi.from.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class MessageFragment extends BaseFragment {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_menu)
    ImageView rightMenu;
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.inject(this, view);
        rightMenu.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
