package com.ndtlg.carcontrol.bean;

import com.ndtlg.carcontrol.F;

import static com.ndtlg.carcontrol.F.readClassAttr;

/**
 * Created by DELL on 2017/6/19.
 */

public class Beanwindow extends BeanBase {
    public String vin = F.mModelappLogin.userInfo.vin;
    public String leftFrontWindow;
    public String rightFrontWindow;
    public String leftRearWindow;
    public String rightRearWindow;

    public Beanwindow(int leftFrontWindow, int rightFrontWindow, int leftRearWindow, int rightRearWindow) {
        this.leftFrontWindow = leftFrontWindow+"";
        this.rightFrontWindow = rightFrontWindow+"";
        this.leftRearWindow = leftRearWindow+"";
        this.rightRearWindow = rightRearWindow+"";
        sign = readClassAttr(this);
    }
}
