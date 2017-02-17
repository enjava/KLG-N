package com.miyin.klg.activity;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 我的酷币
 */
public class WDKBActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private RedQRTitleBar wdkb_titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_wdkb;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        wdkb_titleBar = $(R.id.wdkb_titleBar);
    }

    @Override
    public void initDate() {
        wdkb_titleBar.setTitle("我的酷币");
        wdkb_titleBar.setClickCallback(this);
        $(R.id.cz_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(CZActivity.class);
            }
        });
        $(R.id.cz_hg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(HGActivity.class);
            }
        });
        $(R.id.cz_zz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ZZActivity.class);
            }
        });
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
