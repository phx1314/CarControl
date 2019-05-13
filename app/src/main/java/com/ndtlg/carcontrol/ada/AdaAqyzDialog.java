//
//  AdaAqyzDialog
//
//  Created by DELL on 2018-09-04 17:29:48
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ndtlg.carcontrol.item.AqyzDialog;

import java.util.List;

public class AdaAqyzDialog extends MAdapter<String>{

   public AdaAqyzDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = AqyzDialog.getView(getContext(), parent);;
        }
        AqyzDialog mAqyzDialog=(AqyzDialog) convertView.getTag();
//        mAqyzDialog.set(item);
        return convertView;
    }
}
