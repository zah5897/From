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
public class HotPage extends BaseRefreshFragment {
    public static final String TAG = "热门";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pullrefresh_listview_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public String getPageTitle() {
        return TAG;
    }
}
