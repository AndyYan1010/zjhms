package com.bt.Smart.Hox.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.bt.Smart.Hox.interfaceFile.IGetMessageCallBack;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/6 18:22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MQTTService extends Service {
    public static final String TAG = MQTTService.class.getSimpleName();

    //    private static MqttAndroidClient client;
    //    private MqttConnectOptions conOpt;

    private        String host     = "tcp://192.168.0.11:61613";
    private        String userName = "admin";
    private        String passWord = "password";
    private static String myTopic  = "ForTest";      //要订阅的主题
    private        String clientId = "androidId";//客户端标识
    private IGetMessageCallBack IGetMessageCallBack;


    @Override
    public IBinder onBind(Intent intent) {
        Log.e(getClass().getName(), "onBind");
        return new CustomBinder();
    }


    public class CustomBinder extends Binder {
        public MQTTService getService() {
            return MQTTService.this;
        }
    }
}
