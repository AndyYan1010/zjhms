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
     {
     "houseList": [
     {
     "house_name": "主卧",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "id": "0b2478550c1d4703b7e89914c3f2bce1",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     },
     {
     "house_name": "餐厅",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "id": "0e56cd272fc341c5af08073255053096",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     },
     {
     "house_name": "次卧",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "id": "12a04eb56c184796af12269191e80822",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     },
     {
     "house_name": "客厅",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "id": "156987723b6c4fa2849784df72feecf3",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     },
     {
     "house_name": "帽间jdn",
     "home_id": "f67e15b2b940416480cbc042882874df",
     "id": "2880841ae31d49b1af350426fa281b75",
     "register_id": "b35e098dddb511e89c91000c2950df04"
     }
     ],
     "message": "家中房间列表查询成功",
     "code": 1
     }
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
