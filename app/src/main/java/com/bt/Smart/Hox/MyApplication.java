package com.bt.Smart.Hox;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.bt.Smart.Hox.utils.ExceptionUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;

/**
 * @创建者 AndyYan
 * @创建时间 2018/5/22 8:51
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MyApplication extends Application {
    public static boolean             isRelease    = false;//判断程序是否异常
    public static ArrayList<Activity> listActivity = new ArrayList<Activity>();//用来装载activity
    public static int                 flag         = -1;//判断是否被回收
    public static int                 isLogin      = 0;//判断是否登录
    public static String  userID;
    public static String  userName;
    public static String  pasword;
    public static String  userPhone;
    public static String  userHeadPic;
    public static String  slecHomeID;
    public static String  slecRoomID;
    public static boolean isLoading;
    public static int     version_code;
    public static String  loadUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        //        JPushInterface.setDebugMode(true);
        //        JPushInterface.init(this);
    }

    public static void exit() {
        try {
            for (Activity activity : listActivity) {
                activity.finish();
            }
            // 结束进程
            System.exit(0);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    public static int getNowVersionCode(Context context) {
        try {
            PackageInfo packageInfo = (context).getPackageManager().getPackageInfo((context).getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
