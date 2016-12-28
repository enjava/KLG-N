package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miyin.klg.R;
import com.miyin.klg.adapter.YYEAdapter;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 营业额记录
 */
public class YYEJLActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar xyejl_TitleBar;
    private RecyclerView xyejl_RecyclerView;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_xyejl;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        xyejl_TitleBar = $(R.id.xyejl_TitleBar);
        xyejl_RecyclerView = $(R.id.xyejl_RecyclerView);
    }

    @Override
    public void initDate() {
        xyejl_TitleBar.setClickCallback(this);
        xyejl_TitleBar.setTitle("营业额记录");
        xyejl_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xyejl_RecyclerView.setAdapter(new YYEAdapter(list));
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
