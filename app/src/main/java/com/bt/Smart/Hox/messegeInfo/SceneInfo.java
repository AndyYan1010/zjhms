package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 10:20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneInfo {

    /**
     * scenelist : [{"scene_status":"0","home_id":"d03949369e494186add23a2211858f5b","id":"8ea296e5-7501-4f93-86a7-f0e36967d6b9","create_date":{"date":22,"day":4,"hours":10,"minutes":37,"month":10,"nanos":0,"seconds":0,"time":1542854220000,"timezoneOffset":-480,"year":118},"scene_name":"是多少","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg"},{"scene_status":"0","home_id":"d03949369e494186add23a2211858f5b","id":"9a327794-c2d1-485a-a71d-683d847122bc","create_date":{"date":22,"day":4,"hours":11,"minutes":34,"month":10,"nanos":0,"seconds":49,"time":1542857689000,"timezoneOffset":-480,"year":118},"scene_name":"顾客","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-vforum-banner-muti-san-pics_1541681958703.jpg"},{"scene_status":"0","home_id":"d03949369e494186add23a2211858f5b","id":"bdf5a360-ca6d-4940-9fe3-6a71fd8dac7b","create_date":{"date":22,"day":4,"hours":9,"minutes":0,"month":10,"nanos":0,"seconds":29,"time":1542848429000,"timezoneOffset":-480,"year":118},"scene_name":"测试","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg"},{"scene_status":"0","home_id":"d03949369e494186add23a2211858f5b","id":"d1a7efc5-0c76-4a3b-9b61-7bb047343e72","create_date":{"date":22,"day":4,"hours":11,"minutes":28,"month":10,"nanos":0,"seconds":6,"time":1542857286000,"timezoneOffset":-480,"year":118},"scene_name":"发货快","scene_pic":"http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-vforum-banner-muti-san-pics_1541681958703.jpg"}]
     * message : 场景查询成功
     * code : 1
     */

    private String              message;
    private int                 code;
    private List<ScenelistBean> scenelist;

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

    public List<ScenelistBean> getScenelist() {
        return scenelist;
    }

    public void setScenelist(List<ScenelistBean> scenelist) {
        this.scenelist = scenelist;
    }

    public static class ScenelistBean {
        /**
         * scene_status : 0
         * home_id : d03949369e494186add23a2211858f5b
         * id : 8ea296e5-7501-4f93-86a7-f0e36967d6b9
         * create_date : {"date":22,"day":4,"hours":10,"minutes":37,"month":10,"nanos":0,"seconds":0,"time":1542854220000,"timezoneOffset":-480,"year":118}
         * scene_name : 是多少
         * scene_pic : http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg
         */

        private String         scene_status;
        private String         home_id;
        private String         id;
        private CreateDateBean create_date;
        private String         scene_name;
        private String         scene_pic;
        /**
         * show_status : 1
         */

        private String         show_status;

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
             * hours : 10
             * minutes : 37
             * month : 10
             * nanos : 0
             * seconds : 0
             * time : 1542854220000
             * timezoneOffset : -480
             * year : 118
             */

            private int  date;
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
