package com.haoqi.from.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoqi.from.R;
import com.haoqi.from.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class EditUserPage extends BaseActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.regist)
    TextView regist;
    @Bind(R.id.user_img)
    ImageView userImg;
    @Bind(R.id.user_img_layout)
    RelativeLayout userImgLayout;
    @Bind(R.id.name_txt)
    TextView nameTxt;
    @Bind(R.id.nick_name)
    EditText nickName;
    @Bind(R.id.man_box)
    CheckBox manBox;
    @Bind(R.id.woman_box)
    CheckBox womanBox;
    @Bind(R.id.most_like_text)
    EditText mostLikeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        ButterKnife.bind(this);
    }

    private ProgressDialog progressDialog;

    @OnClick({R.id.btn_commit, R.id.back, R.id.user_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.user_img:
                int selectedMode = MODE_SINGLE;
                boolean showCamera = true;
                int maxNum = 1;
                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
                // 是否显示拍摄图片
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);
                // 最大可选择图片数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
                // 选择模式
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
                // 默认选择
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
            case R.id.btn_commit:

                break;
        }
    }
}
