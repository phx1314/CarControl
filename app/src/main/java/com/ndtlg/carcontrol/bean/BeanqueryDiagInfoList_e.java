package com.ndtlg.carcontrol.bean;

import com.ab.view.pullview.BeanListBase;
import com.ndtlg.carcontrol.F;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfoList_e extends BeanListBase {
    public String vin= F.mModelappLogin.userInfo.vin;
    public String endTime = "";
    public BeanqueryDiagInfoList_e(String endTime) {
//        this.vin = "07B8101790228760";
        this.endTime = endTime+" 00:00:00";
    }
}
