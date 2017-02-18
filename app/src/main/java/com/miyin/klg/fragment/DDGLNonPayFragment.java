package com.miyin.klg.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.DDGLDetailsActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.util.ConstantsStoreURL;
import com.miyin.klg.util.Http;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 订单管理 未付款fragment.
 */
public class DDGLNonPayFragment extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView fj_RecyclerView;
    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("a", "b", "c", "d", "f", "g", "h", "i", "j", "k", "l", "m", "n", "c", "k", "l", "m", "n", "c", "k", "l", "m", "n", "c", "k", "l", "m", "n", "c");

    public static DDGLNonPayFragment newInstance(String param1) {
        DDGLNonPayFragment fragment = new DDGLNonPayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ddgl;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        fj_RecyclerView = $(R.id.fj_RecyclerView);
        fj_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_ddgl, list) {
            @Override
            protected void convert(ViewHolder holder, final String s, final int position) {
                //Log.e("CommonAdapter", s + "position" + position);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(DDGLDetailsActivity.class);
                    }
                });

            }
        };
        fj_RecyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                openActivity(DDGLDetailsActivity.class);
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
    }


    @Override
    protected void onFirstUserVisible() {
        Http.instance().post(ConstantsStoreURL.STORE_ORDERLIST_URL, fragmentApp.mSession, new Http.OnHttpListenerProxy() {
            @Override
            public void setData(Http.HttpData httpData) {
//                httpData.put("pageSize", "30");
//                httpData.put("isDel", "1");
//                httpData.put("checked", "2");//

            }

            @Override
            public void onSuccess(Http.HttpSession session, String json) {
                Log.e(TAG, json);
            }

            @Override
            public void onFail(String json) {
                Log.e(TAG, "onFail"+json);
            }
        });
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


}
