package com.miyin.klg.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

/**
 * @Title: BaseActivity.java
 * @Description: 基础Activity，用户Activity可以继承此Activity，实现自定义的方法，另外可以实现对话框等其他公用方法
 */
public abstract class BaseActivity extends AppCompatActivity {
    private boolean isExit = false; // 是否退出按钮的转态标记
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView(savedInstanceState);
        initDate();
        mContext = this;
    }

    public abstract int setLayout();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initDate();

    protected void showToast(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 省去类型转换
     */
    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 跳转页面
     *
     * @param pClass  跳转的页面
     * @param pBundle 传递过去的参数
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 启动带结果的Activity
     *
     * @param pClass
     * @param pBundle
     * @param requestCode
     */
    protected void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
            startActivityForResult(intent, requestCode);
        } else {
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 双击退出程序
     */
    protected void exitBy2click() {
        Timer eExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            eExit = new Timer();
            eExit.schedule(new TimerTask() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    public void finish() {
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}