//
//  FrgZk
//
//  Created by DELL on 2018-09-04 15:54:49
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.item.AqyzDialog;
import com.ndtlg.carcontrol.item.DsDialog;
import com.ndtlg.carcontrol.item.JrzlDialog;
import com.ndtlg.carcontrol.item.KtmsDialog;
import com.ndtlg.carcontrol.pop.PopShowLeftDm;

import static com.ndtlg.carcontrol.F.secToTime;
import static com.ndtlg.carcontrol.frg.FrgClkz.changeStateImage;
import static com.ndtlg.carcontrol.frg.FrgClkz.changeStateImageAcc;
import static com.ndtlg.carcontrol.frg.FrgClkz.go2OverSpecial;
import static com.ndtlg.carcontrol.frg.FrgClkz.go3AccOver;
import static com.ndtlg.carcontrol.frg.FrgClzt.zk;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelqueryAirConditionState;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;


public class FrgZk extends BaseFrg {

    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public LinearLayout mLinearLayout_wd;
    public TextView mTextView_state;
    public TextView mTextView_kt_state;
    public TextView mImageView_cz;
    public TextView mTextView_6;
    public TextView mTextView_7;
    public TextView mTextView_8;
    public ProgressBar vertical_progressbar;
    public TextView mTextView_9;
    public RelativeLayout mRelativeLayout_8;
    public LinearLayout mLinearLayout_1;
    public LinearLayout mLinearLayout_2;
    public LinearLayout mLinearLayout_3;
    public LinearLayout mLinearLayout_6;
    public LinearLayout mLinearLayout_7;
    public LinearLayout mLinearLayout_9;
    public ImageView mImageView1;
    public ImageView mImageView2;
    public ImageView mImageView3;
    public ImageView mImageView6;
    public ImageView mImageView7;
    public ImageView imageView;
    public ImageView mImageView9;
    public TextView mTextView_ktwd;
    public Handler handler = new Handler();
    public Runnable runnable;
    public Handler handler1 = new Handler();
    public Runnable runnable1;
    public String kt = "";

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zk);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mModelCz.eState = go3AccOver(modelqueryState.content.eState, Integer.valueOf(obj.toString()), mImageView1, R.drawable.ic_control_start_selected, R.drawable.ic_control_start_nor, R.drawable.ic_control_acc_selected);
                break;
            case 110://实时
                mTextView_state.setText(zk);
                break;
            case 120://有条件限制
                changeStateImageAcc(modelqueryState.content.eState, mImageView1, R.drawable.ic_control_start_selected, R.drawable.ic_control_start_nor, R.drawable.ic_control_acc_selected);
                changeStateImage(modelqueryState.content.lockCentral, mImageView2, R.drawable.ic_control_fortification_selected, R.drawable.ic_control_fortification_nor, mImageView3, R.drawable.ic_control_defending_selected, R.drawable.ic_control_defending_nor);
                break;
            case 111://实时
                doData();
                mTextView_ktwd.setText(mModelqueryAirConditionState.content.inTemp + "");
                if (mModelqueryAirConditionState.content.remainSTime > 0) {
                    vertical_progressbar.setMax(mModelqueryAirConditionState.content.remainSTime_all);
                    imageView.setBackgroundResource(R.drawable.ic_control_timing);
                    handler.removeCallbacks(runnable);
                    handler.post(runnable);
                } else if (mModelqueryAirConditionState.content.remainETime > 0) {
                    vertical_progressbar.setMax(mModelqueryAirConditionState.content.remainETime_all);
                    imageView.setBackgroundResource(R.drawable.ic_control_timing);
                    handler1.removeCallbacks(runnable1);
                    handler1.post(runnable1);
                } else {
                    vertical_progressbar.setProgress(0);
                    imageView.setBackgroundResource(R.drawable.ic_control_timing_nor);
                }
                break;
            case 121://有条件限制
                changeStateImage(mModelqueryAirConditionState.content.off, mImageView6, R.drawable.ic_control_ac_selected, R.drawable.ic_control_ac_nor);
                changeStateImage(mModelqueryAirConditionState.content.off, mImageView9, R.drawable.ic_control_mode_selected, R.drawable.ic_control_mode_nor);
                break;
            case 122:
                changeStateImage(mModelCz.content.off, mImageView6, R.drawable.ic_control_ac_selected, R.drawable.ic_control_ac_nor);
                changeStateImage(mModelCz.content.off, mImageView9, R.drawable.ic_control_mode_selected, R.drawable.ic_control_mode_nor);
//                if (mModelCz.content.remainSTime_all > 0 || mModelCz.content.remainETime_all > 0) {
//                    vertical_progressbar.setProgress(100);
//                    imageView.setBackgroundResource(R.drawable.ic_control_timing);
//                } else {
//                    vertical_progressbar.setProgress(0);
//                    imageView.setBackgroundResource(R.drawable.ic_control_timing_nor);
//                }
                break;
        }
    }

    public void doData() {
        kt = "";
        double kt_wd = mModelqueryAirConditionState.content.sTemp;
        if (mModelqueryAirConditionState.content.off > 0) {
            if (mModelqueryAirConditionState.content.ac > 0) {
                kt = "制冷  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
            } else if (mModelqueryAirConditionState.content.heat > 0) {
                kt = "制热  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
            } else if (mModelqueryAirConditionState.content.auto > 0) {
                kt = "自动  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
            }
            kt += "\n";
            if (mModelqueryAirConditionState.content.remainSTime > 0) {
                kt += secToTime(mModelqueryAirConditionState.content.remainSTime) + "后开启\n";
            } else if (mModelqueryAirConditionState.content.remainETime > 0) {
                kt += secToTime(mModelqueryAirConditionState.content.remainETime) + "后关闭\n";
            }

            if (mModelqueryAirConditionState.content.damper == 0) {
                kt += "对上  " + (mModelqueryAirConditionState.content.cycle == 0 ? "外循环" : "内循环");
            } else if (mModelqueryAirConditionState.content.damper == 1) {
                kt += "对上下  " + (mModelqueryAirConditionState.content.cycle == 0 ? "外循环" : "内循环");
            } else if (mModelqueryAirConditionState.content.damper == 2) {
                kt += "对下  " + (mModelqueryAirConditionState.content.cycle == 0 ? "外循环" : "内循环");
            } else if (mModelqueryAirConditionState.content.damper == 3) {
                kt += "对下除雾  " + (mModelqueryAirConditionState.content.cycle == 0 ? "外循环" : "内循环");
            } else if (mModelqueryAirConditionState.content.damper == 4) {
                kt += "前挡除雾  " + (mModelqueryAirConditionState.content.cycle == 0 ? "外循环" : "内循环");
            }

        } else {
            kt = "关闭";
            if (mModelqueryAirConditionState.content.remainSTime > 0) {
                kt += "\n" + secToTime(mModelqueryAirConditionState.content.remainSTime) + "后开启";
            }
        }
        mTextView_kt_state.setText(kt);
    }


    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mLinearLayout_wd = (LinearLayout) findViewById(R.id.mLinearLayout_wd);
        mTextView_state = (TextView) findViewById(R.id.mTextView_state);
        mTextView_kt_state = (TextView) findViewById(R.id.mTextView_kt_state);
        mImageView_cz = (TextView) findViewById(R.id.mImageView_cz);
        mTextView_6 = (TextView) findViewById(R.id.mTextView_6);
        mTextView_7 = (TextView) findViewById(R.id.mTextView_7);
        mTextView_8 = (TextView) findViewById(R.id.mTextView_8);
        vertical_progressbar = (ProgressBar) findViewById(R.id.vertical_progressbar);
        mTextView_9 = (TextView) findViewById(R.id.mTextView_9);
        mRelativeLayout_8 = (RelativeLayout) findViewById(R.id.mRelativeLayout_8);
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mLinearLayout_6 = (LinearLayout) findViewById(R.id.mLinearLayout_6);
        mLinearLayout_7 = (LinearLayout) findViewById(R.id.mLinearLayout_7);
        mLinearLayout_9 = (LinearLayout) findViewById(R.id.mLinearLayout_9);
        mImageView1 = (ImageView) findViewById(R.id.mImageView1);
        mImageView2 = (ImageView) findViewById(R.id.mImageView2);
        mImageView3 = (ImageView) findViewById(R.id.mImageView3);
        mImageView6 = (ImageView) findViewById(R.id.mImageView6);
        mImageView7 = (ImageView) findViewById(R.id.mImageView7);
        imageView = (ImageView) findViewById(R.id.imageView);
        mImageView9 = (ImageView) findViewById(R.id.mImageView9);
        mTextView_ktwd = (TextView) findViewById(R.id.mTextView_ktwd);

        mLinearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopShowLeftDm mPopShowLeftCh = new PopShowLeftDm(getContext(), view);
                mPopShowLeftCh.show();
            }
        });
        mLinearLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.lockCentral = go2OverSpecial(modelqueryState.content.lockCentral, mModelCz.lockCentral, mImageView2, R.drawable.ic_control_fortification_selected, R.drawable.ic_control_fortification_nor, mImageView3, R.drawable.ic_control_defending_selected, R.drawable.ic_control_defending_nor);
            }
        });
        mLinearLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModelCz.lockCentral = go2OverSpecial(modelqueryState.content.lockCentral, mModelCz.lockCentral, mImageView3, R.drawable.ic_control_defending_nor, R.drawable.ic_control_defending_selected, mImageView2, R.drawable.ic_control_fortification_nor, R.drawable.ic_control_fortification_selected);
            }
        });
        mLinearLayout_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = JrzlDialog.getView(getContext(), null);
                F.showCenterDialog(getActivity(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((JrzlDialog) view1.getTag()).set(mDialog);
                    }
                });
            }
        });

        mRelativeLayout_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = DsDialog.getView(getContext(), null);
                F.showCenterDialog(getActivity(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((DsDialog) view1.getTag()).set(mDialog);
                    }
                });
            }
        });
        mLinearLayout_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = KtmsDialog.getView(getContext(), null);
                F.showCenterDialog(getActivity(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((KtmsDialog) view1.getTag()).set(mDialog);
                    }
                });
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
        runnable = new Runnable() {
            @Override
            public void run() {
                if (mModelqueryAirConditionState.content.remainSTime > 0) {
                    mModelqueryAirConditionState.content.remainSTime--;
                    handler.postDelayed(runnable, 1000);
                    vertical_progressbar.setProgress(mModelqueryAirConditionState.content.remainSTime);
                    doData();
                }
            }
        };
        runnable1 = new Runnable() {
            @Override
            public void run() {
                if (mModelqueryAirConditionState.content.remainETime > 0) {
                    mModelqueryAirConditionState.content.remainETime--;
                    handler1.postDelayed(runnable1, 1000);
                    vertical_progressbar.setProgress(mModelqueryAirConditionState.content.remainETime);
                    doData();
                }
            }
        };
    }


}