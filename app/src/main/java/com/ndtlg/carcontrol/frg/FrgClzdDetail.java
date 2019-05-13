//
//  FrgClzdDetail
//
//  Created by Administrator on 2018-09-09 10:28:00
//  Copyright (c) Administrator All rights reserved.


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
import com.ndtlg.carcontrol.R;


public class FrgClzdDetail extends BaseFrg {

    public TextView mImageView_dl;
    public LinearLayout mLinearLayout1;
    public TextView mTextView_time;
    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public TextView mTextView_count;
    public TextView mTextView_wt;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clzd_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_fen = (TextView) findViewById(R.id.mTextView_fen);
        mTextView_fenZ1 = (TextView) findViewById(R.id.mTextView_fenZ1);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mTextView_wt = (TextView) findViewById(R.id.mTextView_wt);

        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgKszd.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆诊断");
        mHeadlayout.setRText("诊断记录");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZdjl.class, TitleAct.class);
            }
        });
    }
}