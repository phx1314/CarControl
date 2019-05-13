package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelKg implements Serializable {


    /**
     * status : 1
     * info : 操作完成
     * code :
     * type : null
     * content : {"result":20}
     */

    public int status;
    public String info;
    public String code;
    public Object type;
    public ContentBean content = new ContentBean();

    public static class ContentBean {
        /**
         * result : 20
         */

        public int result;
        public int type;
        public String methodName;


    }
}
