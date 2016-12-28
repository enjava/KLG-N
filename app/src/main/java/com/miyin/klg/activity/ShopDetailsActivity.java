package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 店铺详情
 */
public class ShopDetailsActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar shop_details_TitleBar;
    private RecyclerView shop_detailsRecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_shop_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        shop_details_TitleBar = $(R.id.shop_details_TitleBar);
        shop_detailsRecyclerView = $(R.id.shop_datailsRecyclerView);
    }

    @Override
    public void initDate() {
        shop_details_TitleBar.setClickCallback(this);
        shop_details_TitleBar.setTitle("店铺详情");
        shop_detailsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new CommonAdapter<String>(ShopDetailsActivity.this, R.layout.item_shopdetails, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
            }
        };
        shop_detailsRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                openActivity(ShopItemDetailsActivity.class);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
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
