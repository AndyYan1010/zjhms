package com.bt.Smart.Hox.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.interfaceFile.IGetMessageCallBack;
import com.bt.Smart.Hox.utils.HexUtil;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private static MqttAndroidClient  client;
    private        MqttConnectOptions conOpt;

    private        String host     = "tcp://112.90.178.68:61613";
    private        String userName = "admin";
    private        String passWord = "password";
    //private static String myTopic  = "mytest";//要订阅的主题
    private static String myTopic  = "0321800001/Measure";//要订阅的主题
    private        String clientId = "18036212618";//客户端标识
    private IGetMessageCallBack IGetMessageCallBack;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(getClass().getName(), "onCreate");
        init();
    }

    public static void publish(String msg) {
        myTopic = msg + "/Measure";
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        try {
            if (client != null) {
                client.publish(topic, msg.getBytes(), qos.intValue(), retained.booleanValue());
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(getClass().getName(), "onBind");
        return new CustomBinder();
    }

    private void init() {
        // 服务器地址（协议+地址+端口号）
        String uri = host;
        client = new MqttAndroidClient(this, uri, clientId);
        // 设置MQTT监听并且接受消息
        client.setCallback(mqttCallback);

        conOpt = new MqttConnectOptions();
        // 清除缓存
        conOpt.setCleanSession(true);
        // 设置超时时间，单位：秒
        conOpt.setConnectionTimeout(10);
        // 心跳包发送间隔，单位：秒
        conOpt.setKeepAliveInterval(5);
        // 用户名
        conOpt.setUserName(userName);
        // 密码
        conOpt.setPassword(passWord.toCharArray());     //将字符串转换为字符串数组

        // last will message
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + clientId + "\"}";
        Log.e(getClass().getName(), "message是:" + message);
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            // MQTT本身就是为信号不稳定的网络设计的，所以难免一些客户端会无故的和Broker断开连接。
            //当客户端连接到Broker时，可以指定LWT，Broker会定期检测客户端是否有异常。
            //当客户端异常掉线时，Broker就往连接时指定的topic里推送当时指定的LWT消息。

            try {
                conOpt.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                Log.i(TAG, "Exception Occured", e);
                doConnect = false;
                iMqttActionListener.onFailure(null, e);
            }
        }

        if (doConnect) {
            doClientConnection();
        }
    }

    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        if (!client.isConnected() && isConnectIsNormal()) {
            try {
                client.connect(conOpt, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            Log.i(TAG, "连接成功 ");
            try {
                // 订阅myTopic话题
                client.subscribe(myTopic, 1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            // 连接失败，重连
            Log.i(TAG, "连接失败 ");
        }
    };

    // MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            byte[] payload0 = message.getPayload();
            String str1 = BinaryToHexString(payload0);
            String str2 = topic + ";qos:" + message.getQos() + ";retained:" + message.isRetained();
            Log.i(TAG, "messageArrived:" + str1);
            Log.i(TAG, str2);

            // subscribe后得到的消息会执行到这里面
            System.out.println("接收消息主题 : " + topic);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println("接收消息时间: " + df.format(new Date()));
            System.out.println("接收消息Qos : " + message.getQos());
            System.out.println("接收消息内容 String类型: " + new String(message.getPayload()));
            System.out.println("接收消息内容 字节类型: " + message.getPayload());
            String payload = HexUtil.encode(message.getPayload());
            System.out.println("接收消息内容 16进制: " + payload);

            String format = df.format(new Date());//接收时间

            byte[] temperature = reverse(subBytes(message.getPayload(), 12, 4));
            ByteBuffer temperatureBf = ByteBuffer.wrap(temperature);
            float temperaturefloat = temperatureBf.getFloat();

            byte[] humidity = reverse(subBytes(message.getPayload(), 16, 4));
            ByteBuffer humidityBf = ByteBuffer.wrap(humidity);
            float humidityfloat = humidityBf.getFloat();

            byte[] PM25 = reverse(subBytes(message.getPayload(), 20, 4));
            ByteBuffer PM25Bf = ByteBuffer.wrap(PM25);
            float PM25float = PM25Bf.getFloat();

            byte[] PM100 = reverse(subBytes(message.getPayload(), 24, 4));
            ByteBuffer PM100Bf = ByteBuffer.wrap(PM100);
            float PM100float = PM100Bf.getFloat();

            byte[] formaldehyde = reverse(subBytes(message.getPayload(), 28, 4));
            ByteBuffer formaldehydeBf = ByteBuffer.wrap(formaldehyde);
            float formaldehydefloat = formaldehydeBf.getFloat();

            byte[] VOC = reverse(subBytes(message.getPayload(), 32, 4));
            ByteBuffer VOCBf = ByteBuffer.wrap(VOC);
            float VOCfloat = VOCBf.getFloat();

            byte[] CO = reverse(subBytes(message.getPayload(), 36, 4));
            ByteBuffer COBf = ByteBuffer.wrap(CO);
            float COfloat = COBf.getFloat();

            byte[] CO2 = reverse(subBytes(message.getPayload(), 40, 4));
            ByteBuffer CO2Bf = ByteBuffer.wrap(CO2);
            float CO2float = CO2Bf.getFloat();

            Log.i(TAG, "温度：" + temperaturefloat + "，湿度：" + humidityfloat + "，PM2.5：" + PM25float + "，PM100：" + PM100float + "，甲醛：" + formaldehydefloat + "，VOCS：" + VOCfloat + "，CO2：" + CO2float + "，CO：" + COfloat);

            if (IGetMessageCallBack != null) {
                IGetMessageCallBack.setMessage(format,temperaturefloat, humidityfloat, PM25float, PM100float, formaldehydefloat, VOCfloat, CO2float, COfloat);
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            // 失去连接，重连
        }
    };

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "MQTT当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "MQTT没有可用网络");
            return false;
        }
    }

    @Override
    public void onDestroy() {
        stopSelf();
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void setIGetMessageCallBack(IGetMessageCallBack IGetMessageCallBack) {
        this.IGetMessageCallBack = IGetMessageCallBack;
    }

    public class CustomBinder extends Binder {
        public MQTTService getService() {
            return MQTTService.this;
        }
    }

    public void toCreateNotification(String message) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this, MQTTService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);//3、创建一个通知，属性太多，使用构造器模式

        Notification notification = builder
                .setTicker("测试标题")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("")
                .setContentText(message)
                .setContentInfo("")
                .setContentIntent(pendingIntent)//点击后才触发的意图，“挂起的”意图
                .setAutoCancel(true)        //设置点击之后notification消失
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(0, notification);
        notificationManager.notify(0, notification);

    }

    private String hexStr = "0123456789ABCDEF";

    private String BinaryToHexString(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    private byte[] reverse(byte[] parm) {
        for (int start = 0, end = parm.length - 1; start < end; start++, end--) {
            byte temp = parm[end];
            parm[end] = parm[start];
            parm[start] = temp;
        }
        return parm;
    }

    private byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }
}
