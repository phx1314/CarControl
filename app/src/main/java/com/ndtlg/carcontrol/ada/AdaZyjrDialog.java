//
//  AdaZyjrDialog
//
//  Created by DELL on 2019-05-13 18:52:26
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ndtlg.carcontrol.item.ZyjrDialog;

import java.util.List;

public class AdaZyjrDialog extends MAdapter<String>{

   public AdaZyjrDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ZyjrDialog.getView(getContext(), parent);;
        }
        ZyjrDialog mZyjrDialog=(ZyjrDialog) convertView.getTag();
//        mZyjrDialog.set(item);
        return convertView;
    }
}
