package com.miyin.klg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.customview.BlackTitleBar;
import com.miyin.klg.entity.RecommList;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;

import java.util.Arrays;
import java.util.List;


/**
 * Created by en on 2017/2/13.
 * 推荐记录
 */

public class RecommendedRecordsActivity extends BaseActivity implements BlackTitleBar.ClickCallback {
    private BlackTitleBar mTitleBar;
    private ListView rrListView;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");

    @Override
    public int setLayout() {
        return R.layout.activity_recommendedrecords;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTitleBar = $(R.id.titleBar);
        rrListView = $(R.id.rr_listview);
        if (mApp.getUser()!=null){
            httpThread();
        }
    }

    @Override
    public void initDate() {
        mTitleBar.setTitle("推荐记录");
        mTitleBar.setClickCallback(this);
//        adapter = new CommonAdapter<String>(RecommendedRecords.this, R.layout.item_recommrecord, list) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//            }
//        };
        //recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                openActivity(RecommendedRecords.class);
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    private RecommList recommList;

    class Madapter extends BaseAdapter {

        @Override
        public int getCount() {
            // 条目的总数
            return recommList.data.list.size();
        }

        @Override
        public Object getItem(int position) {
            return recommList.data.list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.item_recommrecord, null);
            TextView tv_name = (TextView) view.findViewById(R.id.recomm_Name);
            TextView tv_price = (TextView) view.findViewById(R.id.recomm_time);
           // ImageView iv_icon = (ImageView) view.findViewById(R.id.gridview_item_iv);

            tv_price.setText(recommList.data.list.get(position).createTime);
            //tv_price.setText("2016-12-24 18:22:49");
           String usrname=recommList.data.list.get(position).username;
            if (!TextUtils.isEmpty(usrname))
              tv_name.setText(usrname);
            else
                tv_name.setText("未知");
            //double d = channelInfos.get(mProductChnanel[position]).getPrice();
//            d = d / 100;
//            tv_price.setText("货道[" + mProductChnanel[position] + "]:" + d + "元");
//            iv_icon.setBackgroundResource(mProductID[position]);
            return view;
        }
    }
    private static final int SUCCESSLIST=600;
    public void httpThread() {
        new Thread() {
            @Override
            public void run() {
                String url = ConstantsURL.USER_MYRECOMMENDLIST_URL;
                super.run();
                String josn = HttpUtil.post(url, new String[]{"pageSize"}, new String[]{"50"}, mCookie);
                if (!TextUtils.isEmpty(josn)&&josn.indexOf("操作成功")!=-1){
                    Gson gson=new Gson();
                    recommList = gson.fromJson(josn, RecommList.class);
                    if (recommList.data.list.size()>0){
                        Message msg=Message.obtain();
                        msg.what=SUCCESSLIST;
                        mHandler.sendMessage(msg);
                    }
                }
            }
        }.start();
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case SUCCESSLIST:
                rrListView.setAdapter(new Madapter());
            	break;

            default:
            	break;
            }
        }
    };
}
