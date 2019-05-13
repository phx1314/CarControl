//
//  AdaKszd
//
//  Created by Administrator on 2018-09-09 18:44:38
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ndtlg.carcontrol.item.Kszd;

public class AdaKszd extends MAdapter<String>{

   public AdaKszd(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Kszd.getView(getContext(), parent);;
        }
        Kszd mKszd=(Kszd) convertView.getTag();
        mKszd.set(item);
        return convertView;
    }
}
