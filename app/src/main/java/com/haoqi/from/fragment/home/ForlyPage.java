package com.haoqi.from.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoqi.from.R;
import com.haoqi.from.base.BaseRefreshFragment;

import butterknife.ButterKnife;

/**
 * Created by youxifuhuaqi on 2015/12/16.
 */
public class ForlyPage extends BaseRefreshFragment {
    public static final String TAG = "福利";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pullrefresh_listview_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefreshListView();
        mList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, new String[]{"1", "2"}));
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
