package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/8 16:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AllDevListInfo {

    /**
     * deviceHomeList : [{"device_config":"1","deviceType":"1","device_status":"0","second_control_id":"fe869542ddaf11e89c91000c2950df04","controltype":"","sensortype":"","second_control_name":"HAir空气哨兵(有线)","type":0,"device_img":"","roomid":"c340f6101d844659a7f6fc97493e51bc","main_control_name":"HoxONE主控","device_name":"HAir空气哨兵(有线)","home_name":"家","registerappuserid":"aa","registerid":"","house_name":"次卧","device_code":"00123456","on_off_status":0,"default_device_type":"HAir(有线)","defaulttype":0,"id":"79f97764d31c41e3bafd393097813c19","master_control":"00000001"},{"device_config":"1","deviceType":"1","device_status":"1","second_control_id":"27ce0d36ddb011e89c91000c2950df04","controltype":"","sensortype":"","second_control_name":"灯控","type":1,"device_img":"","roomid":"c340f6101d844659a7f6fc97493e51bc","main_control_name":"HoxONE主控","device_name":"灯控","home_name":"家","registerappuserid":"aa","registerid":"","house_name":"书房","device_code":"01000001","on_off_status":0,"default_device_type":"lamp","defaulttype":0,"id":"f4e912b6ab8e44c7ae14d2000dfbff75","master_control":"00000001"}]
     * message : 家庭设备列表查询成功
     * code : 1
     */

    private String message;
    private int                      code;
    private List<DeviceHomeListBean> deviceHomeList;

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

    public List<DeviceHomeListBean> getDeviceHomeList() {
        return deviceHomeList;
    }

    public void setDeviceHomeList(List<DeviceHomeListBean> deviceHomeList) {
        this.deviceHomeList = deviceHomeList;
    }

    public static class DeviceHomeListBean {
        /**
         * device_config : 1
         * deviceType : 1
         * device_status : 0
         * second_control_id : fe869542ddaf11e89c91000c2950df04
         * controltype :
         * sensortype :
         * second_control_name : HAir空气哨兵(有线)
         * type : 0
         * device_img :
         * roomid : c340f6101d844659a7f6fc97493e51bc
         * main_control_name : HoxONE主控
         * device_name : HAir空气哨兵(有线)
         * home_name : 家
         * registerappuserid : aa
         * registerid :
         * house_name : 次卧
         * device_code : 00123456
         * on_off_status : 0
         * default_device_type : HAir(有线)
         * defaulttype : 0
         * id : 79f97764d31c41e3bafd393097813c19
         * master_control : 00000001
         */

        private String device_config;
        private String deviceType;
        private String device_status;
        private String second_control_id;
        private String controltype;
        private String sensortype;
        private String second_control_name;
        private int    type;
        private String device_img;
        private String roomid;
        private String main_control_name;
        private String device_name;
        private String home_name;
        private String registerappuserid;
        private String registerid;
        private String house_name;
        private String device_code;
        private int    on_off_status;
        private String default_device_type;
        private int    defaulttype;
        private String id;
        private String master_control;

        public String getDevice_config() {
            return device_config;
        }

        public void setDevice_config(String device_config) {
            this.device_config = device_config;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDevice_status() {
            return device_status;
        }

        public void setDevice_status(String device_status) {
            this.device_status = device_status;
        }

        public String getSecond_control_id() {
            return second_control_id;
        }

        public void setSecond_control_id(String second_control_id) {
            this.second_control_id = second_control_id;
        }

        public String getControltype() {
            return controltype;
        }

        public void setControltype(String controltype) {
            this.controltype = controltype;
        }

        public String getSensortype() {
            return sensortype;
        }

        public void setSensortype(String sensortype) {
            this.sensortype = sensortype;
        }

        public String getSecond_control_name() {
            return second_control_name;
        }

        public void setSecond_control_name(String second_control_name) {
            this.second_control_name = second_control_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDevice_img() {
            return device_img;
        }

        public void setDevice_img(String device_img) {
            this.device_img = device_img;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getMain_control_name() {
            return main_control_name;
        }

        public void setMain_control_name(String main_control_name) {
            this.main_control_name = main_control_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getHome_name() {
            return home_name;
        }

        public void setHome_name(String home_name) {
            this.home_name = home_name;
        }

        public String getRegisterappuserid() {
            return registerappuserid;
        }

        public void setRegisterappuserid(String registerappuserid) {
            this.registerappuserid = registerappuserid;
        }

        public String getRegisterid() {
            return registerid;
        }

        public void setRegisterid(String registerid) {
            this.registerid = registerid;
        }

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
        }

        public String getDevice_code() {
            return device_code;
        }

        public void setDevice_code(String device_code) {
            this.device_code = device_code;
        }

        public int getOn_off_status() {
            return on_off_status;
        }

        public void setOn_off_status(int on_off_status) {
            this.on_off_status = on_off_status;
        }

        public String getDefault_device_type() {
            return default_device_type;
        }

        public void setDefault_device_type(String default_device_type) {
            this.default_device_type = default_device_type;
        }

        public int getDefaulttype() {
            return defaulttype;
        }

        public void setDefaulttype(int defaulttype) {
            this.defaulttype = defaulttype;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMaster_control() {
            return master_control;
        }

        public void setMaster_control(String master_control) {
            this.master_control = master_control;
        }
    }
}
