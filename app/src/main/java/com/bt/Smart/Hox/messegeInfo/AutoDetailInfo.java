package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/13 14:14
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoDetailInfo {

    /**
     * result : 1
     * automation : {"id":"c340f6101d844659a7f6fc97493e51bc","automations":{"AutomaticName":"自动测试1","ConditionList":[{"kind_name":"时间","kind_img":"../../../../images/icon/scene_timing.png","kind_code":"time","status":0,"when":0,"time_start":"00:00","time_end":"23:59"}],"ActionList":[{"id":"2fe2950e0ece40d893b574fb1db4f06f","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试02","second_name":"","status":0,"when":0,"kind":"scene"},{"id":"c9407ef42b9a43c79ec03c9db0a39801","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试01","second_name":"","status":0,"when":0,"kind":"scene"}]}}
     * message : 数据查找成功
     */

    private int result;
    private AutomationBean automation;
    private String         message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public AutomationBean getAutomation() {
        return automation;
    }

    public void setAutomation(AutomationBean automation) {
        this.automation = automation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class AutomationBean {
        /**
         * id : c340f6101d844659a7f6fc97493e51bc
         * automations : {"AutomaticName":"自动测试1","ConditionList":[{"kind_name":"时间","kind_img":"../../../../images/icon/scene_timing.png","kind_code":"time","status":0,"when":0,"time_start":"00:00","time_end":"23:59"}],"ActionList":[{"id":"2fe2950e0ece40d893b574fb1db4f06f","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试02","second_name":"","status":0,"when":0,"kind":"scene"},{"id":"c9407ef42b9a43c79ec03c9db0a39801","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试01","second_name":"","status":0,"when":0,"kind":"scene"}]}
         */

        private String id;
        private AutomationsBean automations;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public AutomationsBean getAutomations() {
            return automations;
        }

        public void setAutomations(AutomationsBean automations) {
            this.automations = automations;
        }

        public static class AutomationsBean {
            /**
             * AutomaticName : 自动测试1
             * ConditionList : [{"kind_name":"时间","kind_img":"../../../../images/icon/scene_timing.png","kind_code":"time","status":0,"when":0,"time_start":"00:00","time_end":"23:59"}]
             * ActionList : [{"id":"2fe2950e0ece40d893b574fb1db4f06f","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试02","second_name":"","status":0,"when":0,"kind":"scene"},{"id":"c9407ef42b9a43c79ec03c9db0a39801","device_img":"","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","room_name":"","fname":"测试01","second_name":"","status":0,"when":0,"kind":"scene"}]
             */

            private String AutomaticName;
            private List<ConditionListBean> ConditionList;
            private List<ActionListBean>    ActionList;

            public String getAutomaticName() {
                return AutomaticName;
            }

            public void setAutomaticName(String AutomaticName) {
                this.AutomaticName = AutomaticName;
            }

            public List<ConditionListBean> getConditionList() {
                return ConditionList;
            }

            public void setConditionList(List<ConditionListBean> ConditionList) {
                this.ConditionList = ConditionList;
            }

            public List<ActionListBean> getActionList() {
                return ActionList;
            }

            public void setActionList(List<ActionListBean> ActionList) {
                this.ActionList = ActionList;
            }

            public static class ConditionListBean {
                /**
                 * kind_name : 时间
                 * kind_img : ../../../../images/icon/scene_timing.png
                 * kind_code : time
                 * status : 0
                 * when : 0
                 * time_start : 00:00
                 * time_end : 23:59
                 */

                private String kind_name;
                private String kind_img;
                private String kind_code;
                private int    status;
                private int    when;
                private String time_start;
                private String time_end;

                public String getKind_name() {
                    return kind_name;
                }

                public void setKind_name(String kind_name) {
                    this.kind_name = kind_name;
                }

                public String getKind_img() {
                    return kind_img;
                }

                public void setKind_img(String kind_img) {
                    this.kind_img = kind_img;
                }

                public String getKind_code() {
                    return kind_code;
                }

                public void setKind_code(String kind_code) {
                    this.kind_code = kind_code;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getWhen() {
                    return when;
                }

                public void setWhen(int when) {
                    this.when = when;
                }

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
            }

            public static class ActionListBean {
                /**
                 * id : 2fe2950e0ece40d893b574fb1db4f06f
                 * device_img :
                 * scenario_img : http://118.25.47.123:8080/upload/回家模式.png
                 * room_name :
                 * fname : 测试02
                 * second_name :
                 * status : 0
                 * when : 0
                 * kind : scene
                 */

                private String id;
                private String device_img;
                private String scenario_img;
                private String room_name;
                private String fname;
                private String second_name;
                private int    status;
                private int    when;
                private String kind;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDevice_img() {
                    return device_img;
                }

                public void setDevice_img(String device_img) {
                    this.device_img = device_img;
                }

                public String getScenario_img() {
                    return scenario_img;
                }

                public void setScenario_img(String scenario_img) {
                    this.scenario_img = scenario_img;
                }

                public String getRoom_name() {
                    return room_name;
                }

                public void setRoom_name(String room_name) {
                    this.room_name = room_name;
                }

                public String getFname() {
                    return fname;
                }

                public void setFname(String fname) {
                    this.fname = fname;
                }

                public String getSecond_name() {
                    return second_name;
                }

                public void setSecond_name(String second_name) {
                    this.second_name = second_name;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getWhen() {
                    return when;
                }

                public void setWhen(int when) {
                    this.when = when;
                }

                public String getKind() {
                    return kind;
                }

                public void setKind(String kind) {
                    this.kind = kind;
                }
            }
        }
    }
}
