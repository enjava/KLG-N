package com.miyin.klg.activity;

import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.MainNavigateTabBar;
import com.miyin.klg.fragment.CZFragment;
import com.miyin.klg.fragment.FJFragment;
import com.miyin.klg.fragment.FKFragment;
import com.miyin.klg.fragment.GMZDFragment;
import com.miyin.klg.fragment.HomeFragment;
import com.miyin.klg.fragment.SJGLFragment;
import com.miyin.klg.fragment.XFDJFragment;
import com.miyin.klg.util.CacheActivity;
import com.miyin.klg.util.StatusBarUtil;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 首页
 */
public class HomeActivity extends BaseActivity {
    private MainNavigateTabBar mainNavigateTabBar;
    private AutoRelativeLayout home_sysLayout;
    @Override
    public int setLayout() {
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.StatusBarLightMode(this);
        return R.layout.activity_home;

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mainNavigateTabBar = $(R.id.home_MainNavigateTabBar);
        mainNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        home_sysLayout = $(R.id.home_sysLayout);
    }

    public void imageViewOnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login:
                setting();
                break;
            case R.id.iv_message:
                openActivity(MessageActivity.class);
                break;


            default:
                break;
        }
    }

    private void setting() {
        if (mApp.getUser() == null && mApp.getStore() == null)
            openActivity(LoginActivity.class);
        else
            openActivity(SettingActivity.class);
    }

    @Override
    public void initDate() {
        mainNavigateTabBar.setDefaultLayout(R.layout.comui_tab_view);
        int type=1;
//        Intent intent = getIntent();
//        if (intent != null) {
//            if (intent.getExtras() != null)
//                type = intent.getExtras().getInt("type");
//        }
        if (mApp.getStore() != null)
            type = -1;
        mainNavigateTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.drawable.home, R.drawable.home_select, "首页"));

        mainNavigateTabBar.addTab(type == 1 ? CZFragment.class : SJGLFragment.class,
                new MainNavigateTabBar.TabParam(type == 1 ? R.drawable.cz : R.drawable.sjgl,
                        type == 1 ? R.drawable.cz_select : R.drawable.sjgl_select, type == 1 ? "充值" : "商家管理"));


        mainNavigateTabBar.addTab(null, new MainNavigateTabBar.TabParam(0, 0, "扫一扫"));

        mainNavigateTabBar.addTab(type == 1 ? FKFragment.class : XFDJFragment.class,
                new MainNavigateTabBar.TabParam(type == 1 ? R.drawable.fk : R.drawable.xfdj,
                        type == 1 ? R.drawable.fk_select : R.drawable.xfdj_select,
                        type == 1 ? "付款" : "消费登记"));

        mainNavigateTabBar.addTab(type == 1 ? FJFragment.class : GMZDFragment.class,
                new MainNavigateTabBar.TabParam(
                        type == 1 ? R.drawable.fj : R.drawable.gmzd,
                        type == 1 ? R.drawable.fj_select : R.drawable.gmzd_select,
                        type == 1 ? "附近" : "购买终端"));
        //扫一扫
        home_sysLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SYSActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CacheActivity.finishOtherActivity(this);
    }

    @Override
    public void onBackPressed() {
        exitBy2click();
    }
}
