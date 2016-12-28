package com.miyin.klg.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miyin.klg.R;
import com.miyin.klg.adapter.FragmentPagerBaseAdapter;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.customview.ViewPagerIndicator;
import com.miyin.klg.fragment.XFFragment;
import com.miyin.klg.fragment.XYSFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 消费记录
 */
public class XFJLActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private ViewPagerIndicator viewPagerIndicator;
    private ViewPager viewPager;
    private List<String> mDatas = Arrays.asList("实体商家消费记录", "售卖终端消费记录");
    private List<Fragment> list = new ArrayList<>();
    private BlackTitleBar blackTitleBar;

    @Override
    public int setLayout() {
        return R.layout.activity_xf;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewPagerIndicator = $(R.id.xf_ViewPagerIndicator);
        viewPager = $(R.id.xf_viewpage);
        blackTitleBar = $(R.id.xf_titleBar);
    }

    @Override
    public void initDate() {
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("消费记录");
        viewPagerIndicator.setTabItemTitles(mDatas);
        for (String title :
                mDatas) {
            list.add(XFFragment.newInstance(title));
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
