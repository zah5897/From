package com.haoqi.from.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoqi.from.FunnyAdapter;
import com.haoqi.from.R;
import com.haoqi.from.base.BaseRefreshFragment;

import butterknife.ButterKnife;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class FunnyPage extends BaseRefreshFragment {
    public static final String TAG = "段子笑话";
    private FunnyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pullrefresh_listview_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!hasInit) {
            initRefreshListView();
            adapter = new FunnyAdapter(FunnyPage.this);
            mList.setAdapter(adapter);
        }
    }

    @Override
    public void onSelected() {
        if (!hasLoad) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setRefreshing();
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    @Override
    public void onNextPage() {
        super.onNextPage();
    }

    @Override
    public String getPageTitle() {
        return TAG;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
