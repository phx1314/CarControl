//
//  FrgClbd3
//
//  Created by DELL on 2018-09-06 15:44:55
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.F;
import com.google.gson.Gson;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanbindCar;
import com.ndtlg.carcontrol.model.ModelappLogin;

import static com.ndtlg.carcontrol.F.bindCar;
import static com.ndtlg.carcontrol.F.changePhone;
import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.mModelappLogin;


public class FrgClbd3 extends BaseFrg {

    public TextView mTextView_phone;
    public EditText mEditText_code;
    public TextView mTextView_fs;
    public EditText mEditText_pcode;
    public TextView mImageView_dl;
    private int times = 60;
    private Handler handler;
    private Runnable runnable;
    public String vin;
    public String name;
    public String idNo;

    @Override
    protected void create(Bundle savedInstanceState) {
        vin = getActivity().getIntent().getStringExtra("vin");
        name = getActivity().getIntent().getStringExtra("name");
        idNo = getActivity().getIntent().getStringExtra("idNo");
        setContentView(R.layout.frg_clbd3);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mEditText_code = (EditText) findViewById(R.id.mEditText_code);
        mTextView_fs = (TextView) findViewById(R.id.mTextView_fs);
        mEditText_pcode = (EditText) findViewById(R.id.mEditText_pcode);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);

        mTextView_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                times = 60;
                doTimer();
                mEditText_code.setText("1234");
            }
        });
        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_code.getText().toString())) {
                    Helper.toast("请输入验证码", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_pcode.getText().toString())) {
                    Helper.toast("请输入PIN码", getContext());
                    return;
                }
                loadJsonUrl(bindCar, new BeanbindCar(vin, mModelappLogin.userInfo.phoneNo, mEditText_code.getText().toString(), name, idNo, mEditText_pcode.getText().toString()));
            }
        }));
    }

    private void doTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (times > 0) {
                    times--;
                    mTextView_fs.setText(times + "s后获取");
                    mTextView_fs.setTextColor(getContent().getResources().getColor(R.color.gray));
                    mTextView_fs.setClickable(false);
                    handler.postDelayed(runnable, 1000);
                } else if (times == 0) {
                    mTextView_fs.setClickable(true);
                    mTextView_fs.setText("发送验证码");
                    mTextView_fs.setTextColor(getContent().getResources().getColor(R.color.A));
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(bindCar)) {
            mModelappLogin = (ModelappLogin) json2Model(content, ModelappLogin.class);
            com.ndtlg.carcontrol.F.saveJson("mModelappLogin", new Gson().toJson(mModelappLogin));
            Frame.HANDLES.sentAll("FrgClbd1,FrgClbd2,FrgMain,FrgHome", 0, null);
            Helper.toast("绑定成功", getContext());
            finish();
            F.closeSoftKey(getActivity());
        }
    }

    public void loaddata() {
        mTextView_phone.setText(changePhone(mModelappLogin.userInfo.phoneNo));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆绑定");
    }
}