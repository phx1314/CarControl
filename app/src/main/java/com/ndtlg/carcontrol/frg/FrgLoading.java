//
//  FrgLoading
//
//  Created by DELL on 2018-09-03 14:11:28
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ndtlg.carcontrol.R;

import static com.ndtlg.carcontrol.F.mModelappLogin;


public class FrgLoading extends BaseFrg {

    public LinearLayout activity_main;
    public MImageView mImageView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_loading);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        mImageView = (MImageView) findViewById(R.id.mImageView);


    }

    public void loaddata() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mModelappLogin == null) {
                    Helper.startActivity(getContext(), FrgLogin.class, IndexLoginAct.class);
                } else {
                    Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
                }
                finish();
            }
        }, 2000);
    }


}