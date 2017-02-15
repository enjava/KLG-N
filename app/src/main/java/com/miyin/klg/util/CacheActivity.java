package com.miyin.klg.util;

import android.app.Activity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by en on 2017/2/15.
 */

public class CacheActivity {

    public static List<Activity> activityList = new CopyOnWriteArrayList<>();

    /**
     * 添加到Activity容器中
     */
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public static void remove(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 便利所有Activigty并finish
     */
    public static void finishAllActivity() {
        try {
                for (Activity activity : activityList) {
                    activity.finish();
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * 便利指定activity之外所有Activity并finish
     */
    public static void finishOtherActivity(Activity activity) {

        try {

                for (Activity act : activityList) {
                    if (!act.equals(activity)) {
                        act.finish();
                    }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * 便利指定activity之外所有Activity并finish
     */
    public static void finishOtherActivity(Class<? extends Activity> activity) {

            for (Activity act : activityList) {
                if (!act.equals(activity.getClass())) {
                    act.finish();
                }
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        try {
                if (activity != null) {

                    activity.finish();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * 结束指定类名的Activity 在遍历一个列表的时候不能执行删除操作，所有我们先记住要删除的对象，遍历之后才去删除。
     */
    public static void finishActivity(Class<?> cls) {
        Activity tempActivity = null;
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }

        finishActivity(tempActivity);
    }

}
