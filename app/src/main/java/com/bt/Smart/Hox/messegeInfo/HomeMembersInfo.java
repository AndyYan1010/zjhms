package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 13:11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeMembersInfo {

    /**
     * homeList : [{"wx_name":"18036215611","fname":"haha","ismanager":"1","head_pic":"http://112.90.178.68:8081/upFiles/1544055659244.jpeg","id":"b35e098dddb511e89c91000c2950df04","ftelephone":"18036215611"},{"wx_name":"Andy Yan","fname":"哈哈","ismanager":"0","wx_pic":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJSoHRiasmEsSO62akEkah2pWGbicFPjeOWlL69alkicTOrvicVciaE8OYqHPMXQUFJNlsTovQu2LtK4rw/132","head_pic":"http://112.90.178.68:8081/upFiles/1543834506992.jpeg","id":"74ee5a6bdb5911e8813c000c2950df04","ftelephone":"18036215618"}]
     * message : 家庭成员列表查询成功
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
         * wx_name : 18036215611
         * fname : haha
         * ismanager : 1
         * head_pic : http://112.90.178.68:8081/upFiles/1544055659244.jpeg
         * id : b35e098dddb511e89c91000c2950df04
         * ftelephone : 18036215611
         * wx_pic : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJSoHRiasmEsSO62akEkah2pWGbicFPjeOWlL69alkicTOrvicVciaE8OYqHPMXQUFJNlsTovQu2LtK4rw/132
         */

        private String wx_name;
        private String fname;
        private String ismanager;
        private String head_pic;
        private String id;
        private String ftelephone;
        private String wx_pic;

        public String getWx_name() {
            return wx_name;
        }

        public void setWx_name(String wx_name) {
            this.wx_name = wx_name;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getIsmanager() {
            return ismanager;
        }

        public void setIsmanager(String ismanager) {
            this.ismanager = ismanager;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFtelephone() {
            return ftelephone;
        }

        public void setFtelephone(String ftelephone) {
            this.ftelephone = ftelephone;
        }

        public String getWx_pic() {
            return wx_pic;
        }

        public void setWx_pic(String wx_pic) {
            this.wx_pic = wx_pic;
        }
    }
}
