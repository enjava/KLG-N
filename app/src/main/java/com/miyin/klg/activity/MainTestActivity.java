package com.miyin.klg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.miyin.klg.R;
/*
测试类

 */
public class MainTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
    }

    public void onclick(View view) {
        System.out.println(view.getId());
    }
}
