package com.miyin.klg.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

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
    private TextView asa_tv_userid,
            asa_tv_username;
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
        asa_tv_userid = $(R.id.asa_tv_userid);
        asa_tv_username = $(R.id.asa_tv_username);
    }

    @Override
    public void initDate() {
        account_security_titleBar.setClickCallback(this);
        account_security_titleBar.setTitle("账号安全");
        account_security_loginPassword.setOnClickListener(this);
        account_security_twoPassword.setOnClickListener(this);
        account_security_forgetTwoPassword.setOnClickListener(this);
        account_security_bindingPassword.setOnClickListener(this);
        String userName;
        String userID;
        if (mApp.getStore()!=null){
            userID=mApp.getStore().data.userId+"";
            userName=mApp.getStore().data.realName;
        } else {
            userID=mApp.getUser().data.userId+"";
            userName=mApp.getUser().data.username;
        }

        if (!TextUtils.isEmpty(userID))
            asa_tv_userid.setText(userID);
        if (!TextUtils.isEmpty(userName))
            asa_tv_username.setText(userName);
        else
            asa_tv_username.setText("未设置");
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
                openActivity(UpdatePassActivity.class);
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
