package com.miyin.klg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.view.RedQRTitleBar;

/**
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity implements RedQRTitleBar.ClickCallback {
    private RedQRTitleBar feedback_titleBar;
    private EditText fee_lxfs, fee_idea;

    @Override
    public int setLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        feedback_titleBar = $(R.id.feedback_titleBar);
        fee_lxfs = $(R.id.fee_lxfs);
        fee_idea = $(R.id.fee_idea);
    }

    @Override
    public void initDate() {
        feedback_titleBar.setClickCallback(this);
        feedback_titleBar.setTitle("意见反馈");
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void onClick(View view) {
        String lxfs = fee_lxfs.getText().toString();
        String idea = fee_idea.getText().toString();

        if (TextUtils.isEmpty(lxfs))
            showToast("联系方式不能为空");
        else if (TextUtils.isEmpty(idea))
            showToast("意见详情");
        else if (lxfs.length() > 50)
            showToast("联系方式不正确");
        else if (idea.length() > 180)
            showToast("意见详情请控制在180个字内");
        else {
            httpThread(lxfs,idea);
        }
    }

    private static final int UPDATE_FAIL = 9;
    private static final int UPDATE_SUCCESS = 10;
    private static final int UPDATE_OLD_FAIL = 11;

    private void httpThread(final String lxfs, final String idea) {
//        new Thread(){
//            @Override
//            public void run() {
//
//            }
//        }.start();

        new Thread() {

            @Override
            public void run() {
                String url = "";
                if (mApp.getStore()!=null)
                    url= ConstantsStoreURL.STORE_BACKMESSAGE_URL;
                else
                    url= ConstantsURL.USER_BACKMESSAGE_URL;
                String json = HttpUtil.post(url, new String[]{"contact","content"}, new String[]{lxfs,idea}, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what = UPDATE_FAIL;
                else {
                    if (json.indexOf("操作成功") != -1)
                        msg.what = UPDATE_SUCCESS;
//                    else if (json.indexOf("旧的密码错误") != -1)
//                        msg.what = UPDATE_OLD_FAIL;
                    else {
                        msg.what = UPDATE_FAIL;
                    }
                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_SUCCESS:
                    showToast("成功提交");
                    finish();
                    break;
                case UPDATE_FAIL:
                    showToast("提交失败");
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
