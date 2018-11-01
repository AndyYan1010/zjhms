package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/1 14:09
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UserHomeInfo {

    /**
     * homeList : [{"faddress":"江苏省南通市海门市","home_name":"家","home_id":"c340f6101d844659a7f6fc97493e51bc","isdefault":"1","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"faddress":"河北省石家庄市长安区","home_name":"测试家02","home_id":"300b188b02514128ac3de8a8669a5ad0","isdefault":"0","register_id":"74ee5a6bdb5911e8813c000c2950df04"}]
     * message : 家列表查询成功
     * code : 1
     */

    private String message;
    private int                code;
    private List<HomeListBean> homeList;

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

    public List<HomeListBean> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<HomeListBean> homeList) {
        this.homeList = homeList;
    }

    public static class HomeListBean {
        /**
         * faddress : 江苏省南通市海门市
         * home_name : 家
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * isdefault : 1
         * register_id : 74ee5a6bdb5911e8813c000c2950df04
         */

        private String faddress;
        private String home_name;
        private String home_id;
        private String isdefault;
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

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(String isdefault) {
            this.isdefault = isdefault;
        }

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }
    }
}
