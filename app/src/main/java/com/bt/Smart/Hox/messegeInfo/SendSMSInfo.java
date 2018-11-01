package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/1 10:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SendSMSInfo {

    /**
     * validateCode : 123456
     * message : 短信获取成功
     * code : 1
     */

    private String validateCode;
    private String message;
    private int    code;

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
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
}
