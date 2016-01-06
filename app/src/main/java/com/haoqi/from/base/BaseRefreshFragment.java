package com.haoqi.from.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoqi.from.R;

import butterknife.Bind;

/**
 * Created by youxifuhuaqi on 2015/12/14.
 */
public class BaseRefreshFragment extends BaseFragment {
    @Bind(R.id.mPullToRefresh)
    PullToRefreshListView mPullToRefresh;
    public ListView mList;

    private long cursor = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void setRefreshing() {
        mPullToRefresh.setRefreshing();
    }

    public void setRefreshing(boolean refreshing) {
        mPullToRefresh.setRefreshing(refreshing);
    }


    protected void initRefreshListView() {
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mList = mPullToRefresh.getRefreshableView();
        mList.setDividerHeight(1);
        if (Build.VERSION.SDK_INT < 19) {
            showListSideDivider(mList, false);
        }
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                OnItemClickListener(parent, view, position, id);
            }
        });
        mPullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                onRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                onNextPage();
            }
        });
        this.hasInit = true;
    }


    public void onRefresh() {

    }

    public void onNextPage() {
    }

    public static void showListSideDivider(ListView v, boolean top) {
        // A hackway to show divider at the bottom
//        View emptyView = new View(v.getContext());
//        if (top) {
//            v.addHeaderView(emptyView);
//        } else {
//            v.addFooterView(emptyView);
//        }
    }

    public void OnItemClickListener(AdapterView<?> parent, View view,
                                    int position, long id) {

    }
}
