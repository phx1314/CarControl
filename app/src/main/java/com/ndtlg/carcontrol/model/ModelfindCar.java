package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelfindCar implements Serializable {


    /**
     * status : 1
     * info : 操作完成
     * code :
     * type : null
     * content : {"result":0,"serialNumber":2009,"type":9,"vin":"07B8101790228757"}
     */

    public int status;
    public String info;
    public String code;
    public Object type;
    public ContentBean content;

    public static class ContentBean {
        /**
         * result : 0.0
         * serialNumber : 2009.0
         * type : 9.0
         * vin : 07B8101790228757
         */

        public double result;
        public double serialNumber;
        public double type;
        public String vin;
    }
}
