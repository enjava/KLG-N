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
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 幸运树 fragment.
 */
public class XYSFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView recyclerView;
    private List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

    public static XYSFragment newInstance(String param1) {
        XYSFragment fragment = new XYSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_xy;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recyclerView = $(R.id.xys_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.xys_item, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

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
