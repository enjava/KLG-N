package com.miyin.klg.fragment;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.CZActivity;
import com.miyin.klg.activity.HGActivity;
import com.miyin.klg.activity.ZZActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.customview.BlackTitleBar;

/**
 * 充值 fragment.
 */
public class CZFragment extends BaseFragment implements BlackTitleBar.ClickCallback {

    private static final String ARG_PARAM1 = "param1";
    //private BlackTitleBar wdkb_titleBar;

    public static CZFragment newInstance(String param1) {
        CZFragment fragment = new CZFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_cz;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        //wdkb_titleBar = $(wdkb_titleBar);
        //wdkb_titleBar.setTitle("我的酷币");
       // wdkb_titleBar.setClickCallback(this);
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
