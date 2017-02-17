package com.miyin.klg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.miyin.klg.R;
import com.miyin.klg.base.BaseFragment;

/**
 * 付款 fragment.
 */
public class FKFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";

    public static FKFragment newInstance(String param1, String param2) {
        FKFragment fragment = new FKFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        Log.i("FKFragment","getContentViewLayoutID");
        return R.layout.fragment_fk;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        Log.i("FKFragment","initViewsAndEvents");
    }
    @Override
    protected void onFirstUserVisible() {
        Log.i("FKFragment","onFirstUserVisible");
    }

    @Override
    protected void onUserVisible() {
        Log.i("FKFragment","onUserVisible");
    }

    @Override
    protected void onUserInvisible() {
        Log.i("FKFragment","onUserInvisible");
    }

    @Override
    protected void DetoryViewAndThing() {
        Log.i("FKFragment","DetoryViewAndThing");
    }

}
