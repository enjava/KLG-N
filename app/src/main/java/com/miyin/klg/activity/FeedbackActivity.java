package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar feedback_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        feedback_titleBar = $(R.id.feedback_titleBar);
    }

    @Override
    public void initDate() {
        feedback_titleBar.setClickCallback(this);
        feedback_titleBar.setTitle("意见反馈");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
