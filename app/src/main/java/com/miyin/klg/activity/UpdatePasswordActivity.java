package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar mTitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.up_titleBar);
    }

    @Override
    public void initDate() {
        mTitleBar.setTitle("修改密码");
        mTitleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
