package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 订单管理
 */
public class DDGLDetailsActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar ddgldetails_TitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_ddgldetails;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ddgldetails_TitleBar = $(R.id.ddgldetails_TitleBar);
    }

    @Override
    public void initDate() {
        ddgldetails_TitleBar.setClickCallback(this);
        ddgldetails_TitleBar.setTitle("订单详情");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
