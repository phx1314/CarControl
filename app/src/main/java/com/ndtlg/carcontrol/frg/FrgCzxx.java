//
//  FrgCzxx
//
//  Created by DELL on 2018-09-10 17:03:54
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;


public class FrgCzxx extends BaseFrg {

    public TextView mTextView1;
    public TextView mTextView2;
    public TextView mTextView3;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_czxx);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView1 = (TextView) findViewById(R.id.mTextView1);
        mTextView2 = (TextView) findViewById(R.id.mTextView2);
        mTextView3 = (TextView) findViewById(R.id.mTextView3);


    }

    public void loaddata() {
        mTextView1.setText(F.mModelappLogin.userInfo.name);
        mTextView2.setText(F.changeSFZ(F.mModelappLogin.userInfo.idNo));
        mTextView3.setText(F.changePhone(F.mModelappLogin.userInfo.phoneNo));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车主信息");
    }
}