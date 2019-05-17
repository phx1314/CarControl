package com.ndtlg.carcontrol.model;

/**
 * Created by DELL on 2019/5/17.
 */

public class ModelPub {

    /**
     * status : 1
     * info : 操作完成
     * code :
     * type : null
     * content : {"result":0,"serialNumber":618,"type":2,"vin":"07B8101790228760"}
     */

    public int status;
    public String info;
    public String code;
    public Object type;
    public ContentBean content;

    public static class ContentBean {
        /**
         * result : 0
         * serialNumber : 618
         * type : 2
         * vin : 07B8101790228760
         */

        public int result;
        public int serialNumber;
        public int type;
        public String vin;
    }
}
