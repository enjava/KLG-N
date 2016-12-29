package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 平台数据
 */
public class PTSJActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar ptsj_TitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_ptsj;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ptsj_TitleBar = $(R.id.ptsj_TitleBar);
    }

    @Override
    public void initDate() {
        ptsj_TitleBar.setClickCallback(this);
        ptsj_TitleBar.setTitle("平台数据");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
