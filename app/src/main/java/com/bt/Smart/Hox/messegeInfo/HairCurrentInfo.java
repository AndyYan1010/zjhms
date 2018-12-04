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
     * hair : {"vocType":0,"temperatureType":2,"formaldehyde":0,"humidityType":0,"co2":522,"pm100":31,"pm25Type":0,"voc":0,"co":0,"formaldehydeType":0,"pm25":29,"coType":0,"faddtime":"2018-12-04 15:11:30","temperature":22.24,"humidity":57.63,"co2Type":0,"status":true}
     * message : 空气检测查询成功
     * code : 1
     */

    private HairBean hair;
    private String   message;
    private int      code;

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
         * formaldehyde : 0
         * humidityType : 0
         * co2 : 522
         * pm100 : 31
         * pm25Type : 0
         * voc : 0
         * co : 0
         * formaldehydeType : 0
         * pm25 : 29
         * coType : 0
         * faddtime : 2018-12-04 15:11:30
         * temperature : 22.24
         * humidity : 57.63
         * co2Type : 0
         * status : true
         */

        private float   vocType;
        private float   temperatureType;
        private float   formaldehyde;
        private float   humidityType;
        private float   co2;
        private float   pm100;
        private float   pm25Type;
        private float   voc;
        private float   co;
        private float   formaldehydeType;
        private float   pm25;
        private float   coType;
        private String  faddtime;
        private float   temperature;
        private float   humidity;
        private float   co2Type;
        private boolean status;

        public float getVocType() {
            return vocType;
        }

        public void setVocType(float vocType) {
            this.vocType = vocType;
        }

        public float getTemperatureType() {
            return temperatureType;
        }

        public void setTemperatureType(float temperatureType) {
            this.temperatureType = temperatureType;
        }

        public float getFormaldehyde() {
            return formaldehyde;
        }

        public void setFormaldehyde(float formaldehyde) {
            this.formaldehyde = formaldehyde;
        }

        public float getHumidityType() {
            return humidityType;
        }

        public void setHumidityType(float humidityType) {
            this.humidityType = humidityType;
        }

        public float getCo2() {
            return co2;
        }

        public void setCo2(float co2) {
            this.co2 = co2;
        }

        public float getPm100() {
            return pm100;
        }

        public void setPm100(float pm100) {
            this.pm100 = pm100;
        }

        public float getPm25Type() {
            return pm25Type;
        }

        public void setPm25Type(float pm25Type) {
            this.pm25Type = pm25Type;
        }

        public float getVoc() {
            return voc;
        }

        public void setVoc(float voc) {
            this.voc = voc;
        }

        public float getCo() {
            return co;
        }

        public void setCo(float co) {
            this.co = co;
        }

        public float getFormaldehydeType() {
            return formaldehydeType;
        }

        public void setFormaldehydeType(float formaldehydeType) {
            this.formaldehydeType = formaldehydeType;
        }

        public float getPm25() {
            return pm25;
        }

        public void setPm25(float pm25) {
            this.pm25 = pm25;
        }

        public float getCoType() {
            return coType;
        }

        public void setCoType(float coType) {
            this.coType = coType;
        }

        public String getFaddtime() {
            return faddtime;
        }

        public void setFaddtime(String faddtime) {
            this.faddtime = faddtime;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getCo2Type() {
            return co2Type;
        }

        public void setCo2Type(float co2Type) {
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
