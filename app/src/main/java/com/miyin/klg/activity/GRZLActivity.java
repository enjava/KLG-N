package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.entity.Store;
import com.miyin.klg.entity.User;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 个人资料
 */
public class GRZLActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private BlackTitleBar mBlackTitleBar;
    private TextView grzl_gotoSetting,grzl_userID,grzl_userName,grzl_zsxm,grzl_szd,grzl_IDcardIv,grzl_tjrName,grzl_tjrID;
    private AutoRelativeLayout grzl_userNameLayout, grzl_tureNameLayout, grzl_addressLayout, grzl_IDcardLayout;
    private ImageView imageView_id,grzl_trueNameIv;
    private User user;
    private Store store;
    private Object objectUser;
    @Override
    public int setLayout() {
        return R.layout.activity_grzl;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mBlackTitleBar = $(R.id.grzl_titleBar);
        grzl_gotoSetting = $(R.id.grzl_gotoSetting);
        grzl_userNameLayout = $(R.id.grzl_userNameLayout);
        grzl_tureNameLayout = $(R.id.grzl_tureNameLayout);
        grzl_addressLayout = $(R.id.grzl_addressLayout);
        grzl_IDcardLayout = $(R.id.grzl_IDcardLayout);


        grzl_userID = $(R.id.grzl_userID);//用户id
        grzl_userName = $(R.id.grzl_userName);//用户名
        grzl_zsxm = $(R.id.grzl_zsxm);//真实姓名
        grzl_szd = $(R.id.grzl_szd);//所在地
        grzl_IDcardIv = $(R.id.grzl_IDcardIv);//身份证
        grzl_tjrName = $(R.id.grzl_tjrName);//推荐人姓名
        grzl_tjrID = $(R.id.grzl_tjrID);//推荐人ID


        imageView_id = $(R.id.imageView_id);//身份证imageView
        grzl_trueNameIv = $(R.id.grzl_trueNameIv);//真实姓名imageView

        store= mApp.getStore();
        if (store==null) {
            user = mApp.getUser();
        }
        //grzl_userID
    }

    @Override
    public void initDate() {
        String cardNum="";
        String realName="";
      if (store!=null) {
          cardNum = store.data.userCard; //身份证
          realName = store.data.realName; //真实姓名
          grzl_userID.setText(""+store.data.userId);//用户id
      }
        else{
          cardNum = user.data.cardNum; //身份证
          realName = user.data.realName; //真实姓名
          grzl_userID.setText(""+user.data.userId);//用户id
      }

        if (!TextUtils.isEmpty(cardNum)) {
            grzl_IDcardIv.setText(cardNum.substring(0, 5) + "***********" + cardNum.substring(16, 18));
            imageView_id.setVisibility(View.INVISIBLE);
        }
        else {
            grzl_IDcardIv.setText("未设置");

            grzl_IDcardLayout.setOnClickListener(this);


        }


        if (!TextUtils.isEmpty(realName)) {
            grzl_zsxm.setText(realName.substring(0, 1) + "****");
            grzl_trueNameIv.setVisibility(View.INVISIBLE);
        }
        else {
            grzl_zsxm.setText("未设置");

            grzl_tureNameLayout.setOnClickListener(this);


        }
        if (store!=null){
            grzl_userNameLayout.setVisibility(View.GONE);
        }
        //grzl_trueNameIv

        mBlackTitleBar.setTitle("个人资料");
        mBlackTitleBar.setClickCallback(this);
        grzl_gotoSetting.setOnClickListener(this);
        grzl_userNameLayout.setOnClickListener(this);

        grzl_addressLayout.setOnClickListener(this);


        if (store==null) {
            String username = user.data.username; //用户名
            if (!TextUtils.isEmpty(username))
                grzl_userName.setText(username);
            else
                grzl_userName.setText("未设置");

        }
        String addr="";
        String sheng="";//省

        String city="";//市

        String area="";//区

        String address="";//详细地址
        int recommendID;
        String recommendName;
        if (store!=null){
            sheng=store.data.sheng;//省
            city=store.data.city;//市
            area=store.data.area;//区
            address=store.data.address;//详细地址
            recommendID= store.data.businessId; //推荐人id
            recommendName=store.data.busUserName; //推荐人用户名
        }else {
            sheng=user.data.sheng;//省
            city=user.data.city;//市
            area=user.data.area;//区
            address=user.data.address;//详细地址
            recommendID= user.data.recommend; //推荐人id
             recommendName=user.data.recommendName; //推荐人用户名
        }
        if (!TextUtils.isEmpty(sheng))
            addr+=sheng;
        if (!TextUtils.isEmpty(city))
            addr+=city;
        if (!TextUtils.isEmpty(area))
            addr+=area;
        if (!TextUtils.isEmpty(address))
            addr+=address;
        if (!TextUtils.isEmpty(addr))
            grzl_szd.setText(addr);
        else
            grzl_szd.setText("未设置");




        if (recommendID>0)
            grzl_tjrID.setText(recommendID+"");
        else
            grzl_tjrID.setText("无");


        if (!TextUtils.isEmpty(recommendName))
            grzl_tjrName.setText(recommendName);
        else
            grzl_tjrName.setText("未设置");

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grzl_gotoSetting:
                openActivity(SettingActivity.class);
                break;
            case R.id.grzl_userNameLayout:
                Bundle bundle = new Bundle();
                bundle.putString("title", "用户名");
                openActivityForResult(UpdateInfoActivity.class,bundle,1);
                break;
            case R.id.grzl_tureNameLayout:
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", "真实姓名");
                openActivityForResult(UpdateInfoActivity.class, bundle1,1);
                break;
            case R.id.grzl_addressLayout:
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "所在地");

                openActivityForResult(UpdateInfoActivity.class, bundle2,1);
                break;
            case R.id.grzl_IDcardLayout:
                Bundle bundle3 = new Bundle();
                bundle3.putString("title", "身份证号");
                openActivityForResult(UpdateInfoActivity.class, bundle3,1);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (resultCode) { //
            case RESULT_OK:
                Bundle b=data.getExtras(); //
                String str=b.getString("from");//
               if (!TextUtils.isEmpty(str)){
                   if ("用户名".equals(str)){
                       grzl_userName.setText(mApp.getUser().data.username);
                   }
                   else if ("所在地".equals(str)){
                       //String addr="";
                       String sheng=user.data.sheng;//省
                       String city=user.data.city;//市
                       String area=user.data.area;//区
                       String address=user.data.address;//详细地址
                       grzl_szd.setText(sheng+city+area+address);
                   }
                   else if ("真实姓名".equals(str)){
                       grzl_zsxm.setText(mApp.getUser().data.realName);
                       grzl_trueNameIv.setVisibility(View.INVISIBLE);
                       grzl_tureNameLayout.setClickable(false);
                   }else if ("身份证号".equals(str)){
                       grzl_IDcardIv.setText(mApp.getUser().data.cardNum.substring(0, 5) + "***********" + mApp.getUser().data.cardNum.substring(16, 18));
                       imageView_id.setVisibility(View.INVISIBLE);
                       grzl_tureNameLayout.setClickable(false);
                   }

               }
                break;
            default:
                break;
        }
    }
}
