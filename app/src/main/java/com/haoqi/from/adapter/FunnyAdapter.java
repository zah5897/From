package com.haoqi.from.adapter;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoqi.from.R;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.model.Funny;
import com.haoqi.from.model.User;
import com.haoqi.from.util.TimeUtil;
import com.haoqi.from.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    public void clearAndAdd(List<Funny> datas) {
        funnies.clear();
        funnies.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(List<Funny> datas) {
        funnies.addAll(datas);
        notifyDataSetChanged();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_funny, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Funny funny = getItem(position);
        User user = funny.getPublisher();
        viewHolder.nickName.setText(user.getNick_name());
        viewHolder.publishTime.setText(TimeUtil.format(funny.getPublish_time()));
        Glide.with(fragment).load(Urls.URL_IMAGE_FROFIX + user.getAvatar()).placeholder(R.mipmap.headlogo).error(R.mipmap.headlogo).transform(new GlideCircleTransform(fragment.getContext())).into(viewHolder.userAvatar);
        viewHolder.content.setText(funny.getContent());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.user_avatar)
        ImageView userAvatar;
        @Bind(R.id.nick_name)
        TextView nickName;
        @Bind(R.id.publish_time)
        TextView publishTime;
        @Bind(R.id.publisher_layout)
        LinearLayout publisherLayout;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.praise)
        TextView praise;
        @Bind(R.id.store)
        TextView store;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
