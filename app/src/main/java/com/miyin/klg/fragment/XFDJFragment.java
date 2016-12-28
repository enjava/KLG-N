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
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.customview.BlackTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 消费登记fragment.
 */
public class XFDJFragment extends BaseFragment implements BlackTitleBar.ClickCallback {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private BlackTitleBar blackTitleBar;
    private RecyclerView fj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");


    public static XFDJFragment newInstance(String param1, String param2) {
        XFDJFragment fragment = new XFDJFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_xfdj;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        fj_RecyclerView = $(R.id.xfdj_RecyclerView);
        blackTitleBar = $(R.id.xfdj_TitleBar);
        fj_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_xfdj, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
            }
        };
        fj_RecyclerView.setAdapter(adapter);
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("消费登记");
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


    @Override
    public void onBackClick() {

    }

    @Override
    public void onRightClick() {

    }
}
