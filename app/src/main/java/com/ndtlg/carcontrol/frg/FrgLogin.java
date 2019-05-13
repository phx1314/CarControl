//
//  FrgLogin
//
//  Created by DELL on 2018-09-03 14:29:16
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanappLogin;
import com.ndtlg.carcontrol.model.ModelappLogin;
import com.ndtlg.carcontrol.service.MQTTServiceNew;

import static com.ndtlg.carcontrol.F.appLogin;
import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.mModelappLogin;


public class FrgLogin extends BaseFrg {

    public EditText mEditText_name;
    public EditText mEditText_pass;
    public TextView mTextView_wjmm;
    public TextView mImageView_dl;
    public com.framewidget.view.Headlayout mHeadlayout;
    public int type;
    public TextView mTextView_1;
    public ImageView mImageView_1;
    public LinearLayout mLinearLayout_1;
    public TextView mTextView_2;
    public ImageView mImageView_2;
    public LinearLayout mLinearLayout_2;
    public TextView mTextView_fs;
    private int times = 60;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        getActivity().stopService(new Intent(getActivity(), MQTTServiceNew.class));
        setContentView(R.layout.frg_login);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
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

    private void findVMethod() {
        mEditText_name = (EditText) findViewById(R.id.mEditText_name);
        mEditText_pass = (EditText) findViewById(R.id.mEditText_pass);
        mTextView_wjmm = (TextView) findViewById(R.id.mTextView_wjmm);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        mHeadlayout = (com.framewidget.view.Headlayout) findViewById(R.id.mHeadlayout);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mTextView_fs = (TextView) findViewById(R.id.mTextView_fs);
        mTextView_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_name.getText().toString())) {
                    Helper.toast("请输入手机号", getContext());
                    return;
                }
                if (mEditText_name.getText().toString().length() != 11) {
                    Helper.toast("请输入正确的手机号", getContext());
                    return;
                }
                times = 60;
                doTimer();
                mEditText_pass.setText("1234");
            }
        });
        mTextView_wjmm.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgForget.class, TitleAct.class);
            }
        }));
        mLinearLayout_1.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 0;
                mTextView_1.setTextColor(Color.parseColor("#ed2634"));
                mImageView_1.setBackgroundColor(Color.parseColor("#ed2634"));
                mTextView_2.setTextColor(Color.parseColor("#FF959595"));
                mImageView_2.setBackgroundColor(Color.parseColor("#FF959595"));
                mEditText_name.setText("");
                mEditText_pass.setText("");
                mEditText_pass.setHint("请输入密码");
                mTextView_fs.setVisibility(View.GONE);
                mEditText_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        }));
        mLinearLayout_2.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 1;
                mTextView_2.setTextColor(Color.parseColor("#ed2634"));
                mImageView_2.setBackgroundColor(Color.parseColor("#ed2634"));
                mTextView_1.setTextColor(Color.parseColor("#FF959595"));
                mImageView_1.setBackgroundColor(Color.parseColor("#FF959595"));
                mEditText_name.setText("");
                mEditText_pass.setText("");

                mEditText_pass.setHint("请输入验证码");
                mEditText_pass.setInputType(InputType.TYPE_CLASS_NUMBER);
                mTextView_fs.setVisibility(View.VISIBLE);
            }
        }));
        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 0) {
                    if (TextUtils.isEmpty(mEditText_name.getText().toString())) {
                        Helper.toast("请输入手机号", getContext());
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
                    loadJsonUrl(appLogin, new BeanappLogin(mEditText_name.getText().toString(), mEditText_pass.getText().toString(), "", "pwd"));
                } else {
                    if (TextUtils.isEmpty(mEditText_name.getText().toString())) {
                        Helper.toast("请输入手机号", getContext());
                        return;
                    }
                    if (TextUtils.isEmpty(mEditText_pass.getText().toString())) {
                        Helper.toast("请输入验证码", getContext());
                        return;
                    }
                    loadJsonUrl(appLogin, new BeanappLogin(mEditText_name.getText().toString(), "", mEditText_pass.getText().toString(), "msg"));
                }


            }
        }));
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(appLogin)) {
            mModelappLogin = (ModelappLogin) json2Model(content, ModelappLogin.class);
            F.saveJson("mModelappLogin", new Gson().toJson(mModelappLogin));
            Helper.toast("登录成功", getContext());
            Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
            finish();
            com.framewidget.F.closeSoftKey(getActivity());
        }
    }

    public void loaddata() {
        mHeadlayout.setRText("注册");
        mHeadlayout.setRightOnclicker(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZc.class, TitleAct.class);
            }
        }));

    }


}