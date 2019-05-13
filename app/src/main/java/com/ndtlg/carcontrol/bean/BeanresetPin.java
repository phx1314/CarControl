package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanresetPin extends BeanBase {
    public String phoneNo;
    public String pin;
    public String msgCode;
    public String sessionKey;

    public BeanresetPin(String phoneNo, String pin, String msgCode) {

        this.phoneNo = phoneNo;
        this.pin = pin;
        this.msgCode = msgCode;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
