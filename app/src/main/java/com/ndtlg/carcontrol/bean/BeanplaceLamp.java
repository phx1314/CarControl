package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanplaceLamp extends BeanBase {
    public String vin;
    public String mode;
    public String placeLampTimes = "30";

    public BeanplaceLamp(  String mode ) {
        this.vin = F.mModelappLogin.userInfo.vin;
        this.mode = mode;
        sign = readClassAttr(this);
    }
}
