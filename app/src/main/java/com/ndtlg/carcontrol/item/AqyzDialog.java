//
//  AqyzDialog
//
//  Created by DELL on 2018-09-04 17:29:48
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;


public class AqyzDialog extends BaseItem {
    public TextView mImageView_dl;
    public EditText mEditText;
    public ImageView mImageView_close;
    public Dialog item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_aqyz_dialog, null);
        convertView.setTag(new AqyzDialog(convertView));
        return convertView;
    }

    public AqyzDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView_dl = (TextView) contentview.findViewById(R.id.mImageView_dl);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mImageView_close = (ImageView) findViewById(R.id.mImageView_close);

        mImageView_dl.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    Helper.toast("请输入PIN码", context);
                    return;
                }
                if (!mEditText.getText().toString().equals(F.mModelappLogin.userInfo.pin)) {
                    Helper.toast("PIN码输入错误", context);
                    return;
                }
                item.dismiss();
                Frame.HANDLES.sentAll("FrgClkz", 0, mEditText.getText().toString());
            }
        }));
        mImageView_close.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();

            }
        }));
    }

    public void set(Dialog item) {
        this.item = item;
    }


}