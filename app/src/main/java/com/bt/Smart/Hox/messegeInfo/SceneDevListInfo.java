package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 9:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneDevListInfo {

    /**
     * device_id : q2131332
     * device_status : 0
     * device_value : 0001
     */

    private String device_id;
    private String device_status;
    private String device_value;
    /**
     * device_name : 灯
     */

    private String device_name;
    /**
     * deviceType : 010
     */

    private String deviceType;
    /**
     * main_control_code : 0101800001
     */

    private String main_control_code;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_status() {
        return device_status;
    }

    public void setDevice_status(String device_status) {
        this.device_status = device_status;
    }

    public String getDevice_value() {
        return device_value;
    }

    public void setDevice_value(String device_value) {
        this.device_value = device_value;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMain_control_code() {
        return main_control_code;
    }

    public void setMain_control_code(String main_control_code) {
        this.main_control_code = main_control_code;
    }
}
