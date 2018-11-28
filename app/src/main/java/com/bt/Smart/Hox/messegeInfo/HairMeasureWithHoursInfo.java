package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/28 13:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HairMeasureWithHoursInfo {

    /**
     * hairList : [{"formaldehyde":0,"pm25":127,"fdeviceid":"0311800001","co2":652,"temperature":22.19818,"pm100":145,"faddtime":"2018-11-28 13:34:16","humidity":52.063507,"id":"a5d45dbdbed44d14b8e8b81a30abf740","voc":0,"co":0},{"formaldehyde":0,"pm25":120,"fdeviceid":"0311800001","co2":460,"temperature":20.986242,"pm100":138,"faddtime":"2018-11-28 09:29:57","humidity":52.77304,"id":"bcdbc487999746caab61ad8291eda80e","voc":0,"co":0}]
     * message : 空气检测查询成功
     * code : 1
     */

    private String message;
    private int                code;
    private List<HairListBean> hairList;

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

    public List<HairListBean> getHairList() {
        return hairList;
    }

    public void setHairList(List<HairListBean> hairList) {
        this.hairList = hairList;
    }

    public static class HairListBean {
        /**
         * formaldehyde : 0
         * pm25 : 127
         * fdeviceid : 0311800001
         * co2 : 652
         * temperature : 22.19818
         * pm100 : 145
         * faddtime : 2018-11-28 13:34:16
         * humidity : 52.063507
         * id : a5d45dbdbed44d14b8e8b81a30abf740
         * voc : 0
         * co : 0
         */

        private int formaldehyde;
        private int    pm25;
        private String fdeviceid;
        private int    co2;
        private double temperature;
        private int    pm100;
        private String faddtime;
        private double humidity;
        private String id;
        private int    voc;
        private int    co;

        public int getFormaldehyde() {
            return formaldehyde;
        }

        public void setFormaldehyde(int formaldehyde) {
            this.formaldehyde = formaldehyde;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public String getFdeviceid() {
            return fdeviceid;
        }

        public void setFdeviceid(String fdeviceid) {
            this.fdeviceid = fdeviceid;
        }

        public int getCo2() {
            return co2;
        }

        public void setCo2(int co2) {
            this.co2 = co2;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getPm100() {
            return pm100;
        }

        public void setPm100(int pm100) {
            this.pm100 = pm100;
        }

        public String getFaddtime() {
            return faddtime;
        }

        public void setFaddtime(String faddtime) {
            this.faddtime = faddtime;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getVoc() {
            return voc;
        }

        public void setVoc(int voc) {
            this.voc = voc;
        }

        public int getCo() {
            return co;
        }

        public void setCo(int co) {
            this.co = co;
        }
    }
}
