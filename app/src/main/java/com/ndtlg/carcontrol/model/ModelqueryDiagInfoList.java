package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelqueryDiagInfoList implements Serializable {


    /**
     * totalDriveBehaviorCount : {"vin":"07B8101790228760","totalAccelerateCount":0,"totalDecelerateCount":2}
     * code : 000000
     * tripDiag : {"avgDriveTime":87.6667,"maxDriveTime":240,"avgChargeSoc":20.9677,"minChargeSoc":10,"vin":"07B8101790228760"}
     */

    public TotalDriveBehaviorCountBean totalDriveBehaviorCount;
    public String code;
    public TripDiagBean tripDiag;

    public static class TotalDriveBehaviorCountBean {
        /**
         * vin : 07B8101790228760
         * totalAccelerateCount : 0
         * totalDecelerateCount : 2
         */

        public String vin;
        public int totalAccelerateCount;
        public int totalDecelerateCount;
    }

    public static class TripDiagBean {
        /**
         * avgDriveTime : 87.6667
         * maxDriveTime : 240.0
         * avgChargeSoc : 20.9677
         * minChargeSoc : 10.0
         * vin : 07B8101790228760
         */

        public double avgDriveTime;
        public double maxDriveTime;
        public double avgChargeSoc;
        public double minChargeSoc;
        public String vin;
    }
}
