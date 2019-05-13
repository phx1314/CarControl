//
//  FrgClbd
//
//  Created by DELL on 2018-09-06 14:04:22
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;


public class FrgClbd extends BaseFrg {

    public ImageButton btn_left;
    public TextView tv_title1;
    public TextView tv_title2;
    public ImageButton btn_right;
    public TextView mImageView_dl;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clbd);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        tv_title1 = (TextView) findViewById(R.id.tv_title1);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        btn_right = (ImageButton) findViewById(R.id.btn_right);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);

        btn_left.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGrzx.class, TitleAct.class);
            }
        }));
        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgClbd1.class, TitleAct.class);
            }
        }));
        btn_right.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgTzh.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        tv_title1.setText("欢迎您," + F.mModelappLogin.userInfo.phoneNo);
    }


}