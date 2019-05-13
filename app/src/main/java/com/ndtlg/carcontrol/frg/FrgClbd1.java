//
//  FrgClbd1
//
//  Created by DELL on 2018-09-06 15:30:33
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
import com.ndtlg.carcontrol.bean.BeancheckVin;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.ndtlg.carcontrol.F.checkVin;
import static com.ndtlg.carcontrol.F.mModelappLogin;


public class FrgClbd1 extends BaseFrg {

    public EditText mEditText_code;
    public EditText mEditText_yzcode;
    public TextView mImageView_dl;
    public TextView mTextView_sj;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clbd1);
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
        mEditText_code = (EditText) findViewById(R.id.mEditText_code);
        mEditText_yzcode = (EditText) findViewById(R.id.mEditText_yzcode);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        mTextView_sj = (TextView) findViewById(R.id.mTextView_sj);
        mTextView_sj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaddata();
            }
        });
        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_code.getText().toString())) {
                    Helper.toast("请输入VIN码", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_yzcode.getText().toString())) {
                    Helper.toast("请输入验证码", getContext());
                    return;
                }
                if (!mEditText_yzcode.getText().toString().equals((mTextView_sj.getText().toString().replace(" ", "")))) {
                    Helper.toast("验证码不正确", getContext());
                    return;
                }
                loadJsonUrl(checkVin, new BeancheckVin(mEditText_code.getText().toString(), mEditText_yzcode.getText().toString() ));
            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(checkVin)) {
            Helper.toast("验证成功", getContext());
            Helper.startActivity(getContext(), FrgClbd2.class, TitleAct.class, "vin", mEditText_code.getText().toString());
            com.framewidget.F.closeSoftKey(getActivity());
        }
    }

    public void loaddata() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append(i + " ");
        }
        mTextView_sj.setText(sb.toString());
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆绑定");
    }
}