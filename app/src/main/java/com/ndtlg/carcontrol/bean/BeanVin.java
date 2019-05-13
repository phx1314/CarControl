package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanVin extends BeanBase {
    public String vin;

    public BeanVin(String vin) {
        this.vin = vin;
        sign = readClassAttr(this);
    }
}
