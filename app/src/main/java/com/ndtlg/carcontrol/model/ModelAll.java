package com.ndtlg.carcontrol.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelAll implements Serializable {


    public String method;
    public Object object;

    public ModelAll(String method, Object object) {
        this.method = method;
        this.object = object;
    }
}
