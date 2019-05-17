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

import java.util.List;

public class AdaJsxw extends MAdapter<String> {

    public AdaJsxw(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Jsxw.getView(getContext(), parent);
            ;
        }
        Jsxw mJsxw = (Jsxw) convertView.getTag();
        mJsxw.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgJsxwDetail.class, TitleAct.class);
            }
        });
        return convertView;
    }
}
