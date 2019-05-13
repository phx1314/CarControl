//
//  FrgClzdOver
//
//  Created by Administrator on 2018-09-09 14:41:14
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MListView;
import com.ndtlg.carcontrol.R;


public class FrgClzdOver extends BaseFrg {

    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public TextView mTextView_jg;
    public TextView mImageView_cxzd;
    public TextView mImageView_zdjl;
    public TextView mTextView_count;
    public TextView mTextView_wt;
    public ImageView mImageView_gou;
    public TextView mTextView_wt2;
    public MListView mMListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clzd_over);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_fen = (TextView) findViewById(R.id.mTextView_fen);
        mTextView_fenZ1 = (TextView) findViewById(R.id.mTextView_fenZ1);
        mTextView_jg = (TextView) findViewById(R.id.mTextView_jg);
        mImageView_cxzd = (TextView) findViewById(R.id.mImageView_cxzd);
        mImageView_zdjl = (TextView) findViewById(R.id.mImageView_zdjl);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mTextView_wt = (TextView) findViewById(R.id.mTextView_wt);
        mImageView_gou = (ImageView) findViewById(R.id.mImageView_gou);
        mTextView_wt2 = (TextView) findViewById(R.id.mTextView_wt2);
        mMListView = (MListView) findViewById(R.id.mMListView);

        mImageView_cxzd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgClzdOver.this.finish();
            }
        });
        mImageView_zdjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZdjl.class, TitleAct.class);
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆诊断");
    }
}