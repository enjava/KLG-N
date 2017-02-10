package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.entity.Store;
import com.miyin.klg.entity.User;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.util.IdcardValidator;

/**
 * 修改资料
 */
public class UpdateInfoActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar update_info_titleBar;
    private String title;
    private TextView updateInfo_tv;
    @Override
    public int setLayout() {
        return R.layout.activity_update_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        update_info_titleBar = $(R.id.update_info_titleBar);
        updateInfo_tv  = $(R.id.updateInfo_tv);
    }

    @Override
    public void initDate() {
        title=getIntent().getExtras().getString("title");
        update_info_titleBar.setTitle(title);
        update_info_titleBar.setClickCallback(this);
        update_info_titleBar.getRightView().setText("修改");
    }

    @Override
    public void onBackClick() {
        finish();
    }
    @Override
    public void onRightClick() {
        String text = updateInfo_tv.getText().toString();
        store=mApp.getStore();
        user=mApp.getUser();
        if ("用户名".equals(title)) {
            if (TextUtils.isEmpty(text)) {
                showToast("用户名不能为空");
            } else if (text.length() > 20) {
                showToast("用户名过长无法保存");
            } else {

                threadHttp(ConstantsURL.USER_MODIFYUSERNAME_URL,new String[]{"username"},new String[]{text});
            }
        }else if ("真实姓名".equals(title)){
            if (TextUtils.isEmpty(text)) {
                showToast("姓名不能为空");
            } else if (text.length() > 50) {
                showToast("姓名过长，无法保存");
            } else {

                threadHttp(ConstantsURL.USER_MODIFYREALNAME_URL,new String[]{"realName"},new String[]{text});
            }
        }else if ("身份证号".equals(title)){
            if (TextUtils.isEmpty(text)) {
                showToast("身份证号不能为空");
            } else if (text.length() > 20) {
                showToast("身份证号不正确");

            } else {
                if(IdcardValidator.isValidate18Idcard(text))
                    threadHttp(ConstantsURL.USER_MODIFYIDNUMBER_URL,new String[]{"IDNumber"},new String[]{text});
                else
                    showToast("身份证号不正确");
            }
        }else if ("所在地".equals(title)){
            if (TextUtils.isEmpty(text)) {
                showToast("所在地不能为空");
            } else if (text.length() > 100) {
                showToast("所在地过长，无法保存");
            } else {
                threadHttp(ConstantsURL.USER_MODIFYADDRESS_URL,new String[]{"sheng","city","area","address"},new String[]{"浙江省","杭州市","余杭区",text});
            }
        }
    }
    private User user;
    private Store store;

    private void threadHttp(final String url, final String [] keys,  final String [] values){
        new Thread(){
            @Override
            public void run() {
                String postJson=  HttpUtil.post(url,keys,values,mCookie);

                Message msg=Message.obtain();

                if (postJson.indexOf("操作成功")!=-1){
                    if ("用户名".equals(title)) {
                        User user = mApp.getUser();

                        user.data.username = values[0];
                        mApp.setUser(user);
                        msg.what = SUCCESS;
                        Intent intent = new Intent(UpdateInfoActivity.this, GRZLActivity.class);
                        String passString = title;
                        intent.putExtra("from", passString);
                        setResult(RESULT_OK, intent);
                    }else if ("真实姓名".equals(title)){
                        User user = mApp.getUser();
                        user.data.realName = values[0];
                        mApp.setUser(user);
                        msg.what = SUCCESS;
                        Intent intent = new Intent(UpdateInfoActivity.this, GRZLActivity.class);
                        String passString = title;
                        intent.putExtra("from", passString);
                        setResult(RESULT_OK, intent);
                    }else if ("身份证号".equals(title)){
                        User user = mApp.getUser();
                        user.data.cardNum = values[0];
                        mApp.setUser(user);
                        msg.what = SUCCESS;
                        Intent intent = new Intent(UpdateInfoActivity.this, GRZLActivity.class);
                        String passString = title;
                        intent.putExtra("from", passString);
                        setResult(RESULT_OK, intent);
                    }else if ("所在地".equals(title)){
                        User user = mApp.getUser();
                        user.data.sheng = values[0];
                        user.data.city = values[1];
                        user.data.area = values[2];
                        user.data.address = values[3];
                        mApp.setUser(user);
                        msg.what = SUCCESS;
                        Intent intent = new Intent(UpdateInfoActivity.this, GRZLActivity.class);
                        String passString = title;
                        intent.putExtra("from", passString);
                        setResult(RESULT_OK, intent);
                    }
                }else
                {
                    msg.what=ERROR;

                }

                mHandler.sendMessage(msg);
            }

        }.start();
    }
    private static final int UPDATA_USERNAME=1;
    private static final int SUCCESS=2;
    private static final int ERROR=3;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case SUCCESS:
                 showToast("修改成功");

                 finish();
            	break;
                case ERROR:
                    showToast("修过失败");
                    finish();
                    break;

            default:
            	break;
            }
        }
    };
}
