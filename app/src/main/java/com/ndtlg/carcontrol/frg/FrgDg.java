//
//  FrgDg
//
//  Created by DELL on 2018-09-04 14:53:44
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

import static com.ndtlg.carcontrol.frg.FrgClkz.changeStateImage;
import static com.ndtlg.carcontrol.frg.FrgClkz.go2Over;
import static com.ndtlg.carcontrol.frg.FrgClzt.dg;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;


public class FrgDg extends BaseFrg {


    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_state;
    public TextView mImageView_cz;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public LinearLayout mLinearLayout_1;
    public LinearLayout mLinearLayout_2;
    public LinearLayout mLinearLayout_3;
    public LinearLayout mLinearLayout_4;
    public ImageView mImageView1;
    public ImageView mImageView2;
    public ImageView mImageView3;
    public ImageView mImageView4;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_dg);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 110:
                mTextView_state.setText(dg);
                break;
            case 120:
                changeStateImage(modelqueryState.content.lowLamp, mImageView1, R.drawable.ic_control_nearlight_selected, R.drawable.ic_control_nearlight_nor);
                changeStateImage(modelqueryState.content.placeLamp, mImageView2, R.drawable.ic_control_shownlight_selected, R.drawable.ic_control_shownlight_nor);
                changeStateImage(modelqueryState.content.highLamp, mImageView3, R.drawable.ic_control_highlight_selected_y, R.drawable.ic_control_highlight_nor_y);
                changeStateImage(modelqueryState.content.flashLamp, mImageView4, R.drawable.ic_control_doubleflash_selected, R.drawable.ic_control_doubleflash_nor);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {


        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_state = (TextView) findViewById(R.id.mTextView_state);
        mImageView_cz = (TextView) findViewById(R.id.mImageView_cz);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) findViewById(R.id.mTextView_4);
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mLinearLayout_4 = (LinearLayout) findViewById(R.id.mLinearLayout_4);
        mImageView1 = (ImageView) findViewById(R.id.mImageView1);
        mImageView2 = (ImageView) findViewById(R.id.mImageView2);
        mImageView3 = (ImageView) findViewById(R.id.mImageView3);
        mImageView4 = (ImageView) findViewById(R.id.mImageView4);

        mLinearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.lowLamp = go2Over(modelqueryState.content.lowLamp, mModelCz.lowLamp, mImageView1, R.drawable.ic_control_nearlight_selected, R.drawable.ic_control_nearlight_nor);
            }
        });
        mLinearLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.placeLamp = go2Over(modelqueryState.content.placeLamp, mModelCz.placeLamp, mImageView2, R.drawable.ic_control_shownlight_selected, R.drawable.ic_control_shownlight_nor);
            }
        });
        mLinearLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.highLamp = go2Over(modelqueryState.content.highLamp, mModelCz.highLamp, mImageView3, R.drawable.ic_control_highlight_selected_y, R.drawable.ic_control_highlight_nor_y);
            }
        });
        mLinearLayout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.flashLamp = go2Over(modelqueryState.content.flashLamp, mModelCz.flashLamp, mImageView4, R.drawable.ic_control_doubleflash_selected, R.drawable.ic_control_doubleflash_nor);
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