package com.ndtlg.carcontrol.bean;

import com.ab.view.pullview.BeanListBase;
import com.ndtlg.carcontrol.F;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanqueryDiagInfoList extends BeanListBase {
    public String startTime = "";
    public String endTime = "";
    public String vin= F.mModelappLogin.userInfo.vin;

    public BeanqueryDiagInfoList(String startTime,String endTime) {
//        this.vin = "07B8101790228760";
        this.startTime = startTime+" 00:00:00";
        this.endTime = endTime+" 00:00:00";
    }
}
