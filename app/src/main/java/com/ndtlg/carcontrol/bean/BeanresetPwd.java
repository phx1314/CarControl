package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanresetPwd extends BeanBase {
    public String phoneNo;
    public String newPwd;
    public String msgCode;
    public String sessionKey;

    public BeanresetPwd(String phoneNo, String newPwd, String msgCode) {
        this.phoneNo = phoneNo;
        this.newPwd = newPwd;
        this.msgCode = msgCode;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
