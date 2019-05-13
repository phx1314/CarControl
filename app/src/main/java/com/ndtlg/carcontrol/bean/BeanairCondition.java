package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanairCondition extends BeanBase {
    public String ac = mModelCz.content.ac + "";
    public String ad = mModelCz.content.ad + "";
    public String off = mModelCz.content.off + "";
    public String damper = mModelCz.content.damper + "";
    public String cycle = mModelCz.content.cycle + "";
    public String exTemp = mModelCz.content.exTemp + "";
    public String inTemp = mModelCz.content.inTemp + "";
    public String av = mModelCz.content.av + "";
    public String bd = mModelCz.content.bd + "";
    public String auto = mModelCz.content.auto + "";
    public String sTemp =  mModelCz.content.sTemp + "";
    public String heat = mModelCz.content.heat + "";
    public String reserve3 = mModelCz.content.reserve3 + "";
    public String serialNumber = mModelCz.content.serialNumber + "";
    public String type = mModelCz.content.type + "";
    public String remainSTime = mModelCz.content.remainSTime_all + "";
    public String remainETime = mModelCz.content.remainETime_all + "";
    public String vin = F.mModelappLogin.userInfo.vin;

    public BeanairCondition() {
        sign = readClassAttr(this);
    }
}
