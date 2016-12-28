package com.miyin.klg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miyin.klg.R;
import com.miyin.klg.activity.DDGLActivity;
import com.miyin.klg.activity.SPGLActivity;
import com.miyin.klg.activity.XFDJActivity;
import com.miyin.klg.activity.YYEJLActivity;
import com.miyin.klg.base.BaseFragment;

/**
 * 商家管理fragment.
 */
public class SJGLFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static SJGLFragment newInstance(String param1, String param2) {
        SJGLFragment fragment = new SJGLFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_sjgl;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        $(R.id.sjgl_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SPGLActivity.class);
            }
        });
        $(R.id.sjgl_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(XFDJActivity.class);
            }
        });
        $(R.id.sjgl_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(DDGLActivity.class);
            }
        });
        $(R.id.sjgl_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(YYEJLActivity.class);
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
