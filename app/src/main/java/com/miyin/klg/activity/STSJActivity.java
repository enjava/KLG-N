package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miyin.klg.R;
import com.miyin.klg.adapter.STSJAdapter;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 实体商家
 */
public class STSJActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar stsj_TitleBar;
    private RecyclerView stsj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_stsj;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        stsj_TitleBar = $(R.id.stsj_TitleBar);
        stsj_RecyclerView = $(R.id.stsj_RecyclerView);
    }

    @Override
    public void initDate() {
        stsj_TitleBar.setClickCallback(this);
        stsj_TitleBar.setTitle("实体商户");
        stsj_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stsj_RecyclerView.setAdapter(new STSJAdapter(list));

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
