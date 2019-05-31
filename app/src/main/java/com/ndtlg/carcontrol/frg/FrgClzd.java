//
//  FrgClzd
//
//  Created by DELL on 2018-09-07 17:06:30
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanqueryDiagInfo;
import com.ndtlg.carcontrol.model.ModelqueryDiagInfo;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.queryDiagInfo;


public class FrgClzd extends BaseFrg {

    public LinearLayout mLinearLayout1;
    public TextView mTextView_time;
    public TextView mTextView_fen;
    public TextView mTextView_count;
    public TextView mTextView_wt;
    public LinearLayout mLinearLayout2;
    public TextView mTextView_time2;
    public TextView mTextView_fen2;
    public TextView mTextView_csh1;
    public TextView mTextView_csh2;
    public TextView mTextView_fenZ1;
    public TextView mTextView_fenZ2;
    public TextView mTextView_sc;
    public TextView mTextView_zdsc;
    public TextView mTextView_soc;
    public TextView mTextView_soczd;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clzd);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_fen = (TextView) findViewById(R.id.mTextView_fen);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mTextView_wt = (TextView) findViewById(R.id.mTextView_wt);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mTextView_time2 = (TextView) findViewById(R.id.mTextView_time2);
        mTextView_fen2 = (TextView) findViewById(R.id.mTextView_fen2);
        mTextView_csh1 = (TextView) findViewById(R.id.mTextView_csh1);
        mTextView_csh2 = (TextView) findViewById(R.id.mTextView_csh2);
        mHeadlayout = (com.framewidget.view.Headlayout) findViewById(R.id.mHeadlayout);
        mTextView_fenZ1 = (TextView) findViewById(R.id.mTextView_fenZ1);
        mTextView_fenZ2 = (TextView) findViewById(R.id.mTextView_fenZ2);
        mTextView_sc = (TextView) findViewById(R.id.mTextView_sc);
        mTextView_zdsc = (TextView) findViewById(R.id.mTextView_zdsc);
        mTextView_soc = (TextView) findViewById(R.id.mTextView_soc);
        mTextView_soczd = (TextView) findViewById(R.id.mTextView_soczd);

        mLinearLayout1.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgClzdDetail.class, TitleAct.class);
            }
        }));
        mLinearLayout2.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgJsxw.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        mHeadlayout.setTitle("车辆诊断");
        mHeadlayout.setLeftBackground(R.drawable.ic_nav_user);
        mHeadlayout.setRightBacgroud(R.drawable.ic_nav_message);
        mHeadlayout.setRightOnclicker(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgTzh.class, TitleAct.class);
            }
        }));
        mHeadlayout.setLeftOnclicker(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGrzx.class, TitleAct.class);
            }
        }));
        loadJsonUrl(queryDiagInfo, new BeanqueryDiagInfo());
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(queryDiagInfo)) {
            ModelqueryDiagInfo mModelqueryDiagInfo = (ModelqueryDiagInfo) json2Model(content, ModelqueryDiagInfo.class);
            mTextView_sc.setText(F.go2Wei(mModelqueryDiagInfo.tripDiag.avgDriveTime) + "");
            mTextView_zdsc.setText(mModelqueryDiagInfo.tripDiag.maxDriveTime + "");
            mTextView_soc.setText(F.go2Wei(mModelqueryDiagInfo.tripDiag.avgChargeSoc) + "%");
            mTextView_soczd.setText(mModelqueryDiagInfo.tripDiag.minChargeSoc + "%");
            mTextView_csh1.setText(mModelqueryDiagInfo.totalDriveBehaviorCount.totalAccelerateCount + "");
            mTextView_csh2.setText(mModelqueryDiagInfo.totalDriveBehaviorCount.totalDecelerateCount + "");
        }
    }
}