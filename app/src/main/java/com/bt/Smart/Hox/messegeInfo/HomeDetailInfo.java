package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 11:19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeDetailInfo {

    /**
     * home : {"faddress":"江苏省南通市海门市","home_name":"家","id":"c340f6101d844659a7f6fc97493e51bc","register_id":"74ee5a6bdb5911e8813c000c2950df04"}
     * houseCount : 6
     * message : 家查询成功
     * code : 1
     */

    private HomeBean home;
    private int    houseCount;
    private String message;
    private int    code;

    public HomeBean getHome() {
        return home;
    }

    public void setHome(HomeBean home) {
        this.home = home;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
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

    public static class HomeBean {
        /**
         * faddress : 江苏省南通市海门市
         * home_name : 家
         * id : c340f6101d844659a7f6fc97493e51bc
         * register_id : 74ee5a6bdb5911e8813c000c2950df04
         */

        private String faddress;
        private String home_name;
        private String id;
        private String register_id;

        public String getFaddress() {
            return faddress;
        }

        public void setFaddress(String faddress) {
            this.faddress = faddress;
        }

        public String getHome_name() {
            return home_name;
        }

        public void setHome_name(String home_name) {
            this.home_name = home_name;
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
