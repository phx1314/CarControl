package com.ndtlg.carcontrol.bean;

import com.ab.view.pullview.BeanListBase;
import com.ndtlg.carcontrol.F;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfoList1 extends BeanListBase {
    public String vin;

    public BeanqueryDiagInfoList1() {
        this.vin = F.mModelappLogin.userInfo.vin;
    }
}
