package com.miyin.klg.activity;

import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 物品详情
 */
public class ShopItemDetailsActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private RedQRTitleBar shop_item_detailsTitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_shop_item_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        shop_item_detailsTitleBar = $(R.id.shop_item_detailsTitleBar);
    }

    @Override
    public void initDate() {
        shop_item_detailsTitleBar.setTitle("商品详情");
        shop_item_detailsTitleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
