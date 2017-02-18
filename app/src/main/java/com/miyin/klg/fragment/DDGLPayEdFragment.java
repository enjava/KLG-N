package com.miyin.klg.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.DDGLDetailsActivity;
import com.miyin.klg.base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 订单管理已付款 fragment.
 */
public class DDGLPayEdFragment extends BaseFragment {
    private final String TAG=getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView fj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("a", "b", "c", "d", "f", "g", "h", "i", "j", "k", "l", "m", "n");

    public static DDGLPayEdFragment newInstance(String param1) {
        DDGLPayEdFragment fragment = new DDGLPayEdFragment();
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
            protected void convert(ViewHolder holder, final String s, final int position) {
                //Log.e("CommonAdapter", s + "position" + position);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(DDGLDetailsActivity.class);
                    }
                });

            }
        };
        fj_RecyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                openActivity(DDGLDetailsActivity.class);
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
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
