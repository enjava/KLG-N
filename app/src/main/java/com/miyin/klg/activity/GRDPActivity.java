package com.miyin.klg.activity;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.view.RedTitleBar;

/**
 * Created by en on 2017/2/11.
 * 个人店铺信息
 */

public class GRDPActivity extends BaseActivity implements RedTitleBar.ClickCallback {
    private RedTitleBar titleBar;
    @Override
    public int setLayout() {
        return R.layout.activity_grdp;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar=  $(R.id.grdp_TitleBar);
    }

    @Override
    public void initDate() {
        titleBar.setClickCallback(this);
        titleBar.setTitle("个人店铺信息");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void nextBtn(View view){
        openActivity(GRZZActivity.class);
    }
}
