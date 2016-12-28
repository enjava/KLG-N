package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 回购界面
 */
public class HGActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar hg_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_hg;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        hg_titleBar = $(R.id.hg_titleBar);
    }

    @Override
    public void initDate() {
        hg_titleBar.setClickCallback(this);
        hg_titleBar.setTitle("回购");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
