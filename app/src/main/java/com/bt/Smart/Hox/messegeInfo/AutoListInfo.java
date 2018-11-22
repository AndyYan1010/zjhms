package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 10:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoListInfo {

    /**
     * autolist : [{"create_time":{"date":22,"day":4,"hours":16,"minutes":55,"month":10,"nanos":0,"seconds":13,"time":1542876913000,"timezoneOffset":-480,"year":118},"auto_status":"0","auto_name":"测试001","home_id":"d03949369e494186add23a2211858f5b","id":"48b2d18e-f7cc-4177-973c-685bb89ada81","auto_type":"0"}]
     * message : 自动化查询成功
     * code : 1
     */

    private String message;
    private int                code;
    private List<AutolistBean> autolist;

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

    public List<AutolistBean> getAutolist() {
        return autolist;
    }

    public void setAutolist(List<AutolistBean> autolist) {
        this.autolist = autolist;
    }

    public static class AutolistBean {
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
}
