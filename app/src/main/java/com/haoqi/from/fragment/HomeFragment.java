package com.haoqi.from.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.astuetz.PagerSlidingTabStrip;
import com.haoqi.from.R;
import com.haoqi.from.base.BaseFragment;
import com.haoqi.from.fragment.home.ForlyPage;
import com.haoqi.from.fragment.home.FunnyPage;
import com.haoqi.from.fragment.home.GifPage;
import com.haoqi.from.fragment.home.HomeViewPagerAdapter;
import com.haoqi.from.fragment.home.HotPage;
import com.haoqi.from.fragment.home.SecretPage;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class HomeFragment extends BaseFragment {
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragmentsList;
    private int currIndex = 0;
    HomeViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {
        if (fragmentsList == null) {
            fragmentsList = new ArrayList<>();
            fragmentsList.add(new HotPage());
            fragmentsList.add(new FunnyPage());
            fragmentsList.add(new ForlyPage());
            fragmentsList.add(new GifPage());
            fragmentsList.add(new SecretPage());
            adapter = new HomeViewPagerAdapter(getChildFragmentManager(), fragmentsList);
        }
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabs.setUnderlineHeight(1);
        tabs.setIndicatorHeight(3);
        tabs.setIndicatorColor(getResources().getColor(R.color.old_red));
        tabs.setViewPager(viewPager);
    }

    @OnClick(R.id.right_menu)
    public void menuRight() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
