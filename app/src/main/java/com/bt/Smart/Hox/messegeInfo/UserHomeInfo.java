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
     {
     "homeList": [
     {
     "home_pic": "http://112.90.178.68:8081/upFiles/1545275556862.jpeg",
     "faddress": "江苏省南通市海门市",
     "home_name": "igvb",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "isdefault": "1",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     }
     ],
     "message": "家列表查询成功",
     "code": 1
     }
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
         * home_pic : 1544057849424.jpeg
         * faddress : 江苏省南通市海门市
         * home_name : igvb
         * home_id : f67e15b2b940416480cbc042882874df
         * isdefault : 0
         * register_id : b35e098dddb511e89c91000c2950df04
         */

        private String home_pic;
        private String faddress;
        private String home_name;
        private String home_id;
        private String isdefault;
        private String register_id;

        public String getHome_pic() {
            return home_pic;
        }

        public void setHome_pic(String home_pic) {
            this.home_pic = home_pic;
        }

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
