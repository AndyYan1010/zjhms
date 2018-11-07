package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/7 18:28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class PlayListInfo {

    /**
     * playList : [{"sys_org_code":"A03","play_introduce":"介绍晒撒大1","play_content":"<p style=\"text-align: center;\"><strong><span style=\"font-size: 20px;\"><img title=\"huawei.jpg\" src=\"http://112.90.178.68:8081/hox/plug-in/ueditor/jsp/upload1/20181106/87851541492034257.jpg\"/><\/span><\/strong><\/p><p style=\"line-height: 1.75em;\">&nbsp;<\/p><p style=\"line-height: 1.75em;\"><strong><span style=\"font-size: 20px;\"><br/><\/span><\/strong><\/p><p><strong><span style=\"font-size: 20px;\">荣耀路由X2开启预售，1元订金享好礼！<\/span><\/strong><br/><\/p><p><br/><\/p><p><br/><\/p><section class=\"findDetails\"><section class=\"content\"><p><br/><\/p><p style=\"line-height: 1.75em;\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">双11的战争越来越响，<\/span><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">对于消费者来说，<\/span><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">则是一场拼手速、拼运气的战役，<\/span><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">网速就是抢占先机的利器，基于此，一款强大的路由器自然是剁手必备法宝。<\/span><\/span><\/p><p style=\"line-height: 1.75em;\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\"><br/><\/span><\/span><\/p><p style=\"line-height: 1.75em;\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">10月22日，荣耀路由新品-荣耀路由X2首发预售，新品主打五大核心卖点，即：双核芯片、双千兆、双独立信号放大器、双频优选以及双网双通，号称卓越\u201c五\u201d双。<\/span><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">涵盖如此多功能特性的荣耀路由X2仅售<\/span><strong style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">149元<\/strong><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">，非常适合对网速、性价比有高要求的小伙伴！<\/span><\/p><p style=\"line-height: 1.75em;\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\"><br/><\/span><\/p><p style=\"line-height: 1.75em;\"><span style=\"color: rgb(34, 34, 34); font-family:;\" helvetica=\"\" micro=\"\" wenquanyi=\"\" microsoft=\"\" sans=\"\" hiragino=\"\" pingfang=\"\">这款路由器有何出众之处？为何能成为刷新性价比的百元路由器？往下看！为你一一剖析：<\/span><\/p><\/section><\/section><p><br/><\/p><p><br/><\/p><p>&nbsp;<\/p>","play_label":"1","update_name":"管理员","update_date":{"date":6,"day":2,"hours":16,"minutes":56,"month":10,"nanos":0,"seconds":33,"time":1541494593000,"timezoneOffset":-480,"year":118},"create_by":"admin","fstatus":"1","sys_company_code":"A03","bpm_status":"1","play_title":"智能控制HoxONE多协议接入上线","id":4,"create_date":{"date":1,"day":4,"hours":11,"minutes":4,"month":10,"nanos":0,"seconds":58,"time":1541041498000,"timezoneOffset":-480,"year":118},"update_by":"admin","create_name":"管理员","play_pic":"upload/files/20181106/Desert_1541487369145.jpg"}]
     * message : 适玩列表查询成功
     * code : 1
     */

    private String message;
    private int                code;
    private List<PlayListBean> playList;

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

    public List<PlayListBean> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayListBean> playList) {
        this.playList = playList;
    }

    public static class PlayListBean {
        /**
         * sys_org_code : A03
         * play_introduce : 介绍晒撒大1
         * play_content : <p style="text-align: center;"><strong><span style="font-size: 20px;"><img title="huawei.jpg" src="http://112.90.178.68:8081/hox/plug-in/ueditor/jsp/upload1/20181106/87851541492034257.jpg"/></span></strong></p><p style="line-height: 1.75em;">&nbsp;</p><p style="line-height: 1.75em;"><strong><span style="font-size: 20px;"><br/></span></strong></p><p><strong><span style="font-size: 20px;">荣耀路由X2开启预售，1元订金享好礼！</span></strong><br/></p><p><br/></p><p><br/></p><section class="findDetails"><section class="content"><p><br/></p><p style="line-height: 1.75em;"><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">双11的战争越来越响，</span><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">对于消费者来说，</span><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang=""><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">则是一场拼手速、拼运气的战役，</span><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">网速就是抢占先机的利器，基于此，一款强大的路由器自然是剁手必备法宝。</span></span></p><p style="line-height: 1.75em;"><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang=""><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang=""><br/></span></span></p><p style="line-height: 1.75em;"><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">10月22日，荣耀路由新品-荣耀路由X2首发预售，新品主打五大核心卖点，即：双核芯片、双千兆、双独立信号放大器、双频优选以及双网双通，号称卓越“五”双。</span><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">涵盖如此多功能特性的荣耀路由X2仅售</span><strong style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">149元</strong><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">，非常适合对网速、性价比有高要求的小伙伴！</span></p><p style="line-height: 1.75em;"><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang=""><br/></span></p><p style="line-height: 1.75em;"><span style="color: rgb(34, 34, 34); font-family:;" helvetica="" micro="" wenquanyi="" microsoft="" sans="" hiragino="" pingfang="">这款路由器有何出众之处？为何能成为刷新性价比的百元路由器？往下看！为你一一剖析：</span></p></section></section><p><br/></p><p><br/></p><p>&nbsp;</p>
         * play_label : 1
         * update_name : 管理员
         * update_date : {"date":6,"day":2,"hours":16,"minutes":56,"month":10,"nanos":0,"seconds":33,"time":1541494593000,"timezoneOffset":-480,"year":118}
         * create_by : admin
         * fstatus : 1
         * sys_company_code : A03
         * bpm_status : 1
         * play_title : 智能控制HoxONE多协议接入上线
         * id : 4
         * create_date : {"date":1,"day":4,"hours":11,"minutes":4,"month":10,"nanos":0,"seconds":58,"time":1541041498000,"timezoneOffset":-480,"year":118}
         * update_by : admin
         * create_name : 管理员
         * play_pic : upload/files/20181106/Desert_1541487369145.jpg
         */

        private String sys_org_code;
        private String         play_introduce;
        private String         play_content;
        private String         play_label;
        private String         update_name;
        private UpdateDateBean update_date;
        private String         create_by;
        private String         fstatus;
        private String         sys_company_code;
        private String         bpm_status;
        private String         play_title;
        private int            id;
        private CreateDateBean create_date;
        private String         update_by;
        private String         create_name;
        private String         play_pic;

        public String getSys_org_code() {
            return sys_org_code;
        }

        public void setSys_org_code(String sys_org_code) {
            this.sys_org_code = sys_org_code;
        }

        public String getPlay_introduce() {
            return play_introduce;
        }

        public void setPlay_introduce(String play_introduce) {
            this.play_introduce = play_introduce;
        }

        public String getPlay_content() {
            return play_content;
        }

        public void setPlay_content(String play_content) {
            this.play_content = play_content;
        }

        public String getPlay_label() {
            return play_label;
        }

        public void setPlay_label(String play_label) {
            this.play_label = play_label;
        }

        public String getUpdate_name() {
            return update_name;
        }

        public void setUpdate_name(String update_name) {
            this.update_name = update_name;
        }

        public UpdateDateBean getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(UpdateDateBean update_date) {
            this.update_date = update_date;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getFstatus() {
            return fstatus;
        }

        public void setFstatus(String fstatus) {
            this.fstatus = fstatus;
        }

        public String getSys_company_code() {
            return sys_company_code;
        }

        public void setSys_company_code(String sys_company_code) {
            this.sys_company_code = sys_company_code;
        }

        public String getBpm_status() {
            return bpm_status;
        }

        public void setBpm_status(String bpm_status) {
            this.bpm_status = bpm_status;
        }

        public String getPlay_title() {
            return play_title;
        }

        public void setPlay_title(String play_title) {
            this.play_title = play_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CreateDateBean getCreate_date() {
            return create_date;
        }

        public void setCreate_date(CreateDateBean create_date) {
            this.create_date = create_date;
        }

        public String getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(String update_by) {
            this.update_by = update_by;
        }

        public String getCreate_name() {
            return create_name;
        }

        public void setCreate_name(String create_name) {
            this.create_name = create_name;
        }

        public String getPlay_pic() {
            return play_pic;
        }

        public void setPlay_pic(String play_pic) {
            this.play_pic = play_pic;
        }

        public static class UpdateDateBean {
            /**
             * date : 6
             * day : 2
             * hours : 16
             * minutes : 56
             * month : 10
             * nanos : 0
             * seconds : 33
             * time : 1541494593000
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

        public static class CreateDateBean {
            /**
             * date : 1
             * day : 4
             * hours : 11
             * minutes : 4
             * month : 10
             * nanos : 0
             * seconds : 58
             * time : 1541041498000
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
