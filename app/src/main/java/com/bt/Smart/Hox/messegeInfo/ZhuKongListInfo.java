package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/4 13:22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ZhuKongListInfo {

    /**
     * homeList : [{"main_control_name":"HoxONE主控","create_time":{"date":30,"day":2,"hours":9,"minutes":0,"month":9,"nanos":0,"seconds":9,"time":1540861209000,"timezoneOffset":-480,"year":118},"main_control_type":"HoxONE","main_control_code":"00000001","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"f6235486dbde11e8813c000c2950df04"}]
     * message : 主控列表查询成功
     * code : 1
     */

    private String message;
    private int                code;
    private List<HomeListBean> homeList;

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

    public List<HomeListBean> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<HomeListBean> homeList) {
        this.homeList = homeList;
    }

    public static class HomeListBean {
        /**
         * main_control_name : HoxONE主控
         * create_time : {"date":30,"day":2,"hours":9,"minutes":0,"month":9,"nanos":0,"seconds":9,"time":1540861209000,"timezoneOffset":-480,"year":118}
         * main_control_type : HoxONE
         * main_control_code : 00000001
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * id : f6235486dbde11e8813c000c2950df04
         */

        private String main_control_name;
        private CreateTimeBean create_time;
        private String         main_control_type;
        private String         main_control_code;
        private String         home_id;
        private String         id;

        public String getMain_control_name() {
            return main_control_name;
        }

        public void setMain_control_name(String main_control_name) {
            this.main_control_name = main_control_name;
        }

        public CreateTimeBean getCreate_time() {
            return create_time;
        }

        public void setCreate_time(CreateTimeBean create_time) {
            this.create_time = create_time;
        }

        public String getMain_control_type() {
            return main_control_type;
        }

        public void setMain_control_type(String main_control_type) {
            this.main_control_type = main_control_type;
        }

        public String getMain_control_code() {
            return main_control_code;
        }

        public void setMain_control_code(String main_control_code) {
            this.main_control_code = main_control_code;
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
             * minutes : 0
             * month : 9
             * nanos : 0
             * seconds : 9
             * time : 1540861209000
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
