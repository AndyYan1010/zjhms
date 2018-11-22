package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 16:43
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoOnlyCGQListInfo {

    /**
     * HA3list : [{"deviceType":"1","device_config":"1","device_status":"0","house_id":"cf578896da894d10916177f8b896bda6","create_time":{"date":21,"day":3,"hours":15,"minutes":9,"month":10,"nanos":0,"seconds":38,"time":1542784178000,"timezoneOffset":-480,"year":118},"second_control_id":"68f9ae6ced5c11e8b051000c29b0f385","main_control_id":"9753f046ebba11e8b051000c29b0f385","main_control_code":"0101800001","device_img":"","device_name":"空气哨兵001","device_code":"0311800003","default_device_type":"031","home_id":"d03949369e494186add23a2211858f5b","id":"af71298bddd24516b9fa9cbb6a748eea"}]
     * message : 家下面的传感器查询成功
     * code : 1
     */

    private String message;
    private int               code;
    private List<HA3listBean> HA3list;

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

    public List<HA3listBean> getHA3list() {
        return HA3list;
    }

    public void setHA3list(List<HA3listBean> HA3list) {
        this.HA3list = HA3list;
    }

    public static class HA3listBean {
        /**
         * deviceType : 1
         * device_config : 1
         * device_status : 0
         * house_id : cf578896da894d10916177f8b896bda6
         * create_time : {"date":21,"day":3,"hours":15,"minutes":9,"month":10,"nanos":0,"seconds":38,"time":1542784178000,"timezoneOffset":-480,"year":118}
         * second_control_id : 68f9ae6ced5c11e8b051000c29b0f385
         * main_control_id : 9753f046ebba11e8b051000c29b0f385
         * main_control_code : 0101800001
         * device_img :
         * device_name : 空气哨兵001
         * device_code : 0311800003
         * default_device_type : 031
         * home_id : d03949369e494186add23a2211858f5b
         * id : af71298bddd24516b9fa9cbb6a748eea
         */

        private String deviceType;
        private String         device_config;
        private String         device_status;
        private String         house_id;
        private CreateTimeBean create_time;
        private String         second_control_id;
        private String         main_control_id;
        private String         main_control_code;
        private String         device_img;
        private String         device_name;
        private String         device_code;
        private String         default_device_type;
        private String         home_id;
        private String         id;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDevice_config() {
            return device_config;
        }

        public void setDevice_config(String device_config) {
            this.device_config = device_config;
        }

        public String getDevice_status() {
            return device_status;
        }

        public void setDevice_status(String device_status) {
            this.device_status = device_status;
        }

        public String getHouse_id() {
            return house_id;
        }

        public void setHouse_id(String house_id) {
            this.house_id = house_id;
        }

        public CreateTimeBean getCreate_time() {
            return create_time;
        }

        public void setCreate_time(CreateTimeBean create_time) {
            this.create_time = create_time;
        }

        public String getSecond_control_id() {
            return second_control_id;
        }

        public void setSecond_control_id(String second_control_id) {
            this.second_control_id = second_control_id;
        }

        public String getMain_control_id() {
            return main_control_id;
        }

        public void setMain_control_id(String main_control_id) {
            this.main_control_id = main_control_id;
        }

        public String getMain_control_code() {
            return main_control_code;
        }

        public void setMain_control_code(String main_control_code) {
            this.main_control_code = main_control_code;
        }

        public String getDevice_img() {
            return device_img;
        }

        public void setDevice_img(String device_img) {
            this.device_img = device_img;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_code() {
            return device_code;
        }

        public void setDevice_code(String device_code) {
            this.device_code = device_code;
        }

        public String getDefault_device_type() {
            return default_device_type;
        }

        public void setDefault_device_type(String default_device_type) {
            this.default_device_type = default_device_type;
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

        public static class CreateTimeBean {
            /**
             * date : 21
             * day : 3
             * hours : 15
             * minutes : 9
             * month : 10
             * nanos : 0
             * seconds : 38
             * time : 1542784178000
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
}
