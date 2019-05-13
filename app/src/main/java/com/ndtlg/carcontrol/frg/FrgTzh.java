//
//  FrgTzh
//
//  Created by DELL on 2018-09-07 13:19:15
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;

import com.framewidget.F;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;

import com.ab.view.pullview.AbPullListView;
import com.ndtlg.carcontrol.ada.AdaTzh;


public class FrgTzh extends BaseFrg {

    public AbPullListView mAbPullListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_tzh);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);


    }

    public void loaddata() {
        mAbPullListView.setAdapter(new AdaTzh(getContext(), F.getData()));
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("通知");
    }
}