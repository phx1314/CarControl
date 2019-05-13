package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeansetPlateNo extends BeanBase {
    public String phoneNo;
    public String plateNo;
    public String sessionKey;

    public BeansetPlateNo(  String plateNo) {
        this.phoneNo = mModelappLogin.userInfo.phoneNo;
        this.plateNo = plateNo;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
