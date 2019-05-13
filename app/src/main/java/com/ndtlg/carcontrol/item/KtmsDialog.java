//
//  KtmsDialog
//
//  Created by Administrator on 2018-09-09 09:24:12
//  Copyright (c) Administrator All rights reserved.


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


public class KtmsDialog extends BaseItem {
    public TextView mTextView_title;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public RadioButton mRadioButton3;
    public RadioButton mRadioButton4;
    public RadioButton mRadioButton5;
    public RadioButton mRadioButton7;
    public RadioButton mRadioButton8;
    public TextView mImageView_dl;
    public RadioGroup mRadioGroup;
    public RadioGroup mRadioGroup2;

    public Dialog item;
    public ImageView mImageView_close;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_ktms_dialog, null);
        convertView.setTag(new KtmsDialog(convertView));
        return convertView;
    }

    public KtmsDialog(View view) {
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
        mRadioButton7 = (RadioButton) contentview.findViewById(R.id.mRadioButton7);
        mRadioButton8 = (RadioButton) contentview.findViewById(R.id.mRadioButton8);
        mImageView_dl = (TextView) contentview.findViewById(R.id.mImageView_dl);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioGroup2 = (RadioGroup) findViewById(R.id.mRadioGroup2);
        mImageView_close = (ImageView) findViewById(R.id.mImageView_close);
        mImageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
            }
        });
        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioButton1.isChecked()) {
                    mModelCz.content.damper = 0;
                } else if (mRadioButton3.isChecked()) {
                    mModelCz.content.damper = 1;
                } else if (mRadioButton2.isChecked()) {
                    mModelCz.content.damper = 2;
                } else if (mRadioButton4.isChecked()) {
                    mModelCz.content.damper = 3;
                } else if (mRadioButton5.isChecked()) {
                    mModelCz.content.damper = 4;
                }

                if (mRadioButton8.isChecked()) {
                    mModelCz.content.cycle = 0;
                } else if (mRadioButton7.isChecked()) {
                    mModelCz.content.cycle = 1;
                }
                mModelCz.isKtCz = true;
                item.dismiss();
                Frame.HANDLES.sentAll("FrgZk",122,null);
            }
        });

    }

    public void set(Dialog item) {
        this.item = item;
        if (mModelCz.content.damper == 0) {
            mRadioGroup.check(R.id.mRadioButton1);
        } else if (mModelCz.content.damper == 1) {
            mRadioGroup.check(R.id.mRadioButton3);
        } else if (mModelCz.content.damper == 2) {
            mRadioGroup.check(R.id.mRadioButton2);
        } else if (mModelCz.content.damper == 3) {
            mRadioGroup.check(R.id.mRadioButton4);
        } else if (mModelCz.content.damper == 4) {
            mRadioGroup.check(R.id.mRadioButton5);
        }

        if (mModelCz.content.cycle == 0) {
            mRadioGroup2.check(R.id.mRadioButton8);
        } else {
            mRadioGroup2.check(R.id.mRadioButton7);
        }
    }


}