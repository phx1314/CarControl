//
//  FrgGrzx
//
//  Created by DELL on 2018-09-07 13:31:06
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;

import static com.ndtlg.carcontrol.F.mModelappLogin;


public class FrgGrzx extends BaseFrg {

    public TextView mTextView1;
    public TextView mTextView2;
    public TextView mTextView3;
    public TextView mTextView4;
    public TextView mTextView5;
    public TextView mImageView_logout;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_grzx);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
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
        mImageView_logout = (TextView) findViewById(R.id.mImageView_logout);

        mTextView1.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgCzxx.class, TitleAct.class);
            }
        }));
        mTextView2.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgClxx.class, TitleAct.class);
            }
        }));
        mTextView3.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgXgmm.class, TitleAct.class);
            }
        }));
        mTextView4.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgXgpm.class, TitleAct.class);
            }
        }));
        mTextView5.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGhsj.class, TitleAct.class);
            }
        }));
        mImageView_logout.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                F.saveJson("mModelappLogin", "");
                mModelappLogin = null;
                Helper.startActivity(getContext(), FrgLogin.class, IndexLoginAct.class);
            }
        }));
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("个人中心");
    }
}