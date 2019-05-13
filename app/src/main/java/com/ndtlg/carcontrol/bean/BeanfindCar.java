package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanfindCar extends BeanBase {
    public String vin = F.mModelappLogin.userInfo.vin;
    public String mode;

    public BeanfindCar(String mode) {
        this.mode = mode;
        sign = readClassAttr(this);
    }
}
