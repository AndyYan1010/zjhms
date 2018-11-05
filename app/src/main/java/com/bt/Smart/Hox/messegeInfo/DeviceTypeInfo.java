package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/5 15:09
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceTypeInfo {

    /**
     * deviceTypeList : {"message":"成功","data":[{"id":"8a8a8a8a65a7c55f0165a82bc7900010","sysCompanyCode":"A01","deviceDescibe":"智能摄像机","devcieTypePic":"upload/files\\20181019\\Chrysanthemum.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:26","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:18","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"camera"},{"id":"8a8a8a8a65a7c55f0165a82c0c390012","sysCompanyCode":"A01","deviceDescibe":"智能音箱","devcieTypePic":"upload/files\\20181019\\Koala.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:43","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:25","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"box"},{"id":"8a8a8a8a65a7c55f0165a82c4b910014","sysCompanyCode":"A01","deviceDescibe":"智能背包","devcieTypePic":"upload/files\\20181019\\Jellyfish.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:59","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:31","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"bag"},{"id":"8a8a8a8a65a7c55f0165a82d22170016","sysCompanyCode":"A01","deviceDescibe":"红外控制器","devcieTypePic":"upload/files\\20181019\\Penguins.jpg","devcieType":"3","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:20:54","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:38","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Infra-red"},{"id":"8a8a8a8a65a7c55f0165a82d6b920018","sysCompanyCode":"A01","deviceDescibe":"智能门锁","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"1","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:21:13","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:43","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"door-lock"},{"id":"8a8a8a8a65a7c55f0165a82e63aa001a","sysCompanyCode":"A01","deviceDescibe":"蓝牙设备","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"0","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:22:17","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:53","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Bluetooth"},{"id":"8a8a8a8a65a7c55f0165a82efb10001c","sysCompanyCode":"A01","deviceDescibe":"窗帘开关","devcieTypePic":"upload/files\\20181019\\Chrysanthemum.jpg","devcieType":"1","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:22:55","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:59","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Window-curtains"},{"id":"8a8a8a8a65f586b70165f590e3080007","sysCompanyCode":"A01","deviceDescibe":"HoxONE主控","devcieTypePic":"upload/files\\20181019\\Hydrangeas.jpg","devcieType":"0","deviceStatus":"1","createName":"管理员","createDate":"2018-09-20 14:00:37","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:04","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HoxONE"},{"id":"8a8a8a8a65f586b70165f5921dd30009","sysCompanyCode":"A01","deviceDescibe":"HAir空气哨兵(有线)","devcieTypePic":"upload/files\\20181019\\Lighthouse.jpg","devcieType":"1","deviceStatus":"1","createName":"管理员","createDate":"2018-09-20 14:01:58","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:10","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HAir(有线)"},{"id":"8a8a8a8a65f586b701661375eb180026","sysCompanyCode":"A01","deviceDescibe":"HAir空气哨兵(无线)","devcieTypePic":"upload/files\\20181019\\Jellyfish.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-26 09:19:46","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:20","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HAir(无线)"},{"id":"8a8a8a8a667fd18b0166808d3bb50954","sysCompanyCode":"A01","deviceDescibe":"灯控","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"1","deviceStatus":"1","createName":"管理员","createDate":"2018-10-17 13:43:51","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:27","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"lamp"}],"respCode":"0","ok":true}
     * message : 设备分类查询成功
     * code : 1
     */

    private DeviceTypeListBean deviceTypeList;
    private String message;
    private int    code;

    public DeviceTypeListBean getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(DeviceTypeListBean deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
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

    public static class DeviceTypeListBean {
        /**
         * message : 成功
         * data : [{"id":"8a8a8a8a65a7c55f0165a82bc7900010","sysCompanyCode":"A01","deviceDescibe":"智能摄像机","devcieTypePic":"upload/files\\20181019\\Chrysanthemum.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:26","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:18","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"camera"},{"id":"8a8a8a8a65a7c55f0165a82c0c390012","sysCompanyCode":"A01","deviceDescibe":"智能音箱","devcieTypePic":"upload/files\\20181019\\Koala.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:43","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:25","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"box"},{"id":"8a8a8a8a65a7c55f0165a82c4b910014","sysCompanyCode":"A01","deviceDescibe":"智能背包","devcieTypePic":"upload/files\\20181019\\Jellyfish.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:19:59","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:31","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"bag"},{"id":"8a8a8a8a65a7c55f0165a82d22170016","sysCompanyCode":"A01","deviceDescibe":"红外控制器","devcieTypePic":"upload/files\\20181019\\Penguins.jpg","devcieType":"3","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:20:54","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:38","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Infra-red"},{"id":"8a8a8a8a65a7c55f0165a82d6b920018","sysCompanyCode":"A01","deviceDescibe":"智能门锁","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"1","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:21:13","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:43","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"door-lock"},{"id":"8a8a8a8a65a7c55f0165a82e63aa001a","sysCompanyCode":"A01","deviceDescibe":"蓝牙设备","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"0","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:22:17","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:53","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Bluetooth"},{"id":"8a8a8a8a65a7c55f0165a82efb10001c","sysCompanyCode":"A01","deviceDescibe":"窗帘开关","devcieTypePic":"upload/files\\20181019\\Chrysanthemum.jpg","devcieType":"1","deviceStatus":"0","createName":"管理员","createDate":"2018-09-05 13:22:55","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:49:59","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"Window-curtains"},{"id":"8a8a8a8a65f586b70165f590e3080007","sysCompanyCode":"A01","deviceDescibe":"HoxONE主控","devcieTypePic":"upload/files\\20181019\\Hydrangeas.jpg","devcieType":"0","deviceStatus":"1","createName":"管理员","createDate":"2018-09-20 14:00:37","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:04","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HoxONE"},{"id":"8a8a8a8a65f586b70165f5921dd30009","sysCompanyCode":"A01","deviceDescibe":"HAir空气哨兵(有线)","devcieTypePic":"upload/files\\20181019\\Lighthouse.jpg","devcieType":"1","deviceStatus":"1","createName":"管理员","createDate":"2018-09-20 14:01:58","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:10","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HAir(有线)"},{"id":"8a8a8a8a65f586b701661375eb180026","sysCompanyCode":"A01","deviceDescibe":"HAir空气哨兵(无线)","devcieTypePic":"upload/files\\20181019\\Jellyfish.jpg","devcieType":"2","deviceStatus":"0","createName":"管理员","createDate":"2018-09-26 09:19:46","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:20","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"HAir(无线)"},{"id":"8a8a8a8a667fd18b0166808d3bb50954","sysCompanyCode":"A01","deviceDescibe":"灯控","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","devcieType":"1","deviceStatus":"1","createName":"管理员","createDate":"2018-10-17 13:43:51","updateName":"管理员","updateBy":"admin","updateDate":"2018-10-19 13:50:27","createBy":"admin","bpmStatus":"1","sysOrgCode":"A01","deviceTypeName":"lamp"}]
         * respCode : 0
         * ok : true
         */

        private String message;
        private String         respCode;
        private boolean        ok;
        private List<DataBean> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 8a8a8a8a65a7c55f0165a82bc7900010
             * sysCompanyCode : A01
             * deviceDescibe : 智能摄像机
             * devcieTypePic : upload/files\20181019\Chrysanthemum.jpg
             * devcieType : 2
             * deviceStatus : 0
             * createName : 管理员
             * createDate : 2018-09-05 13:19:26
             * updateName : 管理员
             * updateBy : admin
             * updateDate : 2018-10-19 13:49:18
             * createBy : admin
             * bpmStatus : 1
             * sysOrgCode : A01
             * deviceTypeName : camera
             */

            private String id;
            private String sysCompanyCode;
            private String deviceDescibe;
            private String devcieTypePic;
            private String devcieType;
            private String deviceStatus;
            private String createName;
            private String createDate;
            private String updateName;
            private String updateBy;
            private String updateDate;
            private String createBy;
            private String bpmStatus;
            private String sysOrgCode;
            private String deviceTypeName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSysCompanyCode() {
                return sysCompanyCode;
            }

            public void setSysCompanyCode(String sysCompanyCode) {
                this.sysCompanyCode = sysCompanyCode;
            }

            public String getDeviceDescibe() {
                return deviceDescibe;
            }

            public void setDeviceDescibe(String deviceDescibe) {
                this.deviceDescibe = deviceDescibe;
            }

            public String getDevcieTypePic() {
                return devcieTypePic;
            }

            public void setDevcieTypePic(String devcieTypePic) {
                this.devcieTypePic = devcieTypePic;
            }

            public String getDevcieType() {
                return devcieType;
            }

            public void setDevcieType(String devcieType) {
                this.devcieType = devcieType;
            }

            public String getDeviceStatus() {
                return deviceStatus;
            }

            public void setDeviceStatus(String deviceStatus) {
                this.deviceStatus = deviceStatus;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getBpmStatus() {
                return bpmStatus;
            }

            public void setBpmStatus(String bpmStatus) {
                this.bpmStatus = bpmStatus;
            }

            public String getSysOrgCode() {
                return sysOrgCode;
            }

            public void setSysOrgCode(String sysOrgCode) {
                this.sysOrgCode = sysOrgCode;
            }

            public String getDeviceTypeName() {
                return deviceTypeName;
            }

            public void setDeviceTypeName(String deviceTypeName) {
                this.deviceTypeName = deviceTypeName;
            }
        }
    }
}
