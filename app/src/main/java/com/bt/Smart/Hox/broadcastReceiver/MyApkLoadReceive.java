package com.bt.Smart.Hox.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bt.Smart.Hox.MyApplication;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/12 11:02
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MyApkLoadReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.loading_over".equals(action)) {
            MyApplication.isLoading = false;
        } else if ("android.intent.action.loading".equals(action)) {
            MyApplication.isLoading = true;
        }
    }
}
