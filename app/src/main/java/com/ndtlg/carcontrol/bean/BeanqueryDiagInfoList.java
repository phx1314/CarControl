package com.ndtlg.carcontrol.bean;

import com.ab.view.pullview.BeanListBase;
import com.ndtlg.carcontrol.F;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfoList extends BeanListBase {
    public String startTime = "";
    public String endTime = "";
    public String vin;

    public BeanqueryDiagInfoList() {
        this.vin = F.mModelappLogin.userInfo.vin;
    }
}
