//
//  FrgMain
//
//  Created by DELL on 2018-09-10 15:26:21
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;


public class FrgMain extends BaseFrg {

    public LinearLayout mLinearLayout_content;
    public FrgClzt mFrgClzt = new FrgClzt();
    public FrgClbd mFrgClbd = new FrgClbd();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_main);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                chageFrgment(mFrgClzt);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);


    }

    public void loaddata() {
        if (F.mModelappLogin.userInfo.bindCar) {
            chageFrgment(mFrgClzt);
        } else {
            chageFrgment(mFrgClbd);
        }
    }


    private void chageFrgment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.mLinearLayout_content, fragment)
                .commitAllowingStateLoss();
    }


}