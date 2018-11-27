package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/27 9:43
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UserDetailInfo {

    /**
     * registerInfo : {"head_pic":"http://112.90.178.68:8081/upFiles/1542796099214.jpeg","ftelephone":"18036215618"}
     * message : 用户信息获取成功
     * code : 1
     */

    private RegisterInfoBean registerInfo;
    private String message;
    private int    code;

    public RegisterInfoBean getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(RegisterInfoBean registerInfo) {
        this.registerInfo = registerInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class RegisterInfoBean {
        /**
         * head_pic : http://112.90.178.68:8081/upFiles/1542796099214.jpeg
         * ftelephone : 18036215618
         */

        private String head_pic;
        private String ftelephone;

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getFtelephone() {
            return ftelephone;
        }

        public void setFtelephone(String ftelephone) {
            this.ftelephone = ftelephone;
        }
    }
}
