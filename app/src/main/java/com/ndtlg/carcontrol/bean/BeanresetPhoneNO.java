package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanresetPhoneNO extends BeanBase {
    public String phoneNo;
    public String newPhoneNo;
    public String newMsgCode;
    public String msgCode;
    public String sessionKey;

    public BeanresetPhoneNO(String phoneNo, String newPhoneNO, String newMsgCode, String msgCode) {
        this.phoneNo = phoneNo;
        this.newPhoneNo = newPhoneNO;
        this.newMsgCode = newMsgCode;
        this.msgCode = msgCode;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
