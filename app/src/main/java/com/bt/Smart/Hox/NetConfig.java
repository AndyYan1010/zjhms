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
    public static String ROOT = "http://www.smart-hox.com:8081/hoxJK/";

    //获取验证码
    public static String CHECKMESSAGE  = ROOT + "checkMessage";
    //注册用户
    public static String USERINSERTPC  = ROOT + "userInsertPC";
    //修改密码
    public static String BACKFPASSWORD = ROOT + "backFpassword";
    //用户登录
    public static String LOGINURL      = ROOT + "userLoginPC";

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
    public static String AUTHORIZATION = ROOT + "authorization";

    //意见反馈
    public static String COMPLAINTFEEDBACK = ROOT + "complaintFeedback";

}
