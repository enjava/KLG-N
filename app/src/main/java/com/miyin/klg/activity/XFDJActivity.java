package com.miyin.klg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.entity.UserMobile;
import com.miyin.klg.util.CommonUtil;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 消费登记
 */
public class XFDJActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private RedQRTitleBar blackTitleBar;

    private EditText dj_phone, dj_money, dj_userName, dj_beizhu;
    private Button dj_commit;
    @Override
    public int setLayout() {
        return R.layout.activity_xfdj;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        blackTitleBar = $(R.id.xfdj_TitleBar);
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("消费登记");

        dj_phone = $(R.id.dj_phone);
        dj_money = $(R.id.dj_money);
        dj_userName = $(R.id.dj_userName);
        dj_beizhu = $(R.id.dj_beizhu);//备注信息
        dj_commit = $(R.id.dj_commit);//
    }


    protected void initViewsAndEvents(View view) {
        blackTitleBar = $(R.id.xfdj_TitleBar);
        blackTitleBar.setClickCallback(this);
        blackTitleBar.setTitle("消费登记");
        dj_phone = $(R.id.dj_phone);
        dj_money = $(R.id.dj_money);
        dj_userName = $(R.id.dj_userName);
        dj_beizhu = $(R.id.dj_beizhu);//备注信息
        dj_commit = $(R.id.dj_commit);//

        dj_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (delayRun != null && !TextUtils.isEmpty(phoneNum)) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    mHandler.removeCallbacks(delayRun);
                }
                if (!dj_phone.getText().toString().equals(phoneNum)) {
                    phoneNum = dj_phone.getText().toString();
                    //延迟800ms，如果不再输入字符，则执行该线程的run方法
                    mHandler.postDelayed(delayRun, 800);
                }

            }
        });

        dj_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = dj_money.getText().toString();
                String beizhu = dj_beizhu.getText().toString();
                if (TextUtils.isEmpty(beizhu))
                    beizhu = "";
                if (!CommonUtil.isMobileNO(phoneNum))
                    showToast("手机号未填写");
                else if (TextUtils.isEmpty(money))
                    showToast("消费金额不能为空");
                else if (TextUtils.isEmpty(money))
                    showToast("消费金额不能为空");
                else if (TextUtils.isEmpty(userid))
                    showToast("输入的手机号,未注册会员");
                else {
                    money = CommonUtil.formatDouble(Double.parseDouble(money)) + "";
                    httpThread(userid, money, beizhu);
                }




            }
        });
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MOBILE_SUCCESS:
                    userid = userMobile.data.userId + "";
                    if (TextUtils.isEmpty(userMobile.data.realName))
                        dj_userName.setText("会员未设置");
                    else
                        dj_userName.setText("**" + userMobile.data.realName.substring(1));
                    break;
                case MOBILE_ERROR:
                    showToast("无此会员");
                    break;
                case HttpUtil.SUCEESS:
                    CommonUtil.showInfoDialog(XFDJActivity.this,"登记成功");
                    userid="";
                    phoneNum="";
                    dj_phone.setText(null);
                    dj_userName.setText(null);
                    dj_beizhu.setText(null);
                    dj_money.setText(null);
                    break;
                case HttpUtil.ERROR:
                    showToast("登记失败");
                default:
                    break;
            }
        }
    };

    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
            if (!TextUtils.isEmpty(phoneNum))
            getSearchResult(phoneNum);
        }
    };
    String phoneNum;

    private void getSearchResult(String editStr) {
        userid="";
        if (!CommonUtil.isMobileNO(editStr))
            showToast("输入的手机号不正确");
        else
            httpThread("", phoneNum, "");

    }

    UserMobile userMobile;
    String userid;
    private static final int MOBILE_SUCCESS = 222;
    private static final int MOBILE_ERROR = 223;

    private void httpThread(final String userID, final String money, final String beizhu) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(userID) && !TextUtils.isEmpty(money)) {
                    String json = HttpUtil.post(ConstantsURL.USER_SELECTUSERBYMOBILE_URL, new String[]{"mobile"}, new String[]{money}, mCookie);

                    if (json.indexOf("操作成功") != -1&&json.indexOf("userId")!=-1) {
                        Gson gson = new Gson();
                        userMobile = gson.fromJson(json, UserMobile.class);
                        userid=userMobile.data.userId+"";
                        msg.what = MOBILE_SUCCESS;
                    } else {
                        msg.what = MOBILE_ERROR;
                    }
                } else {
                    String json = HttpUtil.post(ConstantsStoreURL.STORE_ADDENTRYORDER_URL, new String[]{"userId", "money", "remark"}, new String[]{userID, money, beizhu}, mCookie);

                    if (json.indexOf("操作成功") != -1) {
                        msg.what = HttpUtil.SUCEESS;
                    } else {
                        msg.what = HttpUtil.ERROR;
                    }
                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }


    @Override
    public void initDate() {

        dj_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (delayRun != null && !TextUtils.isEmpty(phoneNum)) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    mHandler.removeCallbacks(delayRun);
                }
                if (!dj_phone.getText().toString().equals(phoneNum)) {
                    phoneNum = dj_phone.getText().toString();

                    //延迟800ms，如果不再输入字符，则执行该线程的run方法
                    mHandler.postDelayed(delayRun, 800);
                }

            }
        });

        dj_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = dj_money.getText().toString();
                String beizhu = dj_beizhu.getText().toString();
                if (TextUtils.isEmpty(beizhu))
                    beizhu = "";
                if (!CommonUtil.isMobileNO(phoneNum))
                    showToast("手机号未填写");
                else if (TextUtils.isEmpty(money))
                    showToast("消费金额不能为空");
                else if (TextUtils.isEmpty(money))
                    showToast("消费金额不能为空");
                else if (TextUtils.isEmpty(userid))
                    showToast("输入的手机号,未注册会员");
                else {
                    money = CommonUtil.formatDouble(Double.parseDouble(money)) + "";
                    httpThread(userid, money, beizhu);
                }




            }
        });

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
