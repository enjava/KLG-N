package com.miyin.klg.fragment;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.HomeActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.util.StatusBarUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 充值 fragment.
 */
public class CZFragment extends BaseFragment implements RedQRTitleBar.ClickCallback {

    private static final String ARG_PARAM1 = "param1";
    private RedQRTitleBar titleBar;

    public static CZFragment newInstance(String param1) {
        CZFragment fragment = new CZFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_cz;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        titleBar = $(R.id.cz_TitleBar);
        titleBar.setTitle("充值");
        titleBar.setClickCallback(this);
//        $(R.id.cz_layout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivity(CZActivity.class);
//            }
//        });
//        $(R.id.cz_hg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivity(HGActivity.class);
//            }
//        });
//        $(R.id.cz_zz).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivity(ZZActivity.class);
//            }
//        });
//
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
        StatusBarUtil.StatusBarLightMode(mActivity);
        ((HomeActivity) mActivity).getMainNavigateTabBar().setCurrentSelectedTab(0);
    }

    @Override
    public void onRightClick() {

    }
}
