package com.haoqi.from.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoqi.from.FunnyAdapter;
import com.haoqi.from.R;
import com.haoqi.from.app.http.DefaultJsonHttpResponseHandler;
import com.haoqi.from.app.http.HttpUtils;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.base.BaseRefreshFragment;
import com.haoqi.from.util.ToastUtil;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

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


    private void loadData(final boolean refresh) {

        if (refresh) {
            cursor = 0;
        } else {
            cursor = adapter.getLastId();
        }
        RequestParams params = new RequestParams();
        params.put("lastID", cursor);
        params.put("pageSize", HttpUtils.PAGE_SIZE);

        HttpUtils.post(Urls.URL_FUNNY, params, new DefaultJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                int code = response.optInt("errorCode");
                if (code == 0) {

                } else {
                    String errorMsg = response.optString("errorMsg");
                    ToastUtil.show(errorMsg);
                }
                onRefreshComplete();
            }

            @Override
            public void onFailure(int statusCode, String responseString, Throwable throwable) {
                super.onFailure(statusCode, responseString, throwable);
                if (statusCode > 0) {
                    ToastUtil.show(responseString);
                }
                onRefreshComplete();
            }
        });

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
        loadData(true);
    }

    @Override
    public void onNextPage() {
        loadData(false);
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
