package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 20:28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RoomsDeviceInfo {

    /**
     * houseList : [{"house_id":"08e70f9e59df4c7fbb119add8490a63d","house_name":"次卧","deviceList":[{"deviceType":"1","device_config":"1","device_status":"0","house_id":"08e70f9e59df4c7fbb119add8490a63d","create_time":{"date":1,"day":4,"hours":16,"minutes":27,"month":10,"nanos":0,"seconds":39,"time":1541060859000,"timezoneOffset":-480,"year":118},"second_control_id":"fe869542ddaf11e89c91000c2950df04","main_control_id":"f6235486dbde11e8813c000c2950df04","second_control_name":"HAir空气哨兵(有线)","main_control_code":"00000001","device_img":"","main_control_name":"HoxONE主控","device_name":"HAir空气哨兵(有线)","device_code":"00123456","default_device_type":"HAir(有线)","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"79f97764d31c41e3bafd393097813c19"},{"deviceType":"1","device_config":"1","device_status":"1","house_id":"08e70f9e59df4c7fbb119add8490a63d","create_time":{"date":1,"day":4,"hours":16,"minutes":28,"month":10,"nanos":0,"seconds":48,"time":1541060928000,"timezoneOffset":-480,"year":118},"second_control_id":"27ce0d36ddb011e89c91000c2950df04","main_control_id":"f6235486dbde11e8813c000c2950df04","second_control_name":"灯控","main_control_code":"00000001","device_img":"","main_control_name":"HoxONE主控","device_name":"灯控","device_code":"01000001","default_device_type":"lamp","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"f4e912b6ab8e44c7ae14d2000dfbff75"}],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"08e70f9e59df4c7fbb119add8490a63d","register_id":"b788b918ddb411e89c91000c2950df04"},{"house_id":"7862ab7cc5524f779bfc43dd5e9daf34","house_name":"书房","deviceList":[],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"7862ab7cc5524f779bfc43dd5e9daf34","register_id":"b788b918ddb411e89c91000c2950df04"},{"house_id":"9a558adc48e644b6965f7bb02f16aa3d","house_name":"主卧","deviceList":[],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"9a558adc48e644b6965f7bb02f16aa3d","register_id":"b788b918ddb411e89c91000c2950df04"},{"house_id":"bf7e3562c14d4d95ae5dfc3dd202fb4e","house_name":"衣帽间","deviceList":[],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"bf7e3562c14d4d95ae5dfc3dd202fb4e","register_id":"b788b918ddb411e89c91000c2950df04"},{"house_id":"cd8e7523361849bc8705f2256a50d19c","house_name":"厨房","deviceList":[],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"cd8e7523361849bc8705f2256a50d19c","register_id":"b788b918ddb411e89c91000c2950df04"},{"house_id":"e158159b75e1433088bd8e4e05beceb3","house_name":"客厅","deviceList":[],"home_id":"c340f6101d844659a7f6fc97493e51bc","id":"e158159b75e1433088bd8e4e05beceb3","register_id":"b788b918ddb411e89c91000c2950df04"}]
     * message : 房间设备列表查询成功
     * code : 1
     */

    private String              message;
    private int                 code;
    private List<HouseListBean> houseList;

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

    public List<HouseListBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HouseListBean> houseList) {
        this.houseList = houseList;
    }

    public static class HouseListBean {
        /**
         * house_id : 08e70f9e59df4c7fbb119add8490a63d
         * house_name : 次卧
         * deviceList : [{"deviceType":"1","device_config":"1","device_status":"0","house_id":"08e70f9e59df4c7fbb119add8490a63d","create_time":{"date":1,"day":4,"hours":16,"minutes":27,"month":10,"nanos":0,"seconds":39,"time":1541060859000,"timezoneOffset":-480,"year":118},"second_control_id":"fe869542ddaf11e89c91000c2950df04","main_control_id":"f6235486dbde11e8813c000c2950df04","second_control_name":"HAir空气哨兵(有线)","main_control_code":"00000001","device_img":"","main_control_name":"HoxONE主控","device_name":"HAir空气哨兵(有线)","device_code":"00123456","default_device_type":"HAir(有线)","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"79f97764d31c41e3bafd393097813c19"},{"deviceType":"1","device_config":"1","device_status":"1","house_id":"08e70f9e59df4c7fbb119add8490a63d","create_time":{"date":1,"day":4,"hours":16,"minutes":28,"month":10,"nanos":0,"seconds":48,"time":1541060928000,"timezoneOffset":-480,"year":118},"second_control_id":"27ce0d36ddb011e89c91000c2950df04","main_control_id":"f6235486dbde11e8813c000c2950df04","second_control_name":"灯控","main_control_code":"00000001","device_img":"","main_control_name":"HoxONE主控","device_name":"灯控","device_code":"01000001","default_device_type":"lamp","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"f4e912b6ab8e44c7ae14d2000dfbff75"}]
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * id : 08e70f9e59df4c7fbb119add8490a63d
         * register_id : b788b918ddb411e89c91000c2950df04
         */

        private String               house_id;
        private String               house_name;
        private String               home_id;
        private String               id;
        private String               register_id;
        private List<DeviceListBean> deviceList;
        /**
         * mineIschoice : false
         */

        private boolean              mineIschoice;
        /**
         * ifCheck : 1
         */

        private int                  ifCheck;

        public String getHouse_id() {
            return house_id;
        }

        public void setHouse_id(String house_id) {
            this.house_id = house_id;
        }

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
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

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public List<DeviceListBean> getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(List<DeviceListBean> deviceList) {
            this.deviceList = deviceList;
        }

        public boolean isMineIschoice() {
            return mineIschoice;
        }

        public void setMineIschoice(boolean mineIschoice) {
            this.mineIschoice = mineIschoice;
        }

        public int getIfCheck() {
            return ifCheck;
        }

        public void setIfCheck(int ifCheck) {
            this.ifCheck = ifCheck;
        }

        public static class DeviceListBean {
            /**
             * deviceType : 1
             * device_config : 1
             * device_status : 0
             * house_id : 08e70f9e59df4c7fbb119add8490a63d
             * create_time : {"date":1,"day":4,"hours":16,"minutes":27,"month":10,"nanos":0,"seconds":39,"time":1541060859000,"timezoneOffset":-480,"year":118}
             * second_control_id : fe869542ddaf11e89c91000c2950df04
             * main_control_id : f6235486dbde11e8813c000c2950df04
             * second_control_name : HAir空气哨兵(有线)
             * main_control_code : 00000001
             * device_img :
             * main_control_name : HoxONE主控
             * device_name : HAir空气哨兵(有线)
             * device_code : 00123456
             * default_device_type : HAir(有线)
             * home_id : c340f6101d844659a7f6fc97493e51bc
             * id : 79f97764d31c41e3bafd393097813c19
             */


            private String         deviceType;
            private String         device_config;
            private String         device_status;
            private String         house_id;
            private CreateTimeBean create_time;
            private String         second_control_id;
            private String         main_control_id;
            private String         second_control_name;
            private String         main_control_code;
            private String         device_img;
            private String         main_control_name;
            private String         device_name;
            private String         device_code;
            private String         default_device_type;
            private String         home_id;
            private String         id;
            /**
             * meChoice : false
             */

            private boolean        meChoice;
            /**
             * ifCheck : 1
             */

            private int            ifCheck;

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getDevice_config() {
                return device_config;
            }

            public void setDevice_config(String device_config) {
                this.device_config = device_config;
            }

            public String getDevice_status() {
                return device_status;
            }

            public void setDevice_status(String device_status) {
                this.device_status = device_status;
            }

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
            }

            public CreateTimeBean getCreate_time() {
                return create_time;
            }

            public void setCreate_time(CreateTimeBean create_time) {
                this.create_time = create_time;
            }

            public String getSecond_control_id() {
                return second_control_id;
            }

            public void setSecond_control_id(String second_control_id) {
                this.second_control_id = second_control_id;
            }

            public String getMain_control_id() {
                return main_control_id;
            }

            public void setMain_control_id(String main_control_id) {
                this.main_control_id = main_control_id;
            }

            public String getSecond_control_name() {
                return second_control_name;
            }

            public void setSecond_control_name(String second_control_name) {
                this.second_control_name = second_control_name;
            }

            public String getMain_control_code() {
                return main_control_code;
            }

            public void setMain_control_code(String main_control_code) {
                this.main_control_code = main_control_code;
            }

            public String getDevice_img() {
                return device_img;
            }

            public void setDevice_img(String device_img) {
                this.device_img = device_img;
            }

            public String getMain_control_name() {
                return main_control_name;
            }

            public void setMain_control_name(String main_control_name) {
                this.main_control_name = main_control_name;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getDevice_code() {
                return device_code;
            }

            public void setDevice_code(String device_code) {
                this.device_code = device_code;
            }

            public String getDefault_device_type() {
                return default_device_type;
            }

            public void setDefault_device_type(String default_device_type) {
                this.default_device_type = default_device_type;
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

            public boolean isMeChoice() {
                return meChoice;
            }

            public void setMeChoice(boolean meChoice) {
                this.meChoice = meChoice;
            }

            public int getIfCheck() {
                return ifCheck;
            }

            public void setIfCheck(int ifCheck) {
                this.ifCheck = ifCheck;
            }

            public static class CreateTimeBean {
                /**
                 * date : 1
                 * day : 4
                 * hours : 16
                 * minutes : 27
                 * month : 10
                 * nanos : 0
                 * seconds : 39
                 * time : 1541060859000
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
}
