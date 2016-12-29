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
 * 消费登记
 */
public class XFDJActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar blackTitleBar;
    private RecyclerView fj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_xfdj;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fj_RecyclerView = $(R.id.xfdj_RecyclerView);
        blackTitleBar = $(R.id.xfdj_TitleBar);
        fj_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<String>(this, R.layout.item_xfdj, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
            }
        };
        fj_RecyclerView.setAdapter(adapter);
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("消费登记");
    }

    @Override
    public void initDate() {

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
