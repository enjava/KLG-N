package com.miyin.klg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;

/**
 * 二级密码
 */
public class TwoPasswordActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar mTitleBar;
    private EditText two_et_oldpass;//旧密码
    private EditText two_et_newpass;//新密码
    private EditText two_newpassconfirm;//确认密码

    @Override
    public int setLayout() {
        return R.layout.activity_two_password;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.up_titleBar);

        two_et_oldpass = $(R.id.two_et_passold);
        two_et_newpass = $(R.id.two_et_pass);
        two_newpassconfirm = $(R.id.two_et_passconfig);
    }

    @Override
    public void initDate() {
        mTitleBar.setTitle("二级密码");
        mTitleBar.setClickCallback(this);


    }

    private void httpThread(final String oldpass, final String newpass, final String confirmpass) {

        new Thread() {
            @Override
            public void run() {
                String url = "";
                if (mApp.getStore() != null)
                    url = ConstantsStoreURL.STORE_MODIFYPWDPAY_URL;
                else
                    url = ConstantsURL.USER_MODIFYPWDPAY_URL;
                String json;
                if(TextUtils.isEmpty(oldpass)) {
                     json = HttpUtil.post(url, new String[]{ "password", "password1"},
                            new String[]{newpass, confirmpass}, mCookie);
                }else
                     json = HttpUtil.post(url, new String[]{"pwdOld", "password", "password1"},
                            new String[]{oldpass, newpass, confirmpass}, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what = UPDATE_FAIL;
                else {
                    if (json.indexOf("操作成功") != -1)
                        msg.what = UPDATE_SUCCESS;
                    else if (json.indexOf("旧的密码错误") != -1)
                        msg.what = UPDATE_OLD_FAIL;
                    else {
                        msg.what = UPDATE_FAIL;
                    }
                }
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    private static final int UPDATE_FAIL = 9;
    private static final int UPDATE_SUCCESS = 10;
    private static final int UPDATE_OLD_FAIL = 11;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_FAIL:
                    showToast("更改失败");
                    finish();
                    break;
                case UPDATE_SUCCESS:
                    showToast("更改成功");
                    finish();
                    break;
                case UPDATE_OLD_FAIL:
                    showToast("旧密码不正确");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void onClick(View view) {

        String oldpass = two_et_oldpass.getText().toString();
        String newpass = two_et_newpass.getText().toString();
        String confirmpass = two_newpassconfirm.getText().toString();

        if (TextUtils.isEmpty(newpass))
            showToast("密码不能为空");
        else if (TextUtils.isEmpty(confirmpass))
            showToast("确认密码不能为空");
        else if (newpass.equals(oldpass))
            showToast("新密码不能和旧密码相同");
        else if (!newpass.equals(confirmpass))
            showToast("新密码和确认密码不一致");
        else {
            if (TextUtils.isEmpty(oldpass))
                oldpass = "";
            httpThread(oldpass, newpass, confirmpass);
        }
    }
}
