package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/28 13:14
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceSequenceInfo {

    /**
     * sequence_number : 6e00
     * message : 设备明细查询成功
     * code : 1
     */

    private String sequence_number;
    private String message;
    private int    code;

    public String getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(String sequence_number) {
        this.sequence_number = sequence_number;
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
