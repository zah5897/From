package com.haoqi.from.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoqi.from.R;
import com.haoqi.from.base.BaseRefreshFragment;

import butterknife.ButterKnife;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class GifPage extends BaseRefreshFragment {
    public static final String TAG = "求出处";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pullrefresh_listview_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public String getPageTitle() {
        return TAG;
    }
}
