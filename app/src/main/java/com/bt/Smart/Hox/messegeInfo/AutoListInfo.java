package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 10:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoListInfo {

    /**
     * result : 1
     * message : 数据查找成功
     * automationlist : [{"fname":"自动测试1","on_off_status":"0","id":"c59e881941334c959a56eb47e0ef649f","register_id":"c340f6101d844659a7f6fc97493e51bc"}]
     */

    private int result;
    private String                   message;
    private List<AutomationlistBean> automationlist;

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

    public List<AutomationlistBean> getAutomationlist() {
        return automationlist;
    }

    public void setAutomationlist(List<AutomationlistBean> automationlist) {
        this.automationlist = automationlist;
    }

    public static class AutomationlistBean {
        /**
         * fname : 自动测试1
         * on_off_status : 0
         * id : c59e881941334c959a56eb47e0ef649f
         * register_id : c340f6101d844659a7f6fc97493e51bc
         */

        private String fname;
        private String on_off_status;
        private String id;
        private String register_id;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getOn_off_status() {
            return on_off_status;
        }

        public void setOn_off_status(String on_off_status) {
            this.on_off_status = on_off_status;
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
