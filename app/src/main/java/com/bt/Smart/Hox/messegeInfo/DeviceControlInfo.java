package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/28 13:23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceControlInfo {

    /**
     * subscribe : 已经订阅过了不能重复订阅
     * publise : {"code":1,"message":"发布主题发布成功"}
     * message : 灯已经操作,等待回应
     * code : 1
     */

    private String subscribe;
    private PubliseBean publise;
    private String      message;
    private int         code;

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public PubliseBean getPublise() {
        return publise;
    }

    public void setPublise(PubliseBean publise) {
        this.publise = publise;
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

    public static class PubliseBean {
        /**
         * code : 1
         * message : 发布主题发布成功
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
