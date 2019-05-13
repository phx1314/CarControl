//
//  FrgXgmm
//
//  Created by DELL on 2018-09-10 14:51:20
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
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanresetPwd;

import static com.ndtlg.carcontrol.F.resetPwd;


public class FrgXgmm extends BaseFrg {

    public EditText mEditText_phone;
    public EditText mEditText_code;
    public TextView mTextView_fs;
    public EditText mEditText_pass;
    public TextView textView;
    public EditText mEditText_pass2;
    public TextView mImageView_dl;
    private int times = 60;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_xgmm);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_code = (EditText) findViewById(R.id.mEditText_code);
        mTextView_fs = (TextView) findViewById(R.id.mTextView_fs);
        mEditText_pass = (EditText) findViewById(R.id.mEditText_pass);
        textView = (TextView) findViewById(R.id.textView);
        mEditText_pass2 = (EditText) findViewById(R.id.mEditText_pass2);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);

        mTextView_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                    Helper.toast("请输入手机号", getContext());
                    return;
                }
                if (mEditText_phone.getText().toString().length() != 11) {
                    Helper.toast("请输入正确的手机号", getContext());
                    return;
                }
                times = 60;
                doTimer();
                mEditText_code.setText("1234");
            }
        });
        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_phone.getText().toString())) {
                    Helper.toast("请输入手机号", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_code.getText().toString())) {
                    Helper.toast("请输入验证码", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_pass.getText().toString())) {
                    Helper.toast("请输入密码", getContext());
                    return;
                }
                if (mEditText_pass.getText().toString().length() < 6 || mEditText_pass.getText().toString().length() > 14) {
                    Helper.toast("请输入6-14位字母或数字", getContext());
                    return;
                }
                if (!mEditText_pass.getText().toString().equals(mEditText_pass2.getText().toString())) {
                    Helper.toast("两次密码输入不一致", getContext());
                    return;
                }
                loadJsonUrl(resetPwd, new BeanresetPwd(mEditText_phone.getText().toString(), mEditText_pass.getText().toString(), mEditText_code.getText().toString()));
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
        if (methodName.equals(resetPwd)) {
            Helper.toast("修改成功", getContext());
            finish();
            F.closeSoftKey(getActivity());
        }
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("修改密码");
    }


}