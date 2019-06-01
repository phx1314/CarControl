//
//  AdaJsxw
//
//  Created by DELL on 2018-09-11 10:31:32
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.frg.FrgJsxwDetail;
import com.ndtlg.carcontrol.item.Jsxw;
import com.ndtlg.carcontrol.model.ModelqueryDiagInfoList;

import java.util.List;

public class AdaJsxw extends MAdapter<ModelqueryDiagInfoList.TripListBean> {

    public AdaJsxw(Context context, List<ModelqueryDiagInfoList.TripListBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelqueryDiagInfoList.TripListBean item = get(position);
        if (convertView == null) {
            convertView = Jsxw.getView(getContext(), parent);
        }
        Jsxw mJsxw = (Jsxw) convertView.getTag();
        mJsxw.set(item,this);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgJsxwDetail.class, TitleAct.class,"start_time",item.startTime,"end_time",item.endTime);
            }
        });
        return convertView;
    }
}
