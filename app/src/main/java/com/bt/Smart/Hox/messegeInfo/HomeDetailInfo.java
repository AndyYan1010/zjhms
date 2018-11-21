package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 11:19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeDetailInfo {

    /**
     * home : {"home_pic":"undefined","faddress":"江苏省南通市海门市","home_name":"家001","id":"d03949369e494186add23a2211858f5b","create_date":{"date":20,"day":2,"hours":13,"minutes":10,"month":10,"nanos":0,"seconds":23,"time":1542690623000,"timezoneOffset":-480,"year":118},"register_id":"74ee5a6bdb5911e8813c000c2950df04"}
     * houseCount : 5
     * message : 家查询成功
     * code : 1
     */

    private HomeBean home;
    private int    houseCount;
    private String message;
    private int    code;

    public HomeBean getHome() {
        return home;
    }

    public void setHome(HomeBean home) {
        this.home = home;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
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

    public static class HomeBean {
        /**
         * home_pic : undefined
         * faddress : 江苏省南通市海门市
         * home_name : 家001
         * id : d03949369e494186add23a2211858f5b
         * create_date : {"date":20,"day":2,"hours":13,"minutes":10,"month":10,"nanos":0,"seconds":23,"time":1542690623000,"timezoneOffset":-480,"year":118}
         * register_id : 74ee5a6bdb5911e8813c000c2950df04
         */

        private String home_pic;
        private String         faddress;
        private String         home_name;
        private String         id;
        private CreateDateBean create_date;
        private String         register_id;

        public String getHome_pic() {
            return home_pic;
        }

        public void setHome_pic(String home_pic) {
            this.home_pic = home_pic;
        }

        public String getFaddress() {
            return faddress;
        }

        public void setFaddress(String faddress) {
            this.faddress = faddress;
        }

        public String getHome_name() {
            return home_name;
        }

        public void setHome_name(String home_name) {
            this.home_name = home_name;
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

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public static class CreateDateBean {
            /**
             * date : 20
             * day : 2
             * hours : 13
             * minutes : 10
             * month : 10
             * nanos : 0
             * seconds : 23
             * time : 1542690623000
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
