package com.haoqi.from.fragment.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.haoqi.from.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragmentsList;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsList.get(position).getPageTitle();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
