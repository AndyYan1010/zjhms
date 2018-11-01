package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/1 16:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HouseDeviceInfo {

    /**
     * deviceHouseList : [{"deviceType":"1","device_config":"1","device_status":"0","house_id":"08e70f9e59df4c7fbb119add8490a63d","create_time":{"date":30,"day":2,"hours":9,"minutes":6,"month":9,"nanos":0,"seconds":32,"time":1540861592000,"timezoneOffset":-480,"year":118},"second_control_id":"da157ea9dbdf11e8813c000c2950df04","main_control_id":"f6235486dbde11e8813c000c2950df04","second_control_name":"HAir空气哨兵(有线)","main_control_code":"00000001","type":1,"device_img":"","main_control_name":"HoxONE主控","device_name":"HAir空气哨兵(有线)","device_code":"00123456","default_device_type":"HAir(有线)","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"1c7d954bb5e044488886fd66052316b5"}]
     * message : 房间设备列表查询成功
     * code : 1
     */

    private String message;
    private int                       code;
    private List<DeviceHouseListBean> deviceHouseList;

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

    public List<DeviceHouseListBean> getDeviceHouseList() {
        return deviceHouseList;
    }

    public void setDeviceHouseList(List<DeviceHouseListBean> deviceHouseList) {
        this.deviceHouseList = deviceHouseList;
    }

    public static class DeviceHouseListBean {
        /**
         * deviceType : 1
         * device_config : 1
         * device_status : 0
         * house_id : 08e70f9e59df4c7fbb119add8490a63d
         * create_time : {"date":30,"day":2,"hours":9,"minutes":6,"month":9,"nanos":0,"seconds":32,"time":1540861592000,"timezoneOffset":-480,"year":118}
         * second_control_id : da157ea9dbdf11e8813c000c2950df04
         * main_control_id : f6235486dbde11e8813c000c2950df04
         * second_control_name : HAir空气哨兵(有线)
         * main_control_code : 00000001
         * type : 1
         * device_img :
         * main_control_name : HoxONE主控
         * device_name : HAir空气哨兵(有线)
         * device_code : 00123456
         * default_device_type : HAir(有线)
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * id : 1c7d954bb5e044488886fd66052316b5
         */

        private String deviceType;
        private String         device_config;
        private String         device_status;
        private String         house_id;
        private CreateTimeBean create_time;
        private String         second_control_id;
        private String         main_control_id;
        private String         second_control_name;
        private String         main_control_code;
        private int            type;
        private String         device_img;
        private String         main_control_name;
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

        public String getSecond_control_name() {
            return second_control_name;
        }

        public void setSecond_control_name(String second_control_name) {
            this.second_control_name = second_control_name;
        }

        public String getMain_control_code() {
            return main_control_code;
        }

        public void setMain_control_code(String main_control_code) {
            this.main_control_code = main_control_code;
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
             * date : 30
             * day : 2
             * hours : 9
             * minutes : 6
             * month : 9
             * nanos : 0
             * seconds : 32
             * time : 1540861592000
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
