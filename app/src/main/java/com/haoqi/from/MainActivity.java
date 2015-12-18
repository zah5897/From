package com.haoqi.from;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haoqi.from.base.BaseFragment;
import com.haoqi.from.fragment.HomeFragment;
import com.haoqi.from.fragment.MessageFragment;
import com.haoqi.from.fragment.RequireFragment;
import com.haoqi.from.fragment.SettingFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.mHomeIcon)
    TextView mHomeIcon;
    @InjectView(R.id.mRequire)
    TextView mRequire;
    @InjectView(R.id.mMessageIcon)
    TextView mMessageIcon;
    @InjectView(R.id.mSetting)
    TextView mSetting;

    @InjectView(R.id.msgTip)
    TextView msgTip;
    private BaseFragment currentFragment;
    public static final String FRAGMENT_HOME = "home";
    public static final String FRAGMENT_REQUIRE = "require";
    public static final String FRAGMENT_MESSAGE = "message";
    public static final String FRAGMENT_SETTING = "setting";
    private int currtenTabIndex = R.id.mHomeIcon;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        getSupportActionBar().hide();
        setClick();
        setFragment(R.id.mHomeIcon);
    }


    private void setFragment(int id) {
        Fragment fragment;
        FragmentTransaction transaction;
        switch (id) {
            case R.id.mHomeIcon:
                fragment = fragmentManager.findFragmentByTag(FRAGMENT_HOME);
                HomeFragment homeFragment;
                transaction = fragmentManager.beginTransaction();
                if (fragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.replace(R.id.container, homeFragment, FRAGMENT_HOME);
                    transaction.commit();
                } else {
                    transaction.show(fragment).commit();
                }
                break;
            case R.id.mRequire:
                fragment = fragmentManager.findFragmentByTag(FRAGMENT_REQUIRE);
                RequireFragment requireFragment;
                transaction = fragmentManager.beginTransaction();
                if (fragment == null) {
                    requireFragment = new RequireFragment();
                    transaction.replace(R.id.container, requireFragment, FRAGMENT_REQUIRE);
                    transaction.commit();
                } else {
                    transaction.show(fragment).commit();
                }
                break;
            case R.id.mMessageIcon:


                fragment = fragmentManager.findFragmentByTag(FRAGMENT_MESSAGE);
                MessageFragment messageFragment;
                transaction = fragmentManager.beginTransaction();
                if (fragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.replace(R.id.container, messageFragment, FRAGMENT_MESSAGE);
                    transaction.commit();
                } else {
                    transaction.show(fragment).commit();
                }


                break;
            case R.id.mSetting:
                fragment = fragmentManager.findFragmentByTag(FRAGMENT_SETTING);
                SettingFragment settingFragment;
                transaction = fragmentManager.beginTransaction();
                if (fragment == null) {
                    settingFragment = new SettingFragment();
                    transaction.replace(R.id.container, settingFragment, FRAGMENT_SETTING);
                    transaction.commit();
                } else {
                    transaction.show(fragment).commit();
                }
                break;
        }
    }

    private void setClick() {
        mHomeIcon.setOnClickListener(this);
        mRequire.setOnClickListener(this);
        mMessageIcon.setOnClickListener(this);
        mSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (currtenTabIndex == id) {
            return;
        }
        currtenTabIndex = id;
        setDefaultDrawable();
        switch (id) {
            case R.id.mHomeIcon:
                setDrawableTop(mHomeIcon, R.mipmap.icon_home_selected);
                break;
            case R.id.mRequire:
                setDrawableTop(mRequire, R.mipmap.icon_activite_selected);
                break;
            case R.id.mMessageIcon:
                setDrawableTop(mMessageIcon, R.mipmap.icon_message_selected);
                break;
            case R.id.mSetting:
                setDrawableTop(mSetting, R.mipmap.icon_setting_selected);
                break;
        }
        setFragment(id);
    }

    private void setDefaultDrawable() {
        setDrawableTop(mHomeIcon, R.drawable.home_selector);
        setDrawableTop(mRequire, R.drawable.require_selector);
        setDrawableTop(mMessageIcon, R.drawable.message_selector);
        setDrawableTop(mSetting, R.drawable.setting_selector);
    }

    private void setDrawableTop(TextView tx, int resId) {
        Drawable drawableTop = getResources().getDrawable(resId);
        drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
        tx.setCompoundDrawables(null, drawableTop, null, null);
    }

    long lastBackTime;

    @Override
    public void onBackPressed() {
        long cuttentTime = System.currentTimeMillis();
        if (cuttentTime - lastBackTime > 2000) {
            Toast.makeText(this, getString(R.string.finish_tip) + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            lastBackTime = cuttentTime;
            return;
        }
        super.onBackPressed();
    }
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
