//
//  AdaKtmsDialog
//
//  Created by Administrator on 2018-09-09 09:24:12
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ndtlg.carcontrol.item.KtmsDialog;

import java.util.List;

public class AdaKtmsDialog extends MAdapter<String>{

   public AdaKtmsDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = KtmsDialog.getView(getContext(), parent);;
        }
        KtmsDialog mKtmsDialog=(KtmsDialog) convertView.getTag();
//        mKtmsDialog.set(item);
        return convertView;
    }
}
