package com.miyin.klg.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar mTitleBar;
    private TextView login_forgetPassword, login_register, login_ok;
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
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
        spinner = $(R.id.spinner);


    }

    @Override
    public void initDate() {
        mTitleBar.setClickCallback(this);
        mTitleBar.setTitle("登录");
        login_forgetPassword.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_ok.setOnClickListener(this);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("酷么会员");
        data_list.add("酷么合伙人");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

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
