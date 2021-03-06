package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 9:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoCondInfo {

    /**
     * device_name : 空气哨兵
     * select_type : 温度
     * select_if : 大于
     * value : 0
     */

    private String device_name;
    private String select_type;
    private String select_if;
    private String value;
    /**
     * device_id : 00000000121
     */

    private String device_id;
    /**
     * ha3_code : 226
     */

    private String ha3_code;
    /**
     * main_control_code : 主控编号
     */

    private String main_control_code;

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getSelect_type() {
        return select_type;
    }

    public void setSelect_type(String select_type) {
        this.select_type = select_type;
    }

    public String getSelect_if() {
        return select_if;
    }

    public void setSelect_if(String select_if) {
        this.select_if = select_if;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getHa3_code() {
        return ha3_code;
    }

    public void setHa3_code(String ha3_code) {
        this.ha3_code = ha3_code;
    }

    public String getMain_control_code() {
        return main_control_code;
    }

    public void setMain_control_code(String main_control_code) {
        this.main_control_code = main_control_code;
    }
}
