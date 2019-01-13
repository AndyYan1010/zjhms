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
     {
     "deviceTypeList": {
     "code": 1,
     "data": [
     {
     "devcieType": "0",
     "deviceDescibe": "中央控制器（POE/Lora）",
     "deviceTypeName": "010",
     "devcieTypePic": "upload/files\\20181019\\Hydrangeas.jpg",
     "id": "8a8a8a8a65f586b70165f590e3080007"
     },
     {
     "devcieType": "1",
     "deviceDescibe": "空气哨兵",
     "deviceTypeName": "031",
     "devcieTypePic": "upload/files\\20181019\\Lighthouse.jpg",
     "id": "8a8a8a8a65f586b70165f5921dd30009"
     },
     {
     "devcieType": "1",
     "deviceDescibe": "调光控制（灯控）",
     "deviceTypeName": "022",
     "devcieTypePic": "upload/files\\20181019\\Penguins.jpg",
     "id": "4d2881e9670b704601670b7681c50005"
     },
     {
     "devcieType": "1",
     "deviceDescibe": "计量控制（灯控）",
     "deviceTypeName": "021",
     "devcieTypePic": "upload/files\\20181019\\Tulips.jpg",
     "id": "8a8a8a8a667fd18b0166808d3bb50954"
     },
     {
     "devcieType": "2",
     "deviceDescibe": "体脂秤",
     "deviceTypeName": "091",
     "devcieTypePic": "upload/files\\20181019\\Jellyfish.jpg",
     "id": "4d2881e9670b704601670b80de6d0021"
     },
     {
     "devcieType": "2",
     "deviceDescibe": "空气哨兵WIFI",
     "deviceTypeName": "032",
     "devcieTypePic": "upload/files\\20181019\\Jellyfish.jpg",
     "id": "8a8a8a8a65f586b701661375eb180026"
     },
     {
     "devcieType": "3",
     "deviceDescibe": "红外遥控",
     "deviceTypeName": "051",
     "devcieTypePic": "upload/files\\20181019\\Penguins.jpg",
     "id": "8a8a8a8a65a7c55f0165a82d22170016"
     }
     ],
     "message": "类型查询查询成功"
     },
     "message": "设备分类查询成功",
     "code": 1
     }
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
         * code : 1
         * data : [{"devcieType":"0","deviceDescibe":"中央控制器（POE/Lora）","deviceTypeName":"010","devcieTypePic":"upload/files\\20181019\\Hydrangeas.jpg","id":"8a8a8a8a65f586b70165f590e3080007"},{"devcieType":"1","deviceDescibe":"空气哨兵","deviceTypeName":"031","devcieTypePic":"upload/files\\20181019\\Lighthouse.jpg","id":"8a8a8a8a65f586b70165f5921dd30009"},{"devcieType":"1","deviceDescibe":"计量控制（灯控）","deviceTypeName":"021","devcieTypePic":"upload/files\\20181019\\Tulips.jpg","id":"8a8a8a8a667fd18b0166808d3bb50954"},{"devcieType":"1","deviceDescibe":"调光控制（灯控）","deviceTypeName":"022","devcieTypePic":"upload/files\\20181019\\Penguins.jpg","id":"4d2881e9670b704601670b7681c50005"},{"devcieType":"3","deviceDescibe":"红外遥控","deviceTypeName":"051","devcieTypePic":"upload/files\\20181019\\Penguins.jpg","id":"8a8a8a8a65a7c55f0165a82d22170016"}]
         * message : 类型查询查询成功
         */

        private int code;
        private String         message;
        private List<DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * devcieType : 0
             * deviceDescibe : 中央控制器（POE/Lora）
             * deviceTypeName : 010
             * devcieTypePic : upload/files\20181019\Hydrangeas.jpg
             * id : 8a8a8a8a65f586b70165f590e3080007
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
}
