package com.miyin.klg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by en on 2017/2/7.
 */

public class OnUpgradeBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        final String msg="intent:"+intent+" action:"+intent.getAction();
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            ApplicationInfo app = new ApplicationInfo();
            if (app.packageName.equals("it.android.downloadapk")) {
                Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(app.packageName);
                context.startActivity(LaunchIntent);


            }


        }
    }

//    @Override
//    public void onReceive(final Context context,final Intent intent)
//    {
//        final String msg="intent:"+intent+" action:"+intent.getAction();
//        Log.d("DEBUG",msg);
//        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
//    }


}
