package com.miyin.klg.app;

import android.app.Application;
import android.util.Log;

import com.miyin.klg.entity.Store;
import com.miyin.klg.entity.User;
import com.miyin.klg.util.HttpUtil.HttpCookie;


/**
 * Created by en on 2016/12/29.
 * 应用App
 */

public class CMApp extends Application {
   public static HttpCookie cookie;
   private static final String tag=CMApp.class.getSimpleName();
   public User user;//用户
   public Store store;
   @Override
   public void onCreate() {
      super.onCreate();
      Log.i(tag,"CMApp初始化onCreate");
      cookie=new HttpCookie();
   }

   public void setCookie(HttpCookie cookie) {
      this.cookie = cookie;
      Log.i(tag,CMApp.cookie.toString());
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Store getStore() {
      return store;
   }

   public void setStore(Store store) {
      this.store = store;
   }

   public void setNoUserAndStore(){
      user=null;
      store=null;
   }
}
