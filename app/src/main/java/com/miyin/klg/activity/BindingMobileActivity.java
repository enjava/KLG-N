package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 更改绑定手机
 */
public class BindingMobileActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar binding_mobile_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_binding_mobile;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        binding_mobile_titleBar = $(R.id.binding_mobile_titleBar);
    }

    @Override
    public void initDate() {
        binding_mobile_titleBar.setClickCallback(this);
        binding_mobile_titleBar.setTitle("更换手机");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
