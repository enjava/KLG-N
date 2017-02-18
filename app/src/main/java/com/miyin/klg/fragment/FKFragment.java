package com.miyin.klg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.HomeActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.util.StatusBarUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 付款 fragment.
 */
public class FKFragment extends BaseFragment implements RedQRTitleBar.ClickCallback{
    private static final String ARG_PARAM1 = "param1";
    private RedQRTitleBar titleBar;
    public static FKFragment newInstance(String param1, String param2) {
        FKFragment fragment = new FKFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_fk;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        titleBar=$(R.id.tab_title);
        titleBar.setTitle("付款");
        titleBar.setClickCallback(this);
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
        ((HomeActivity)mActivity).getMainNavigateTabBar().setCurrentSelectedTab(0);
    }

    @Override
    public void onRightClick() {

    }
}
