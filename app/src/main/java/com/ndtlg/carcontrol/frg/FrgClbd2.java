//
//  FrgClbd2
//
//  Created by DELL on 2018-09-06 15:35:51
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeancheckRealName;

import static com.ndtlg.carcontrol.F.checkRealName;


public class FrgClbd2 extends BaseFrg {

    public EditText mEditText_name;
    public EditText mEditText_code;
    public TextView mImageView_dl;
    public String vin;

    @Override
    protected void create(Bundle savedInstanceState) {
        vin = getActivity().getIntent().getStringExtra("vin");
        setContentView(R.layout.frg_clbd2);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_name = (EditText) findViewById(R.id.mEditText_name);
        mEditText_code = (EditText) findViewById(R.id.mEditText_code);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);

        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_name.getText().toString())) {
                    Helper.toast("请输入姓名", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_code.getText().toString())) {
                    Helper.toast("请输入身份证号码", getContext());
                    return;
                }
                loadJsonUrl(checkRealName, new BeancheckRealName(mEditText_name.getText().toString(), mEditText_code.getText().toString()));
            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(checkRealName)) {
            Helper.toast("验证成功", getContext());
            Helper.startActivity(getContext(), FrgClbd3.class, TitleAct.class, "vin", vin, "name", mEditText_name.getText().toString(), "idNo", mEditText_code.getText().toString());
            com.framewidget.F.closeSoftKey(getActivity());
        }
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆绑定");
    }
}