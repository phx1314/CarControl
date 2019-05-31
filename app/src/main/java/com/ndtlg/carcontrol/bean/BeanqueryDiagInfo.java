package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfo extends BeanBase {
    public String vin;

    public BeanqueryDiagInfo() {
        this.vin = F.mModelappLogin.userInfo.vin;
        sign = readClassAttr(this);
    }
}
