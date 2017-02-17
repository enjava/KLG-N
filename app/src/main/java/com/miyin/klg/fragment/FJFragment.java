package com.miyin.klg.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.activity.HomeActivity;
import com.miyin.klg.base.BaseFragment;
import com.miyin.klg.util.StatusBarUtil;
import com.miyin.klg.view.RedQRTitleBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 付款 fragment.
 */
public class FJFragment extends BaseFragment implements RedQRTitleBar.ClickCallback {
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView fj_RecyclerView;

    private CommonAdapter adapter = null;
    List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "");
    private RedQRTitleBar titleBar;
    public static FJFragment newInstance(String param1, String param2) {
        FJFragment fragment = new FJFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_fj;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        titleBar=$(R.id.tab_title);

        titleBar.setTitle("附近");
        titleBar.setClickCallback(this);
        fj_RecyclerView = $(R.id.fj_RecyclerView);
        fj_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_znsmzd, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setVisible(R.id.item_znsmzdHintLayout, position == 0 ? true : position == 4 ? true : false);
                holder.setVisible(R.id.item_znsmzdAddressFJIV, position > 3 ? true : false);
                holder.setText(R.id.item_znsmzdHint, position == 0 ? "最近使用" : position == 4 ? "附近" : "");
            }
        };
        fj_RecyclerView.setAdapter(adapter);
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
    public void onBackClick() {
        StatusBarUtil.StatusBarLightMode(mActivity);
        ((HomeActivity)mActivity).getMainNavigateTabBar().setCurrentSelectedTab(0);
    }

    @Override
    public void onRightClick() {

    }
}
