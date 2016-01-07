package com.haoqi.from;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.haoqi.from.model.Funny;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxifuhuaqi on 2016/1/6.
 */
public class FunnyAdapter extends BaseAdapter {
    private List<Funny> funnies;
    private Fragment fragment;

    public FunnyAdapter(Fragment fragment) {
        funnies = new ArrayList<>();
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return funnies.size();
    }

    public long getLastId() {
        if (funnies.size() > 0) {
            return getItemId(getCount() - 1);
        } else {
            return 0;
        }

    }

    @Override
    public Funny getItem(int position) {
        return funnies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
