package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/29 17:44
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceTypeListInfo {

    /**
     * deviceTypeList : [{"devcieType":"2","deviceDescibe":"智能插座","deviceTypeName":"081","devcieTypePic":"upload/files\\20181019\\Hydrangeas.jpg","id":"4d2881e9670b704601670b7fbb01001c"},{"devcieType":"2","deviceDescibe":"体脂秤","deviceTypeName":"091","devcieTypePic":"upload/files\\20181019\\Jellyfish.jpg","id":"4d2881e9670b704601670b80de6d0021"}]
     * message : 设备分类查询成功
     * code : 1
     */

    private String message;
    private int                      code;
    private List<DeviceTypeListBean> deviceTypeList;

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

    public List<DeviceTypeListBean> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<DeviceTypeListBean> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    public static class DeviceTypeListBean {
        /**
         * devcieType : 2
         * deviceDescibe : 智能插座
         * deviceTypeName : 081
         * devcieTypePic : upload/files\20181019\Hydrangeas.jpg
         * id : 4d2881e9670b704601670b7fbb01001c
         */

        private String devcieType;
        private String deviceDescibe;
        private String deviceTypeName;
        private String devcieTypePic;
        private String id;

        public String getDevcieType() {
            return devcieType;
        }

        public void setDevcieType(String devcieType) {
            this.devcieType = devcieType;
        }

        public String getDeviceDescibe() {
            return deviceDescibe;
        }

        public void setDeviceDescibe(String deviceDescibe) {
            this.deviceDescibe = deviceDescibe;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDevcieTypePic() {
            return devcieTypePic;
        }

        public void setDevcieTypePic(String devcieTypePic) {
            this.devcieTypePic = devcieTypePic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
