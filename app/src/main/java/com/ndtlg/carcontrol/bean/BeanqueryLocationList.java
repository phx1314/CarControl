package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryLocationList extends BeanBase {
    public String vin = F.mModelappLogin.userInfo.vin;
    public String startTime;
    public String endTime;

    public BeanqueryLocationList(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        sign = readClassAttr(this);
    }
}
