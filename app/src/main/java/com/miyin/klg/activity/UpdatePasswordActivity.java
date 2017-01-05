package com.miyin.klg.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.util.CommonUtil;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.CountDownTimer;
import com.miyin.klg.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.miyin.klg.util.HttpUtil.post;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends BaseActivity implements BlackTitleBar.ClickCallback {

    private BlackTitleBar mTitleBar;
    private Button mCodeBtn;

    private String phoneCode;//手机验证码
    private String mobile;//手机号码

    private EditText etPwdConfirm;//确认密码
    private EditText etPwd;//密码
    private EditText etMobile;//手机号
    private EditText etPhoneCode;//验证码
    private TimeCount mTimeCount;
    private static final int SEND_CODE = 11;//发送验证码
    private static final int UPDATE_SUBMIT = 12;//更改提交
    private static final int MOBILE_EXIST = 13;//注册提交
    private static final int MOBILE_NOT_EXIST = 14;//手机号码已被注册
    private static final int UPDATE_SUCCESS = 15;//更改密码成功
    private static final int UPDATE_FAIL = 16;//更改密码成功
    private static final int SEND_NET_ERROR = 104;//网络异常
    private static final int SEND_CODE_SUCCESS = 110;//验证码已发送
    private static final int SEND_CODE_FAIL = 111;//发送失败


    private final String tag = getClass().getSimpleName();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SEND_CODE:
                    if (!btnPressed) {
                        mobileExisted();
                        mTimeCount = new TimeCount(60000, 1000);
                        mTimeCount.start();
                        btnPressed = true;
                    }
                    break;
                case MOBILE_EXIST:
                    //手机号码存在
                    sendExamine();
                    break;
                case MOBILE_NOT_EXIST:
                    //该手机未注册
                    showToast("该手机未注册,请确认");
                    break;
                case SEND_NET_ERROR:
                    //网络异常
                    showToast("网络异常");
                    break;
                case SEND_CODE_SUCCESS:
                    //验证码已发送
                    showToast("验证码已发送");
                    break;
                case SEND_CODE_FAIL:
                    //验证码发送失败
                    showToast("验证码发送失败");
                    break;
                case UPDATE_SUBMIT:
                    //更改密码
                    updatePwd();
                    break;
                case UPDATE_SUCCESS:
                    //更改密码
                    showDialogSuccess();
                    break;
                case UPDATE_FAIL:
                    //更改密码
                    showToast("更改失败");
                    break;
                default:
                    break;
            }
        }
    };

    private void showDialogSuccess() {
        CommonUtil.showInfoDialog(this,"密码修改成功","提示", "马上登陆",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openActivity(LoginActivity.class);
                finish();
            }
        });
    }
    //更改密码
    private void updatePwd() {
        final String pwd1 = etPwd.getText().toString();//密码
        String pwd2 = etPwdConfirm.getText().toString();//确认密码
        String code = etPhoneCode.getText().toString();
        if (TextUtils.isEmpty(code))
            showToast("验证码不能为空");
        else if (!code.equals(phoneCode))
            showToast("手机校验码输入不正确");
        else if (TextUtils.isEmpty(pwd1))
            showToast("密码不能为空");
        else if (!pwd1.equals(pwd2))
            showToast("两次输入的密码不一致");
        else {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Map<String, String> request = new HashMap<String, String>();
                    request.put("mobile", mobile);
                    request.put("password", pwd1);
                    request.put("password1", pwd1);
                    String json = HttpUtil.post(ConstantsURL.USER_FORGETPWD_URL, request);
                    Message msg=Message.obtain();
                    if (TextUtils.isEmpty(json)){
                        msg.what=SEND_NET_ERROR;
                    }
                    else if (json.indexOf("更新登录密码成功")!=-1){
                        msg.what=UPDATE_SUCCESS;
                    }
                    else {
                        msg.what=UPDATE_FAIL;
                        Log.i(tag,json);
                    }
                    mHandler.sendMessage(msg);
                }
            }.start();
        }

    }
    //判断手机是否存在
    private void mobileExisted() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Map<String, String> request = new HashMap<>();
                request.put("mobile", mobile);
                String result = HttpUtil.postData(ConstantsURL.USER_MOBILEEXISTED_URL, request);
                Message msg = new Message();
                if (!TextUtils.isEmpty(result) && result.indexOf("该手机号已存在") != -1) {
                    //手机号码已经存在
                    msg.what = MOBILE_EXIST;
                } else if (!TextUtils.isEmpty(result) && result.indexOf("手机号不存在") != -1) {
                    //手机号码不存在
                    msg.what = MOBILE_NOT_EXIST;
                } else {
                    msg.what=SEND_NET_ERROR;
                    Log.i(tag, result);
                }
                mHandler.sendMessage(msg);

            }
        }.start();
    }
    @Override
    public int setLayout() {
        return R.layout.activity_update_password;
    }
    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.up_titleBar);
        mCodeBtn = $(R.id.up_codeBtn);

        etPhoneCode = $(R.id.et_code);//验证码
        etMobile = $(R.id.et_phoneNum);//手机号
        etPwd = $(R.id.et_pwd);//密码
        etPwdConfirm = $(R.id.et_pwd_confirm);//密码
    }
    @Override
    public void initDate() {
        mTitleBar.setTitle("找回密码");
        mTitleBar.setClickCallback(this);
    }
    @Override
    public void onBackClick() {
        finish();
    }
    @Override
    public void onRightClick() {

    }
    //发送验证码
    private synchronized void sendExamine() {
        new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                Map<String, String> maprequest = new HashMap<>();
                maprequest.put("mobile", mobile);
                String postJson = post(ConstantsURL.USER_SENDEXAMINE_URL, maprequest, mCookie);
                JSONObject job;
                if (TextUtils.isEmpty(postJson)) {
                    //网络异常
                    msg.what = SEND_NET_ERROR;

                } else {
                    if (postJson.indexOf("验证码发送成功") != -1) {
                        try {
                            job = new JSONObject(postJson);
                            JSONObject data = job.getJSONObject("data");
                            String codeId = data.getString("codeId");
                            Log.i(tag, "codeId:" + codeId);
                            String code = (String) data.get("code");
                            phoneCode = code;
                            msg.what = SEND_CODE_SUCCESS;
                            Log.i(tag, "code:" + code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        msg.what = SEND_CODE_FAIL;
                        Log.i(tag, postJson);
                    }
                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    public void btnOnClick(View view) {
        mobile = etMobile.getText().toString();
        if (TextUtils.isEmpty(mobile))
            showToast("手机号码不能空");
        else if (!CommonUtil.isMobileNO(mobile))
            showToast("手机号码输入不正确");
        else {
            //1 判断手机是否已经注册
            Message msg = Message.obtain();
            switch (view.getId()) {
                case R.id.up_codeBtn://发送验证码
                    msg.what = SEND_CODE;
                    break;
                case R.id.btn_submit:
                    msg.what = UPDATE_SUBMIT;//提交更改
                    break;
                default:
                    System.out.println("btnPressed");
                    break;
            }
            mHandler.sendMessage(msg);
        }

    }

    private static boolean btnPressed = false;

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //计时过程显示
            mCodeBtn.setText(millisUntilFinished / 1000 + "后重新发送");
            mCodeBtn.setTextColor(getResources().getColor(R.color.colorTextGary));
        }

        @Override
        public void onFinish() {
            btnPressed = false;
            mCodeBtn.setTextColor(getResources().getColor(R.color.colorRed));
            mCodeBtn.setText("获取验证码");
        }
    }

}
