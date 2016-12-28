package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 修改资料
 */
public class UpdateInfoActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar update_info_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_update_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        update_info_titleBar = $(R.id.update_info_titleBar);
    }

    @Override
    public void initDate() {
        update_info_titleBar.setTitle(getIntent().getExtras().getString("title"));
        update_info_titleBar.setClickCallback(this);
        update_info_titleBar.getRightView().setText("修改");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {
        finish();
    }
}
