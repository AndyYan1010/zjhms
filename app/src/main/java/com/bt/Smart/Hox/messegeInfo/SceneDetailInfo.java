package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/13 16:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneDetailInfo {

    /**
     * result : 1
     * scenario : {"id":"2fe2950e0ece40d893b574fb1db4f06f","scenarios":{"Scene_name":"测试02","Scene_Room_Icon":"http://118.25.47.123:8080/upload/回家模式.png","Scene_EQList":[],"Scene_AutomaticList":[],"Scene_timing":{"time_start":"22:58","time_end":"23:59","time_control":{"SingleTime":"0","Monday":"0","Tuesday":"0","Wednesday":"1","Thursday":"0","Friday":"0","Sunday":"0","Saturday":"1"}}}}
     * message : 数据查找成功
     */

    private int result;
    private ScenarioBean scenario;
    private String       message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ScenarioBean getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioBean scenario) {
        this.scenario = scenario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ScenarioBean {
        /**
         * id : 2fe2950e0ece40d893b574fb1db4f06f
         * scenarios : {"Scene_name":"测试02","Scene_Room_Icon":"http://118.25.47.123:8080/upload/回家模式.png","Scene_EQList":[],"Scene_AutomaticList":[],"Scene_timing":{"time_start":"22:58","time_end":"23:59","time_control":{"SingleTime":"0","Monday":"0","Tuesday":"0","Wednesday":"1","Thursday":"0","Friday":"0","Sunday":"0","Saturday":"1"}}}
         */

        private String id;
        private ScenariosBean scenarios;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ScenariosBean getScenarios() {
            return scenarios;
        }

        public void setScenarios(ScenariosBean scenarios) {
            this.scenarios = scenarios;
        }

        public static class ScenariosBean {
            /**
             * Scene_name : 测试02
             * Scene_Room_Icon : http://118.25.47.123:8080/upload/回家模式.png
             * Scene_EQList : []
             * Scene_AutomaticList : []
             * Scene_timing : {"time_start":"22:58","time_end":"23:59","time_control":{"SingleTime":"0","Monday":"0","Tuesday":"0","Wednesday":"1","Thursday":"0","Friday":"0","Sunday":"0","Saturday":"1"}}
             */

            private String Scene_name;
            private String          Scene_Room_Icon;
            private SceneTimingBean Scene_timing;
            private List<?>         Scene_EQList;
            private List<?>         Scene_AutomaticList;

            public String getScene_name() {
                return Scene_name;
            }

            public void setScene_name(String Scene_name) {
                this.Scene_name = Scene_name;
            }

            public String getScene_Room_Icon() {
                return Scene_Room_Icon;
            }

            public void setScene_Room_Icon(String Scene_Room_Icon) {
                this.Scene_Room_Icon = Scene_Room_Icon;
            }

            public SceneTimingBean getScene_timing() {
                return Scene_timing;
            }

            public void setScene_timing(SceneTimingBean Scene_timing) {
                this.Scene_timing = Scene_timing;
            }

            public List<?> getScene_EQList() {
                return Scene_EQList;
            }

            public void setScene_EQList(List<?> Scene_EQList) {
                this.Scene_EQList = Scene_EQList;
            }

            public List<?> getScene_AutomaticList() {
                return Scene_AutomaticList;
            }

            public void setScene_AutomaticList(List<?> Scene_AutomaticList) {
                this.Scene_AutomaticList = Scene_AutomaticList;
            }

            public static class SceneTimingBean {
                /**
                 * time_start : 22:58
                 * time_end : 23:59
                 * time_control : {"SingleTime":"0","Monday":"0","Tuesday":"0","Wednesday":"1","Thursday":"0","Friday":"0","Sunday":"0","Saturday":"1"}
                 */

                private String time_start;
                private String          time_end;
                private TimeControlBean time_control;

                public String getTime_start() {
                    return time_start;
                }

                public void setTime_start(String time_start) {
                    this.time_start = time_start;
                }

                public String getTime_end() {
                    return time_end;
                }

                public void setTime_end(String time_end) {
                    this.time_end = time_end;
                }

                public TimeControlBean getTime_control() {
                    return time_control;
                }

                public void setTime_control(TimeControlBean time_control) {
                    this.time_control = time_control;
                }

                public static class TimeControlBean {
                    /**
                     * SingleTime : 0
                     * Monday : 0
                     * Tuesday : 0
                     * Wednesday : 1
                     * Thursday : 0
                     * Friday : 0
                     * Sunday : 0
                     * Saturday : 1
                     */

                    private String SingleTime;
                    private String Monday;
                    private String Tuesday;
                    private String Wednesday;
                    private String Thursday;
                    private String Friday;
                    private String Sunday;
                    private String Saturday;

                    public String getSingleTime() {
                        return SingleTime;
                    }

                    public void setSingleTime(String SingleTime) {
                        this.SingleTime = SingleTime;
                    }

                    public String getMonday() {
                        return Monday;
                    }

                    public void setMonday(String Monday) {
                        this.Monday = Monday;
                    }

                    public String getTuesday() {
                        return Tuesday;
                    }

                    public void setTuesday(String Tuesday) {
                        this.Tuesday = Tuesday;
                    }

                    public String getWednesday() {
                        return Wednesday;
                    }

                    public void setWednesday(String Wednesday) {
                        this.Wednesday = Wednesday;
                    }

                    public String getThursday() {
                        return Thursday;
                    }

                    public void setThursday(String Thursday) {
                        this.Thursday = Thursday;
                    }

                    public String getFriday() {
                        return Friday;
                    }

                    public void setFriday(String Friday) {
                        this.Friday = Friday;
                    }

                    public String getSunday() {
                        return Sunday;
                    }

                    public void setSunday(String Sunday) {
                        this.Sunday = Sunday;
                    }

                    public String getSaturday() {
                        return Saturday;
                    }

                    public void setSaturday(String Saturday) {
                        this.Saturday = Saturday;
                    }
                }
            }
        }
    }
}
