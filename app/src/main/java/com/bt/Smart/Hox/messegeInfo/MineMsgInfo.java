package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/29 19:12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MineMsgInfo {

    /**
     * devList : [{"devStatus":[{"device_name":"计量控制（灯控）","fdeviceid":"0211800001","fdevicestatus":"00","faddtime":"2018-11-29 18:20:26"},{"device_name":"空气哨兵","fdeviceid":"0311800001","fdevicestatus":"00","faddtime":"2018-11-29 18:20:26"}],"main_control_code":"0101800001"}]
     * message : 我的消息查询成功
     * code : 1
     */

    private String message;
    private int               code;
    private List<DevListBean> devList;

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

    public List<DevListBean> getDevList() {
        return devList;
    }

    public void setDevList(List<DevListBean> devList) {
        this.devList = devList;
    }

    public static class DevListBean {
        /**
         * devStatus : [{"device_name":"计量控制（灯控）","fdeviceid":"0211800001","fdevicestatus":"00","faddtime":"2018-11-29 18:20:26"},{"device_name":"空气哨兵","fdeviceid":"0311800001","fdevicestatus":"00","faddtime":"2018-11-29 18:20:26"}]
         * main_control_code : 0101800001
         */

        private String main_control_code;
        private List<DevStatusBean> devStatus;

        public String getMain_control_code() {
            return main_control_code;
        }

        public void setMain_control_code(String main_control_code) {
            this.main_control_code = main_control_code;
        }

        public List<DevStatusBean> getDevStatus() {
            return devStatus;
        }

        public void setDevStatus(List<DevStatusBean> devStatus) {
            this.devStatus = devStatus;
        }

        public static class DevStatusBean {
            /**
             * device_name : 计量控制（灯控）
             * fdeviceid : 0211800001
             * fdevicestatus : 00
             * faddtime : 2018-11-29 18:20:26
             */

            private String device_name;
            private String fdeviceid;
            private String fdevicestatus;
            private String faddtime;

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getFdeviceid() {
                return fdeviceid;
            }

            public void setFdeviceid(String fdeviceid) {
                this.fdeviceid = fdeviceid;
            }

            public String getFdevicestatus() {
                return fdevicestatus;
            }

            public void setFdevicestatus(String fdevicestatus) {
                this.fdevicestatus = fdevicestatus;
            }

            public String getFaddtime() {
                return faddtime;
            }

            public void setFaddtime(String faddtime) {
                this.faddtime = faddtime;
            }
        }
    }
}
