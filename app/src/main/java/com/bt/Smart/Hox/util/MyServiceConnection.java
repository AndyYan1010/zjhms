package com.bt.Smart.Hox.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.bt.Smart.Hox.interfaceFile.IGetMessageCallBack;
import com.bt.Smart.Hox.service.MQTTService;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/7 10:47
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MyServiceConnection implements ServiceConnection {

    private MQTTService         mqttService;
    private IGetMessageCallBack IGetMessageCallBack;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mqttService = ((MQTTService.CustomBinder)iBinder).getService();
        mqttService.setIGetMessageCallBack(IGetMessageCallBack);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    public MQTTService getMqttService(){
        return mqttService;
    }

    public void setIGetMessageCallBack(IGetMessageCallBack IGetMessageCallBack){
        this.IGetMessageCallBack = IGetMessageCallBack;
    }
}
