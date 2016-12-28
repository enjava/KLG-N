package com.miyin.klg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //会员 1  合伙人-1
        $(R.id.vip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                openActivity(HomeActivity.class, bundle);
            }
        });
        $(R.id.hhr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", -1);
                openActivity(HomeActivity.class, bundle1);
            }
        });
    }

    @Override
    public void initDate() {

    }
}
