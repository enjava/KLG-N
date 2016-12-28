package com.miyin.klg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar mTitleBar;
    private TextView login_forgetPassword, login_register, login_ok;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.login_titleBar);
        login_forgetPassword = $(R.id.login_forgetPassword);
        login_register = $(R.id.login_register);
        login_ok = $(R.id.login_ok);
    }

    @Override
    public void initDate() {
        mTitleBar.setClickCallback(this);
        mTitleBar.setTitle("登录");
        login_forgetPassword.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_ok.setOnClickListener(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_forgetPassword:
                openActivity(UpdatePasswordActivity.class);
                break;
            case R.id.login_register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.login_ok:
                openActivity(SettingActivity.class);
                break;
        }
    }
}
