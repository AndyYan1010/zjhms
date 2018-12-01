package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/1 13:28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LightMeasureTypeInfo {

    /**
     * energyList : [{"faddtime":"2018-12-01 13:28:11","power":"0.00"},{"faddtime":"2018-12-01 12:28:17","power":"0.00"}]
     * message : 类型下能源监测息查询成功
     * code : 1
     */

    private String message;
    private int                  code;
    private List<EnergyListBean> energyList;

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

    public List<EnergyListBean> getEnergyList() {
        return energyList;
    }

    public void setEnergyList(List<EnergyListBean> energyList) {
        this.energyList = energyList;
    }

    public static class EnergyListBean {
        /**
         * faddtime : 2018-12-01 13:28:11
         * power : 0.00
         */

        private String faddtime;
        private String power;
        /**
         * electric_quantity : 0.07
         * voltage : 226.30
         */

        private String electric_quantity;
        private String voltage;

        public String getFaddtime() {
            return faddtime;
        }

        public void setFaddtime(String faddtime) {
            this.faddtime = faddtime;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getElectric_quantity() {
            return electric_quantity;
        }

        public void setElectric_quantity(String electric_quantity) {
            this.electric_quantity = electric_quantity;
        }

        public String getVoltage() {
            return voltage;
        }

        public void setVoltage(String voltage) {
            this.voltage = voltage;
        }
    }
}
