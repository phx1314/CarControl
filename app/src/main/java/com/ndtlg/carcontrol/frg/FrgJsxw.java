//
//  FrgJsxw
//
//  Created by Administrator on 2018-09-09 10:08:52
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
import com.ndtlg.carcontrol.ada.AdaJsxw;


public class FrgJsxw extends BaseFrg {

    public AbPullListView mAbPullListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_jsxw);
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
        mAbPullListView.setAdapter(new AdaJsxw(getContext(), F.getData()));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("驾驶行为分析");
    }
}