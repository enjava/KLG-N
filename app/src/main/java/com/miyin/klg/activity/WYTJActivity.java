package com.miyin.klg.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.util.QRCodeUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 我要推荐
 */
public class WYTJActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private RedQRTitleBar wytj_titleBar;
    private ImageView mImageViewTuiJian;
    private TextView wytj_tv_name,wytj_tv_name2;
    @Override
    public int setLayout() {
        return R.layout.activity_wy;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        wytj_titleBar = $(R.id.wytj_titleBar);
        mImageViewTuiJian = $(R.id.wdtj_iv);
        wytj_tv_name = $(R.id.wytj_tv_name);
        wytj_tv_name2 = $(R.id.wytj_tv_name2);
    }
    private String userCode="tt=aaaaaaaaaa";
    private String username="未知";
    @Override
    public void initDate() {
        wytj_titleBar.setClickCallback(this);
        wytj_titleBar.setTitle("我要推荐");
        wytj_titleBar.getRightView().setText("记录");
        if (mApp.getStore()!=null){
            userCode="tt=aaaaaaaaaa";
            if (!TextUtils.isEmpty(mApp.getStore().data.realName))
                username=mApp.getStore().data.realName;
        }else if (mApp.getUser()!=null)
        {
            userCode= "CMZCuserCode="+mApp.getUser().data.userCode;
            if (!TextUtils.isEmpty(mApp.getUser().data.username))
                username=mApp.getUser().data.username;
        }
        wytj_tv_name.setText("酷友专属二维码，保存图片，分享此图赚钱");
        wytj_tv_name2.setText("我是"+username);
        mImageViewTuiJian.setImageBitmap(QRCodeUtil.createBitmap(userCode));

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {
        openActivity(RecommendedRecordsActivity.class);
    }
}
