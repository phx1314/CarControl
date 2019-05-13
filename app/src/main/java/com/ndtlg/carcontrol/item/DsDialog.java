//
//  DsDialog
//
//  Created by DELL on 2018-09-05 15:30:56
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.ndtlg.carcontrol.R;

import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;


public class DsDialog extends BaseItem {
    public TextView mTextView_title;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public RadioButton mRadioButton3;
    public RadioButton mRadioButton4;
    public RadioButton mRadioButton5;
    public RadioButton mRadioButton6;
    public RadioButton mRadioButton7;
    public RadioButton mRadioButton8;
    public TextView mImageView_dl;
    public ImageView mImageView_close;
    public Dialog item;
    public RadioGroup mRadioGroup1;
    public RadioGroup mRadioGroup2;
    public int remainSTime = 0;
    public int remainETime = 0;
    public TextView mImageView_cancel;
    public RadioButton mRadioButton0;
    public RadioButton mRadioButton9;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_ds_dialog, null);
        convertView.setTag(new DsDialog(convertView));
        return convertView;
    }

    public DsDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mRadioButton1 = (RadioButton) contentview.findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) contentview.findViewById(R.id.mRadioButton2);
        mRadioButton3 = (RadioButton) contentview.findViewById(R.id.mRadioButton3);
        mRadioButton4 = (RadioButton) contentview.findViewById(R.id.mRadioButton4);
        mRadioButton5 = (RadioButton) contentview.findViewById(R.id.mRadioButton5);
        mRadioButton6 = (RadioButton) contentview.findViewById(R.id.mRadioButton6);
        mRadioButton7 = (RadioButton) contentview.findViewById(R.id.mRadioButton7);
        mRadioButton8 = (RadioButton) contentview.findViewById(R.id.mRadioButton8);
        mImageView_dl = (TextView) contentview.findViewById(R.id.mImageView_dl);
        mImageView_close = (ImageView) findViewById(R.id.mImageView_close);
        mRadioGroup1 = (RadioGroup) findViewById(R.id.mRadioGroup1);
        mRadioGroup2 = (RadioGroup) findViewById(R.id.mRadioGroup2);
        mImageView_cancel = (TextView) findViewById(R.id.mImageView_cancel);
        mRadioButton0 = (RadioButton) findViewById(R.id.mRadioButton0);
        mRadioButton9 = (RadioButton) findViewById(R.id.mRadioButton9);
        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButton0:
                        remainSTime = 0;
                        break;
                    case R.id.mRadioButton1:
                        remainSTime = 5 * 60;
                        break;
                    case R.id.mRadioButton2:
                        remainSTime = 10 * 60;
                        break;
                    case R.id.mRadioButton3:
                        remainSTime = 30 * 60;
                        break;
                    case R.id.mRadioButton4:
                        remainSTime = 60 * 60;
                        break;
                }
            }
        });
        mRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButton5:
                        remainETime = 5 * 60;
                        break;
                    case R.id.mRadioButton6:
                        remainETime = 10 * 60;
                        break;
                    case R.id.mRadioButton7:
                        remainETime = 30 * 60;
                        break;
                    case R.id.mRadioButton8:
                        remainETime = 60 * 60;
                        break;
                    case R.id.mRadioButton9:
                        remainETime = 0;
                        break;
                }
            }
        });
        mImageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
            }
        });
        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.content.remainSTime_all = remainSTime;
                mModelCz.content.remainETime_all = remainETime;
                mModelCz.isKtCz = true;
                item.dismiss();
                Frame.HANDLES.sentAll("FrgZk", 122, null);
            }
        });
        mImageView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.content.remainSTime_all = 0;
                mModelCz.content.remainETime_all = 0;
                mModelCz.isKtCz = true;
                item.dismiss();
                Frame.HANDLES.sentAll("FrgZk", 122, null);
            }
        });
    }

    public void set(Dialog item) {
        this.item = item;
        switch (mModelCz.content.remainSTime_all) {
            case 0:
                mRadioGroup1.check(R.id.mRadioButton0);
                break;
            case 5 * 60:
                mRadioGroup1.check(R.id.mRadioButton1);
                break;
            case 10 * 60:
                mRadioGroup1.check(R.id.mRadioButton2);
                break;
            case 30 * 60:
                mRadioGroup1.check(R.id.mRadioButton3);
                break;
            case 60 * 60:
                mRadioGroup1.check(R.id.mRadioButton4);
                break;
        }
        switch (mModelCz.content.remainETime_all) {
            case 5 * 60:
                mRadioGroup2.check(R.id.mRadioButton5);
                break;
            case 10 * 60:
                mRadioGroup2.check(R.id.mRadioButton6);
                break;
            case 30 * 60:
                mRadioGroup2.check(R.id.mRadioButton7);
                break;
            case 60 * 60:
                mRadioGroup2.check(R.id.mRadioButton8);
                break;
            case 0:
                mRadioGroup2.check(R.id.mRadioButton9);
                break;
        }
    }


}