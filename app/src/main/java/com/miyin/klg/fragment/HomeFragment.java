package com.miyin.klg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.GRZLActivity;
import com.miyin.klg.activity.HGActivity;
import com.miyin.klg.activity.PTSJActivity;
import com.miyin.klg.activity.STSJActivity;
import com.miyin.klg.activity.WDKBActivity;
import com.miyin.klg.activity.WYTJActivity;
import com.miyin.klg.activity.XFJLActivity;
import com.miyin.klg.activity.XYSActivity;
import com.miyin.klg.activity.ZNSMZDActivity;
import com.miyin.klg.activity.ZZActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.customview.CircleMenu;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 主页 fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, CircleMenu.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private CircleMenu circleMenu;
    private AutoRelativeLayout userInfoLayout;
    private String[] mItemTexts = new String[]{"我的幸运树 ", "我的酷币", "回购", "转赠 ", "我要推荐", "消费记录", "平台数据", "智能售卖终端", "实体商家",
    };
    private int[] mItemImgs = new int[]{R.drawable.xys, R.drawable.wokb, R.drawable.gm, R.drawable.zz,
            R.drawable.wydz, R.drawable.xf, R.drawable.ptsj, R.drawable.hg, R.drawable.stsj
    };

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    private static final String TAG = "HomeFragment_log";

    @Override
    protected void initViewsAndEvents(View view) {
        circleMenu = $(R.id.circle_menu_items);
        circleMenu.setAngle(180f);
        userInfoLayout = $(R.id.userInfoLayout);
        userInfoLayout.setOnClickListener(this);
        circleMenu.setRotating(true);//是否启用旋转
        circleMenu.setItems(mItemTexts, mItemImgs);//显示文字及图标
        circleMenu.setIconSize(20);//图标大小，单位为dp
        circleMenu.setTextSize(16);
        circleMenu.setOnItemClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userInfoLayout:
                openActivity(GRZLActivity.class);
                break;
        }
    }

    public int index;

    @Override
    public void onItemClick(CircleMenu.ItemView view) {
            for (int i = 0; i < mItemTexts.length; i++) {
                if (mItemTexts[i].equals(view.getText())) {
                    index = i;
                }
            }
            switch (index) {
                //我的幸运树
                case 0:
                    openActivity(XYSActivity.class);
                    break;
                //酷币
                case 1:
                    openActivity(WDKBActivity.class);
                    break;
                //回购
                case 2:
                    openActivity(HGActivity.class);
                    break;
                //转增
                case 3:
                    openActivity(ZZActivity.class);
                    break;
                //我要推荐
                case 4:
                    openActivity(WYTJActivity.class);
                    break;
                //消费记录
                case 5:
                    openActivity(XFJLActivity.class);
                    break;
                //平台数据
                case 6:
                    openActivity(PTSJActivity.class);
                    break;
                //智能售卖终端
                case 7:
                    openActivity(ZNSMZDActivity.class);
                    break;
                //实体商家
                case 8:
                    openActivity(STSJActivity.class);
                    break;
                default:
                    break;
        }
    }
}
