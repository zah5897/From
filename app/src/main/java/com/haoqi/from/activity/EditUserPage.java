package com.haoqi.from.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoqi.from.R;
import com.haoqi.from.app.UserManager;
import com.haoqi.from.app.http.Urls;
import com.haoqi.from.app.listener.CallBackListener;
import com.haoqi.from.base.BaseActivity;
import com.haoqi.from.model.User;
import com.haoqi.from.util.DisplayUtil;
import com.haoqi.from.util.FileUtil;
import com.haoqi.from.util.ProgressDialogUtil;
import com.haoqi.from.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by youxifuhuaqi on 2015/12/29.
 */
public class EditUserPage extends BaseActivity {


    public static final int REQUEST_IMAGE = 2;
    public static final int ZOOM_IMAGE = 3;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
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


    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        ButterKnife.bind(this);
        title.setText("用户编辑");

        User user = UserManager.getInstance().getUser();
        if (user != null) {
            String nickNameS = user.getNick_name();
            if (nickNameS != null) {
                nickName.setText(nickNameS);
            }

            String info = user.getInfo();

            if (info != null) {
                mostLikeText.setText(info);
            }
            String imageUrl = user.getAvatar();
            if (imageUrl != null) {
                int width = DisplayUtil.dip2px(this, 60);
                Glide.with(this).load(Urls.URL_IMAGE_FROFIX + imageUrl).placeholder(R.mipmap.headlogo).error(R.mipmap.headlogo).override(width, width).into(userImg);
            }

            if (user.getSex() == 0) {
                manBox.setChecked(true);
            } else {
                womanBox.setChecked(true);
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_IMAGE) {
            ArrayList<String> tempSelectPath = data
                    .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (tempSelectPath != null && tempSelectPath.size() > 0) {
                selectedImagePath = tempSelectPath.get(0);
                chooseSmalPic();
            }
        } else if (requestCode == ZOOM_IMAGE) {
            Bitmap bitmap = data.getParcelableExtra("data");
            this.userImg.setImageBitmap(bitmap);
            try {
                selectedImagePath = FileUtil.saveBitmap(bitmap).getAbsolutePath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ToastUtil.show("图片保存失败");
                selectedImagePath = null;
                this.userImg.setImageResource(R.mipmap.headlogo);
            }
        }
    }

    private void chooseSmalPic() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(selectedImagePath)), "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, ZOOM_IMAGE);
    }

    private ProgressDialog progressDialog;

    @OnClick({R.id.btn_commit, R.id.back, R.id.user_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.user_img:
                Intent intent = new Intent(this, MultiImageSelectorActivity.class);
                // 是否显示拍摄图片
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                // 最大可选择图片数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                // 选择模式
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                // 默认选择
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
            case R.id.btn_commit:

                String nickNameStr = nickName.getText().toString();
                String info = mostLikeText.getText().toString();

                int sex = manBox.isChecked() ? 0 : 1;

                if (TextUtils.isEmpty(nickNameStr)) {
                    ToastUtil.show("昵称不能为空！");
                    return;
                }
                progressDialog = ProgressDialogUtil.show(this, "正在修改信息...");
                UserManager.getInstance().modifyUser(nickNameStr, info, sex, selectedImagePath, new CallBackListener() {
                    @Override
                    public void onSuccess(int statusCode, Object... obj) {
                        ProgressDialogUtil.dismiss(progressDialog);
                        ToastUtil.show("修改成功!");
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, String message) {
                        if (statusCode > 0) {
                            ToastUtil.show("修改失败，" + message);
                        }
                        ProgressDialogUtil.dismiss(progressDialog);
                    }
                });
                break;
        }
    }
}
