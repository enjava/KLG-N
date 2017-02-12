package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.view.RedTitleBar;

/**
 * Created by en on 2017/2/12.
 */

public class GRZZActivity extends BaseActivity  implements RedTitleBar.ClickCallback{
    private RedTitleBar titleBar;
    @Override
    public int setLayout() {
        return R.layout.activity_grzz;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar= $(R.id.grzz_TitleBar);

    }

    @Override
    public void initDate() {
        titleBar.setTitle("资质信息");
        titleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
