package com.miyin.klg.cascade.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.miyin.klg.R;

public class SelectPicPopupWindow extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectpicpopupwindow);
	}
	//实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override  
    public boolean onTouchEvent(MotionEvent event){  
        finish();  
        return true;  
    }
	public void onClick(View view){
		startActivity(new Intent(this,AreaSelectActivity.class));
	}
}

