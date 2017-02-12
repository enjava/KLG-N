package com.miyin.klg.fragment;

import android.view.View;
import android.widget.Button;

import com.miyin.klg.R;
import com.miyin.klg.activity.QYDPActivity;
import com.miyin.klg.base.BaseFragment;

/**
 * Created by en on 2017/2/11.
 */

public class QYFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_qy;
    }

    @Override
    protected void initViewsAndEvents(View view) {

        $(R.id.btn_qy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(QYDPActivity.class);
            }
        });
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
}
