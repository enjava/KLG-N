package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 账户充值
 */
public class CZActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar cz_TitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_cz;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        cz_TitleBar = $(R.id.cz_TitleBar);
    }

    @Override
    public void initDate() {
        cz_TitleBar.setClickCallback(this);
        cz_TitleBar.setTitle("账户充值");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
