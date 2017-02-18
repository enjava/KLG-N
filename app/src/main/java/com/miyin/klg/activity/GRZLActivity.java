package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.cascade.activity.AreaSelectActivity;
import com.miyin.klg.entity.Store;
import com.miyin.klg.entity.User;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.view.RedQRTitleBar;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 个人资料
 */
public class GRZLActivity extends BaseActivity implements RedQRTitleBar.ClickCallback, View.OnClickListener {
    private RedQRTitleBar mBlackTitleBar;
    private TextView grzl_gotoSetting, grzl_userID, grzl_userName, grzl_zsxm, grzl_szd, grzl_IDcardIv, grzl_tjrName, grzl_tjrID, grzl_addressxx;
    private AutoRelativeLayout grzl_userNameLayout, grzl_tureNameLayout, grzl_addressLayout, grzl_IDcardLayout, grzl_areaLayout;
    private ImageView imageView_id, grzl_trueNameIv;
    private User user;
    private Store store;

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
        grzl_areaLayout = $(R.id.grzl_areaLayout);


        grzl_userID = $(R.id.grzl_userID);//用户id
        grzl_userName = $(R.id.grzl_userName);//用户名
        grzl_zsxm = $(R.id.grzl_zsxm);//真实姓名
        grzl_szd = $(R.id.grzl_szd);//所在地
        grzl_IDcardIv = $(R.id.grzl_IDcardIv);//身份证
        grzl_tjrName = $(R.id.grzl_tjrName);//推荐人姓名
        grzl_tjrID = $(R.id.grzl_tjrID);//推荐人ID
        grzl_addressxx = $(R.id.grzl_addressxx);//详细地址


        imageView_id = $(R.id.imageView_id);//身份证imageView
        grzl_trueNameIv = $(R.id.grzl_trueNameIv);//真实姓名imageView
        mBlackTitleBar.setTitle("个人资料");
        mBlackTitleBar.setClickCallback(this);
        grzl_gotoSetting.setOnClickListener(this);

        store = mApp.getStore();
        user = mApp.getUser();
        if (store == null) {
            if (!user.isMyuserinfo()) {
                httpThread(true);
            } else
                initData();
        } else {
            if (!store.isMyuserinfo())
                httpThread(false);
            else
                initData();
        }
    }

    private static final int MYUSERINFO = 119;

    private void httpThread(final boolean bool) {
        new Thread() {
            @Override
            public void run() {
                String url = "";
                if (bool)
                    url = ConstantsURL.USER_MYUSERINFO_URL;
                else
                    url = ConstantsStoreURL.STORE_MYUSERINFO_URL;

                String json = HttpUtil.post(url, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what = MYUSERINFO;
                else if (json.indexOf("操作成功") != -1) {
                    if (bool) {
                        Gson gson = new Gson();
                        user = gson.fromJson(json, User.class);
                        user.setMyuserinfo(true);
                        mApp.setUser(user);
                    } else {
                        Gson gson = new Gson();
                        store = gson.fromJson(json, Store.class);
                        store.setMyuserinfo(true);
                        mApp.setStore(store);
                    }

                    msg.what = MYUSERINFO;
                } else
                    msg.what = MYUSERINFO;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void initDate() {
    }

    ;

    private String sheng;
    private String city;
    private String area;
    private String address = "";//详细地址


    public void initData() {
        String cardNum = "";
        String realName = "";

        String recommendID;
        String recommendName;
        if (store != null) {
            cardNum = store.data.userCard; //身份证
            realName = store.data.realName; //真实姓名
            grzl_userID.setText("" + store.data.userCode);//用户id
            grzl_areaLayout.setVisibility(View.GONE);
            grzl_userNameLayout.setVisibility(View.GONE);
            sheng = store.data.sheng;//省
            city = store.data.city;//市
            area = store.data.area;//区
            recommendID = store.data.busUserCode; //推荐人id
            recommendName = store.data.busUserName; //推荐人用户名
            address = store.data.address;//详细地址
        } else {
            sheng = user.data.sheng;//省
            city = user.data.city;//市
            area = user.data.area;//区
            address = user.data.address;//详细地址
            recommendID = user.data.recommendCode; //推荐人id
            recommendName = user.data.recommendName; //推荐人用户名
            cardNum = user.data.cardNum; //身份证
            realName = user.data.realName; //真实姓名
            grzl_userID.setText("" + user.data.userCode);//用户id
            String username = user.data.username; //用户名
            if (!TextUtils.isEmpty(username))
                grzl_userName.setText(username);
            else
                grzl_userName.setText("未设置");
            grzl_addressLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityForResult(AreaSelectActivity.class, null, 88);
                }
            });
            grzl_userNameLayout.setOnClickListener(this);
            grzl_areaLayout.setOnClickListener(this);
        }

        if (!TextUtils.isEmpty(cardNum)) {
            if (cardNum.length()==18)
            grzl_IDcardIv.setText(cardNum.substring(0, 5) + "***********" + cardNum.substring(16, 18));

          else
                grzl_IDcardIv.setText(cardNum);

            imageView_id.setVisibility(View.INVISIBLE);
        } else {
            grzl_IDcardIv.setText("未设置");
            grzl_IDcardLayout.setOnClickListener(this);
        }
        if (TextUtils.isEmpty(address))
            grzl_addressxx.setText("未设置");
        else
            grzl_addressxx.setText(address);

        if (!TextUtils.isEmpty(realName)) {
            grzl_zsxm.setText("**" + realName.substring(1));
            grzl_trueNameIv.setVisibility(View.INVISIBLE);
        } else {
            grzl_zsxm.setText("未设置");
            grzl_tureNameLayout.setOnClickListener(this);
        }

        if (!TextUtils.isEmpty(sheng))
            grzl_szd.setText(sheng + city + area);
        else
            grzl_szd.setText("未设置");

        if (!TextUtils.isEmpty(recommendID))
            grzl_tjrID.setText(recommendID);
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
                openActivityForResult(UpdateInfoActivity.class, bundle, 1);
                break;
            case R.id.grzl_tureNameLayout:
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", "真实姓名");
                openActivityForResult(UpdateInfoActivity.class, bundle1, 1);
                break;
            case R.id.grzl_areaLayout:
                if (TextUtils.isEmpty(sheng))
                    showToast("请先选择所属区域");
                else {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("title", "详细地址," + sheng + "," + city + "," + area);
                    openActivityForResult(UpdateInfoActivity.class, bundle2, 22);
                }
                break;
            case R.id.grzl_IDcardLayout:
                Bundle bundle3 = new Bundle();
                bundle3.putString("title", "身份证号");
                openActivityForResult(UpdateInfoActivity.class, bundle3, 1);
                break;
        }
    }

    private String resultArea;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //
            case RESULT_OK:
                Bundle b = data.getExtras(); //
                if (requestCode == 88) {
                    resultArea = b.getString("areaselect");
                    if (!TextUtils.isEmpty(resultArea) && resultArea.indexOf(",") != -1) {
                        String[] results = resultArea.split(",");
                        if (results.length > 2) {
                            sheng = results[0];
                            city = results[1];
                            area = results[2];
                            grzl_szd.setText(sheng + city + area);
                            if (!TextUtils.isEmpty(address))
                                httpThread();
                        }
                    }
                }

                if (b != null) {
                    String str = b.getString("from");//
                    if (!TextUtils.isEmpty(str)) {
                        if ("用户名".equals(str)) {
                            grzl_userName.setText(mApp.getUser().data.username);
                        } else if ("详细地址".equals(str)) {
                            address = user.data.address;//详细地址
                            grzl_addressxx.setText(address);
                        } else if ("真实姓名".equals(str)) {
                            grzl_zsxm.setText(mApp.getUser().data.realName);
                            grzl_trueNameIv.setVisibility(View.INVISIBLE);
                            grzl_tureNameLayout.setClickable(false);
                        } else if ("身份证号".equals(str)) {
                            grzl_IDcardIv.setText(mApp.getUser().data.cardNum.substring(0, 5) + "***********" + mApp.getUser().data.cardNum.substring(16, 18));
                            imageView_id.setVisibility(View.INVISIBLE);
                            grzl_tureNameLayout.setClickable(false);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private void httpThread() {
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtil.post(ConstantsURL.USER_MODIFYADDRESS_URL, new String[]{"sheng", "city", "area", "address"}, new String[]{sheng, city, area, address}, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what = HttpUtil.ERROR;
                else if (json.indexOf("操作成功") != -1)
                    msg.what = HttpUtil.SUCEESS;
                else
                    msg.what = HttpUtil.ERROR;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MYUSERINFO:
                    initData();
                    break;
                case HttpUtil.ERROR:
                    showToast("区域保存失败");
                    break;
                case HttpUtil.SUCEESS:
                    showToast("区域保存成功");
                    break;
                default:
                    break;
            }
        }
    };

}
