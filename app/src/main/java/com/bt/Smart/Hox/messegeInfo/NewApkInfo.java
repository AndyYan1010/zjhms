package com.bt.Smart.Hox.messegeInfo;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 20:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class NewApkInfo {

    /**
     * newAppVersion : {"apk_file":"upload/files/20181122/SmartHox主控权限管理及通信协议_1542864184731.docx","change_message":"32424234sfwfewf","show_code":"1.0.2"}
     * message : 最新app版本查询成功
     * code : 1
     */

    private NewAppVersionBean newAppVersion;
    private String message;
    private int    code;

    public NewAppVersionBean getNewAppVersion() {
        return newAppVersion;
    }

    public void setNewAppVersion(NewAppVersionBean newAppVersion) {
        this.newAppVersion = newAppVersion;
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

    public static class NewAppVersionBean {
        /**
         * apk_file : upload/files/20181122/SmartHox主控权限管理及通信协议_1542864184731.docx
         * change_message : 32424234sfwfewf
         * show_code : 1.0.2
         */

        private String apk_file;
        private String change_message;
        private String show_code;
        /**
         * version_code : 1
         */

        private int    version_code;

        public String getApk_file() {
            return apk_file;
        }

        public void setApk_file(String apk_file) {
            this.apk_file = apk_file;
        }

        public String getChange_message() {
            return change_message;
        }

        public void setChange_message(String change_message) {
            this.change_message = change_message;
        }

        public String getShow_code() {
            return show_code;
        }

        public void setShow_code(String show_code) {
            this.show_code = show_code;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }
    }
}
