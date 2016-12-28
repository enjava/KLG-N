package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar mTitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.register_titleBar);
    }

    @Override
    public void initDate() {
        mTitleBar.setClickCallback(this);
        mTitleBar.setTitle("注册");
        mTitleBar.getRightView().setText("登录");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {
        openActivity(LoginActivity.class);
    }
}
