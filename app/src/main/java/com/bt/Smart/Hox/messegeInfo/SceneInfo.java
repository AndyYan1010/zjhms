package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 10:20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneInfo {

    /**
     * result : 1
     * message : 场景数据查找成功
     * scenariolist : [{"fname":"测试02","device_name":"测试02","on_off_status":"0","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","id":"2fe2950e0ece40d893b574fb1db4f06f","register_id":"c340f6101d844659a7f6fc97493e51bc"},{"fname":"测试01","device_name":"测试01","on_off_status":"0","scenario_img":"http://118.25.47.123:8080/upload/回家模式.png","id":"c9407ef42b9a43c79ec03c9db0a39801","register_id":"c340f6101d844659a7f6fc97493e51bc"}]
     */

    private int                    result;
    private String                 message;
    private List<ScenariolistBean> scenariolist;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ScenariolistBean> getScenariolist() {
        return scenariolist;
    }

    public void setScenariolist(List<ScenariolistBean> scenariolist) {
        this.scenariolist = scenariolist;
    }

    public static class ScenariolistBean {
        /**
         * fname : 测试02
         * device_name : 测试02
         * on_off_status : 0
         * scenario_img : http://118.25.47.123:8080/upload/回家模式.png
         * id : 2fe2950e0ece40d893b574fb1db4f06f
         * register_id : c340f6101d844659a7f6fc97493e51bc
         */

        private String fname;
        private String device_name;
        private String on_off_status;
        private String scenario_img;
        private String id;
        private String register_id;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getOn_off_status() {
            return on_off_status;
        }

        public void setOn_off_status(String on_off_status) {
            this.on_off_status = on_off_status;
        }

        public String getScenario_img() {
            return scenario_img;
        }

        public void setScenario_img(String scenario_img) {
            this.scenario_img = scenario_img;
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
    }
}
