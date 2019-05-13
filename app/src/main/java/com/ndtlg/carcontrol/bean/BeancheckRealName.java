package com.ndtlg.carcontrol.bean;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeancheckRealName extends BeanBase {
    public String name;
    public String idNo;
    public String phoneNo;
    public String sessionKey;

    public BeancheckRealName(String name, String idNo) {
        this.name = name;
        this.idNo = idNo;
        this.phoneNo = mModelappLogin.userInfo.phoneNo;
        this.sessionKey = mModelappLogin.sessionKey;
        sign = readClassAttr(this);
    }
}
