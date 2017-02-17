package com.miyin.klg.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.miyin.klg.R;
import com.miyin.klg.adapter.FragmentPagerBaseAdapter;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.ViewPagerIndicator;
import com.miyin.klg.fragment.GRFragment;
import com.miyin.klg.fragment.QYFragment;
import com.miyin.klg.view.RedQRTitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by en on 2017/2/11.
 * 商家入住
 */
public class BusCheckInActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private ViewPagerIndicator viewPagerIndicator;
    private ViewPager viewPager;
    private List<String> mDatas = Arrays.asList("个人", "企业");
    private List<Fragment> list = new ArrayList<>();
    private RedQRTitleBar titleBar;
    @Override
    public int setLayout() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.colorRed);//通知栏所需颜色
        return R.layout.activity_buscheckin;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.buscheckin_TitleBar);
        viewPagerIndicator = $(R.id.buscheckin_ViewPagerIndicator);
        viewPager = $(R.id.buscheckin_viewpage);

    }

    @Override
    public void initDate() {
        titleBar.setClickCallback(this);
        titleBar.setTitle("商家入驻");
        viewPagerIndicator.setTabItemTitles(mDatas);

        list.add(new GRFragment());
        list.add(new QYFragment());

        viewPager.setAdapter(new FragmentPagerBaseAdapter(getSupportFragmentManager(), list));
        viewPagerIndicator.setViewPager(viewPager, 0);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                // 把当前显示的position传递出去
//                mCurrentPosition = position;
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onBackClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onBackPressed() {
        exitBy2click();
    }

    public void onClick(View view) {
        showToast("aaa");
        switch (view.getId()) {
            case R.id.btn_gr:
                openActivity(GRDPActivity.class);
                break;
            case R.id.btn_qy:
                openActivity(QYDPActivity.class);
                break;
            default:
                break;
        }
    }
}
