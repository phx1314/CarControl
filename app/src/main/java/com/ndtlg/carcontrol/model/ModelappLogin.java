package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelappLogin implements Serializable {


    /**
     * userInfo : {"phoneNo":"13915089457","vin":"07B8101790228757","name":"测试","idNo":"9*******1","bindCar":true}
     * code : 000000
     * sessionKey : Zvfx8ct581ejFPf
     * vehicleInfo : {"id":64,"gmt_create":"2018-08-06 14:19:57","gmt_modified":"2018-08-16 14:51:01","isActive":1,"modelCode":" ","vehicleType":"跑车","vinCode":"07B8101790228757","frameNo":" ","plateNo":" 苏D00001","config":" ","color":" ","tboxModelCode":" ","tboxSerialNo":" ","netType":"2G","iccid":" ","simNo":" ","bindVinCode":"07B8101790228757","online":0,"locationInfo":null}
     */

    public UserInfoBean userInfo;
    public String code;
    public String sessionKey;
    public VehicleInfoBean vehicleInfo;

    public static class UserInfoBean {
        /**
         * phoneNo : 13915089457
         * vin : 07B8101790228757
         * name : 测试
         * idNo : 9*******1
         * bindCar : true
         */

        public String phoneNo;
        public String vin;
        public String pin;
        public String name;
        public String idNo;
        public boolean bindCar;
    }

    public static class VehicleInfoBean {
        /**
         * id : 64
         * gmt_create : 2018-08-06 14:19:57
         * gmt_modified : 2018-08-16 14:51:01
         * isActive : 1
         * modelCode :
         * vehicleType : 跑车
         * vinCode : 07B8101790228757
         * frameNo :
         * plateNo :  苏D00001
         * config :
         * color :
         * tboxModelCode :
         * tboxSerialNo :
         * netType : 2G
         * iccid :
         * simNo :
         * bindVinCode : 07B8101790228757
         * online : 0
         * locationInfo : null
         */

        public int id;
        public String gmt_create;
        public String gmt_modified;
        public int isActive;
        public String modelCode;
        public String vehicleType;
        public String vinCode;
        public String frameNo;
        public String plateNo;
        public String config;
        public String color;
        public String tboxModelCode;
        public String tboxSerialNo;
        public String netType;
        public String iccid;
        public String simNo;
        public String bindVinCode;
        public int online;
        public Object locationInfo;
    }
}
