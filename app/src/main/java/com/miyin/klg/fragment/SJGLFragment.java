package com.miyin.klg.fragment;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.DDGLActivity;
import com.miyin.klg.activity.HomeActivity;
import com.miyin.klg.activity.XFDJActivity;
import com.miyin.klg.activity.YYEJLActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.util.StatusBarUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 商家管理fragment.
 */
public class SJGLFragment extends BaseFragment implements RedQRTitleBar.ClickCallback{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RedQRTitleBar titleBar;
    public static SJGLFragment newInstance(String param1, String param2) {
        SJGLFragment fragment = new SJGLFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_sjgl;
    }

    @Override
    protected void initViewsAndEvents(View view) {

        titleBar = $(R.id.tab_title);
        titleBar.setTitle("商家管理");
        titleBar.setClickCallback(this);

        $(R.id.sjgl_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(XFDJActivity.class);
            }
        });
        $(R.id.sjgl_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(DDGLActivity.class);
            }
        });
        $(R.id.sjgl_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(YYEJLActivity.class);
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
        StatusBarUtil.StatusBarLightMode(mActivity);
        ((HomeActivity)mActivity).getMainNavigateTabBar().setCurrentSelectedTab(0);
    }
   @Override
    public void onRightClick() {

    }
}
