package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 转赠
 */
public class ZZActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar zz_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_zz;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        zz_titleBar = $(R.id.zz_titleBar);
    }

    @Override
    public void initDate() {
        zz_titleBar.setTitle("转赠");
        zz_titleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
