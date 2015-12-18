package com.haoqi.from.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoqi.from.R;
import com.haoqi.from.base.BaseFragment;
import com.haoqi.from.base.BaseRefreshFragment;

/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class RequireFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
