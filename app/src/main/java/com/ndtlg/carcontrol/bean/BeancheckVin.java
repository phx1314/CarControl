package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeancheckVin extends BeanBase {
    public String vin;
    public String checkCode;
    public String phoneNo;
    public String sessionKey;

    public BeancheckVin(String vin, String checkCode) {
        this.vin = vin;
        this.checkCode = checkCode;
        this.phoneNo = mModelappLogin.userInfo.phoneNo;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
