package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.util.Constants;
import com.miyin.klg.util.SpUtil;
import com.miyin.klg.util.StatusBarUtil;


public class SplashActivity extends BaseActivity {
    private final String tag= getClass().getSimpleName();
    RelativeLayout activitySplash;

    @Override
    public int setLayout() {
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.StatusBarLightMode(this);
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        activitySplash= $(R.id.activity_splash);

    }

    @Override
    public void initDate() {
        RotateAnimation animRotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(1000);//动画时间
        animRotate.setFillAfter(true);//保持动画结束的状态

        //缩放动画
        ScaleAnimation animScale=new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);//保持动画结束的状态

        //渐变动画
        AlphaAnimation animAlpha=new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(animRotate);
        animationSet.addAnimation(animScale);
        animationSet.addAnimation(animAlpha);


        activitySplash.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(tag,"动画开始onAnimationStart");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(tag,"动画重置onAnimationRepeat");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(tag,"动画结束onAnimationEnd");
                // 动画结束,跳转页面
                // 如果是第一次进入, 跳新手引导
                // 否则跳主页面
                boolean isFirstEnter = SpUtil.getBoolean(SplashActivity.this, Constants.IS_FIRST_ENTER, true);
                Intent intent;
                Bundle bundle = new Bundle();
                if (isFirstEnter){
                    // 新手引导
//                    intent = new Intent(getApplicationContext(),
//                            LoginActivity.class);

                    bundle.putInt("type", 1);

                }
                else {
                    // 主页面
//                    intent = new Intent(getApplicationContext(),
//                            LoginActivity.class);
                    bundle.putInt("type", 1);
                }
                openActivity(HomeActivity.class, bundle);
                finish();// 结束当前页面
            }
        });
    }
}
