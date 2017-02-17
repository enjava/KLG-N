package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.entity.BankList;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.util.SerializableMap;
import com.miyin.klg.view.RedQRTitleBar;

import static com.miyin.klg.util.HttpUtil.SUCEESS;

/**
 * Created by en on 2017/2/13.
 * 绑定银行卡
 */

public class AddBankCardActivity extends BaseActivity implements RedQRTitleBar.ClickCallback, View.OnClickListener {
    RedQRTitleBar titleBar;
    TextView cardRealName, add_yinhang;
    private EditText cardId;
    RelativeLayout selectBank;
    protected static String[] mBankNameDatas ;
    protected static String[] mBankIdDatas ;

    @Override
    public int setLayout() {
        return R.layout.activity_addbankcard;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.titleBar);
        titleBar.setTitle("绑定银行卡");
        titleBar.setClickCallback(this);

        cardRealName = $(R.id.add_cardRealName);
        cardId = $(R.id.add_cardId);
        add_yinhang = $(R.id.add_yinhang);
        selectBank = $(R.id.add_selectBank);


        $(R.id.add_btn).setOnClickListener(this);
    }

    private String realName;

    @Override
    public void initDate() {
        if (mApp.getStore() != null) {
            realName = mApp.getStore().data.realName;
            selectBank.setOnClickListener(this);
            httpThread(null);
        } else if (mApp.getUser() != null) {
            realName = mApp.getUser().data.realName;
            selectBank.setOnClickListener(this);
            httpThread(null);
        }
        if (!TextUtils.isEmpty(realName))
            cardRealName.setText(realName);

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
            case R.id.add_btn:
                if (!TextUtils.isEmpty(realName))
                    addBankCard();
                break;
            case R.id.add_selectBank:
                Bundle mBundle = new Bundle();

                if (mBankIdDatas!=null&&mBankIdDatas.length>1) {
                    SerializableMap datas=new SerializableMap();
                    datas.setmBankIdDatas(mBankIdDatas);
                    datas.setmBankNameDatas(mBankNameDatas);
                    mBundle.putSerializable("mBankNameDatas", datas);
                }
                openActivityForResult(SelectBankDialog.class,mBundle,1);
                break;
            default:
                break;
        }
    }

    private void addBankCard() {
        String cardid = cardId.getText().toString();
        String cardBank = add_yinhang.getText().toString();
        if (TextUtils.isEmpty(cardid))
            showToast("银行卡号不能为空");
        else if (TextUtils.isEmpty(cardBank))
            showToast("开户行不能为空");
        else if (cardid.length() < 9)
            showToast("银行卡号不正确");
        else if (!cardBank.equals(bankName))
            showToast("请选择开户行");
        else {
            httpThread(cardid);
        }
    }

  private   SerializableMap serializableMap;
  private   String bankId = "";
   private String bankName = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                // 获取该Intent所携带的数据
                Bundle bundle = data.getExtras();
                // 从bundle数据包中取出数据
                if (bundle != null) {
                    serializableMap = (SerializableMap) bundle.getSerializable("bank");
                    if (serializableMap != null && serializableMap.getMap() != null) {
                        bankId = serializableMap.getMap().get("id");
                        bankName = serializableMap.getMap().get("name");
                        add_yinhang.setText(bankName);
                    }
                }
//                Bundle bundle = data.getExtras();
//                String result = bundle.getString("bank");
//                if (!TextUtils.isEmpty(result)) {
//                    add_yinhang.setText(result);
//                }
                break;

            default:
                break;
        }
    }


    private void httpThread(final String cardid) {
     if (!TextUtils.isEmpty(cardid)) {
         new Thread() {
             @Override
             public void run() {

                 String url = ConstantsURL.USER_BINDBANK_URL;
                 if (mApp.getStore() != null)
                     url = ConstantsStoreURL.STORE_BINDBANK_URL;
                 String json = HttpUtil.post(url, new String[]{"bankId", "bankNumber", "username"}, new String[]{bankId, cardid, realName}, mCookie);
                 Message msg = Message.obtain();
                 if (TextUtils.isEmpty(json))
                     msg.what = HttpUtil.ERROR;
                 else if (json.indexOf("操作成功") != -1) {
                     msg.what = SUCEESS;
                 } else
                     msg.what = HttpUtil.ERROR;
                 mHandler.sendMessage(msg);

             }
         }.start();
     }else {
         new Thread() {
             @Override
             public void run() {
                 String url = ConstantsURL.USER_BANKLIST_URL;
                 String json = HttpUtil.post(url, new String[]{"isDel"}, new String[]{"0"}, mCookie);
                 Message msg = Message.obtain();
                 if (TextUtils.isEmpty(json))
                     msg.what = HttpUtil.ERROR;
                 else if (json.indexOf("操作成功") != -1) {
                     msg.what = HttpUtil.SUCEESS;
                     Gson gson = new Gson();
                     BankList bankList = gson.fromJson(json, BankList.class);
                     if (bankList.data.size() > 0) {
                         mBankNameDatas = new String[bankList.data.size()];
                         mBankIdDatas = new String[bankList.data.size()];
                         for (int i = 0; i < bankList.data.size(); i++) {
                             mBankNameDatas[i] = bankList.data.get(i).bankName;
                             mBankIdDatas[i] = bankList.data.get(i).bankId + "";
                         }
                     }
                 } else
                     msg.what = HttpUtil.ERROR;
                 //mHandler.sendMessage(msg);

             }
         }.start();
     }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCEESS:
                    showToast("保存成功");
                    finish();
                    break;
                case HttpUtil.ERROR:
                    break;
                default:
                    break;
            }
        }
    };
}
