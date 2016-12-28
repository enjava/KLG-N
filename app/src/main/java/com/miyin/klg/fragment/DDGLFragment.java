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
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.activity.DDGLDetailsActivity;
import com.miyin.klg.base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 订单管理 fragment.
 */
public class DDGLFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView fj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    public static DDGLFragment newInstance(String param1) {
        DDGLFragment fragment = new DDGLFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ddgl;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        fj_RecyclerView = $(R.id.fj_RecyclerView);
        fj_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_ddgl, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
            }
        };
        fj_RecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                openActivity(DDGLDetailsActivity.class);
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
