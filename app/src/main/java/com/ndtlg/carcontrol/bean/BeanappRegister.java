package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanappRegister extends BeanBase {
    public String phoneNo;
    public String password;
    public String msgCode;

    public BeanappRegister(String phoneNo, String password, String msgCode) {
        this.phoneNo = phoneNo;
        this.password = password;
        this.msgCode = msgCode;
        sign = readClassAttr(this);
    }
}
