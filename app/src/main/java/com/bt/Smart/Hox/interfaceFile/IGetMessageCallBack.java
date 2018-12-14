package com.bt.Smart.Hox.interfaceFile;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/6 18:27
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface IGetMessageCallBack {
    void setMessage(String time, float temperaturefloat, float humidityfloat, float PM25float, float PM100float, float formaldehydefloat, float VOCfloat, float CO2float, float COfloat);
}
