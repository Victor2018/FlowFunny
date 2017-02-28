package com.siss.dao;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by victor on 2017/2/28.
 */
public class ActivityManager {
    private Stack<Activity> activityStack;
    private static ActivityManager mActivityManager;

    public static ActivityManager getInstance () {
        if (mActivityManager == null) {
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    /**
     * 将activity移除栈
     * @param activity
     */
    public void popActivity (Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定activity
     * @param activity
     */
    public void endActivity (Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前（即最上层）activity
     * @return
     */
    public Activity getCurrentActivity () {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 将activity压入栈中
     * @param activity
     */
    public void pushActivity (Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 弹出除cls外的所有activity
     * @param cls
     */
    public void popAllActivityExceptOne (Class<? extends Activity> cls) {
        while (true) {
            Activity activity = getCurrentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 结束除cls之外的所有activity,执行结果都会清空栈Stack
     * @param cls
     */
    public void finishAllActivityExcetpOne (Class <? extends Activity> cls) {
        while (!activityStack.empty()) {
            Activity activity = getCurrentActivity();
            if (activity.getClass().equals(cls)) {
                popActivity(activity);
            } else {
                endActivity(activity);
            }
        }
    }

    /**
     *  结束所有activity
     */
    public void finishAllActivity () {
        while (!activityStack.empty()) {
            Activity activity = getCurrentActivity();
            endActivity(activity);
        }
    }
}
