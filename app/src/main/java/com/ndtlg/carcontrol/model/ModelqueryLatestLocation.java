package com.ndtlg.carcontrol.model;

/**
 * Created by DELL on 2019/5/17.
 */

public class ModelqueryLatestLocation {


    /**
     * status : 1
     * info : 操作完成
     * code :
     * type : null
     * content : {"lng":"119.989039","uploadTime":"2019-05-15 14:42:21","lat":"31.814666"}
     */

    public int status;
    public String info;
    public String code;
    public Object type;
    public ContentBean content;

    public static class ContentBean {
        /**
         * lng : 119.989039
         * uploadTime : 2019-05-15 14:42:21
         * lat : 31.814666
         */

        public String lng;
        public String address;
        public String uploadTime;
        public String lat;
    }
}
