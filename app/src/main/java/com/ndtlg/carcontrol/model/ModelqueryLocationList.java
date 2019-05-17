package com.ndtlg.carcontrol.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/6/19.
 */

public class ModelqueryLocationList implements Serializable {


    /**
     * status : 1
     * info : 操作完成
     * code : null
     * type : null
     * content : [{"uid":0,"vin":"07B8101790228760","positionStatus":null,"lng":"119.988934","lat":"31.814848","uploadTime":"2019-05-17 00:00:06","createTime":null}]
     */

    public int status;
    public String info;
    public Object code;
    public Object type;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * uid : 0
         * vin : 07B8101790228760
         * positionStatus : null
         * lng : 119.988934
         * lat : 31.814848
         * uploadTime : 2019-05-17 00:00:06
         * createTime : null
         */

        public int uid;
        public String vin;
        public Object positionStatus;
        public String lng;
        public String lat;
        public String uploadTime;
        public Object createTime;
    }
}
