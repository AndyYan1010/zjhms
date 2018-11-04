package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/4 14:01
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CongKongListInfo {

    /**
     * secondControlList : [{"deviceType":"1","second_contrl_code":"01000001","second_control_category":"lamp","create_time":{"date":1,"day":4,"hours":16,"minutes":28,"month":10,"nanos":0,"seconds":48,"time":1541060928000,"timezoneOffset":-480,"year":118},"main_control_id":"f6235486dbde11e8813c000c2950df04","main_control_code":"00000001","second_control_name":"灯控","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"27ce0d36ddb011e89c91000c2950df04"},{"deviceType":"1","second_contrl_code":"00123456","second_control_category":"HAir(有线)","create_time":{"date":1,"day":4,"hours":16,"minutes":27,"month":10,"nanos":0,"seconds":39,"time":1541060859000,"timezoneOffset":-480,"year":118},"main_control_id":"f6235486dbde11e8813c000c2950df04","main_control_code":"00000001","second_control_name":"HAir空气哨兵(有线)","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"fe869542ddaf11e89c91000c2950df04"}]
     * message : 从控列表查询成功
     * code : 1
     */

    private String message;
    private int                         code;
    private List<SecondControlListBean> secondControlList;

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

    public List<SecondControlListBean> getSecondControlList() {
        return secondControlList;
    }

    public void setSecondControlList(List<SecondControlListBean> secondControlList) {
        this.secondControlList = secondControlList;
    }

    public static class SecondControlListBean {
        /**
         * deviceType : 1
         * second_contrl_code : 01000001
         * second_control_category : lamp
         * create_time : {"date":1,"day":4,"hours":16,"minutes":28,"month":10,"nanos":0,"seconds":48,"time":1541060928000,"timezoneOffset":-480,"year":118}
         * main_control_id : f6235486dbde11e8813c000c2950df04
         * main_control_code : 00000001
         * second_control_name : 灯控
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * id : 27ce0d36ddb011e89c91000c2950df04
         */

        private String deviceType;
        private String         second_contrl_code;
        private String         second_control_category;
        private CreateTimeBean create_time;
        private String         main_control_id;
        private String         main_control_code;
        private String         second_control_name;
        private String         home_id;
        private String         id;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getSecond_contrl_code() {
            return second_contrl_code;
        }

        public void setSecond_contrl_code(String second_contrl_code) {
            this.second_contrl_code = second_contrl_code;
        }

        public String getSecond_control_category() {
            return second_control_category;
        }

        public void setSecond_control_category(String second_control_category) {
            this.second_control_category = second_control_category;
        }

        public CreateTimeBean getCreate_time() {
            return create_time;
        }

        public void setCreate_time(CreateTimeBean create_time) {
            this.create_time = create_time;
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

        public String getSecond_control_name() {
            return second_control_name;
        }

        public void setSecond_control_name(String second_control_name) {
            this.second_control_name = second_control_name;
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
             * date : 1
             * day : 4
             * hours : 16
             * minutes : 28
             * month : 10
             * nanos : 0
             * seconds : 48
             * time : 1541060928000
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
