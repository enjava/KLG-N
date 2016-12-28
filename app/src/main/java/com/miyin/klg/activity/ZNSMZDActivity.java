package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
 * 智能售卖终端
 */
public class ZNSMZDActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private RecyclerView znsmzd_RecyclerView;
    private BlackTitleBar titleBar;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_znsmzd;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.znsmzd_TitleBar);
        znsmzd_RecyclerView = $(R.id.znsmzd_RecyclerView);
        znsmzd_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =
                new CommonAdapter<String>(ZNSMZDActivity.this, R.layout.item_znsmzd, list) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {
                        holder.setVisible(R.id.item_znsmzdHintLayout, position == 0 ? true : position == 4 ? true : false);
                        holder.setVisible(R.id.item_znsmzdAddressFJIV, position > 3 ? true : false);
                        holder.setText(R.id.item_znsmzdHint, position == 0 ? "最近使用" : position == 4 ? "附近" : "");
                    }
                };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                openActivity(ShopDetailsActivity.class);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        znsmzd_RecyclerView.setAdapter(adapter);
    }

    @Override
    public void initDate() {
        titleBar.setTitle("智能售卖终端");
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
