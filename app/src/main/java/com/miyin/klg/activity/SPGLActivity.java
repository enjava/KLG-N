package com.miyin.klg.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 商品管理
 */
public class SPGLActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar spgl_TitleBar;
    private RecyclerView spgl_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_spgl;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        spgl_TitleBar = $(R.id.spgl_TitleBar);
        spgl_RecyclerView = $(R.id.spgl_RecyclerView);
    }

    @Override
    public void initDate() {
        spgl_TitleBar.setTitle("商品管理");
        spgl_TitleBar.setClickCallback(this);
        spgl_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<String>(this, R.layout.item_spgl, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
            }
        };
        spgl_RecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
