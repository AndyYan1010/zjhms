package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 13:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneDetailInfoNew {

    /**
     * sceneDetail : [{"scene_status":"0","home_id":"d03949369e494186add23a2211858f5b","id":"8ea296e5-7501-4f93-86a7-f0e36967d6b9","create_date":{"date":22,"day":4,"hours":13,"minutes":18,"month":10,"nanos":0,"seconds":13,"time":1542863893000,"timezoneOffset":-480,"year":118},"scene_name":"是多少","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg"}]
     * sceneDeviceDetail : [{"device_status":"1","device_name":"调光控制（灯控）","device_id":"00f5350d73b14d87b54e23d58d03e3e0","device_value":"50","default_device_type":"022","id":"7d464828edff11e8b051000c29b0f385"},{"device_status":"1","device_name":"计量控制（灯控）","device_id":"1b1db0203a7f401f9cedbc1d5783c2e4","device_value":"1","default_device_type":"021","id":"7d48827cedff11e8b051000c29b0f385"},{"device_status":"1","device_name":"空气哨兵","device_id":"b0dd9a6e0f144deaa38939128c3613ae","device_value":"50","default_device_type":"022","id":"7d4aef9cedff11e8b051000c29b0f385"}]
     * message : 场景详情查询成功
     * code : 1
     */

    private String message;
    private int                         code;
    private List<SceneDetailBean>       sceneDetail;
    private List<SceneDeviceDetailBean> sceneDeviceDetail;

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

    public List<SceneDetailBean> getSceneDetail() {
        return sceneDetail;
    }

    public void setSceneDetail(List<SceneDetailBean> sceneDetail) {
        this.sceneDetail = sceneDetail;
    }

    public List<SceneDeviceDetailBean> getSceneDeviceDetail() {
        return sceneDeviceDetail;
    }

    public void setSceneDeviceDetail(List<SceneDeviceDetailBean> sceneDeviceDetail) {
        this.sceneDeviceDetail = sceneDeviceDetail;
    }

    public static class SceneDetailBean {
        /**
         * scene_status : 0
         * home_id : d03949369e494186add23a2211858f5b
         * id : 8ea296e5-7501-4f93-86a7-f0e36967d6b9
         * create_date : {"date":22,"day":4,"hours":13,"minutes":18,"month":10,"nanos":0,"seconds":13,"time":1542863893000,"timezoneOffset":-480,"year":118}
         * scene_name : 是多少
         * scene_pic : http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg
         */

        private String scene_status;
        private String         home_id;
        private String         id;
        private CreateDateBean create_date;
        private String         scene_name;
        private String         scene_pic;
        /**
         * show_status : 1
         */

        private String show_status;

        public String getScene_status() {
            return scene_status;
        }

        public void setScene_status(String scene_status) {
            this.scene_status = scene_status;
        }

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CreateDateBean getCreate_date() {
            return create_date;
        }

        public void setCreate_date(CreateDateBean create_date) {
            this.create_date = create_date;
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

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public static class CreateDateBean {
            /**
             * date : 22
             * day : 4
             * hours : 13
             * minutes : 18
             * month : 10
             * nanos : 0
             * seconds : 13
             * time : 1542863893000
             * timezoneOffset : -480
             * year : 118
             */

            private int date;
            private int  day;
            private int  hours;
            private int  minutes;
            private int  month;
            private int  nanos;
            private int  seconds;
            private long time;
            private int  timezoneOffset;
            private int  year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }

    public static class SceneDeviceDetailBean {
        /**
         * device_status : 1
         * device_name : 调光控制（灯控）
         * device_id : 00f5350d73b14d87b54e23d58d03e3e0
         * device_value : 50
         * default_device_type : 022
         * id : 7d464828edff11e8b051000c29b0f385
         */

        private String device_status;
        private String device_name;
        private String device_id;
        private String device_value;
        private String default_device_type;
        private String id;
        /**
         * main_control_code : 0101800001
         */

        private String main_control_code;

        public String getDevice_status() {
            return device_status;
        }

        public void setDevice_status(String device_status) {
            this.device_status = device_status;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_value() {
            return device_value;
        }

        public void setDevice_value(String device_value) {
            this.device_value = device_value;
        }

        public String getDefault_device_type() {
            return default_device_type;
        }

        public void setDefault_device_type(String default_device_type) {
            this.default_device_type = default_device_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMain_control_code() {
            return main_control_code;
        }

        public void setMain_control_code(String main_control_code) {
            this.main_control_code = main_control_code;
        }
    }
}
