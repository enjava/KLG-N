package com.miyin.klg.activity;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.miyin.klg.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements BlackTitleBar.ClickCallback {

    private BlackTitleBar mTitleBar;
    private Button mCodeBtn;
    private String phoneCode;//手机验证码
    private EditText mEtPhoneCode;
    private String mobile;//手机号码
    private EditText mEtPhoneNum;
    private String userCode;//推荐码
    private EditText mEtUserCode;
    private String password;//用户密码
    private EditText mEtPass;//密码
    private TimeCount mTimeCount;
    private static final int SEND_CODE = 11;//发送验证码
    private static final int REGISTER_CODE = 12;//注册提交
    private static final int REGISTER_SUCCESS = 13;//注册提交
    private static final int REGISTER_MOBILE_EXIST = 14;//手机号码已被注册
    private static final int REGISTER_MOBILE_FAIL = 15;//手机号码已被注册
    private static final int MOBILE_EXIST = 16;//注册提交
    private static final int MOBILE_NOT_EXIST = 17;//手机号码已被注册
    private static final int SEND_NET_ERROR = 104;//网络异常
    private static final int SEND_CODE_SUCCESS = 110;//发送验证码
    private static final int SEND_CODE_FAIL = 111;//发送失败
    private static final String tag = mContext.getClass().getSimpleName();

    //region mHandler
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
                case REGISTER_CODE://注册
                    registerUser();
                    break;
                case SEND_CODE_SUCCESS://发送验证码成功
                   showToast("成功发送验证码");
                    break;
                case SEND_NET_ERROR://发送验证码成功
                    showToast("网络异常");
                    break;
                case SEND_CODE_FAIL://发送验证码成功
                    showToast("发送验证码失败");
                    break;
                case REGISTER_SUCCESS://注册成功
                    showDialogSuccess();
                    break;
                case REGISTER_MOBILE_EXIST://手机号码已经存在
                    showToast("手机号码已经存在");
                    break;
                case REGISTER_MOBILE_FAIL://手机号码已经存在
                    showToast("注册失败");
                    break;
                case MOBILE_EXIST:
                    //手机号码存在
                    CommonUtil.showInfoDialog(mContext,"该手机号已经注册");
                    break;
                case MOBILE_NOT_EXIST:
                    //该手机未注册
                    sendExamine();
                    break;
                default:

                    Log.i(tag, "msg.what" + msg.what);
                    break;
            }

        }
    };
    //endregion

    //判断手机是否存在
    private void mobileExisted() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Map<String, String> request = new HashMap<>();
                request.put("mobile", mobile);
                String result = HttpUtil.post(ConstantsURL.USER_MOBILEEXISTED_URL, request);
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

    private void showDialogSuccess() {
        CommonUtil.showInfoDialog(this,"注册成功","提示", "马上登陆",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openActivity(LoginActivity.class);
                finish();
            }
        });
    }

    private void registerUser() {
        password=mEtPass.getText().toString();
        String code=mEtPhoneCode.getText().toString();
        if (TextUtils.isEmpty(phoneCode)) {
            showToast("验证码输入不正确");
        }else if (TextUtils.isEmpty(password)){
            showToast("密码不能为空");
        }
        else if (!CommonUtil.isPwd(password)){
            showToast("密码只能是6-8位的字母和数字组合");
        }
        else  if (!phoneCode.equals(code)) {
            showToast("验证码输入不正确");
        }
        else{
            userCode=mEtUserCode.getText().toString();
            final Map<String,String> request=new HashMap<>();
            request.put("mobile",mobile);
            request.put("password",password);
            request.put("password1",password);
            if (!TextUtils.isEmpty(userCode))
                request.put("userCode",userCode);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                   String json=   HttpUtil.post(ConstantsURL.USER_REGISTER_URL,request,mCookie);
                    Log.i(tag,"mCookie:"+mCookie.toString());
                    saveCookie();
                    Message msg=Message.obtain();
                   if (json.indexOf("注册成功")!=-1) {
                       msg.what=REGISTER_SUCCESS;
                   }else if (json.indexOf("该手机号已存在")!=-1){
                       msg.what=REGISTER_MOBILE_EXIST;
                   }
                   else{
                       msg.what=REGISTER_MOBILE_FAIL;
                   }
                    mHandler.sendMessage(msg);
                }
            }.start();
        }

    }
  @Override
    public int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.register_titleBar);
        mCodeBtn = $(R.id.up_codeBtn);
        mEtPhoneCode = $(R.id.et_code);//验证码
        mEtPhoneNum = $(R.id.et_phoneNum);//手机号
        mEtUserCode = $(R.id.et_userCode);//推荐码
        mEtPass = $(R.id.et_pass);//推荐码

    }

    @Override
    public void initDate() {
        mTitleBar.setClickCallback(this);
        mTitleBar.setTitle("注册");
        mTitleBar.getRightView().setText("登录");
       Intent intent=  getIntent();
        if (intent!=null){
            String extras = intent.getStringExtra("result");
            if (!TextUtils.isEmpty(extras)&&extras.indexOf("CMZCuserCode=")!=-1){
                mEtUserCode.setText(extras.replace("CMZCuserCode=",""));
                mEtUserCode.setEnabled(false);
            }
        }
    }

    public void saoyisao(View view){
        Bundle bundle = new Bundle();
        bundle.putString("userCode", "userId");
        openActivityForResult(CaptureActivity.class, bundle,1);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {
        openActivity(LoginActivity.class);
    }


    //发送验证码
    private synchronized void sendExamine() {
        new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                Map<String, String> maprequest = new HashMap<>();
                maprequest.put("mobile", mobile);
                String postJson = HttpUtil.post(ConstantsURL.USER_SENDEXAMINE_URL, maprequest, mCookie);
                JSONObject job = null;
                if (TextUtils.isEmpty(postJson)) {
                    //网络异常
                    msg.what = SEND_NET_ERROR;
                    mHandler.sendMessage(msg);
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
                            mHandler.sendMessage(msg);
                            Log.i(tag, "code:" + code);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //验证码发送失败
                        msg.what = SEND_CODE_FAIL;
                        mHandler.sendMessage(msg);
                        Log.i(tag, postJson);
                    }
                }

            }
        }.start();
    }


    public void btnOnClick(View view) {
        mobile = mEtPhoneNum.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            showToast("手机号码不能空");
            return;
        } else if (!CommonUtil.isMobileNO(mobile)) {
            showToast("手机号码输入不正确");
            return;
        }
        Message msg = Message.obtain();
        switch (view.getId()) {
            case R.id.up_codeBtn://发送验证码
                msg.what = SEND_CODE;
                break;
            case R.id.btn_register:
                msg.what = REGISTER_CODE;//提交注册
                break;
            default:
                System.out.println("btnPressed");
                break;
        }
        mHandler.sendMessage(msg);

    }

    private static boolean btnPressed = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:

                    Bundle bundle = data.getExtras();
                    String      result=   bundle.getString("result");
                if(!TextUtils.isEmpty(result)&&result.indexOf("CMZCuserCode=")!=-1){
                    result=result.replace("CMZCuserCode=","");
                    mEtUserCode.setText(result);
                }else
                showToast("无法获取正确的推荐码");
                break;

        }
    }

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
