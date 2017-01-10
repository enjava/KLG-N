package com.miyin.klg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.util.CommonUtil;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements BlackTitleBar.ClickCallback, View.OnClickListener {
    private static final int LOGIN_SUCCESS = 10;//登录成功
    private static final int LOGIN_ERROR = 11;//账号密码错误
    private static final int LOGIN_BAN = 12;//禁止登录
    private BlackTitleBar mTitleBar;
    private TextView login_forgetPassword, login_register, login_ok;
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private EditText etPassword;
    private EditText etUserName;
    private static final int SEND_NET_ERROR = 104;//网络异常
    private final String tag = getClass().getSimpleName();

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        login_forgetPassword = $(R.id.login_forgetPassword);
        login_register = $(R.id.login_register);
        login_ok = $(R.id.login_ok);
        spinner = $(R.id.spinner);
        etPassword = $(R.id.et_password);
        etUserName = $(R.id.et_userName);
    }

    @Override
    public void initDate() {
        login_forgetPassword.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_ok.setOnClickListener(this);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("酷友");
        data_list.add("酷么合伙人");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
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
            case R.id.login_forgetPassword:
                openActivity(UpdatePasswordActivity.class);
                break;
            case R.id.login_register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.login_ok:
                //openActivity(SettingActivity.class);
                login();
                break;
        }
    }
    private int ispinner;
    private void login() {
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        ispinner = spinner.getSelectedItemPosition();
        Log.i(tag, "i:" + ispinner + " username:" + username + " password:" + password);
        if (TextUtils.isEmpty(username))
            showToast("账户不能为空");
        else if (TextUtils.isEmpty(password))
            showToast("密码不能为空不能为空");
        else if (!CommonUtil.isMobileNO(username))
            showToast("目前仅支持手机号登录");
        else if (!CommonUtil.isPwd(password))
            showToast("密码只能是6-8位的字母和数字组合");
        else {
            postLogin(username, password);
        }
    }

    private void postLogin(String username, String password) {
        final Map<String, String> request = new HashMap<>();
        request.put("mobile", username);
        request.put("password", password);
        new Thread() {
            @Override
            public void run() {
                super.run();
                String postJson = HttpUtil.post(ConstantsURL.USER_LOGIN_URL, request, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(postJson)) {
                    msg.what = SEND_NET_ERROR;
                } else if (postJson.indexOf("登录成功") != -1) {
                    saveCookie();
                    msg.what = LOGIN_SUCCESS;
                } else if (postJson.indexOf("用户名或密码错误") != -1) {
                    msg.what = LOGIN_ERROR;
                } else if (postJson.indexOf("您被禁止登录") != -1) {
                    msg.what = LOGIN_BAN;//登录禁止
                } else {
                    Log.i(tag, postJson);
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
                case SEND_NET_ERROR://网络异常
                    showToast("网络异常");
                    break;
                case LOGIN_SUCCESS:
                    startLogin();
                    break;
                case LOGIN_ERROR:
                    CommonUtil.showInfoDialog(LoginActivity.this,"账户密码错误");
                    break;
                case LOGIN_BAN:
                    CommonUtil.showInfoDialog(LoginActivity.this,"您的账号已被管理停用，如有疑问，请联系管理员");
                break;
                default:
                    break;
            }
        }
    };
    private void startLogin()
    {
        Bundle bundle = new Bundle();
        bundle.putInt("type",ispinner==0?1:-1);
        openActivity(HomeActivity.class, bundle);
        finish();

    }
}
