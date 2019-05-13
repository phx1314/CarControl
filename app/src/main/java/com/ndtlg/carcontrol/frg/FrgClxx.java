//
//  FrgClxx
//
//  Created by DELL on 2018-09-10 17:06:16
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;


public class FrgClxx extends BaseFrg {

    public TextView mTextView1;
    public TextView mTextView2;
    public TextView mTextView3;
    public TextView mTextView4;
    public TextView mTextView5;
    public LinearLayout mLinearLayout_cp;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clxx);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                loaddata();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView1 = (TextView) findViewById(R.id.mTextView1);
        mTextView2 = (TextView) findViewById(R.id.mTextView2);
        mTextView3 = (TextView) findViewById(R.id.mTextView3);
        mTextView4 = (TextView) findViewById(R.id.mTextView4);
        mTextView5 = (TextView) findViewById(R.id.mTextView5);
        mLinearLayout_cp = (LinearLayout) findViewById(R.id.mLinearLayout_cp);

        mLinearLayout_cp.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgXgcph.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        mTextView1.setText(F.mModelappLogin.vehicleInfo.vinCode);
        mTextView2.setText(F.mModelappLogin.vehicleInfo.plateNo);
        mTextView3.setText(F.mModelappLogin.vehicleInfo.vehicleType);
        mTextView4.setText(F.mModelappLogin.vehicleInfo.netType);
        mTextView5.setText(F.mModelappLogin.vehicleInfo.color);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆信息");
    }

}