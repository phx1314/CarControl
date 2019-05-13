//
//  AdaTzh
//
//  Created by DELL on 2018-09-07 13:23:16
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ndtlg.carcontrol.item.Tzh;

public class AdaTzh extends MAdapter<String>{

   public AdaTzh(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Tzh.getView(getContext(), parent);;
        }
        Tzh mTzh=(Tzh) convertView.getTag();
        mTzh.set(item);
        return convertView;
    }
}
