package com.miyin.klg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.CZActivity;
import com.miyin.klg.activity.HGActivity;
import com.miyin.klg.activity.ZZActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.view.RedTitleBar;

/**
 * 充值 fragment.
 */
public class CZFragment extends BaseFragment implements RedTitleBar.ClickCallback {

    private static final String ARG_PARAM1 = "param1";
    private RedTitleBar titleBar;

    public static CZFragment newInstance(String param1) {
        CZFragment fragment = new CZFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        Log.i("CZFragment","getContentViewLayoutID");
        return R.layout.fragment_cz;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        titleBar = $(R.id.tab_title);
        titleBar.setTitle("我的酷币");
        titleBar.setClickCallback(this);
        $(R.id.cz_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(CZActivity.class);
            }
        });
        $(R.id.cz_hg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(HGActivity.class);
            }
        });
        $(R.id.cz_zz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ZZActivity.class);
            }
        });
        Log.i("CZFragment","initViewsAndEvents");
    }

    @Override
    protected void onFirstUserVisible() {
        Log.i("CZFragment","onFirstUserVisible");
    }

    @Override
    protected void onUserVisible() {
        Log.i("CZFragment","onUserVisible");
    }

    @Override
    protected void onUserInvisible() {
        Log.i("CZFragment","onUserInvisible");
    }

    @Override
    protected void DetoryViewAndThing() {
        Log.i("CZFragment","DetoryViewAndThing");
    }

    @Override
    public void onBackClick() {

    }

    @Override
    public void onRightClick() {

    }
}
