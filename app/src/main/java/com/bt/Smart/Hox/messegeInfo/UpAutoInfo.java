package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 16:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UpAutoInfo {

    /**
     * auto_execute_device : [{"deviceType":"010","device_id":"000000121","device_status":"1","device_value":"10"}]
     * auto_execute_scene : [{"scene_id":"000000121","scene_status":"1"}]
     * auto_ifha3_list : [{"if_ha3_id":"空气哨兵id","if_select":"大于","if_select_type":"温度0","if_type":"按空气哨兵传1","if_value":"0"}]
     * auto_iftime_list : [{"if_begin_time":"8:00","if_end_time":"10:00","if_type":"按时间0"}]
     * auto_time_list : [{"ftime":"0"},{"ftime":"1"}]
     *
     * auto_name : 111
     * auto_type : 1
     * home_id : 1298301830
     */

    /**
     * auto_status : 1
     * id : 1111
     */

    private String                                     auto_name;
    private String                                     auto_type;
    private String                                     home_id;
    private List<InsertAutoInfo.AutoExecuteDeviceBean> auto_execute_device;
    private List<InsertAutoInfo.AutoExecuteSceneBean>  auto_execute_scene;
    private List<InsertAutoInfo.AutoIfha3ListBean>     auto_ifha3_list;
    private List<InsertAutoInfo.AutoIftimeListBean>    auto_iftime_list;
    private List<InsertAutoInfo.AutoTimeListBean>      auto_time_list;
    private String                                     auto_status;
    private String                                     id;

    public String getAuto_name() {
        return auto_name;
    }

    public void setAuto_name(String auto_name) {
        this.auto_name = auto_name;
    }

    public String getAuto_type() {
        return auto_type;
    }

    public void setAuto_type(String auto_type) {
        this.auto_type = auto_type;
    }

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public List<InsertAutoInfo.AutoExecuteDeviceBean> getAuto_execute_device() {
        return auto_execute_device;
    }

    public void setAuto_execute_device(List<InsertAutoInfo.AutoExecuteDeviceBean> auto_execute_device) {
        this.auto_execute_device = auto_execute_device;
    }

    public List<InsertAutoInfo.AutoExecuteSceneBean> getAuto_execute_scene() {
        return auto_execute_scene;
    }

    public void setAuto_execute_scene(List<InsertAutoInfo.AutoExecuteSceneBean> auto_execute_scene) {
        this.auto_execute_scene = auto_execute_scene;
    }

    public List<InsertAutoInfo.AutoIfha3ListBean> getAuto_ifha3_list() {
        return auto_ifha3_list;
    }

    public void setAuto_ifha3_list(List<InsertAutoInfo.AutoIfha3ListBean> auto_ifha3_list) {
        this.auto_ifha3_list = auto_ifha3_list;
    }

    public List<InsertAutoInfo.AutoIftimeListBean> getAuto_iftime_list() {
        return auto_iftime_list;
    }

    public void setAuto_iftime_list(List<InsertAutoInfo.AutoIftimeListBean> auto_iftime_list) {
        this.auto_iftime_list = auto_iftime_list;
    }

    public List<InsertAutoInfo.AutoTimeListBean> getAuto_time_list() {
        return auto_time_list;
    }

    public void setAuto_time_list(List<InsertAutoInfo.AutoTimeListBean> auto_time_list) {
        this.auto_time_list = auto_time_list;
    }

    public String getAuto_status() {
        return auto_status;
    }

    public void setAuto_status(String auto_status) {
        this.auto_status = auto_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class AutoExecuteDeviceBean {
        /**
         * deviceType : 010
         * device_id : 000000121
         * device_status : 1
         * device_value : 10
         */

        private String deviceType;
        private String device_id;
        private String device_status;
        private String device_value;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

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
    }

    public static class AutoExecuteSceneBean {
        /**
         * scene_id : 000000121
         * scene_status : 1
         */

        private String scene_id;
        private String scene_status;

        public String getScene_id() {
            return scene_id;
        }

        public void setScene_id(String scene_id) {
            this.scene_id = scene_id;
        }

        public String getScene_status() {
            return scene_status;
        }

        public void setScene_status(String scene_status) {
            this.scene_status = scene_status;
        }
    }

    public static class AutoIfha3ListBean {
        /**
         * if_ha3_id : 空气哨兵id
         * if_select : 大于
         * if_select_type : 温度0
         * if_type : 按空气哨兵传1
         * if_value : 0
         */

        private String if_ha3_id;
        private String if_select;
        private String if_select_type;
        private String if_type;
        private String if_value;

        public String getIf_ha3_id() {
            return if_ha3_id;
        }

        public void setIf_ha3_id(String if_ha3_id) {
            this.if_ha3_id = if_ha3_id;
        }

        public String getIf_select() {
            return if_select;
        }

        public void setIf_select(String if_select) {
            this.if_select = if_select;
        }

        public String getIf_select_type() {
            return if_select_type;
        }

        public void setIf_select_type(String if_select_type) {
            this.if_select_type = if_select_type;
        }

        public String getIf_type() {
            return if_type;
        }

        public void setIf_type(String if_type) {
            this.if_type = if_type;
        }

        public String getIf_value() {
            return if_value;
        }

        public void setIf_value(String if_value) {
            this.if_value = if_value;
        }
    }

    public static class AutoIftimeListBean {
        /**
         * if_begin_time : 8:00
         * if_end_time : 10:00
         * if_type : 按时间0
         */

        private String if_begin_time;
        private String if_end_time;
        private String if_type;

        public String getIf_begin_time() {
            return if_begin_time;
        }

        public void setIf_begin_time(String if_begin_time) {
            this.if_begin_time = if_begin_time;
        }

        public String getIf_end_time() {
            return if_end_time;
        }

        public void setIf_end_time(String if_end_time) {
            this.if_end_time = if_end_time;
        }

        public String getIf_type() {
            return if_type;
        }

        public void setIf_type(String if_type) {
            this.if_type = if_type;
        }
    }

    public static class AutoTimeListBean {
        /**
         * ftime : 0
         */

        private String ftime;

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
