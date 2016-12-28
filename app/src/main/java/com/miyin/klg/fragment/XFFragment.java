package com.miyin.klg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miyin.klg.R;
import com.miyin.klg.activity.ProductDetailsActivity;
import com.miyin.klg.base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 消费fragment.
 */
public class XFFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView xf_RecyclerView;
    List<String> list = Arrays.asList("", "", "", "", "");
    private CommonAdapter adapter;

    public static XFFragment newInstance(String param1) {
        XFFragment fragment = new XFFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_xf;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        xf_RecyclerView = $(R.id.xf_RecyclerView);
        xf_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(), getArguments().getString(ARG_PARAM1).equals("实体商家消费记录") ? R.layout.item_xfjlstsj : R.layout.item_xfjlxfzd, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        xf_RecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                openActivity(ProductDetailsActivity.class);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void DetoryViewAndThing() {

    }

}
