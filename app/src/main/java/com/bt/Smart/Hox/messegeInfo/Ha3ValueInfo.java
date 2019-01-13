package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 8:51
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Ha3ValueInfo {

    /**
     {
     "ha3TypeVlaueList": [
     {
     "fname": "温度",
     "fval": "temperature"
     },
     {
     "fname": "湿度",
     "fval": "humidity"
     },
     {
     "fname": "PM2.5",
     "fval": "pm25"
     },
     {
     "fname": "PM100",
     "fval": "pm100"
     },
     {
     "fname": "甲醛",
     "fval": "formaldehyde"
     },
     {
     "fname": "VOCs",
     "fval": "voc"
     },
     {
     "fname": "CO2",
     "fval": "co2"
     },
     {
     "fname": "CO",
     "fval": "co"
     }
     ],
     "message": "空气哨兵指标值",
     "code": 1
     }
     */

    private String message;
    private int                        code;
    private List<Ha3TypeVlaueListBean> ha3TypeVlaueList;

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

    public List<Ha3TypeVlaueListBean> getHa3TypeVlaueList() {
        return ha3TypeVlaueList;
    }

    public void setHa3TypeVlaueList(List<Ha3TypeVlaueListBean> ha3TypeVlaueList) {
        this.ha3TypeVlaueList = ha3TypeVlaueList;
    }

    public static class Ha3TypeVlaueListBean {
        /**
         * fname : 温度
         * fval : temperature
         */

        private String fname;
        private String fval;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getFval() {
            return fval;
        }

        public void setFval(String fval) {
            this.fval = fval;
        }
    }
}
