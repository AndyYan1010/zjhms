package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/29 13:19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HairCurrentInfo {

    /**
     * hair : {"vocType":0,"temperatureType":2,"formaldehyde":"0.00","humidityType":0,"co2":"552.00","pm100":"178.00","pm25Type":3,"voc":"0.00","co":"0.00","formaldehydeType":0,"pm25":"161.00","coType":0,"faddtime":"2018-11-29 13:16:50","temperature":"22.09","humidity":"51.80","co2Type":0,"status":false}
     * message : 空气检测查询成功
     * code : 1
     */

    private HairBean hair;
    private String message;
    private int    code;

    public HairBean getHair() {
        return hair;
    }

    public void setHair(HairBean hair) {
        this.hair = hair;
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

    public static class HairBean {
        /**
         * vocType : 0
         * temperatureType : 2
         * formaldehyde : 0.00
         * humidityType : 0
         * co2 : 552.00
         * pm100 : 178.00
         * pm25Type : 3
         * voc : 0.00
         * co : 0.00
         * formaldehydeType : 0
         * pm25 : 161.00
         * coType : 0
         * faddtime : 2018-11-29 13:16:50
         * temperature : 22.09
         * humidity : 51.80
         * co2Type : 0
         * status : false
         */

        private int vocType;
        private int     temperatureType;
        private String  formaldehyde;
        private int     humidityType;
        private String  co2;
        private String  pm100;
        private int     pm25Type;
        private String  voc;
        private String  co;
        private int     formaldehydeType;
        private String  pm25;
        private int     coType;
        private String  faddtime;
        private String  temperature;
        private String  humidity;
        private int     co2Type;
        private boolean status;

        public int getVocType() {
            return vocType;
        }

        public void setVocType(int vocType) {
            this.vocType = vocType;
        }

        public int getTemperatureType() {
            return temperatureType;
        }

        public void setTemperatureType(int temperatureType) {
            this.temperatureType = temperatureType;
        }

        public String getFormaldehyde() {
            return formaldehyde;
        }

        public void setFormaldehyde(String formaldehyde) {
            this.formaldehyde = formaldehyde;
        }

        public int getHumidityType() {
            return humidityType;
        }

        public void setHumidityType(int humidityType) {
            this.humidityType = humidityType;
        }

        public String getCo2() {
            return co2;
        }

        public void setCo2(String co2) {
            this.co2 = co2;
        }

        public String getPm100() {
            return pm100;
        }

        public void setPm100(String pm100) {
            this.pm100 = pm100;
        }

        public int getPm25Type() {
            return pm25Type;
        }

        public void setPm25Type(int pm25Type) {
            this.pm25Type = pm25Type;
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

        public int getFormaldehydeType() {
            return formaldehydeType;
        }

        public void setFormaldehydeType(int formaldehydeType) {
            this.formaldehydeType = formaldehydeType;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public int getCoType() {
            return coType;
        }

        public void setCoType(int coType) {
            this.coType = coType;
        }

        public String getFaddtime() {
            return faddtime;
        }

        public void setFaddtime(String faddtime) {
            this.faddtime = faddtime;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public int getCo2Type() {
            return co2Type;
        }

        public void setCo2Type(int co2Type) {
            this.co2Type = co2Type;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
