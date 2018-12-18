package com.bt.Smart.Hox.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.bt.Smart.Hox.R;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/18 9:43
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SoundPoolUtil {
    private static SoundPoolUtil soundPoolUtil;
    private static SoundPool     soundPool;

    //单例模式
    public static SoundPoolUtil getInstance(Context context) {
        if (soundPoolUtil == null)
            soundPoolUtil = new SoundPoolUtil(context);
        return soundPoolUtil;
    }

    private SoundPoolUtil(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0);
        //加载音频文件
        soundPool.load(context, R.raw.yulu, 1);
        soundPool.load(context, R.raw.duing, 1);
        soundPool.load(context, R.raw.yulu, 1);
    }

    public static void play(int number) {
        Log.d("tag", "number " + number);
        //播放音频
        soundPool.play(number, 1, 1, 0, 0, 1);
    }
}
