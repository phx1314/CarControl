//
//  AdaClzdOver
//
//  Created by Administrator on 2018-09-09 14:49:03
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ndtlg.carcontrol.item.ClzdOver;

public class AdaClzdOver extends MAdapter<String>{

   public AdaClzdOver(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ClzdOver.getView(getContext(), parent);;
        }
        ClzdOver mClzdOver=(ClzdOver) convertView.getTag();
        mClzdOver.set(item);
        return convertView;
    }
}
