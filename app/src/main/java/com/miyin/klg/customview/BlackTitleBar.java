package com.miyin.klg.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miyin.klg.R;


/**
 * 白色主题Toolbar
 *
 * @author
 */
public class BlackTitleBar extends RelativeLayout implements OnClickListener {

    private RelativeLayout layout;

    public BlackTitleBar(Context context) {
        this(context, null);
    }

    private ImageView leftView, mRightImage;
    private TextView titleView;
    private TextView rightView;
    private LinearLayout mLeftLayout;
    private RelativeLayout mRightLayout;
    private View titltBar_linear;

    public BlackTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_titlebar, this, true);
        leftView = (ImageView) view.findViewById(R.id.head_left);
        titleView = (TextView) view.findViewById(R.id.head_title);
        rightView = (TextView) view.findViewById(R.id.head_right);
        mRightImage = (ImageView) view.findViewById(R.id.head_right_image);
        mLeftLayout = (LinearLayout) view.findViewById(R.id.titlebar_while_back);
        mRightLayout = (RelativeLayout) view.findViewById(R.id.titlebar_while_right);
        titltBar_linear = view.findViewById(R.id.titltBar_linear);
        layout = (RelativeLayout) view.findViewById(R.id.titleBar);
        mLeftLayout.setOnClickListener(this);
        mRightLayout.setOnClickListener(this);
    }

    /**
     * @return
     */
    public void setBackground(int id) {
        layout.setBackgroundResource(id);
    }

    /**
     * 获取返回按钮
     *
     * @return
     */
    public ImageView getBackView() {
        return leftView;
    }

    /**
     * 为左边按钮设置可变资源
     *
     * @param resid
     */
    public void setLeftDrawable(int resid) {
        leftView.setBackgroundResource(resid);
    }

    /**
     * 为右边按钮设置图片资源
     *
     * @param resid
     */
    public void setRightDrawable(int resid) {
        mRightImage.setBackgroundResource(resid);
        mRightImage.setVisibility(View.VISIBLE);
        rightView.setVisibility(View.GONE);
    }

    /**
     * 设置左边按钮不显示
     */
    public void setLeftGone() {
        leftView.setVisibility(View.GONE);
    }


    /**
     * 设置右边按钮不显示
     */
    public void setRightGone() {
        mRightLayout.setVisibility(View.GONE);
    }
    /**
     * 设置左边按钮不显示
     */
    public void setLeftVisible() {
        leftView.setVisibility(View.VISIBLE);
    }


    /**
     * 设置右边按钮不显示
     */
    public void setRightVisible() {
        mRightLayout.setVisibility(View.VISIBLE);
    }
    /**
     * 获取标题控件
     *
     * @return
     */
    public TextView getTitleView() {
        return titleView;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleView.setText(title);
    }

    /**
     * 设置标题
     *
     */
    public void setTitleColor(int color) {
        titleView.setTextColor(color);
    }

    /**
     * 设置标题栏下面的横线隐藏
     */
    public void setLinearGone() {
        titltBar_linear.setVisibility(View.GONE);
    }

    /**
     * 获取右侧按钮,默认不显示(TextView)
     *
     * @return
     */
    public TextView getRightView() {
        return rightView;
    }

    public ImageView getRightImage() {
        return mRightImage;
    }

    private ClickCallback callback;

    /**
     * 设置按钮点击回调接口
     *
     * @param callback
     */
    public void setClickCallback(ClickCallback callback) {
        this.callback = callback;
    }


    /**
     * 导航栏点击回调接口 </br>如若需要标题可点击,可再添加
     *
     * @author Asia
     */
    public static interface ClickCallback {
        /**
         * 点击返回按钮回调
         */
        void onBackClick();

        void onRightClick();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.titlebar_while_back) {
            if (callback != null) {
                callback.onBackClick();
            }
            return;
        }
        if (id == R.id.titlebar_while_right) {
            if (callback != null) {
                callback.onRightClick();
            }
            return;
        }
    }
}
