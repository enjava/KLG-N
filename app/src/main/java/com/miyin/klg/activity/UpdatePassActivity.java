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
 * 修改密码
 */
public class UpdatePassActivity extends BaseActivity implements BlackTitleBar.ClickCallback {

    private BlackTitleBar mTitleBar;


    private EditText updatepass_et_oldpass;//确认密码
    private EditText updatepass_et_newpass;//密码
    private EditText updatepass_et_newpassconfirm;//手机号


    private final String tag = getClass().getSimpleName();


    @Override
    public int setLayout() {
        return R.layout.activity_update_pass;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.up_titleBar);
        updatepass_et_oldpass = $(R.id.updatepass_et_oldpass);//手机号
        updatepass_et_newpass = $(R.id.updatepass_et_newpass);//密码
        updatepass_et_newpassconfirm = $(R.id.updatepass_et_newpassconfirm);//密码
    }

    @Override
    public void initDate() {
        mTitleBar.setTitle("修改密码");
        mTitleBar.setClickCallback(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void btnOnClick(View view) {
        String oldpass = updatepass_et_oldpass.getText().toString();
        String newpass = updatepass_et_newpass.getText().toString();
        String confirmpass = updatepass_et_newpassconfirm.getText().toString();

        if (TextUtils.isEmpty(oldpass))
            showToast("旧密码不能为空");
        else if (TextUtils.isEmpty(newpass))
            showToast("密码不能为空");
        else if (TextUtils.isEmpty(confirmpass))
            showToast("确认密码不能为空");
        else if (oldpass.equals(newpass))
            showToast("新密码不能和旧密码相同");
        else if (!newpass.equals(confirmpass))
            showToast("新密码和确认密码不一致");
        else {
            httpThread(oldpass, newpass, confirmpass);
        }

    }

    private void httpThread(final String oldpass, final String newpass, final String confirmpass) {

        new Thread() {
            @Override
            public void run() {
                String url="";
                if (mApp.getStore()!=null)
                    url= ConstantsStoreURL.STORE_MODIFYPWD_URL;
                else
                    url= ConstantsURL.USER_MODIFYPWD_URL;
                String json = HttpUtil.post(url, new String[]{"pwdOld", "password", "password1"},
                        new String[]{oldpass, newpass, confirmpass}, mCookie);
                Message msg=Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what=UPDATE_FAIL;
                else {
                    if (json.indexOf("操作成功")!=-1)
                        msg.what=UPDATE_SUCCESS;
                    else if (json.indexOf("旧的密码错误")!=-1)
                        msg.what=UPDATE_OLD_FAIL;
                    else {
                        msg.what=UPDATE_FAIL;
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

}
