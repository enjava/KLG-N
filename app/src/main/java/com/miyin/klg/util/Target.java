package com.miyin.klg.util;

import android.app.Activity;

/**
 * Created by en on 2017/2/13.
 */

public class Target {
    Activity mActivity;
   public static String  TargetPhotoPath;
    public Target(Activity activity){
        this.mActivity=activity;

    }

    public void setInfo(String path, String patha){
        TargetPhotoPath=patha;
    }

    public String getInfo(String picturePath){
        return TargetPhotoPath;
    }
}
