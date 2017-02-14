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
 * 个人店铺信息
 */

public class GRDPActivity extends BaseActivity implements RedTitleBar.ClickCallback, View.OnClickListener {
    private RedTitleBar titleBar;
    AutoRelativeLayout selectarea;
    TextView address;
    Button grdp_wangzhanjietu;
    private EditText gedp_et_name,gedp_et_phone,grdp_address,grdp_hangye,grdp_yingyeTime,grdp_dpjs;

    @Override
    public int setLayout() {
        storeConfig=new StoreConfig();
        return R.layout.activity_grdp;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleBar = $(R.id.grdp_TitleBar);
        selectarea = $(R.id.grdp_selectarea);
        address = $(R.id.grdp_tv_address);
        grdp_wangzhanjietu= $(R.id.grdp_wangzhanjietu);


        gedp_et_name= $(R.id.gedp_et_name);//店铺名称
        gedp_et_phone= $(R.id.gedp_et_phone);//店铺电话
        grdp_address= $(R.id.grdp_address);//网站地址
        grdp_hangye= $(R.id.grdp_hangye);//行业类型
        grdp_yingyeTime= $(R.id.grdp_yingyeTime);//营业时间
        grdp_dpjs= $(R.id.grdp_dpjs);//店铺介绍
    }

    @Override
    public void initDate() {
        titleBar.setClickCallback(this);
        titleBar.setTitle("个人店铺信息");
        selectarea.setOnClickListener(this);
        grdp_wangzhanjietu.setOnClickListener(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
    private  StoreConfig storeConfig;
    public void nextBtn(View view) {

       String dpName= gedp_et_name.getText().toString();
       String dpphone= gedp_et_phone.getText().toString();
       String dpwz= grdp_address.getText().toString();
       String dplx= grdp_hangye.getText().toString();
       String dptime= grdp_yingyeTime.getText().toString();
       String dpjs= grdp_dpjs.getText().toString();
        if (TextUtils.isEmpty(dpName)||TextUtils.isEmpty(dpphone)||TextUtils.isEmpty(dpwz)||
        TextUtils.isEmpty(dplx)||TextUtils.isEmpty(dptime)||TextUtils.isEmpty(dpjs))
            showToast("请先完成上面的所有要填写的项目");
        else if (TextUtils.isEmpty(sheng))
            showToast("请选择所在区域");
        else if (!storeConfig.isWZ())
            showToast("请上传网站截图");
        else {

            storeConfig.setType(1);
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
            openActivity(GRZZActivity.class,bundle);
        }
    }

   private  boolean isupload=false;
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.grdp_selectarea:
                openActivityForResult(AreaSelectActivity.class, null, 1);
                break;
            case R.id.grdp_wangzhanjietu:
                if (!isupload){
                    isupload=true;
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
                break;
            default:
                break;
        }

    }

    private String sheng;
    private String city;
    private String area;
    private Bitmap bitmap=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String result="";
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
                else {
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
//                        ImageView imageView = (ImageView) findViewById(R.id.iv01);
//                /* 将Bitmap设定到ImageView */
//                        imageView.setImageBitmap(bitmap);
                     InputStream inputStream= CommonUtil.Bitmap2InputStream(bitmap,60);
                        grdp_wangzhanjietu.setText("图片上传中...");
                        upFileThread(ConstantsStoreURL.STORE_UPSTOREPHOTO_URL,"storePaperPic",inputStream);

                    } catch (FileNotFoundException e) {
                        Log.e("Exception", e.getMessage(), e);
                    }

                }
                break;
        }
    }
    private static final int UP_ERROR=701;//上传失败
    private static final int UP_SUCCESS=801;//上传成功
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
                grdp_wangzhanjietu.setBackground(new BitmapDrawable( bitmap));
                grdp_wangzhanjietu.setText("上传成功");
                grdp_wangzhanjietu.setTextColor(getResources().getColor(R.color.colorRed));
                isupload=false;
                storeConfig.setWZ(true);
            	break;

            default:
            	break;
            }
        }
    };
}
