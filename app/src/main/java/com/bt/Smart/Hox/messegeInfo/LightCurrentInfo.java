package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/1 13:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LightCurrentInfo {

    /**
     * energy : {"faddtime":"2018-12-01 13:15:29","power":"0.00","electric_quantity":"0.07","voltage":"226.70"}
     * message : 能源监测息查询成功
     * code : 1
     */

    private EnergyBean energy;
    private String message;
    private int    code;

    public EnergyBean getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyBean energy) {
        this.energy = energy;
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

    public static class EnergyBean {
        /**
         * faddtime : 2018-12-01 13:15:29
         * power : 0.00
         * electric_quantity : 0.07
         * voltage : 226.70
         */

        private String faddtime;
        private String power;
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
