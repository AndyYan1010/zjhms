package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 10:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddSceneResultInfo {

    /**
     * result : 1
     * device_status : 0
     * device_id : q2131332
     * device_value : 0001
     * scene_id : 4aef7996-bb61-45e8-92f3-865e5715a46d
     * id : d061f331edfe11e8b051000c29b0f385
     * message : 场景新增成功
     */

    private int result;
    private String device_status;
    private String device_id;
    private String device_value;
    private String scene_id;
    private String id;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDevice_status() {
        return device_status;
    }

    public void setDevice_status(String device_status) {
        this.device_status = device_status;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_value() {
        return device_value;
    }

    public void setDevice_value(String device_value) {
        this.device_value = device_value;
    }

    public String getScene_id() {
        return scene_id;
    }

    public void setScene_id(String scene_id) {
        this.scene_id = scene_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
