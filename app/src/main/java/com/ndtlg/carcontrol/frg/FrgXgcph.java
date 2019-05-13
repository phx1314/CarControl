//
//  FrgXgcph
//
//  Created by DELL on 2018-09-26 13:41:09
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeansetPlateNo;
import com.ndtlg.carcontrol.view.EditTextWithDel;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.setPlateNo;


public class FrgXgcph extends BaseFrg {

    public EditTextWithDel mEditText_phone;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_xgcph);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_phone = (EditTextWithDel) findViewById(R.id.mEditText_phone);


    }

    public void loaddata() {
        mEditText_phone.setText(mModelappLogin.vehicleInfo.plateNo);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车牌号码");
        mHeadlayout.setRText("保存");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                    Helper.toast("请输入车牌号", getContext());
                    return;
                }
                loadJsonUrl(setPlateNo, new BeansetPlateNo(mEditText_phone.getText().toString()));
            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(setPlateNo)) {
            mModelappLogin.vehicleInfo.plateNo = mEditText_phone.getText().toString();
            F.saveJson("mModelappLogin", new Gson().toJson(mModelappLogin));
            Helper.toast("修改成功", getContext());
            Frame.HANDLES.sentAll("FrgClxx", 0, null);
            FrgXgcph.this.finish();
        }
    }
}