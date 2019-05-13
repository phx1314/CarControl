package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelqueryState implements Serializable {


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

        public int doorLf;
        public int doorRf;
        public int doorLr;
        public int doorRr;
        public int hood;
        public int lockLs;
        public int lockRs;
        public int bFogLamp;
        public int aFogLamp;
        public int bLight;
        public int backLight;
        public int dayLight;
        public int startStatus;
        public int cause;
        public int type;


        public int status;

        public int doorState;
        public int lockCentral;
        public int tSignalState;
        public int leftFrontWindowState;

        public int rightFrontWindowState;
        public int leftRearWindowState;
        public int rightRearWindowState;
        public int lightState;
        public int honkState;
        public int remainMileage;
        public int leftFrontTireTemp;
        public int rightFrontTireTemp;
        public int leftRearTireTemp;
        public int rightRearTireTemp;
        public int spareTireTemp;
        public int leftFrontTirePressure;
        public int rightFrontTirePressure;
        public int leftRearTirePressure;
        public int rightRearTirePressure;
        public int spareTirePressure;
        public int serialNumber;

        public int highLamp;
        public int lowLamp;
        public int placeLamp;
        public int flashLamp;
        public int eState;
        public int trunk;
        public String vin;
    }

}
