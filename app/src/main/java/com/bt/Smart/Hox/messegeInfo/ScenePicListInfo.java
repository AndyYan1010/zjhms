package com.bt.Smart.Hox.messegeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 9:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ScenePicListInfo {

    /**
     * scenePiclist : [{"fpic":"upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg","forder":1,"id":1},{"fpic":"upload/files/20181108/vmw-vforum-banner-muti-san-pics_1541681958703.jpg","forder":2,"id":2}]
     * message : 场景默认图片查询成功
     * code : 1
     */

    private String message;
    private int                    code;
    private List<ScenePiclistBean> scenePiclist;

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

    public List<ScenePiclistBean> getScenePiclist() {
        return scenePiclist;
    }

    public void setScenePiclist(List<ScenePiclistBean> scenePiclist) {
        this.scenePiclist = scenePiclist;
    }

    public static class ScenePiclistBean {
        /**
         * fpic : upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg
         * forder : 1
         * id : 1
         */

        private String fpic;
        private int forder;
        private int id;

        public String getFpic() {
            return fpic;
        }

        public void setFpic(String fpic) {
            this.fpic = fpic;
        }

        public int getForder() {
            return forder;
        }

        public void setForder(int forder) {
            this.forder = forder;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
