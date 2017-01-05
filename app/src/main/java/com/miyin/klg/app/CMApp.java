package com.miyin.klg.app;

import android.app.Application;
import android.util.Log;

import com.miyin.klg.util.HttpUtil.HttpCookie;


/**
 * Created by en on 2016/12/29.
 */

public class CMApp extends Application {
   public static HttpCookie cookie;
   private static final String tag=CMApp.class.getSimpleName();
   @Override
   public void onCreate() {
      super.onCreate();
      Log.i(tag,"CMApp初始化onCreate");
      cookie=new HttpCookie();
   }

   public static void setCookie(HttpCookie cookie) {
      CMApp.cookie = cookie;
      Log.i(tag,CMApp.cookie.toString());
   }
}
