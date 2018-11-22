package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 17:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoDetailInfoNew {

    /**
     * autodetail : [{"create_time":{"date":22,"day":4,"hours":16,"minutes":55,"month":10,"nanos":0,"seconds":13,"time":1542876913000,"timezoneOffset":-480,"year":118},"auto_status":"0","auto_name":"测试001","home_id":"d03949369e494186add23a2211858f5b","id":"48b2d18e-f7cc-4177-973c-685bb89ada81","auto_type":"0"}]
     * auto_iftime_list : [{"auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","if_type":"0","id":"45479ad5ee3411e8b051000c29b0f385","if_begin_time":"03:02:05","if_end_time":"07:02:08"}]
     * auto_ifha3_list : [{"if_value":"5","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","if_type":"1","if_ha3_id":"af71298bddd24516b9fa9cbb6a748eea","device_name":"空气哨兵001","if_select":"小于","if_select_type":"CO2"}]
     * auto_time_list : [{"auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","id":"453e8082ee3411e8b051000c29b0f385","fval":"1"},{"auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","id":"4540ec9eee3411e8b051000c29b0f385","fval":"3"},{"auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","id":"4543588fee3411e8b051000c29b0f385","fval":"5"},{"auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","id":"4545c5d1ee3411e8b051000c29b0f385","fval":"7"}]
     * auto_execute_device_list : [{"deviceType":"021","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","device_status":"1","device_name":"计量控制（灯控）","device_id":"1b1db0203a7f401f9cedbc1d5783c2e4","device_value":"1","id":"455640b1ee3411e8b051000c29b0f385"},{"deviceType":"022","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","device_status":"1","device_name":"调光控制（灯控）","device_id":"00f5350d73b14d87b54e23d58d03e3e0","device_value":"50","id":"455a8662ee3411e8b051000c29b0f385"},{"deviceType":"022","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","device_status":"1","device_name":"空气哨兵","device_id":"b0dd9a6e0f144deaa38939128c3613ae","device_value":"50","id":"455e2f79ee3411e8b051000c29b0f385"}]
     * auto_execute_scene_list : [{"scene_status":"1","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","scene_id":"9a327794-c2d1-485a-a71d-683d847122bc","id":"45609b26ee3411e8b051000c29b0f385","scene_name":"顾客","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-vforum-banner-muti-san-pics_1541681958703.jpg"},{"scene_status":"1","auto_id":"48b2d18e-f7cc-4177-973c-685bb89ada81","scene_id":"bdf5a360-ca6d-4940-9fe3-6a71fd8dac7b","id":"456447e4ee3411e8b051000c29b0f385","scene_name":"测试","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg"}]
     * message : 自动化详情查询成功
     * code : 1
     */

    private String message;
    private int                             code;
    private List<AutodetailBean>            autodetail;
    private List<AutoIftimeListBean>        auto_iftime_list;
    private List<AutoIfha3ListBean>         auto_ifha3_list;
    private List<AutoTimeListBean>          auto_time_list;
    private List<AutoExecuteDeviceListBean> auto_execute_device_list;
    private List<AutoExecuteSceneListBean>  auto_execute_scene_list;

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

    public List<AutodetailBean> getAutodetail() {
        return autodetail;
    }

    public void setAutodetail(List<AutodetailBean> autodetail) {
        this.autodetail = autodetail;
    }

    public List<AutoIftimeListBean> getAuto_iftime_list() {
        return auto_iftime_list;
    }

    public void setAuto_iftime_list(List<AutoIftimeListBean> auto_iftime_list) {
        this.auto_iftime_list = auto_iftime_list;
    }

    public List<AutoIfha3ListBean> getAuto_ifha3_list() {
        return auto_ifha3_list;
    }

    public void setAuto_ifha3_list(List<AutoIfha3ListBean> auto_ifha3_list) {
        this.auto_ifha3_list = auto_ifha3_list;
    }

    public List<AutoTimeListBean> getAuto_time_list() {
        return auto_time_list;
    }

    public void setAuto_time_list(List<AutoTimeListBean> auto_time_list) {
        this.auto_time_list = auto_time_list;
    }

    public List<AutoExecuteDeviceListBean> getAuto_execute_device_list() {
        return auto_execute_device_list;
    }

    public void setAuto_execute_device_list(List<AutoExecuteDeviceListBean> auto_execute_device_list) {
        this.auto_execute_device_list = auto_execute_device_list;
    }

    public List<AutoExecuteSceneListBean> getAuto_execute_scene_list() {
        return auto_execute_scene_list;
    }

    public void setAuto_execute_scene_list(List<AutoExecuteSceneListBean> auto_execute_scene_list) {
        this.auto_execute_scene_list = auto_execute_scene_list;
    }

    public static class AutodetailBean {
        /**
         * create_time : {"date":22,"day":4,"hours":16,"minutes":55,"month":10,"nanos":0,"seconds":13,"time":1542876913000,"timezoneOffset":-480,"year":118}
         * auto_status : 0
         * auto_name : 测试001
         * home_id : d03949369e494186add23a2211858f5b
         * id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * auto_type : 0
         */

        private CreateTimeBean create_time;
        private String auto_status;
        private String auto_name;
        private String home_id;
        private String id;
        private String auto_type;

        public CreateTimeBean getCreate_time() {
            return create_time;
        }

        public void setCreate_time(CreateTimeBean create_time) {
            this.create_time = create_time;
        }

        public String getAuto_status() {
            return auto_status;
        }

        public void setAuto_status(String auto_status) {
            this.auto_status = auto_status;
        }

        public String getAuto_name() {
            return auto_name;
        }

        public void setAuto_name(String auto_name) {
            this.auto_name = auto_name;
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

        public String getAuto_type() {
            return auto_type;
        }

        public void setAuto_type(String auto_type) {
            this.auto_type = auto_type;
        }

        public static class CreateTimeBean {
            /**
             * date : 22
             * day : 4
             * hours : 16
             * minutes : 55
             * month : 10
             * nanos : 0
             * seconds : 13
             * time : 1542876913000
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

    public static class AutoIftimeListBean {
        /**
         * auto_id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * if_type : 0
         * id : 45479ad5ee3411e8b051000c29b0f385
         * if_begin_time : 03:02:05
         * if_end_time : 07:02:08
         */

        private String auto_id;
        private String if_type;
        private String id;
        private String if_begin_time;
        private String if_end_time;

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

        public String getIf_type() {
            return if_type;
        }

        public void setIf_type(String if_type) {
            this.if_type = if_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
    }

    public static class AutoIfha3ListBean {
        /**
         * if_value : 5
         * auto_id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * if_type : 1
         * if_ha3_id : af71298bddd24516b9fa9cbb6a748eea
         * device_name : 空气哨兵001
         * if_select : 小于
         * if_select_type : CO2
         */

        private String if_value;
        private String auto_id;
        private String if_type;
        private String if_ha3_id;
        private String device_name;
        private String if_select;
        private String if_select_type;

        public String getIf_value() {
            return if_value;
        }

        public void setIf_value(String if_value) {
            this.if_value = if_value;
        }

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

        public String getIf_type() {
            return if_type;
        }

        public void setIf_type(String if_type) {
            this.if_type = if_type;
        }

        public String getIf_ha3_id() {
            return if_ha3_id;
        }

        public void setIf_ha3_id(String if_ha3_id) {
            this.if_ha3_id = if_ha3_id;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
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
    }

    public static class AutoTimeListBean {
        /**
         * auto_id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * id : 453e8082ee3411e8b051000c29b0f385
         * fval : 1
         */

        private String auto_id;
        private String id;
        private String fval;

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFval() {
            return fval;
        }

        public void setFval(String fval) {
            this.fval = fval;
        }
    }

    public static class AutoExecuteDeviceListBean {
        /**
         * deviceType : 021
         * auto_id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * device_status : 1
         * device_name : 计量控制（灯控）
         * device_id : 1b1db0203a7f401f9cedbc1d5783c2e4
         * device_value : 1
         * id : 455640b1ee3411e8b051000c29b0f385
         */

        private String deviceType;
        private String auto_id;
        private String device_status;
        private String device_name;
        private String device_id;
        private String device_value;
        private String id;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class AutoExecuteSceneListBean {
        /**
         * scene_status : 1
         * auto_id : 48b2d18e-f7cc-4177-973c-685bb89ada81
         * scene_id : 9a327794-c2d1-485a-a71d-683d847122bc
         * id : 45609b26ee3411e8b051000c29b0f385
         * scene_name : 顾客
         * scene_pic : http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-vforum-banner-muti-san-pics_1541681958703.jpg
         */

        private String scene_status;
        private String auto_id;
        private String scene_id;
        private String id;
        private String scene_name;
        private String scene_pic;

        public String getScene_status() {
            return scene_status;
        }

        public void setScene_status(String scene_status) {
            this.scene_status = scene_status;
        }

        public String getAuto_id() {
            return auto_id;
        }

        public void setAuto_id(String auto_id) {
            this.auto_id = auto_id;
        }

        public String getScene_id() {
            return scene_id;
        }

        public void setScene_id(String scene_id) {
            this.scene_id = scene_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
