package com.bt.Smart.Hox;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/28 8:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class NetConfig {
    //服务器总地址
    public static String ROOT             = "http://www.smart-hox.com:8081/hoxJK/";
    //图片地址
    public static String IMG_HEAD         = "http://www.smart-hox.com:8083/upFiles/";
    public static String IMG_HEAD_IP      = "http://112.90.178.68:8081/upFiles/";
    //场景选择图片的链接头
    public static String IMG_FOR_SCENE    = "http://www.smart-hox.com:8081/upFiles/";
    //设备图片地址头
    public static String IMG_FOR_DEV      = "http://112.90.178.68:8083/upFiles/";
    //获取最新版本apk信息
    public static String GETNEWAPPVERSION = ROOT + "getNewAppVersion";

    //base64上传图片
    public static String UPLOADBASE64ANDROID   = ROOT + "uploadBase64Android";
    public static String UPLOADBASE64          = ROOT + "uploadBase64";
    //获取验证码
    public static String CHECKMESSAGE          = ROOT + "checkMessage";
    //注册用户
    public static String USERINSERTPC          = ROOT + "userInsertPC";
    //修改密码(记得原密码)
    public static String BACKFPASSWORD         = ROOT + "backFpassword";
    //修改密码(忘记原密码)
    public static String BACKFPASSWORDBYMOBILE = ROOT + "backFpasswordByMobile";
    //用户登录
    public static String LOGINURL              = ROOT + "userLoginPC";
    //获取用户信息
    public static String REGISTERINFO          = ROOT + "registerInfo";
    //更新用户信息
    public static String UPDATEPC              = ROOT + "updatePC";


    //获取用户的家列表
    public static String HOME   = ROOT + "home";
    //获取家的房间信息
    public static String HOUSE  = ROOT + "house";
    //获取房间设备信息
    public static String DEVICE = ROOT + "device";

    //添加家庭
    public static String home              = ROOT + "home";
    //家详细信息
    public static String HOMEDETAIL        = ROOT + "homeDetail";
    //家庭成员信息
    public static String HOMEMEMBER        = ROOT + "homeMember";
    //添加家庭成员
    public static String homeMember        = ROOT + "homeMember";
    //家授权，查询房间设备列表
    public static String AUTHORIZATIONEDIT = ROOT + "authorizationEdit";
    //共享设备
    public static String AUTHORIZATION     = ROOT + "authorization";

    //获取所有可添加设备列表
    public static String DEVICETYPE    = ROOT + "deviceType";
    //查看当前家下所有处传感器之外的设备
    public static String SELECTEQLIST  = ROOT + "selecteqlist";
    //更改设备房间
    public static String DEVICEHOUSE   = ROOT + "deviceHouse";
    //获取可添加主控设备列表
    public static String DEVICETYPENEW = ROOT + "deviceTypeNew";


    //主控列表//更新主控
    public static String MAINCONTROL     = ROOT + "mainControl";
    public static String MAINCONTROLCODE = ROOT + "mainControlCode";
    //从控列表
    public static String SECONDCONTROL   = ROOT + "secondControl";
    //获取命令序号
    public static String DEVICESEQUENCE  = ROOT + "deviceSequence";


    //设备控制
    public static String DEVICECONTROL = ROOT + "deviceControl";

    //空气哨兵测量信息
    public static String HAIRLIST     = ROOT + "hairList";
    //空气哨兵测量信息当前值
    public static String HAIR_CURRENT = ROOT + "hair_current";
    //空气哨兵测量信息type
    public static String HAIR         = ROOT + "hair";


    //计量灯控测量信息当前值
    public static String ENERGY_CURRENT = ROOT + "energy_current";
    //计量灯控测量信息type
    public static String ENERGY         = ROOT + "energy";


    //获取家下所有场景
    public static String SELECTALLSCENARIO = ROOT + "selectallscenario";
    //查看家下面的场景模式
    public static String QUERYSCENELIST    = ROOT + "querySceneList";
    //查看场景默认所有图片
    public static String QUERYDEFAULTPIC   = ROOT + "queryDefaultPic";
    //添加场景
    public static String INSERTSCENARIO    = ROOT + "insertscenario";
    public static String INSERTSCENE       = ROOT + "insertscene";
    //获取场景详情
    public static String QUERYSCENEDETAIL  = ROOT + "querySceneDetail";
    public static String SELECTNOSCENARIO  = ROOT + "selectnoscenario";

    //场景的开启或关闭
    public static String UPDATESTATUS     = ROOT + "updateStatus";
    //场景首页展示
    public static String UPDATESHOWSTATUS = ROOT + "updateShowStatus";
    //更新场景
    public static String UPDATESCENE      = ROOT + "updatescene";
    //删除场景
    public static String DELETESCENE      = ROOT + "deleteScene";


    //获取家下传感器
    public static String QUERYHA3LIST    = ROOT + "queryHA3List";
    //查看家下面除传感器之外的设备
    public static String QUERYNOTHA3LIST = ROOT + "queryNotHA3List";


    //获取家下所有自动化
    public static String QUERYAUTOLIST       = ROOT + "queryAutoList";
    public static String SELECTALLAUTOMATION = ROOT + "selectallautomation";
    //添加自动化
    public static String INSERTAUTO          = ROOT + "insertauto";
    //自动化详情
    public static String QUERYAUTODETIAL     = ROOT + "queryAutoDetial";
    //获得当前家的某个自动化
    public static String SELECTNOAUTOMATION  = ROOT + "selectnoautomation";
    //删除自动化
    public static String DELETEAUTOMATION    = ROOT + "deleteautomation";
    public static String DELETEAUTO          = ROOT + "deleteAuto";
    //更新自动化
    public static String UPDATEAUTO          = ROOT + "updateAuto";
    //开启或关闭自动化
    public static String UPDATEAUTOSTATUS    = ROOT + "updateAutoStatus";


    //获取空气哨兵的值
    public static String QUERYHA3TYPEVLAUELIST = ROOT + "queryHa3TypeVlaueList";


    //适玩列表
    public static String PLAYLIST = ROOT + "playList";
    //适玩详情
    public static String PLAYBYID = ROOT + "playById";

    //意见反馈
    public static String COMPLAINTFEEDBACK = ROOT + "complaintFeedback";
    //我的消息
    public static String MYMESSAGE         = ROOT + "myMessage";

}
