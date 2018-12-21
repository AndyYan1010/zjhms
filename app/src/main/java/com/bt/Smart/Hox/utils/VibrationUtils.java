package com.bt.Smart.Hox.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Vibrator;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/21 9:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class VibrationUtils {
    private static VibrationUtils mVibrationUtils;
    private static Context        mContext;
    private static Vibrator       mVib;
    private static boolean        isHasVib;
    private static boolean        isPause;

    public static VibrationUtils getInstance(Context context) {
        if (null == mVibrationUtils) {
            mVibrationUtils = new VibrationUtils(context);
        }
        return mVibrationUtils;
    }

    public VibrationUtils(Context context) {
        mContext = context;
        SensorManager mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager == null) {
            isHasVib = false;
        } else {
            isHasVib = true;
            mVib = (Vibrator) ((Activity) mContext).getSystemService(Service.VIBRATOR_SERVICE);
            if (!mVib.hasVibrator())
                isHasVib = false;
        }
    }

    public static void vibrateFor100s() {
        vibrate(100);
    }

    public static void vibrateWithArr() {
        vibrateWithArr(new long[]{500, 500, 500, 500}, -1);
    }

    //震动milliseconds毫秒
    public static void vibrate(long milliseconds) {
        if (isPause)
            return;
        if (isHasVib && null != mVib)
            mVib.vibrate(milliseconds);
    }

    //以pattern[]方式震动
    public static void vibrateWithArr(long[] pattern, int repeat) {//等待、震动、等待...从repeat处开始重复，-1不重复
        if (isPause)
            return;
        if (isHasVib && null != mVib)
            mVib.vibrate(pattern, repeat);
    }

    public static void restartVibra() {
        isPause = false;
    }

    public static void pauseVibra() {
        isPause = true;
    }

    //取消震动
    public static void cancleVibra() {
        if (null != mVib)
            mVib.cancel();
    }
}
