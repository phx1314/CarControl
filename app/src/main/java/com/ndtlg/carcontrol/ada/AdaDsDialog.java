//
//  AdaDsDialog
//
//  Created by DELL on 2018-09-05 15:30:56
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ndtlg.carcontrol.item.DsDialog;

import java.util.List;

public class AdaDsDialog extends MAdapter<String>{

   public AdaDsDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = DsDialog.getView(getContext(), parent);;
        }
        DsDialog mDsDialog=(DsDialog) convertView.getTag();
//        mDsDialog.set(item);
        return convertView;
    }
}
