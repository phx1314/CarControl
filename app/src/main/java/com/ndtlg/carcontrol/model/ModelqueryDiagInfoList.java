package com.ndtlg.carcontrol.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelqueryDiagInfoList implements Serializable {


    /**
     * code : 000000
     * tripList : [{"id":4,"vin":"07B8101790228760","plateNo":"312","startTime":"2019-05-17 10:25:23","endTime":"2019-05-17 14:25:27","gmt_create":"2019-05-17 14:25:35","driveMileage":50,"driveTime":240,"socUse":30,"startLocation":{"uid":21772623,"vin":"07B8101790228760","positionStatus":0,"lng":119.989023,"lat":31.815436,"uploadTime":"2019-05-17 10:25:26","createTime":null},"endLocation":{"uid":21774062,"vin":"07B8101790228760","positionStatus":0,"lng":119.989076,"lat":31.814635,"uploadTime":"2019-05-17 14:25:27","createTime":null},"totalAccelerateCount":0,"totalDecelerateCount":0}]
     * totalCount : 0
     */

    public String code;
    public int totalCount;
    public List<TripListBean> tripList;

    public static class TripListBean implements Serializable{
        /**
         * id : 4
         * vin : 07B8101790228760
         * plateNo : 312
         * startTime : 2019-05-17 10:25:23
         * endTime : 2019-05-17 14:25:27
         * gmt_create : 2019-05-17 14:25:35
         * driveMileage : 50
         * driveTime : 240
         * socUse : 30
         * startLocation : {"uid":21772623,"vin":"07B8101790228760","positionStatus":0,"lng":119.989023,"lat":31.815436,"uploadTime":"2019-05-17 10:25:26","createTime":null}
         * endLocation : {"uid":21774062,"vin":"07B8101790228760","positionStatus":0,"lng":119.989076,"lat":31.814635,"uploadTime":"2019-05-17 14:25:27","createTime":null}
         * totalAccelerateCount : 0
         * totalDecelerateCount : 0
         */

        public int id;
        public String vin;
        public String plateNo;
        public String startTime;
        public String endTime;
        public String gmt_create;
        public int driveMileage;
        public int driveTime;
        public int socUse;
        public StartLocationBean startLocation;
        public EndLocationBean endLocation;
        public int totalAccelerateCount;
        public int totalDecelerateCount;

        public static class StartLocationBean implements Serializable{
            /**
             * uid : 21772623
             * vin : 07B8101790228760
             * positionStatus : 0
             * lng : 119.989023
             * lat : 31.815436
             * uploadTime : 2019-05-17 10:25:26
             * createTime : null
             */

            public int uid;
            public String vin;
            public String address="";
            public int positionStatus;
            public double lng;
            public double lat;
            public String uploadTime;
            public Object createTime;
        }

        public static class EndLocationBean implements Serializable{
            /**
             * uid : 21774062
             * vin : 07B8101790228760
             * positionStatus : 0
             * lng : 119.989076
             * lat : 31.814635
             * uploadTime : 2019-05-17 14:25:27
             * createTime : null
             */

            public int uid;
            public String vin;
            public String address="";
            public int positionStatus;
            public double lng;
            public double lat;
            public String uploadTime;
            public Object createTime;
        }
    }
}
