package com.miyin.klg.activity;

import android.annotation.TargetApi;
import android.content.ContentResolver;
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
import com.miyin.klg.view.RedTitleBar;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by en on 2017/2/12.
 * 个人资质信息
 */

public class GRZZActivity extends BaseActivity implements RedTitleBar.ClickCallback, View.OnClickListener {
    private RedTitleBar titleBar;
    private Button grzz_chengnuo, grzz_handIDCard;
    EditText gedp_et_name, gedp_et_idNum, gedp_phone;

    @Override
    public int setLayout() {
        return R.layout.activity_grzz;
    }
    private StoreConfig storeConfig;
    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.grzz_TitleBar);
        grzz_chengnuo = $(R.id.grzz_chengnuo);
        grzz_handIDCard = $(R.id.grzz_handIdCard);


        gedp_et_name = $(R.id.gedp_et_name);//身份证姓名
        gedp_et_idNum = $(R.id.gedp_et_idNum);//身份证号
        gedp_phone = $(R.id.gedp_phone);//电话

    }

    public void finishBtn(View view) {
        String realName= gedp_et_name.getText().toString();
        String idNum= gedp_et_idNum.getText().toString();
        String phone= gedp_phone.getText().toString();

        if (TextUtils.isEmpty(realName)||TextUtils.isEmpty(idNum)||TextUtils.isEmpty(phone) )
            showToast("您还没有完成上面的所有要填写的项目,无法提交");
        else if (IdcardValidator.isValidate18Idcard(idNum))
            showToast("身份证号不正确,请检查,无法提交");
        else if (!storeConfig.isHandID())
            showToast("还未上传手持身份证照,无法提交");
        else if (!storeConfig.isChengnuo())
            showToast("还未上传商家承诺书,无法提交");
        else {
            storeConfig.setRealName(realName);
            storeConfig.setUserCard(idNum);
            storeConfig.setPhone(phone);
            httpThread();
        }

    }


    private void httpThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();
              String json=  HttpUtil.post(ConstantsStoreURL.STORE_MODIFYSTOREINFO_URL,new String[]{
                        "type","storeName","storePhone","sheng","city","area","address", "businessType2", "shopHours", "storeInfo", "realName", "userCard","phone"
                },new String[]{storeConfig.getType()+"",storeConfig.getStoreName(),storeConfig.getStorePhone(),storeConfig.getSheng(),
                      storeConfig.getCity(),storeConfig.getArea(),storeConfig.getAddress(),storeConfig.getBusinessType2(),storeConfig.getShopHours(),storeConfig.getStoreInfo(),
                      storeConfig.getRealName(),storeConfig.getUserCard(),storeConfig.getPhone()

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
    public void initDate() {
        titleBar.setTitle("资质信息");
        titleBar.setClickCallback(this);
        grzz_chengnuo.setOnClickListener(this);
        grzz_handIDCard.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            storeConfig = (StoreConfig) bundle.getSerializable("storeConfig");

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
            case R.id.grzz_handIdCard:
                if (!isupload) {
                    isupload = true;
                    Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 11);
                }
            case R.id.grzz_chengnuo:
                if (!isupload) {
                    isupload = true;
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

                if (requestCode == 11 || requestCode == 10) {
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
                            grzz_handIDCard.setText("图片上传中...");
                            urla = ConstantsStoreURL.STORE_MODIFYSTORECARDPIC_URL;
                            key = "storeCardPic";

                        } else {
                            grzz_chengnuo.setText("图片上传中...");
                            urla = ConstantsStoreURL.STORE_MODIFYSTOREPAPERPIC_URL;
                            key = "storePaperPic";
                            hand = "rePaper";
                        }
                        upFileThread(urla, key, inputStream, hand);

                    } catch (FileNotFoundException e) {
                        Log.e("Exception", e.getMessage(), e);
                    }

                }
                break;
        }
    }

    private static final int UP_ERROR = 701;//上传失败
    private static final int UP_SUCCESS = 801;//上传成功
    private static final int UP_HAND_ERROR = 702;//上传失败
    private static final int UP_HAND_SUCCESS = 802;//上传成功

    private void upFileThread(final String url, final String storePaperPic, final InputStream inputStream, final String hand) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtil.uploadFormFile(url, storePaperPic, storePaperPic + ".png", inputStream, mCookie);
                Message msg = Message.obtain();
                if (TextUtils.isEmpty(json)) {
                    if (hand.equals("hand"))
                        msg.what = UP_HAND_ERROR;
                    else
                        msg.what = UP_ERROR;
                } else if (json.indexOf("操作成功") != -1)
                    if (hand.equals("hand"))
                        msg.what = UP_HAND_SUCCESS;
                    else
                        msg.what = UP_SUCCESS;
                else if (hand.equals("hand"))
                    msg.what = UP_HAND_ERROR;
                else
                    msg.what = UP_ERROR;
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
                    grzz_chengnuo.setBackground(new BitmapDrawable(bitmap));
                    grzz_chengnuo.setText("上传成功");
                    grzz_chengnuo.setTextColor(getResources().getColor(R.color.colorRed));
                    storeConfig.setChengnuo(true);
                    isupload = false;
                    break;
                case UP_HAND_SUCCESS:
                    grzz_handIDCard.setBackground(new BitmapDrawable(bitmap));
                    grzz_handIDCard.setText("上传成功");
                    grzz_handIDCard.setTextColor(getResources().getColor(R.color.colorRed));
                    storeConfig.setHandID(true);
                    isupload = false;
                    break;
                case UP_HAND_ERROR:
                    grzz_handIDCard.setText("上传失败");
                    grzz_handIDCard.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    break;
                case UP_ERROR:
                    grzz_chengnuo.setText("上传失败");
                    grzz_chengnuo.setTextColor(getResources().getColor(R.color.colorRed));
                    isupload = false;
                    break;
                case HttpUtil.SUCEESS:
                    showToast("提交成功,等待审核通过");
                    openActivity(HomeActivity.class);
                     finish();
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
