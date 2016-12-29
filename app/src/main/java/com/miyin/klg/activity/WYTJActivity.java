package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 我要推荐
 */
public class WYTJActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar wytj_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_wy;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        wytj_titleBar = $(R.id.wytj_titleBar);
    }

    @Override
    public void initDate() {
        wytj_titleBar.setClickCallback(this);
        wytj_titleBar.setTitle("我要推荐");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
