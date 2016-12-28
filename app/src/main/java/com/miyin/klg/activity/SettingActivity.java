package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar mTitleBar;
    private AutoRelativeLayout setting_zhsz, setting_bbxx, setting_gyht, setting_yjfk, setting_xxtx;

    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.setting_titleBar);
        setting_zhsz = $(R.id.setting_zhsz);
        setting_bbxx = $(R.id.setting_bbxx);
        setting_gyht = $(R.id.setting_gyht);
        setting_yjfk = $(R.id.setting_yjfk);
        setting_xxtx = $(R.id.setting_xxtx);
    }

    @Override
    public void initDate() {
        mTitleBar.setClickCallback(this);
        mTitleBar.setTitle("设置");
        setting_zhsz.setOnClickListener(this);
        setting_bbxx.setOnClickListener(this);
        setting_gyht.setOnClickListener(this);
        setting_yjfk.setOnClickListener(this);
        setting_xxtx.setOnClickListener(this);
        $(R.id.setting_exitTv).setOnClickListener(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_zhsz:
                openActivity(AccountSecurityActivity.class);
                break;
            case R.id.setting_bbxx:
                break;
            case R.id.setting_gyht:
                break;
            case R.id.setting_yjfk:
                openActivity(FeedbackActivity.class);
                break;
            case R.id.setting_xxtx:
                break;
            case R.id.setting_exitTv:
                openActivity(LoginActivity.class);
                break;
        }
    }
}
