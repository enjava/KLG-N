package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * Created by en on 2017/2/9.
 */

public class MessageActivity extends BaseActivity implements RedQRTitleBar.ClickCallback{
    private RedQRTitleBar mBlackTitleBar;
    @Override
    public int setLayout() {
        return R.layout.activity_message;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mBlackTitleBar = $(R.id.message_titleBar);
        mBlackTitleBar.setClickCallback(this);

    }

    @Override
    public void initDate() {
        mBlackTitleBar.setTitle("我的消息");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

}
