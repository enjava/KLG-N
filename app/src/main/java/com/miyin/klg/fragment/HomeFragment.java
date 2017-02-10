package com.miyin.klg.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.activity.GRZLActivity;
import com.miyin.klg.activity.LoginActivity;
import com.miyin.klg.activity.PTSJActivity;
import com.miyin.klg.activity.STSJActivity;
import com.miyin.klg.activity.WDKBActivity;
import com.miyin.klg.activity.WYTJActivity;
import com.miyin.klg.activity.XFJLActivity;
import com.miyin.klg.activity.XYSActivity;
import com.miyin.klg.activity.ZNSMZDActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.customview.CircleImageview;
import com.miyin.klg.customview.CircleMenu;
import com.miyin.klg.entity.Store;
import com.miyin.klg.entity.User;
import com.miyin.klg.util.CommonUtil;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 主页 fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, CircleMenu.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private CircleMenu circleMenu;
    private TextView mTvjlkb;//奖励酷币
    private TextView mTvptkb;//普通酷币
    private TextView mTvjrjl;//今日激励
    private TextView mTvxinyuan;//心愿指数
    private TextView mTvuserid,mTvusername;//user id
    private TextView mTvriqi;//日期
    private CircleImageview mHeadImg;//用户头像
    private AutoRelativeLayout userInfoLayout;
    private String[] mItemTexts = new String[]{" 我的心愿盒 ", "    我的酷币  ",  "    我要推荐  ", "    消费记录  ", "    平台数据  ", "智能售卖终端", "    实体商家",
    };
    private int[] mItemImgs = new int[]{R.mipmap.xyh, R.mipmap.mykubi,
            R.drawable.wydz, R.drawable.xf, R.drawable.ptsj, R.drawable.hg, R.drawable.stsj
    };

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    private static final String TAG = "HomeFragment_log";

    @Override
    protected void initViewsAndEvents(View view) {
        circleMenu = $(R.id.circle_menu_items);
        circleMenu.setAngle(180f);
        userInfoLayout = $(R.id.userInfoLayout);

        circleMenu.setRotating(true);//是否启用旋转
        circleMenu.setItems(mItemTexts, mItemImgs);//显示文字及图标
        circleMenu.setIconSize(20);//图标大小，单位为dp
        circleMenu.setTextSize(16);
        circleMenu.setOnItemClickListener(this);
        mTvjlkb= $(R.id.tv_main_jlkb);//奖励酷币
        mTvptkb= $(R.id.tv_main_ptkb);//普通酷币
        mTvjrjl= $(R.id.tv_main_jrjl);//今日激励
        mTvxinyuan= $(R.id.tv_main_xinyuan);//心愿指数
        mTvuserid= $(R.id.tv_main_userid);//user id
        mTvusername= $(R.id.tv_main_username);//user id
        mTvriqi= $(R.id.tv_main_riqi);//日期
        mHeadImg= $(R.id.iv_main_CircleImageview);//user id

        userInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (fragmentApp.getUser() == null && fragmentApp.getStore()== null)
                        openActivity(LoginActivity.class);
                    else
                        openActivity(GRZLActivity.class);

            }
        });
    }

    private User user;
    private Store store;
    @Override
    protected void initData(){
        mTvriqi.setText(CommonUtil.formatDate("(yyyy-MM-dd)"));
        user= fragmentApp.getUser();
        store= fragmentApp.getStore();
        if (user!=null) {
            mTvuserid.setText(user.data.userCode+"");
            if (!TextUtils.isEmpty(user.data.username)){
                mTvusername.setText(user.data.username);
            }
        }else if (store!=null){
            mTvuserid.setText(store.data.userCode+"");
            if (!TextUtils.isEmpty(store.data.realName)){
                mTvusername.setText(store.data.realName);
            }
        }
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void DetoryViewAndThing() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userInfoLayout:
                openActivity(GRZLActivity.class);
                break;
        }
    }

    public int index;

    @Override
    public void onItemClick(CircleMenu.ItemView view) {
            for (int i = 0; i < mItemTexts.length; i++) {
                if (mItemTexts[i].equals(view.getText())) {
                    index = i;
                }
            }
            switch (index) {
                //我的幸运树
                case 0:
                    openActivity(XYSActivity.class);
                    break;
                //酷币
                case 1:
                    openActivity(WDKBActivity.class);
                    break;
//                //回购
//                case 2:
//                    openActivity(HGActivity.class);
//                    break;
//                //转增
//                case 3:
//                    openActivity(ZZActivity.class);
//                    break;
                //我要推荐
                case 2:
                    openActivity(WYTJActivity.class);
                    break;
                //消费记录
                case 3:
                    openActivity(XFJLActivity.class);
                    break;
                //平台数据
                case 4:
                    openActivity(PTSJActivity.class);
                    break;
                //智能售卖终端
                case 5:
                    openActivity(ZNSMZDActivity.class);
                    break;
                //实体商家
                case 6:
                    openActivity(STSJActivity.class);
                    break;
                default:
                    break;
        }
    }
}
