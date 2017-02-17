package com.miyin.klg.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.miyin.klg.R;
import com.miyin.klg.adapter.FragmentPagerBaseAdapter;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.customview.ViewPagerIndicator;
import com.miyin.klg.fragment.DDGLFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 订单管理
 */
public class DDGLActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private ViewPagerIndicator viewPagerIndicator;
    private ViewPager viewPager;
    private List<String> mDatas = Arrays.asList("未付款", "已付款", "已撤销");
    private List<Fragment> list = new ArrayList<>();
    private BlackTitleBar blackTitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_ddgl;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewPagerIndicator = $(R.id.ddgl_ViewPagerIndicator);
        viewPager = $(R.id.ddgl_viewpage);
        blackTitleBar = $(R.id.ddgl_TitleBar);
    }

    @Override
    public void initDate() {
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("订单管理");
        viewPagerIndicator.setTabItemTitles(mDatas);
        for (String title :
                mDatas) {
            list.add(DDGLFragment.newInstance(title));
        }
        viewPager.setAdapter(new FragmentPagerBaseAdapter(getSupportFragmentManager(), list));
        viewPagerIndicator.setViewPager(viewPager, 0);

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
