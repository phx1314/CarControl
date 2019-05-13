//
//  AdaJsxw
//
//  Created by DELL on 2018-09-11 10:31:32
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ndtlg.carcontrol.item.Jsxw;

public class AdaJsxw extends MAdapter<String>{

   public AdaJsxw(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Jsxw.getView(getContext(), parent);;
        }
        Jsxw mJsxw=(Jsxw) convertView.getTag();
        mJsxw.set(item);
        return convertView;
    }
}
