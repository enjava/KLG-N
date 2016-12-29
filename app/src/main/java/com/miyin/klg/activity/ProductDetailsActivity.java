package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 商品详情页
 */
public class ProductDetailsActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar titleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.product_details_TitleBar);
    }

    @Override
    public void initDate() {
        titleBar.setTitle("商品详情");
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
