package com.gui.royal.youtalking.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 2015/5/27.
 * 管理所有活动的管理类
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public  static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    public  static void finishAllActivities() {
        for (Activity activity : activities)
            if (!activity.isFinishing()) {
                activity.finish();
            }
    }
}
