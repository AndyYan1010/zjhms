package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/28 8:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoginInfo {

    /**
     * message : 用户登录成功
     * memberInfo : {"wx_name":"QW5keSBZYW4=","fstatus":"0","wx_openid":"oGm3u0BHZwOPxMrEs-187NnVIquM","wx_pic":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJSoHRiasmEsSO62akEkah2pWGbicFPjeOWlL69alkicTOrvicVciaE8OYqHPMXQUFJNlsTovQu2LtK4rw/132","bpmstatus":"1","id":"74ee5a6bdb5911e8813c000c2950df04","fpassword":"e10adc3949ba59abbe56e057f20f883e","create_date":{"date":29,"day":1,"hours":17,"minutes":4,"month":9,"nanos":0,"seconds":30,"time":1540803870000,"timezoneOffset":-480,"year":118},"ftelephone":"18036215618"}
     * code : 1
     */

    private String message;
    private MemberInfoBean memberInfo;
    private int            code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MemberInfoBean getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfoBean memberInfo) {
        this.memberInfo = memberInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class MemberInfoBean {
        /**
         * wx_name : QW5keSBZYW4=
         * fstatus : 0
         * wx_openid : oGm3u0BHZwOPxMrEs-187NnVIquM
         * wx_pic : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJSoHRiasmEsSO62akEkah2pWGbicFPjeOWlL69alkicTOrvicVciaE8OYqHPMXQUFJNlsTovQu2LtK4rw/132
         * bpmstatus : 1
         * id : 74ee5a6bdb5911e8813c000c2950df04
         * fpassword : e10adc3949ba59abbe56e057f20f883e
         * create_date : {"date":29,"day":1,"hours":17,"minutes":4,"month":9,"nanos":0,"seconds":30,"time":1540803870000,"timezoneOffset":-480,"year":118}
         * ftelephone : 18036215618
         */

        private String wx_name;
        private String         fstatus;
        private String         wx_openid;
        private String         wx_pic;
        private String         bpmstatus;
        private String         id;
        private String         fpassword;
        private CreateDateBean create_date;
        private String         ftelephone;

        public String getWx_name() {
            return wx_name;
        }

        public void setWx_name(String wx_name) {
            this.wx_name = wx_name;
        }

        public String getFstatus() {
            return fstatus;
        }

        public void setFstatus(String fstatus) {
            this.fstatus = fstatus;
        }

        public String getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(String wx_openid) {
            this.wx_openid = wx_openid;
        }

        public String getWx_pic() {
            return wx_pic;
        }

        public void setWx_pic(String wx_pic) {
            this.wx_pic = wx_pic;
        }

        public String getBpmstatus() {
            return bpmstatus;
        }

        public void setBpmstatus(String bpmstatus) {
            this.bpmstatus = bpmstatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFpassword() {
            return fpassword;
        }

        public void setFpassword(String fpassword) {
            this.fpassword = fpassword;
        }

        public CreateDateBean getCreate_date() {
            return create_date;
        }

        public void setCreate_date(CreateDateBean create_date) {
            this.create_date = create_date;
        }

        public String getFtelephone() {
            return ftelephone;
        }

        public void setFtelephone(String ftelephone) {
            this.ftelephone = ftelephone;
        }

        public static class CreateDateBean {
            /**
             * date : 29
             * day : 1
             * hours : 17
             * minutes : 4
             * month : 9
             * nanos : 0
             * seconds : 30
             * time : 1540803870000
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
