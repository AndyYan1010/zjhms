package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/5 17:15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddRoomToServiceInfo {

    /**
     * home_id : f67e15b2b940416480cbc042882874df
     * register_id : b35e098dddb511e89c91000c2950df04
     * house_member : [{"house_name":"衣帽间"},{"house_name":"帽间"}]
     */

    private String home_id;
    private String                register_id;
    private List<HouseMemberBean> house_member;

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public String getRegister_id() {
        return register_id;
    }

    public void setRegister_id(String register_id) {
        this.register_id = register_id;
    }

    public List<HouseMemberBean> getHouse_member() {
        return house_member;
    }

    public void setHouse_member(List<HouseMemberBean> house_member) {
        this.house_member = house_member;
    }

    public static class HouseMemberBean {
        /**
         * house_name : 衣帽间
         */

        private String house_name;

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
        }
    }
}
