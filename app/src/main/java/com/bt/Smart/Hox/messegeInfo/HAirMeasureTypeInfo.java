package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/28 15:28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HAirMeasureTypeInfo {

    /**
     * hairList : [{"temperature":"23.24","faddtime":"2018-11-28 14:45:50"},{"temperature":"20.99","faddtime":"2018-11-28 09:29:57"}]
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
         * temperature : 23.24
         * faddtime : 2018-11-28 14:45:50
         */

        private String temperature;
        private String faddtime;
        /**
         * humidity :
         * pm25 :
         * pm100 :
         * formaldehyde :
         * voc :
         * co :
         */

        private String humidity;
        private String pm25;
        private String pm100;
        private String formaldehyde;
        private String voc;
        private String co;
        /**
         * co2 :
         */

        private String co2;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getFaddtime() {
            return faddtime;
        }

        public void setFaddtime(String faddtime) {
            this.faddtime = faddtime;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm100() {
            return pm100;
        }

        public void setPm100(String pm100) {
            this.pm100 = pm100;
        }

        public String getFormaldehyde() {
            return formaldehyde;
        }

        public void setFormaldehyde(String formaldehyde) {
            this.formaldehyde = formaldehyde;
        }

        public String getVoc() {
            return voc;
        }

        public void setVoc(String voc) {
            this.voc = voc;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getCo2() {
            return co2;
        }

        public void setCo2(String co2) {
            this.co2 = co2;
        }
    }
}
