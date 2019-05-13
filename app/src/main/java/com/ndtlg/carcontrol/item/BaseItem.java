//
//  BaseItem
//
//  Created by DELL on 2018-09-04 17:06:39
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.item;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseItem implements OnClickListener {
	protected Context context;
	protected View contentview;

	@Override
	public void onClick(View v) {

	}

	public View findViewById(int id) {
         return this.contentview.findViewById(id);
    }

}

