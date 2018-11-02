package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 9:41
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RoomChoiceInfo {
    public RoomChoiceInfo() {

    }

    public RoomChoiceInfo(String room_name, boolean isChoice) {
        this.room_name = room_name;
        this.isChoice = isChoice;
    }

    /**
     * room_name : 客厅
     * isChoice : true
     */

    private String  room_name;
    private boolean isChoice;

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public boolean isIsChoice() {
        return isChoice;
    }

    public void setIsChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }
}
