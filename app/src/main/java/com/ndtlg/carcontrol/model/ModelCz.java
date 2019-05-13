package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelCz implements Serializable {

    public int highLamp = -1;
    public int lowLamp = -1;
    public int placeLamp = -1;
    public int flashLamp = -1;
    public int eState = -1;
    public int trunk = -1;
    public int leftFrontWindowState = -1;
    public int rightFrontWindowState = -1;
    public int leftRearWindowState = -1;
    public int rightRearWindowState = -1;
    public int lockCentral = -1;
    public boolean isKtCz;
    public ModelqueryAirConditionState.ContentBean content = new ModelqueryAirConditionState.ContentBean();

}
