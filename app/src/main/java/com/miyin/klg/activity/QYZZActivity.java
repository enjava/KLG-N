package com.miyin.klg.activity;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.entity.StoreConfig;
import com.miyin.klg.util.CommonUtil;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.util.IdcardValidator;
import com.miyin.klg.util.LicenseNumberRegexUtil;
import com.miyin.klg.view.RedTitleBar;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by en on 2017/2/12.
 * 企业资质信息
 */

public class QYZZActivity extends BaseActivity implements RedTitleBar.ClickCallback, View.OnClickListener {
    private RedTitleBar titleBar;
    private Button qy_chengnuo, qy_handIDCard, qy_yingyezhizhao;
    private EditText qyzz_et_name,qyzz_et_idcard,qyzz_phone,qyzz_zzName,qyzz_zcid;
    @Override
    public int setLayout() {
        return R.layout.activity_qyzz;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.qyzz_TitleBar);
        titleBar.setClickCallback(this);

         //button
        qy_chengnuo = $(R.id.qyzz_chengnuo);
        qy_handIDCard = $(R.id.qyzz_handIDCard);
        qy_yingyezhizhao = $(R.id.qyzz_yingyezhizhao);


        qyzz_et_name = $(R.id.qyzz_et_name);
        qyzz_et_idcard = $(R.id.qyzz_et_idcard);
        qyzz_phone = $(R.id.qyzz_phone);
        qyzz_zzName = $(R.id.qyzz_zzName);
        qyzz_zcid = $(R.id.qyzz_zcid);


    }
    private StoreConfig storeConfig;
    @Override
    public void initDate() {
        titleBar.setTitle("资质信息");
        qy_chengnuo.setOnClickListener(this);
        qy_handIDCard.setOnClickListener(this);
        qy_yingyezhizhao.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            storeConfig = (StoreConfig) bundle.getSerializable("storeConfig");
    }
    public void finishBtn(View view) {
        String realName= qyzz_et_name.getText().toString();
        String idNum= qyzz_et_idcard.getText().toString();
        String phone= qyzz_phone.getText().toString();
        String  zzName= qyzz_zzName.getText().toString();
        String  zcid= qyzz_zcid.getText().toString();
        if (TextUtils.isEmpty(realName))
            showToast("法人姓名不能为空");
        else if (realName.length()<2)
            showToast("法人姓名不正确");
        else if (TextUtils.isEmpty(idNum))
            showToast("身份证号必填,请认真填写");
        else if (!IdcardValidator.isValidate18Idcard(idNum))
            showToast("身份证号不正确,请检查,无法提交");
        else if (TextUtils.isEmpty(phone))
            showToast("联系电话号必填不能为空,请认真填写");
        else if (phone.length()<11)
            showToast("联系电话号码输入不正确,请认真填写");
        else if (TextUtils.isEmpty(zzName))
            showToast("执照名称必填不能为空,请认真填写");
        else if (zzName.length()<5)
            showToast("执照名称输入不正确,请认真填写");
        else if (TextUtils.isEmpty(zcid))
            showToast("联系电话号必填不能为空,请认真填写");
        else if (zcid.length()<15)
            showToast("您输入的营业执照注册号有误，请核对后再输!");

        else if (!storeConfig.isHandID())
            showToast("还未上传手持身份证照,无法提交");
        else if (!storeConfig.isChengnuo())
            showToast("还未上传商家承诺书,无法提交");
        else if (!storeConfig.isLicence())
            showToast("营业执照还未上传");
        else {
            if (zcid.length()==15&&LicenseNumberRegexUtil.isLicense_15(zcid)) {

                storeConfig.setRealName(realName);
                storeConfig.setUserCard(idNum);
                storeConfig.setPhone(phone);
                storeConfig.setCompanyName(zzName);
                storeConfig.setLicenceNum(zcid);
                httpThread();
            }
           else if (zcid.length()==18&&LicenseNumberRegexUtil.isLicense_18(zcid)){
                storeConfig.setRealName(realName);
                storeConfig.setUserCard(idNum);
                storeConfig.setPhone(phone);
                storeConfig.setCompanyName(zzName);
                storeConfig.setLicenceNum(zcid);
                httpThread();
            }else {
                showToast("您输入的营业执照注册号有误，请核对后再输!");
            }
        }

    }


    private void httpThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                String json=  HttpUtil.post(ConstantsStoreURL.STORE_MODIFYSTOREINFO_URL,new String[]{
                        "type","storeName","storePhone","sheng","city","area","address", "businessType2", "shopHours", "storeInfo",
                        "realName", "userCard","phone","longitude","latitude","companyName","licenceNum"
                },new String[]{storeConfig.getType()+"",storeConfig.getStoreName(),storeConfig.getStorePhone(),storeConfig.getSheng(),
                        storeConfig.getCity(),storeConfig.getArea(),storeConfig.getAddress(),storeConfig.getBusinessType2(),storeConfig.getShopHours(),storeConfig.getStoreInfo(),
                        storeConfig.getRealName(),storeConfig.getUserCard(),storeConfig.getPhone(),"-1","-1",storeConfig.getCompanyName(),storeConfig.getLicenceNum()
                },mCookie);

                Message msg=Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what= HttpUtil.ERROR;
                else if (json.indexOf("操作成功")!=-1)
                    msg.what= HttpUtil.SUCEESS;
                else
                    msg.what = HttpUtil.ERROR;

                mHandler.sendMessage(msg);
            }
        }.start();
    }
    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    private static boolean isupload = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qyzz_handIDCard:
                if (!isupload) {

                    Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 11);
                }
                break;
            case R.id.qyzz_chengnuo:
                if (!isupload) {
                    Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 12);
                }
                break;
            case R.id.qyzz_yingyezhizhao:
                if (!isupload) {
                    Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 10);
                }
                break;
            default:
                break;
        }
    }

    private Bitmap bitmap = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == 11 || requestCode == 10 || requestCode == 12) {
                    isupload = true;
                    Uri uri = data.getData();
                    Log.e("uri", uri.toString());

                    String[] proja = {MediaStore.Images.Media.DATA};
                    Cursor actualimagecursor = getContentResolver().query(uri, proja, null, null, null);
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    String img_path = actualimagecursor.getString(actual_image_column_index);
                    if (!TextUtils.isEmpty(img_path))
                        Log.i("path", img_path);

                    ContentResolver cr = this.getContentResolver();
                    try {
                        bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        InputStream inputStream = CommonUtil.Bitmap2InputStream(bitmap, 60);
                        String urla = "";
                        String hand = "hand";
                        String key = "";
                        if (requestCode == 11) {
                            // 身份证
                            qy_handIDCard.setText("图片上传中...");
                            urla = ConstantsStoreURL.STORE_MODIFYSTORECARDPIC_URL;
                            key = "storeCardPic";

                        } else if (requestCode == 12) {
                            //承诺书
                            qy_chengnuo.setText("图片上传中...");
                            urla = ConstantsStoreURL.STORE_MODIFYSTOREPAPERPIC_URL;
                            key = "storePaperPic";
                            hand = "rePaper";
                        } else {
                            //营业执照
                            qy_yingyezhizhao.setText("图片上传中...");
                            urla = ConstantsStoreURL.STORE_MODIFYLICENCEIMG_URL;
                            key = "licenceImg";
                            hand = "qy";
                        }
                        upFileThread(urla, key, inputStream, hand);

                    } catch (FileNotFoundException e) {
                        Log.e("Exception", e.getMessage(), e);
                    }

                }else
                    isupload=false;
                break;
        }
    }

    private static final int UP_ERROR = 701;//上传失败
    private static final int UP_SUCCESS = 801;//上传成功
    private static final int UP_HAND_ERROR = 702;//上传失败
    private static final int UP_HAND_SUCCESS = 802;//上传成功
    private static final int UP_QY_ERROR = 703;//上传失败
    private static final int UP_QY_SUCCESS = 803;//上传成功

    private void upFileThread(final String url, final String storePaperPic, final InputStream inputStream, final String hand) {
        new Thread() {
            @Override
            public void run() {

                String json = HttpUtil.uploadFormFile(url, storePaperPic, storePaperPic + ".png", inputStream, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json)) {
                    if (hand.equals("hand"))
                        msg.what = UP_HAND_ERROR;
                    else if (hand.equals("rePaper"))
                        msg.what = UP_ERROR;
                    else
                        msg.what = UP_QY_ERROR;
                } else if (json.indexOf("操作成功") != -1) {
                    if (hand.equals("hand"))
                        msg.what = UP_HAND_SUCCESS;
                    else if (hand.equals("rePaper"))
                        msg.what = UP_SUCCESS;
                    else
                        msg.what = UP_QY_SUCCESS;
                } else {
                    if (hand.equals("hand"))
                        msg.what = UP_HAND_ERROR;
                    else if (hand.equals("rePaper"))
                        msg.what = UP_ERROR;
                    else
                        msg.what = UP_QY_ERROR;
                }
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UP_SUCCESS:
                    qy_chengnuo.setBackground(new BitmapDrawable(bitmap));
                    qy_chengnuo.setText("上传成功");
                    qy_chengnuo.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    storeConfig.setChengnuo(true);
                    break;
                case UP_HAND_SUCCESS:
                    qy_handIDCard.setBackground(new BitmapDrawable(bitmap));
                    qy_handIDCard.setText("上传成功");
                    qy_handIDCard.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    storeConfig.setHandID(true);
                    break;
                case UP_HAND_ERROR:
                    qy_handIDCard.setText("上传失败");
                    qy_handIDCard.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    break;
                case UP_ERROR:
                    qy_chengnuo.setText("上传失败");
                    qy_chengnuo.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    break;
                case UP_QY_SUCCESS:
                    qy_yingyezhizhao.setBackground(new BitmapDrawable(bitmap));
                    qy_yingyezhizhao.setText("上传成功");
                    qy_yingyezhizhao.setTextColor(getResources().getColor(R.color.colorRed));
                    storeConfig.setLicence(true);
                    isupload = false;
                    break;
                case UP_QY_ERROR:
                    qy_yingyezhizhao.setText("上传失败");
                    qy_yingyezhizhao.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    break;
                case HttpUtil.SUCEESS:

                    CommonUtil.showInfoDialog(QYZZActivity.this, "提交成功,等待审核通过", "提示", "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openActivity(HomeActivity.class);
                            finish();
                        }

                    });
                    break;
                case HttpUtil.ERROR:
                    showToast("提交失败");
                    break;
                default:
                    break;
            }
        }
    };
}
