package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/11 10:38
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class WeatherInfo {

    /**
     * result : 1
     * temperaturelist : {"weaid":"nantong","days":"2018-12-11","week":"星期二","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"9℃/0℃","humidity":"0%/0%","weather":"小雨转阴","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/2.gif","wind":"西北风","winp":"5-6级","temp_high":"9","temp_low":"0","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"3","windid":"15","winpid":"41","weather_iconid":"7","weather_iconid1":"2","aqi":"29","aqi_levid":"1","aqi_levnm":"优","aqi_remark":"参加户外活动呼吸清新空气","pm25":"8.93","jsonTemp2":[{"weaid":"nantong","days":"2018-12-11","week":"星期二","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"9℃/0℃","humidity":"0%/0%","weather":"小雨转阴","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/2.gif","wind":"西北风","winp":"5-6级","temp_high":"9","temp_low":"0","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"3","windid":"15","winpid":"41","weather_iconid":"7","weather_iconid1":"2","aqi":"29","aqi_levid":"1","aqi_levnm":"优","aqi_remark":"参加户外活动呼吸清新空气","pm25":"8.93"},{"weaid":"402","days":"2018-12-12","week":"星期三","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"8℃/-3℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风","winp":"3-4级","temp_high":"8","temp_low":"-3","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"20","winpid":"14","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-13","week":"星期四","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"7℃/-1℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风转西北风","winp":"3-4级","temp_high":"7","temp_low":"-1","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"64","winpid":"14","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-14","week":"星期五","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"11℃/6℃","humidity":"0%/0%","weather":"多云转小雨","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/7.gif","wind":"东风转东南风","winp":"3-4级","temp_high":"11","temp_low":"6","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"8","windid":"62","winpid":"14","weather_iconid":"1","weather_iconid1":"7"},{"weaid":"402","days":"2018-12-15","week":"星期六","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"12℃/5℃","humidity":"0%/0%","weather":"小雨","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/7.gif","wind":"南风转西风","winp":"3-4级","temp_high":"12","temp_low":"5","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"8","windid":"106","winpid":"14","weather_iconid":"7","weather_iconid1":"7"},{"weaid":"402","days":"2018-12-16","week":"星期日","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"10℃/2℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"西北风","winp":"3-4级转<3级","temp_high":"10","temp_low":"2","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"15","winpid":"402","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-17","week":"星期一","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"10℃/5℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"南风","winp":"<3级","temp_high":"10","temp_low":"5","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"5","winpid":"395","weather_iconid":"1","weather_iconid1":"1"}],"cityname":"南通","SD":"94%","temp":"7℃"}
     * message : 信息查找成功
     */

    private int result;
    private TemperaturelistBean temperaturelist;
    private String              message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public TemperaturelistBean getTemperaturelist() {
        return temperaturelist;
    }

    public void setTemperaturelist(TemperaturelistBean temperaturelist) {
        this.temperaturelist = temperaturelist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class TemperaturelistBean {
        /**
         * weaid : nantong
         * days : 2018-12-11
         * week : 星期二
         * cityno : nantong
         * citynm : 南通
         * cityid : 101190501
         * temperature : 9℃/0℃
         * humidity : 0%/0%
         * weather : 小雨转阴
         * weather_icon : http://api.k780.com/upload/weather/d/7.gif
         * weather_icon1 : http://api.k780.com/upload/weather/n/2.gif
         * wind : 西北风
         * winp : 5-6级
         * temp_high : 9
         * temp_low : 0
         * humi_high : 0
         * humi_low : 0
         * weatid : 8
         * weatid1 : 3
         * windid : 15
         * winpid : 41
         * weather_iconid : 7
         * weather_iconid1 : 2
         * aqi : 29
         * aqi_levid : 1
         * aqi_levnm : 优
         * aqi_remark : 参加户外活动呼吸清新空气
         * pm25 : 8.93
         * jsonTemp2 : [{"weaid":"nantong","days":"2018-12-11","week":"星期二","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"9℃/0℃","humidity":"0%/0%","weather":"小雨转阴","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/2.gif","wind":"西北风","winp":"5-6级","temp_high":"9","temp_low":"0","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"3","windid":"15","winpid":"41","weather_iconid":"7","weather_iconid1":"2","aqi":"29","aqi_levid":"1","aqi_levnm":"优","aqi_remark":"参加户外活动呼吸清新空气","pm25":"8.93"},{"weaid":"402","days":"2018-12-12","week":"星期三","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"8℃/-3℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风","winp":"3-4级","temp_high":"8","temp_low":"-3","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"20","winpid":"14","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-13","week":"星期四","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"7℃/-1℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风转西北风","winp":"3-4级","temp_high":"7","temp_low":"-1","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"64","winpid":"14","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-14","week":"星期五","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"11℃/6℃","humidity":"0%/0%","weather":"多云转小雨","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/7.gif","wind":"东风转东南风","winp":"3-4级","temp_high":"11","temp_low":"6","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"8","windid":"62","winpid":"14","weather_iconid":"1","weather_iconid1":"7"},{"weaid":"402","days":"2018-12-15","week":"星期六","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"12℃/5℃","humidity":"0%/0%","weather":"小雨","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/7.gif","wind":"南风转西风","winp":"3-4级","temp_high":"12","temp_low":"5","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"8","windid":"106","winpid":"14","weather_iconid":"7","weather_iconid1":"7"},{"weaid":"402","days":"2018-12-16","week":"星期日","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"10℃/2℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"西北风","winp":"3-4级转<3级","temp_high":"10","temp_low":"2","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"15","winpid":"402","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"402","days":"2018-12-17","week":"星期一","cityno":"nantong","citynm":"南通","cityid":"101190501","temperature":"10℃/5℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"南风","winp":"<3级","temp_high":"10","temp_low":"5","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"5","winpid":"395","weather_iconid":"1","weather_iconid1":"1"}]
         * cityname : 南通
         * SD : 94%
         * temp : 7℃
         */

        private String weaid;
        private String              days;
        private String              week;
        private String              cityno;
        private String              citynm;
        private String              cityid;
        private String              temperature;
        private String              humidity;
        private String              weather;
        private String              weather_icon;
        private String              weather_icon1;
        private String              wind;
        private String              winp;
        private String              temp_high;
        private String              temp_low;
        private String              humi_high;
        private String              humi_low;
        private String              weatid;
        private String              weatid1;
        private String              windid;
        private String              winpid;
        private String              weather_iconid;
        private String              weather_iconid1;
        private String              aqi;
        private String              aqi_levid;
        private String              aqi_levnm;
        private String              aqi_remark;
        private String              pm25;
        private String              cityname;
        private String              SD;
        private String              temp;
        private List<JsonTemp2Bean> jsonTemp2;

        public String getWeaid() {
            return weaid;
        }

        public void setWeaid(String weaid) {
            this.weaid = weaid;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCityno() {
            return cityno;
        }

        public void setCityno(String cityno) {
            this.cityno = cityno;
        }

        public String getCitynm() {
            return citynm;
        }

        public void setCitynm(String citynm) {
            this.citynm = citynm;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
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

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeather_icon() {
            return weather_icon;
        }

        public void setWeather_icon(String weather_icon) {
            this.weather_icon = weather_icon;
        }

        public String getWeather_icon1() {
            return weather_icon1;
        }

        public void setWeather_icon1(String weather_icon1) {
            this.weather_icon1 = weather_icon1;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWinp() {
            return winp;
        }

        public void setWinp(String winp) {
            this.winp = winp;
        }

        public String getTemp_high() {
            return temp_high;
        }

        public void setTemp_high(String temp_high) {
            this.temp_high = temp_high;
        }

        public String getTemp_low() {
            return temp_low;
        }

        public void setTemp_low(String temp_low) {
            this.temp_low = temp_low;
        }

        public String getHumi_high() {
            return humi_high;
        }

        public void setHumi_high(String humi_high) {
            this.humi_high = humi_high;
        }

        public String getHumi_low() {
            return humi_low;
        }

        public void setHumi_low(String humi_low) {
            this.humi_low = humi_low;
        }

        public String getWeatid() {
            return weatid;
        }

        public void setWeatid(String weatid) {
            this.weatid = weatid;
        }

        public String getWeatid1() {
            return weatid1;
        }

        public void setWeatid1(String weatid1) {
            this.weatid1 = weatid1;
        }

        public String getWindid() {
            return windid;
        }

        public void setWindid(String windid) {
            this.windid = windid;
        }

        public String getWinpid() {
            return winpid;
        }

        public void setWinpid(String winpid) {
            this.winpid = winpid;
        }

        public String getWeather_iconid() {
            return weather_iconid;
        }

        public void setWeather_iconid(String weather_iconid) {
            this.weather_iconid = weather_iconid;
        }

        public String getWeather_iconid1() {
            return weather_iconid1;
        }

        public void setWeather_iconid1(String weather_iconid1) {
            this.weather_iconid1 = weather_iconid1;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getAqi_levid() {
            return aqi_levid;
        }

        public void setAqi_levid(String aqi_levid) {
            this.aqi_levid = aqi_levid;
        }

        public String getAqi_levnm() {
            return aqi_levnm;
        }

        public void setAqi_levnm(String aqi_levnm) {
            this.aqi_levnm = aqi_levnm;
        }

        public String getAqi_remark() {
            return aqi_remark;
        }

        public void setAqi_remark(String aqi_remark) {
            this.aqi_remark = aqi_remark;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getSD() {
            return SD;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public List<JsonTemp2Bean> getJsonTemp2() {
            return jsonTemp2;
        }

        public void setJsonTemp2(List<JsonTemp2Bean> jsonTemp2) {
            this.jsonTemp2 = jsonTemp2;
        }

        public static class JsonTemp2Bean {
            /**
             * weaid : nantong
             * days : 2018-12-11
             * week : 星期二
             * cityno : nantong
             * citynm : 南通
             * cityid : 101190501
             * temperature : 9℃/0℃
             * humidity : 0%/0%
             * weather : 小雨转阴
             * weather_icon : http://api.k780.com/upload/weather/d/7.gif
             * weather_icon1 : http://api.k780.com/upload/weather/n/2.gif
             * wind : 西北风
             * winp : 5-6级
             * temp_high : 9
             * temp_low : 0
             * humi_high : 0
             * humi_low : 0
             * weatid : 8
             * weatid1 : 3
             * windid : 15
             * winpid : 41
             * weather_iconid : 7
             * weather_iconid1 : 2
             * aqi : 29
             * aqi_levid : 1
             * aqi_levnm : 优
             * aqi_remark : 参加户外活动呼吸清新空气
             * pm25 : 8.93
             */

            private String weaid;
            private String days;
            private String week;
            private String cityno;
            private String citynm;
            private String cityid;
            private String temperature;
            private String humidity;
            private String weather;
            private String weather_icon;
            private String weather_icon1;
            private String wind;
            private String winp;
            private String temp_high;
            private String temp_low;
            private String humi_high;
            private String humi_low;
            private String weatid;
            private String weatid1;
            private String windid;
            private String winpid;
            private String weather_iconid;
            private String weather_iconid1;
            private String aqi;
            private String aqi_levid;
            private String aqi_levnm;
            private String aqi_remark;
            private String pm25;

            public String getWeaid() {
                return weaid;
            }

            public void setWeaid(String weaid) {
                this.weaid = weaid;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCityno() {
                return cityno;
            }

            public void setCityno(String cityno) {
                this.cityno = cityno;
            }

            public String getCitynm() {
                return citynm;
            }

            public void setCitynm(String citynm) {
                this.citynm = citynm;
            }

            public String getCityid() {
                return cityid;
            }

            public void setCityid(String cityid) {
                this.cityid = cityid;
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

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWeather_icon() {
                return weather_icon;
            }

            public void setWeather_icon(String weather_icon) {
                this.weather_icon = weather_icon;
            }

            public String getWeather_icon1() {
                return weather_icon1;
            }

            public void setWeather_icon1(String weather_icon1) {
                this.weather_icon1 = weather_icon1;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWinp() {
                return winp;
            }

            public void setWinp(String winp) {
                this.winp = winp;
            }

            public String getTemp_high() {
                return temp_high;
            }

            public void setTemp_high(String temp_high) {
                this.temp_high = temp_high;
            }

            public String getTemp_low() {
                return temp_low;
            }

            public void setTemp_low(String temp_low) {
                this.temp_low = temp_low;
            }

            public String getHumi_high() {
                return humi_high;
            }

            public void setHumi_high(String humi_high) {
                this.humi_high = humi_high;
            }

            public String getHumi_low() {
                return humi_low;
            }

            public void setHumi_low(String humi_low) {
                this.humi_low = humi_low;
            }

            public String getWeatid() {
                return weatid;
            }

            public void setWeatid(String weatid) {
                this.weatid = weatid;
            }

            public String getWeatid1() {
                return weatid1;
            }

            public void setWeatid1(String weatid1) {
                this.weatid1 = weatid1;
            }

            public String getWindid() {
                return windid;
            }

            public void setWindid(String windid) {
                this.windid = windid;
            }

            public String getWinpid() {
                return winpid;
            }

            public void setWinpid(String winpid) {
                this.winpid = winpid;
            }

            public String getWeather_iconid() {
                return weather_iconid;
            }

            public void setWeather_iconid(String weather_iconid) {
                this.weather_iconid = weather_iconid;
            }

            public String getWeather_iconid1() {
                return weather_iconid1;
            }

            public void setWeather_iconid1(String weather_iconid1) {
                this.weather_iconid1 = weather_iconid1;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getAqi_levid() {
                return aqi_levid;
            }

            public void setAqi_levid(String aqi_levid) {
                this.aqi_levid = aqi_levid;
            }

            public String getAqi_levnm() {
                return aqi_levnm;
            }

            public void setAqi_levnm(String aqi_levnm) {
                this.aqi_levnm = aqi_levnm;
            }

            public String getAqi_remark() {
                return aqi_remark;
            }

            public void setAqi_remark(String aqi_remark) {
                this.aqi_remark = aqi_remark;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }
        }
    }
}
