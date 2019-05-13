//
//  AdaZdjl
//
//  Created by DELL on 2018-09-11 10:05:29
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ndtlg.carcontrol.item.Zdjl;

public class AdaZdjl extends MAdapter<String>{

   public AdaZdjl(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Zdjl.getView(getContext(), parent);;
        }
        Zdjl mZdjl=(Zdjl) convertView.getTag();
        mZdjl.set(item);
        return convertView;
    }
}
