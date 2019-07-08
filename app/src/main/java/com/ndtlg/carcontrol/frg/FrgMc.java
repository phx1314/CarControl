//
//  FrgMc
//
//  Created by DELL on 2018-09-03 17:08:14
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.item.AqyzDialog;
import com.ndtlg.carcontrol.pop.PopShowLeftCh;
import com.ndtlg.carcontrol.pop.PopShowRightCh;

import static com.ndtlg.carcontrol.R.drawable.ic_control_window_slightly_selected;
import static com.ndtlg.carcontrol.frg.FrgClkz.changeStateImage;
import static com.ndtlg.carcontrol.frg.FrgClkz.go2Over;
import static com.ndtlg.carcontrol.frg.FrgClkz.go3Over;
import static com.ndtlg.carcontrol.frg.FrgClzt.mc;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;


public class FrgMc extends BaseFrg {

    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public TextView mTextView_5;
    public TextView mTextView_state;
    public TextView mImageView_cz;
    public TextView mTextView_6;
    public TextView mTextView_7;
    public TextView mTextView_8;
    public TextView mTextView_9;
    public TextView mTextView_10;
    public LinearLayout mLinearLayout_1;
    public LinearLayout mLinearLayout_2;
    public LinearLayout mLinearLayout_3;
    public LinearLayout mLinearLayout_4;
    public LinearLayout mLinearLayout_5;
    public LinearLayout mLinearLayout_6;
    public LinearLayout mLinearLayout_7;
    public LinearLayout mLinearLayout_8;
    public LinearLayout mLinearLayout_9;
    public LinearLayout mLinearLayout_10;
    public ImageView mImageView_1;
    public ImageView mImageView_2;
    public ImageView mImageView_3;
    public ImageView mImageView_4;
    public ImageView mImageView_5;
    public ImageView mImageView_6;
    public ImageView mImageView_7;
    public ImageView mImageView_8;
    public ImageView mImageView_9;
    public ImageView mImageView_10;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_mc);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                if (obj.toString().equals("0")) {
                    mModelCz.leftFrontWindowState = go3Over(modelqueryState.content.leftFrontWindowState, 2, mImageView_3, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.leftRearWindowState = go3Over(modelqueryState.content.leftRearWindowState, 2, mImageView_4, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 1:
                if (obj.toString().equals("0")) {
                    mModelCz.leftFrontWindowState = go3Over(modelqueryState.content.leftFrontWindowState, 3, mImageView_3, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.leftRearWindowState = go3Over(modelqueryState.content.leftRearWindowState, 3, mImageView_4, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 2:
                if (obj.toString().equals("0")) {
                    mModelCz.leftFrontWindowState = go3Over(modelqueryState.content.leftFrontWindowState, 1, mImageView_3, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.leftRearWindowState = go3Over(modelqueryState.content.leftRearWindowState, 1, mImageView_4, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 3:
                if (obj.toString().equals("0")) {
                    mModelCz.rightFrontWindowState = go3Over(modelqueryState.content.rightFrontWindowState, 2, mImageView_8, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.rightRearWindowState = go3Over(modelqueryState.content.rightRearWindowState, 2, mImageView_9, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 4:
                if (obj.toString().equals("0")) {
                    mModelCz.rightFrontWindowState = go3Over(modelqueryState.content.rightFrontWindowState, 3, mImageView_8, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.rightRearWindowState = go3Over(modelqueryState.content.rightRearWindowState, 3, mImageView_9, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 5:
                if (obj.toString().equals("0")) {
                    mModelCz.rightFrontWindowState = go3Over(modelqueryState.content.rightFrontWindowState, 1, mImageView_8, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                } else {
                    mModelCz.rightRearWindowState = go3Over(modelqueryState.content.rightRearWindowState, 1, mImageView_9, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, ic_control_window_slightly_selected);
                }
                break;
            case 110://实时
                mTextView_state.setText(mc);
                break;
            case 120://有条件限制
                if(mModelCz.leftFrontWindowState==-1)   changeStateImage(modelqueryState.content.leftFrontWindowState, mImageView_3, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, R.drawable.ic_control_window_slightly_selected);
                if(mModelCz.rightFrontWindowState==-1) changeStateImage(modelqueryState.content.rightFrontWindowState, mImageView_8, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, R.drawable.ic_control_window_slightly_selected);
                if(mModelCz.leftRearWindowState==-1) changeStateImage(modelqueryState.content.leftRearWindowState, mImageView_4, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, R.drawable.ic_control_window_slightly_selected);
                if(mModelCz.rightRearWindowState==-1) changeStateImage(modelqueryState.content.rightRearWindowState, mImageView_9, R.drawable.ic_control_window_selected, R.drawable.ic_control_window_nor, R.drawable.ic_control_window_slightly_selected);
                if(mModelCz.trunk==-1) changeStateImage(modelqueryState.content.trunk, mImageView_5, R.drawable.ic_control_trunk_selected, R.drawable.ic_control_trunk_nor);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) findViewById(R.id.mTextView_4);
        mTextView_5 = (TextView) findViewById(R.id.mTextView_5);
        mTextView_state = (TextView) findViewById(R.id.mTextView_state);
        mImageView_cz = (TextView) findViewById(R.id.mImageView_cz);
        mTextView_6 = (TextView) findViewById(R.id.mTextView_6);
        mTextView_7 = (TextView) findViewById(R.id.mTextView_7);
        mTextView_8 = (TextView) findViewById(R.id.mTextView_8);
        mTextView_9 = (TextView) findViewById(R.id.mTextView_9);
        mTextView_10 = (TextView) findViewById(R.id.mTextView_10);
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mLinearLayout_4 = (LinearLayout) findViewById(R.id.mLinearLayout_4);
        mLinearLayout_5 = (LinearLayout) findViewById(R.id.mLinearLayout_5);
        mLinearLayout_6 = (LinearLayout) findViewById(R.id.mLinearLayout_6);
        mLinearLayout_7 = (LinearLayout) findViewById(R.id.mLinearLayout_7);
        mLinearLayout_8 = (LinearLayout) findViewById(R.id.mLinearLayout_8);
        mLinearLayout_9 = (LinearLayout) findViewById(R.id.mLinearLayout_9);
        mLinearLayout_10 = (LinearLayout) findViewById(R.id.mLinearLayout_10);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        mImageView_3 = (ImageView) findViewById(R.id.mImageView_3);
        mImageView_4 = (ImageView) findViewById(R.id.mImageView_4);
        mImageView_5 = (ImageView) findViewById(R.id.mImageView_5);
        mImageView_6 = (ImageView) findViewById(R.id.mImageView_6);
        mImageView_7 = (ImageView) findViewById(R.id.mImageView_7);
        mImageView_8 = (ImageView) findViewById(R.id.mImageView_8);
        mImageView_9 = (ImageView) findViewById(R.id.mImageView_9);
        mImageView_10 = (ImageView) findViewById(R.id.mImageView_10);

        mLinearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLinearLayout_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.trunk = go2Over(modelqueryState.content.trunk, mModelCz.trunk, mImageView_5, R.drawable.ic_control_trunk_selected, R.drawable.ic_control_trunk_nor);
            }
        });
        mLinearLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopShowLeftCh mPopShowLeftCh = new PopShowLeftCh(getContext(), view, 0);
                mPopShowLeftCh.show();
            }
        });
        mLinearLayout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopShowLeftCh mPopShowLeftCh = new PopShowLeftCh(getContext(), view, 1);
                mPopShowLeftCh.show();
            }
        });
        mLinearLayout_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopShowRightCh mPopShowLeftCh = new PopShowRightCh(getContext(), view, 0);
                mPopShowLeftCh.show();
            }
        });
        mLinearLayout_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopShowRightCh mPopShowLeftCh = new PopShowRightCh(getContext(), view, 1);
                mPopShowLeftCh.show();
            }
        });
        mImageView_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = AqyzDialog.getView(getContext(), null);
                F.showCenterDialog(getActivity(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((AqyzDialog) view1.getTag()).set(mDialog);
                    }
                });
            }
        });

    }

    public void loaddata() {

    }


}