//
//  AdaJrzlDialog
//
//  Created by DELL on 2018-09-04 17:06:39
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ndtlg.carcontrol.item.JrzlDialog;

import java.util.List;

public class AdaJrzlDialog extends MAdapter<String>{

   public AdaJrzlDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = JrzlDialog.getView(getContext(), parent);;
        }
        JrzlDialog mJrzlDialog=(JrzlDialog) convertView.getTag();
//        mJrzlDialog.set(item);
        return convertView;
    }
}
