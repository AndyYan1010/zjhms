package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 14:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ParamSceneInfoWithID {
    /**
     * home_id : 125252
     * scene_name : 111
     * scene_pic : aa.pig
     */

    private String                              home_id;
    private String                              scene_name;
    private String                              scene_pic;
    private List<ParamSceneInfo.DevicelistBean> devicelist;
    /**
     * id : bb1d8b2f-c587-472f-91d6-b1c38f3935e2
     */

    private String                              id;

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }

    public String getScene_pic() {
        return scene_pic;
    }

    public void setScene_pic(String scene_pic) {
        this.scene_pic = scene_pic;
    }

    public List<ParamSceneInfo.DevicelistBean> getDevicelist() {
        return devicelist;
    }

    public void setDevicelist(List<ParamSceneInfo.DevicelistBean> devicelist) {
        this.devicelist = devicelist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class DevicelistBean {
        /**
         * device_id : q2131332
         * device_status : 0
         * device_value : 0001
         */

        private String device_id;
        private String device_status;
        private String device_value;

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
}
