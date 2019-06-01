package com.ndtlg.carcontrol.bean;

import com.ab.view.pullview.BeanListBase;
import com.ndtlg.carcontrol.F;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfoList_s extends BeanListBase {
    public String vin= F.mModelappLogin.userInfo.vin;
    public String startTime = "";
    public BeanqueryDiagInfoList_s(String startTime) {
//        this.vin = "07B8101790228760";
        this.startTime = startTime+" 00:00:00";
    }
}
