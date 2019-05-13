package com.ndtlg.carcontrol.bean;

import java.io.Serializable;


/**
 * Created by DELL on 2017/6/19.
 */

public class BeanBase implements Serializable {
    public String timestamp = System.currentTimeMillis() + "";
    public String sign = "";


}
