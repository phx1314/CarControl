package com.ab.view.pullview;

import java.io.Serializable;

/**
 * Created by DELL on 2017/6/19.
 */

public class BeanListBase implements Serializable {
    public String timestamp = System.currentTimeMillis() + "";
    public String sign = "";
    public int page = 1;
    public int pageSize = 5;


}
