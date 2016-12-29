package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 我的酷币
 */
public class WDKBActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar wdkb_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_wdkb;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        wdkb_titleBar = $(R.id.wdkb_titleBar);
    }

    @Override
    public void initDate() {
        wdkb_titleBar.setTitle("我的酷币");
        wdkb_titleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
