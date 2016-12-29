package com.miyin.klg.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 个人资料
 */
public class GRZLActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar mBlackTitleBar;
    private TextView grzl_gotoSetting;
    private AutoRelativeLayout grzl_userNameLayout, grzl_tureNameLayout, grzl_addressLayout, grzl_IDcardLayout;

    @Override
    public int setLayout() {
        return R.layout.activity_grzl;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mBlackTitleBar = $(R.id.grzl_titleBar);
        grzl_gotoSetting = $(R.id.grzl_gotoSetting);
        grzl_userNameLayout = $(R.id.grzl_userNameLayout);
        grzl_tureNameLayout = $(R.id.grzl_tureNameLayout);
        grzl_addressLayout = $(R.id.grzl_addressLayout);
        grzl_IDcardLayout = $(R.id.grzl_IDcardLayout);
    }

    @Override
    public void initDate() {
        mBlackTitleBar.setTitle("个人资料");
        mBlackTitleBar.setClickCallback(this);
        grzl_gotoSetting.setOnClickListener(this);
        grzl_userNameLayout.setOnClickListener(this);
        grzl_tureNameLayout.setOnClickListener(this);
        grzl_addressLayout.setOnClickListener(this);
        grzl_IDcardLayout.setOnClickListener(this);
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
            case R.id.grzl_gotoSetting:
                openActivity(SettingActivity.class);
                break;
            case R.id.grzl_userNameLayout:
                Bundle bundle = new Bundle();
                bundle.putString("title", "用户名");
                openActivity(UpdateInfoActivity.class, bundle);
                break;
            case R.id.grzl_tureNameLayout:
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", "真实姓名");
                openActivity(UpdateInfoActivity.class, bundle1);
                break;
            case R.id.grzl_addressLayout:
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "所在地");
                openActivity(UpdateInfoActivity.class, bundle2);
                break;
            case R.id.grzl_IDcardLayout:
                Bundle bundle3 = new Bundle();
                bundle3.putString("title", "身份证号");
                openActivity(UpdateInfoActivity.class, bundle3);
                break;
        }
    }
}
