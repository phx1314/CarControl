//
//  FrgGhsj
//
//  Created by DELL on 2018-09-07 13:54:04
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
import com.ndtlg.carcontrol.bean.BeanresetPhoneNO;

import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.resetPhoneNO;


public class FrgGhsj extends BaseFrg {

    public EditText mEditText_phone;
    public EditText mEditText_code;
    public TextView mTextView_fs;
    public EditText mEditText_phone_n;
    public EditText mEditText_code_n;
    public TextView mTextView_fs_n;
    public TextView mImageView_dl;
    private int times = 60;
    private Handler handler;
    private Runnable runnable;
    private int times2 = 60;
    private Handler handler2;
    private Runnable runnable2;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_ghsj);
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
        mEditText_phone_n = (EditText) findViewById(R.id.mEditText_phone_n);
        mEditText_code_n = (EditText) findViewById(R.id.mEditText_code_n);
        mTextView_fs_n = (TextView) findViewById(R.id.mTextView_fs_n);
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
        mTextView_fs_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_phone_n.getText().toString())) {
                    Helper.toast("请输入新手机号", getContext());
                    return;
                }
                if (mEditText_phone_n.getText().toString().length() != 11) {
                    Helper.toast("请输入正确的新手机号", getContext());
                    return;
                }
                times2 = 60;
                doTimer2();
                mEditText_code_n.setText("1234");
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
                if (TextUtils.isEmpty(mEditText_phone_n.getText().toString())) {
                    Helper.toast("请输入新手机号", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_code_n.getText().toString())) {
                    Helper.toast("请输入新手机号验证码", getContext());
                    return;
                }
                loadJsonUrl(resetPhoneNO, new BeanresetPhoneNO(mEditText_phone.getText().toString(), mEditText_phone_n.getText().toString(), mEditText_code_n.getText().toString(), mEditText_code.getText().toString()));
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

    private void doTimer2() {
        handler2 = new Handler();
        runnable2 = new Runnable() {
            @Override
            public void run() {
                if (times2 > 0) {
                    times2--;
                    mTextView_fs_n.setText(times2 + "s后获取");
                    mTextView_fs_n.setTextColor(getContent().getResources().getColor(R.color.gray));
                    mTextView_fs_n.setClickable(false);
                    handler2.postDelayed(runnable2, 1000);
                } else if (times == 0) {
                    mTextView_fs_n.setClickable(true);
                    mTextView_fs_n.setText("发送验证码");
                    mTextView_fs_n.setTextColor(getContent().getResources().getColor(R.color.A));
                }
            }
        };
        handler2.post(runnable2);
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(resetPhoneNO)) {
            Helper.toast("更换成功,请重新登录", getContext());
            F.closeSoftKey(getActivity());
            com.ndtlg.carcontrol.F.saveJson("mModelappLogin", "");
            mModelappLogin = null;
            Helper.startActivity(getContext(), FrgLogin.class, IndexLoginAct.class);
            finish();
        }
    }

    public void loaddata() {
        mEditText_phone.setText(mModelappLogin.userInfo.phoneNo);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("更换绑定手机号");
    }
}