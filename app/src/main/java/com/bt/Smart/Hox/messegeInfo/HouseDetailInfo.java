package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/1 14:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HouseDetailInfo {

    /**
     * houseList : [{"house_name":"次卧","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"08e70f9e59df4c7fbb119add8490a63d","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"house_name":"书房","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"7862ab7cc5524f779bfc43dd5e9daf34","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"house_name":"主卧","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"9a558adc48e644b6965f7bb02f16aa3d","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"house_name":"衣帽间","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"bf7e3562c14d4d95ae5dfc3dd202fb4e","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"house_name":"厨房","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"cd8e7523361849bc8705f2256a50d19c","register_id":"74ee5a6bdb5911e8813c000c2950df04"},{"house_name":"客厅","home_id":"c340f6101d844659a7f6fc97493e51bc","id":"e158159b75e1433088bd8e4e05beceb3","register_id":"74ee5a6bdb5911e8813c000c2950df04"}]
     * message : 家中房间列表查询成功
     * code : 1
     */

    private String message;
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
         * house_name : 次卧
         * home_id : c340f6101d844659a7f6fc97493e51bc
         * id : 08e70f9e59df4c7fbb119add8490a63d
         * register_id : 74ee5a6bdb5911e8813c000c2950df04
         */

        private String house_name;
        private String home_id;
        private String id;
        private String register_id;

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
    }
}
