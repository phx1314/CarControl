package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelqueryAirConditionState implements Serializable {


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

    //    {"status":0,"ac":0,"ad":0,"off":0,"damper":0,"cycle":0,"exTemp":0,"inTemp":0,"av":0,"bd":0,"auto":0,"sTemp":0,"heat":0,"reserve3":0,"serialNumber":65535}
    public static class ContentBean {


        public int result;
        public int ac;
        public int ad;
        public int off;
        public int damper;
        public int cycle;
        public int exTemp;
        public int inTemp;
        public int av;
        public int bd;
        public int auto;
        public double sTemp;
        public int heat;
        public int reserve3;
        public int serialNumber;
        public int type;
        public int remainSTime;
        public int remainETime;
        public int remainSTime_all;
        public int remainETime_all;
        public String vin;
    }


}
