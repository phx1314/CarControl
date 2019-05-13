//
//  FrgZdjl
//
//  Created by Administrator on 2018-09-09 09:52:39
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;

import com.framewidget.F;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;

import com.ab.view.pullview.AbPullListView;
import com.ndtlg.carcontrol.ada.AdaZdjl;


public class FrgZdjl extends BaseFrg {

    public AbPullListView mAbPullListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zdjl);
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
        mAbPullListView.setAdapter(new AdaZdjl(getContext(), F.getData()));
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("诊断记录");
    }
}