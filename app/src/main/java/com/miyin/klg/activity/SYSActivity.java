package com.miyin.klg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.zxing.activity.CaptureActivity;

public class SYSActivity extends BaseActivity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    /**
     * 显示扫描结果
     */
    private TextView mTextView ;
    /**
     * 显示扫描拍的图片
     */
    private ImageView mImageView;
    @Override
    public int setLayout() {
        return R.layout.activity_sys;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
        Intent intent = new Intent();
        intent.setClass(SYSActivity.this, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
        //扫描完了之后调到该界面
        Button mButton = (Button) findViewById(R.id.button1);
        Button mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SYSActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // openActivityForResult(intent,null,SCANNIN_GREQUEST_CODE);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    @Override
    public void initDate() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    mTextView.setText(bundle.getString("result"));
                    //显示
                    //mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }
}
