package com.miyin.klg.activity;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.cascade.activity.AreaSelectActivity;
import com.miyin.klg.entity.StoreConfig;
import com.miyin.klg.util.CommonUtil;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.view.RedTitleBar;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by en on 2017/2/11.
 * 企业店铺信息
 */

public class QYDPActivity extends BaseActivity implements RedTitleBar.ClickCallback ,View.OnClickListener {
    private RedTitleBar titleBar;
    private AutoRelativeLayout selectarea;
    private Button qydp_mm;
    private EditText qydp_et_name,qydp_et_phone,qydp_address,qrdp_hangye,qrdp_yingyeTime,qrdp_dpjs;

    protected static Activity instance = null;
    @Override
    public int setLayout() {
        instance=this;
        storeConfig=new StoreConfig();
        storeConfig.setWZ(false);
        storeConfig.setHandID(false);
        storeConfig.setLicence(false);
        storeConfig.setChengnuo(false);
        return R.layout.activity_qydp;
    }
    private StoreConfig storeConfig;
    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar=  $(R.id.qydp_TitleBar);
        address = $(R.id.qydp_tv_address);
        selectarea = $(R.id.qydp_selectarea);
        qydp_mm = $(R.id.qydp_mm);


        qydp_et_name = $(R.id.qydp_et_name);
        qydp_et_phone = $(R.id.qydp_et_phone);
        qydp_address = $(R.id.qydp_address);
        qrdp_hangye = $(R.id.qrdp_hangye);
        qrdp_yingyeTime = $(R.id.qrdp_yingyeTime);
        qrdp_dpjs = $(R.id.qrdp_dpjs);
    }

    @Override
    public void initDate() {
        titleBar.setClickCallback(this);
        titleBar.setTitle("企业店铺信息");
        selectarea.setOnClickListener(this);
        qydp_mm.setOnClickListener(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void nextBtn(View view){

        String dpName= qydp_et_name.getText().toString();
        String dpphone= qydp_et_phone.getText().toString();
        String dpwz= qydp_address.getText().toString();
        String dplx= qrdp_hangye.getText().toString();
        String dptime= qrdp_yingyeTime.getText().toString();
        String dpjs= qrdp_dpjs.getText().toString();

//        if (TextUtils.isEmpty(dpName)  ||TextUtils.isEmpty(dpphone)||TextUtils.isEmpty(dpwz)||
//                TextUtils.isEmpty(dplx)||TextUtils.isEmpty(dptime)||TextUtils.isEmpty(dpjs))
//            showToast("请先完成上面的所有要填写的项目");
        if (TextUtils.isEmpty(dpName))
            showToast("店铺名称为必填");
        else if (dpName.length()<3)
            showToast("店铺名称输入格式不正确,请认真填写");
        else if (TextUtils.isEmpty(dpphone))
            showToast("店铺电话号码必填,不能为空");
        else if (dpphone.length()<11)
            showToast("店铺电话号码不正确,请认真填写");
        else if (TextUtils.isEmpty(dpwz))
            showToast("详细地址必填,不能为空");
        else if (dpwz.length()<4)
            showToast("详细地址不正确,请认真填写");
        else if (TextUtils.isEmpty(dplx))
            showToast("所属行业必填,不能为空");
        else if (dplx.length()<2)
            showToast("所属行业不正确,请认真填写");
        else if (TextUtils.isEmpty(dptime))
            showToast("营业时间必填,不能为空");
        else if (TextUtils.isEmpty(dpjs))
            showToast("店铺介绍");
        else if (dpjs.length()<7)
            showToast("店铺介绍不能少于7个字");
        else if (TextUtils.isEmpty(sheng))
            showToast("请选择所在区域");
        else if (!storeConfig.isWZ())
            showToast("请上传门店图");
        else {

            storeConfig.setType(0);
            storeConfig.setStoreName(dpName);//店铺名称
            storeConfig.setStorePhone(dpphone);//店铺电话
            storeConfig.setAddress(dpwz);//网站地址
            storeConfig.setBusinessType2(dplx);//行业类型
            storeConfig.setShopHours(dptime);//营业时间
            storeConfig.setStoreInfo(dpjs);//店铺介绍
            storeConfig.setSheng(sheng);
            storeConfig.setCity(city);
            storeConfig.setArea(area);
            Bundle bundle = new Bundle();
            bundle.putSerializable("storeConfig", storeConfig);
            openActivity(QYZZActivity.class,bundle);
        }
    }


    private String sheng;
    private String city;
    private String area;
    private TextView address;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String result ="";
                if (bundle!=null)
                    result = bundle.getString("areaselect");
                if (!TextUtils.isEmpty(result) && result.indexOf(",") != -1) {
                    String[] results = result.split(",");
                    if (results.length > 2) {
                        sheng = results[0];
                        city = results[1];
                        area = results[2];
                        address.setText(sheng + city + area);
                    }
                }
                else if (requestCode==11){
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
                            InputStream inputStream= CommonUtil.Bitmap2InputStream(bitmap,60);
                            qydp_mm.setText("图片上传中...");
                            upFileThread(ConstantsStoreURL.STORE_UPSTOREPHOTO_URL,"storePaperPic",inputStream);

                        } catch (FileNotFoundException e) {
                            Log.e("Exception", e.getMessage(), e);
                        }

                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.qydp_selectarea:
            openActivityForResult(AreaSelectActivity.class);
        	break;
            case R.id.qydp_mm:
                if (!isupload){

                    Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 11);
                }
                break;
        default:
        	break;
        }
    }
    private Bitmap bitmap=null;
    private static final int UP_ERROR=701;//上传失败
    private static final int UP_SUCCESS=801;//上传成功
    private  static boolean isupload=false;
    private void upFileThread(final String url,final String storePaperPic, final InputStream inputStream) {
        new Thread(){
            @Override
            public void run() {
                String json=   HttpUtil.uploadFormFile(url,storePaperPic,storePaperPic+".png",inputStream,mCookie);
                Message msg=Message.obtain();
                if (TextUtils.isEmpty(json))
                    msg.what=UP_ERROR;
                else if (json.indexOf("操作成功")!=-1)
                    msg.what=UP_SUCCESS;
                else
                    msg.what=UP_ERROR;
                mHandler.sendMessage(msg);

            }
        }.start();
    }
    private Handler mHandler=new Handler(){
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UP_SUCCESS:
                    qydp_mm.setBackground(new BitmapDrawable( bitmap));
                    qydp_mm.setText("上传成功");
                    qydp_mm.setTextColor(getResources().getColor(R.color.colorRed));
                    storeConfig.setWZ(true);
                    break;
                case UP_ERROR:
                    qydp_mm.setText("上传失败");
                    qydp_mm.setTextColor(getResources().getColor(R.color.colorRed));
                    break;
                default:
                    break;
            }
        }
    };

}
