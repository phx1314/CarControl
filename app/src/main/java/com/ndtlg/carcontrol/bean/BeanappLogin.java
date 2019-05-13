package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanappLogin extends BeanBase {
    public String phoneNo;
    public String password;
    public String msgCode;
    public String loginType;

    public BeanappLogin(String phoneNo, String password, String msgCode, String loginType) {
        this.phoneNo = phoneNo;
        this.password = password;
        this.msgCode = msgCode;
        this.loginType = loginType;
        sign = readClassAttr(this);
    }
}
