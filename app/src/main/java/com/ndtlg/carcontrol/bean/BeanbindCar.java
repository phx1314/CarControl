package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanbindCar extends BeanBase {
    public String vin;
    public String phoneNo;
    public String sessionKey;
    public String msgCode;
    public String name;
    public String idNo;
    public String pin;

    public BeanbindCar(String vin, String phoneNo, String msgCode, String name, String idNo, String pin) {
        this.vin = vin;
        this.phoneNo = phoneNo;
        this.sessionKey = mModelappLogin.sessionKey;
        this.msgCode = msgCode;
        this.name = name;
        this.idNo = idNo;
        this.pin = pin;
        sign = readClassAttr(this);
    }
}
