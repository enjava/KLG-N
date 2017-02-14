package com.miyin.klg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.miyin.klg.R;
import com.miyin.klg.util.SerializableMap;
import com.miyin.klg.wheel.widget.OnWheelChangedListener;
import com.miyin.klg.wheel.widget.WheelView;
import com.miyin.klg.wheel.widget.adapters.ArrayWheelAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by en on 2017/2/13.
 */
public class SelectBankDialog extends Activity implements OnWheelChangedListener, View.OnClickListener {
    private WheelView mWheelView;
    /**
     * 银行列表
     */
    protected static String[] mBankNameDatas;
    protected static String[] mBankIdDatas;
    private Button btn_bank_select;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectbank);
        initView(savedInstanceState);
        initDate();
    }


    public void initView(Bundle savedInstanceState) {
        mWheelView = (WheelView) findViewById(R.id.id_bank);
        btn_bank_select = (Button) findViewById(R.id.btn_bank_select);
    }

    public void initDate() {
        btn_bank_select.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SerializableMap datas = null;
        if (bundle != null)
            datas = (SerializableMap) bundle.getSerializable("mBankNameDatas");
        if (datas != null) {
            mBankIdDatas = datas.getmBankIdDatas();
            mBankNameDatas = datas.getmBankNameDatas();

            mWheelView.addChangingListener(SelectBankDialog.this);
            mWheelView.setViewAdapter(new ArrayWheelAdapter<String>(SelectBankDialog.this, mBankNameDatas));
            if (mBankNameDatas.length > 7)
                mWheelView.setVisibleItems(7);

            Map<String, String> map = new HashMap();
            map.put("id", mBankIdDatas[0]);
            map.put("name", mBankNameDatas[0]);
            serializableMap = new SerializableMap();
            serializableMap.setMap(map);
        }


    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    private SerializableMap serializableMap;

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel.equals(mWheelView)) {
            int pCurrent = mWheelView.getCurrentItem();
            String name = mBankNameDatas[pCurrent];
            String id = mBankIdDatas[pCurrent];
            Map<String, String> map = new HashMap();
            map.put("id", id);
            map.put("name", name);
            serializableMap = new SerializableMap();
            serializableMap.setMap(map);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bank_select:
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("bank", serializableMap);
                resultIntent.putExtras(bundle);
                this.setResult(RESULT_OK, resultIntent);
                finish();
                break;

            default:
                break;
        }
    }

}
