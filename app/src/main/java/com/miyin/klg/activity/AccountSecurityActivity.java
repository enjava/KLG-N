package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 账户安全
 */
public class AccountSecurityActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar account_security_titleBar;
    private AutoRelativeLayout account_security_loginPassword,
            account_security_twoPassword,
            account_security_forgetTwoPassword, account_security_bindingPassword;

    @Override
    public int setLayout() {
        return R.layout.activity_account_security;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        account_security_titleBar = $(R.id.account_security_titleBar);
        account_security_loginPassword = $(R.id.account_security_loginPassword);
        account_security_twoPassword = $(R.id.account_security_twoPassword);
        account_security_forgetTwoPassword = $(R.id.account_security_forgetTwoPassword);
        account_security_bindingPassword = $(R.id.account_security_bindingPassword);
    }

    @Override
    public void initDate() {
        account_security_titleBar.setClickCallback(this);
        account_security_titleBar.setTitle("账号安全");
        account_security_loginPassword.setOnClickListener(this);
        account_security_twoPassword.setOnClickListener(this);
        account_security_forgetTwoPassword.setOnClickListener(this);
        account_security_bindingPassword.setOnClickListener(this);
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
            case R.id.account_security_loginPassword:
                openActivity(UpdatePasswordActivity.class);
                break;
            case R.id.account_security_twoPassword:
                openActivity(TwoPasswordActivity.class);
                break;
            case R.id.account_security_forgetTwoPassword:
                openActivity(ForgetTwoPasswordActivity.class);
                break;
            case R.id.account_security_bindingPassword:
                openActivity(BindingMobileActivity.class);
                break;
        }
    }
}
