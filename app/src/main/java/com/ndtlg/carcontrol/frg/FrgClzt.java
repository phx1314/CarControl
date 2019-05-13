//
//  FrgClzt
//
//  Created by DELL on 2018-09-06 14:00:28
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.google.gson.Gson;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanVin;
import com.ndtlg.carcontrol.model.ModelqueryAirConditionState;
import com.ndtlg.carcontrol.model.ModelqueryState;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.queryAirConditionState;
import static com.ndtlg.carcontrol.F.queryState;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelqueryAirConditionState;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;


public class FrgClzt extends BaseFrg {

    public ImageButton btn_left;
    public TextView tv_title1;
    public TextView tv_title2;
    public ImageButton btn_right;
    public TextView mTextView_lc;
    public TextView mTextView_dl;
    public TextView mTextView_wd;
    public MImageView mMImageView;
    public TextView mTextView_time;
    public ImageView mImageView_gx;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public TextView mTextView_5;
    public TextView mTextView_6;
    public TextView mTextView_time_ty;
    public ImageView mImageView_gx_ty;
    public TextView mTextView_ts;
    public ImageView mImageView_left1;
    public ImageView mImageView_right2;
    public ImageView mImageView_right1;
    public ImageView mImageView_left2;
    public ImageView mImageView_c;
    public TextView mTextView_left1;
    public TextView mTextView_left2;
    public TextView mTextView_right1;
    public TextView mTextView_right2;
    public TextView mTextView_time_state;
    public ImageView mImageView_gx_state;
    public TextView mTextView_state1;
    public TextView mTextView_state2;
    public TextView mTextView_state3;
    public TextView mTextView_state4;
    public TextView mTextView_state5;
    public TextView mTextView_state6;
    public TextView mTextView_state7;
    public TextView mTextView_state8;
    public TextView mTextView_state9;
    public TextView mTextView_state10;
    public TextView mTextView_state11;
    public TextView mTextView_state12;
    public TextView mTextView_state13;
    public TextView mTextView_state14;
    public TextView mTextView_state15;
    public TextView mTextView_state16;
    public TextView mTextView_state17;
    public TextView mTextView_state18;
    public TextView mTextView_state19;
    public TextView mTextView_state20;
    public TextView mTextView_state21;
    public TextView mTextView_state22;
    public TextView mTextView_state23;
    public TextView mTextView_state24;
    public TextView mTextView_state_lefts;
    public TextView mTextView_state_rights;

    public static String mc = "左前窗：关闭  右前窗：关闭\n左后窗：关闭  右后窗：关闭\n后备箱：关闭";
    public static String dg = "近光灯：关闭  远光灯：关闭\n示廓灯：关闭  双闪灯：关闭";
    public static String zk = "关闭\n解防";

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clzt);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mTextView_time.setText(AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm") + "更新");
                mTextView_time_ty.setText(AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm") + "更新");
                mTextView_time_state.setText(AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm") + "更新");
                mTextView_lc.setText(modelqueryState.content.remainMileage + "");
                mTextView_left1.setText(modelqueryState.content.leftFrontTirePressure + "kpa\n" + modelqueryState.content.leftFrontTireTemp + "℃");
                mTextView_left2.setText(modelqueryState.content.leftRearTirePressure + "kpa\n" + modelqueryState.content.leftRearTireTemp + "℃");
                mTextView_right1.setText(modelqueryState.content.rightFrontTirePressure + "kpa\n" + modelqueryState.content.rightFrontTireTemp + "℃");
                mTextView_right2.setText(modelqueryState.content.rightRearTirePressure + "kpa\n" + modelqueryState.content.rightRearTireTemp + "℃");
                if (modelqueryState.content.doorLf > 0) {
                    mTextView_state1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_lfdoor_selected, 0, 0, 0);
                    mTextView_state1.setText("左前门：开启");
                } else {
                    mTextView_state1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_lfdoor_nor, 0, 0, 0);
                    mTextView_state1.setText("左前门：关闭 ");
                }
                if (modelqueryState.content.doorRf > 0) {
                    mTextView_state2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_rfdoor_selected, 0, 0, 0);
                    mTextView_state2.setText("右前门：开启");
                } else {
                    mTextView_state2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_rfdoor_nor, 0, 0, 0);
                    mTextView_state2.setText("右前门：关闭 ");
                }
                if (modelqueryState.content.doorLr > 0) {
                    mTextView_state3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_lbdoor_selected, 0, 0, 0);
                    mTextView_state3.setText("左后门：开启");
                } else {
                    mTextView_state3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_lbdoor_nor, 0, 0, 0);
                    mTextView_state3.setText("左后门：关闭 ");
                }
                if (modelqueryState.content.doorRr > 0) {
                    mTextView_state4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_rbdoor_selected, 0, 0, 0);
                    mTextView_state4.setText("右后门：开启");
                } else {
                    mTextView_state4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_rbdoor_nor, 0, 0, 0);
                    mTextView_state4.setText("右后门：关闭 ");
                }


                if (modelqueryState.content.leftFrontWindowState == 2) {
                    mTextView_state5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_selected, 0, 0, 0);
                    mTextView_state5.setText("左前窗：开启");
                } else if (modelqueryState.content.leftFrontWindowState == 3) {
                    mTextView_state5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_selected, 0, 0, 0);
                    mTextView_state5.setText("左前窗：微开");
                } else {
                    mTextView_state5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_nor, 0, 0, 0);
                    mTextView_state5.setText("左前窗：关闭 ");
                }
                if (modelqueryState.content.rightFrontWindowState == 2) {
                    mTextView_state6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_selected, 0, 0, 0);
                    mTextView_state6.setText("右前窗：开启");
                } else if (modelqueryState.content.rightFrontWindowState == 3) {
                    mTextView_state6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_selected, 0, 0, 0);
                    mTextView_state6.setText("右前窗：微开");
                } else {
                    mTextView_state6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_window_nor, 0, 0, 0);
                    mTextView_state6.setText("右前窗：关闭 ");
                }
                if (modelqueryState.content.leftRearWindowState == 2) {
                    mTextView_state7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_selected, 0, 0, 0);
                    mTextView_state7.setText("左后窗：开启");
                } else if (modelqueryState.content.leftRearWindowState == 3) {
                    mTextView_state7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_selected, 0, 0, 0);
                    mTextView_state7.setText("左后窗：微开");
                } else {
                    mTextView_state7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_nor, 0, 0, 0);
                    mTextView_state7.setText("左后窗：关闭 ");
                }
                if (modelqueryState.content.rightRearWindowState == 2) {
                    mTextView_state8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_selected, 0, 0, 0);
                    mTextView_state8.setText("右后窗：开启");
                } else if (modelqueryState.content.rightRearWindowState == 3) {
                    mTextView_state8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_selected, 0, 0, 0);
                    mTextView_state8.setText("右后窗：微开");
                } else {
                    mTextView_state8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_nor, 0, 0, 0);
                    mTextView_state8.setText("右后窗：关闭 ");
                }

                if (modelqueryState.content.trunk > 0) {
                    mTextView_state9.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_trunk_selected, 0, 0, 0);
                    mTextView_state9.setText("后备箱：开启");
                } else {
                    mTextView_state9.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_trunk_nor, 0, 0, 0);
                    mTextView_state9.setText("后备箱：关闭 ");
                }

                if (modelqueryState.content.hood > 0) {
                    mTextView_state10.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_skylight_selected, 0, 0, 0);
                    mTextView_state10.setText("引擎盖：开启");
                } else {
                    mTextView_state10.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_skylight_nor, 0, 0, 0);
                    mTextView_state10.setText("引擎盖：关闭 ");
                }

                if (modelqueryState.content.lowLamp > 0) {
                    mTextView_state11.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_nearlight_selected, 0, 0, 0);
                    mTextView_state11.setText("近光灯：开启");
                } else {
                    mTextView_state11.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_nearlight_nor, 0, 0, 0);
                    mTextView_state11.setText("近光灯：关闭 ");
                }
                if (modelqueryState.content.highLamp > 0) {
                    mTextView_state12.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_highlight_selected, 0, 0, 0);
                    mTextView_state12.setText("远光灯：开启");
                } else {
                    mTextView_state12.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_highlight_nor, 0, 0, 0);
                    mTextView_state12.setText("远光灯：关闭 ");
                }

                if (modelqueryState.content.tSignalState == 1) {
                    mTextView_state13.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_leftlight_selected, 0, 0, 0);
                    mTextView_state13.setText("左转灯：开启");
                    mTextView_state14.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_control_rightlight_nor, 0, 0, 0);
                    mTextView_state14.setText("右转灯：关闭");
                } else if (modelqueryState.content.tSignalState == 2) {
                    mTextView_state13.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_leftlight_nor, 0, 0, 0);
                    mTextView_state13.setText("左转灯：关闭 ");
                    mTextView_state14.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_control_rightlight_selected, 0, 0, 0);
                    mTextView_state14.setText("右转灯：开启");
                } else if (modelqueryState.content.tSignalState == 0) {
                    mTextView_state13.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_leftlight_nor, 0, 0, 0);
                    mTextView_state13.setText("左转灯：关闭 ");
                    mTextView_state14.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_control_rightlight_nor, 0, 0, 0);
                    mTextView_state14.setText("右转灯：关闭");
                }
                if (modelqueryState.content.bFogLamp > 0) {
                    mTextView_state15.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_forntfog_selected, 0, 0, 0);
                    mTextView_state15.setText("前雾灯：开启");
                } else {
                    mTextView_state15.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_forntfog_nor, 0, 0, 0);
                    mTextView_state15.setText("前雾灯：关闭 ");
                }
                if (modelqueryState.content.aFogLamp > 0) {
                    mTextView_state16.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_backfog_selected, 0, 0, 0);
                    mTextView_state16.setText("后雾灯：开启");
                } else {
                    mTextView_state16.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_backfog_nor, 0, 0, 0);
                    mTextView_state16.setText("后雾灯：关闭 ");
                }
                if (modelqueryState.content.placeLamp > 0) {
                    mTextView_state17.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_shownlight_selected, 0, 0, 0);
                    mTextView_state17.setText("示廓灯：开启");
                } else {
                    mTextView_state17.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_shownlight_nor, 0, 0, 0);
                    mTextView_state17.setText("示廓灯：关闭 ");
                }
                if (modelqueryState.content.dayLight > 0) {
                    mTextView_state18.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_ambient_selected, 0, 0, 0);
                    mTextView_state18.setText("氛围灯：开启");
                } else {
                    mTextView_state18.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_ambient_nor, 0, 0, 0);
                    mTextView_state18.setText("氛围灯：关闭 ");
                }
                if (modelqueryState.content.eState == 2) {
                    mTextView_state19.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_start_selected, 0, 0, 0);
                    mTextView_state19.setText("电门：点火");
                } else if (modelqueryState.content.eState == 1) {
                    mTextView_state19.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_start_selected, 0, 0, 0);
                    mTextView_state19.setText("电门：ACC开");
                } else {
                    mTextView_state19.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_start_nor, 0, 0, 0);
                    mTextView_state19.setText("电门：关闭 ");
                }
                if (modelqueryState.content.lockCentral > 0) {
                    mTextView_state21.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_fortification_selected, 0, 0, 0);
                    mTextView_state21.setText("设防：开启");
                    mTextView_state23.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_defending_nor, 0, 0, 0);
                    mTextView_state23.setText("解防：关闭");
                } else {
                    mTextView_state21.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_fortification_nor, 0, 0, 0);
                    mTextView_state21.setText("设防：关闭");
                    mTextView_state23.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_defending_selected, 0, 0, 0);
                    mTextView_state23.setText("解防：开启");
                }
                if (modelqueryState.content.lockLs > 0) {
                    mTextView_state_lefts.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_fdoorlock_selected, 0, 0, 0);
                    mTextView_state_lefts.setText("左门锁：开启");
                } else {
                    mTextView_state_lefts.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_fdoorlock_nor, 0, 0, 0);
                    mTextView_state_lefts.setText("左门锁：关闭");
                }
                if (modelqueryState.content.lockRs > 0) {
                    mTextView_state_rights.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_selected, 0, 0, 0);
                    mTextView_state_rights.setText("右门锁：开启");
                } else {
                    mTextView_state_rights.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_bdoorlock_nor, 0, 0, 0);
                    mTextView_state_rights.setText("右门锁：关闭");
                }

                mc = mTextView_state5.getText().toString() + " " + mTextView_state6.getText().toString() + "\n" + mTextView_state7.getText().toString() + " " + mTextView_state8.getText().toString() + "\n" + mTextView_state9.getText().toString();
                dg = mTextView_state11.getText().toString() + " " + mTextView_state12.getText().toString() + "\n" + mTextView_state17.getText().toString();
                zk = modelqueryState.content.eState > 0 ? (modelqueryState.content.eState > 1 ? "点火" : "ACC开") : "关闭\n" + (modelqueryState.content.lockCentral > 0 ? "设防" : "解防");
                Frame.HANDLES.sentAll("FrgMc,FrgDg,FrgZk", 110, null);
                Frame.HANDLES.sentAll("FrgMc,FrgDg,FrgZk", 120, null);
                break;
            case 1:
                if (mModelqueryAirConditionState.content.off > 0) {
                    mTextView_state20.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_ac_selected, 0, 0, 0);
                    mTextView_state20.setText("空调：开启");
                    mTextView_state22.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_heating_selected, 0, 0, 0);
                    mTextView_state22.setText("空调模式：开启");
                } else {
                    mTextView_state20.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_ac_nor, 0, 0, 0);
                    mTextView_state20.setText("空调：关闭");
                    mTextView_state22.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_heating_nor, 0, 0, 0);
                    mTextView_state22.setText("空调模式：关闭");
                }
                if (mModelqueryAirConditionState.content.off > 0) {
                    mTextView_state24.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_timing_selected, 0, 0, 0);
                    mTextView_state24.setText("空调定时：开启");
                } else {
                    mTextView_state24.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_timing_nor, 0, 0, 0);
                    mTextView_state24.setText("空调定时：关闭");
                }
                mTextView_wd.setText(mModelqueryAirConditionState.content.inTemp + "");

                String kt = "";
                double kt_wd = mModelqueryAirConditionState.content.sTemp;
                if (mModelqueryAirConditionState.content.off > 0) {
                    if (mModelqueryAirConditionState.content.ac > 0) {
                        kt = "制冷  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
                    } else if (mModelqueryAirConditionState.content.heat > 0) {
                        kt = "制热  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
                    } else if (mModelqueryAirConditionState.content.auto > 0) {
                        kt = "自动  " + kt_wd + "℃  " + mModelqueryAirConditionState.content.av + "档";
                    }

                } else {
                    kt = "关闭";
                }

                mTextView_3.setText("空调状态：" + kt);
                Frame.HANDLES.sentAll("FrgZk", 111, null);
                Frame.HANDLES.sentAll("FrgZk", 121, null);
                break;
            case 3:

                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        tv_title1 = (TextView) findViewById(R.id.tv_title1);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        btn_right = (ImageButton) findViewById(R.id.btn_right);
        mTextView_lc = (TextView) findViewById(R.id.mTextView_lc);
        mTextView_dl = (TextView) findViewById(R.id.mTextView_dl);
        mTextView_wd = (TextView) findViewById(R.id.mTextView_wd);
        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mImageView_gx = (ImageView) findViewById(R.id.mImageView_gx);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) findViewById(R.id.mTextView_4);
        mTextView_5 = (TextView) findViewById(R.id.mTextView_5);
        mTextView_6 = (TextView) findViewById(R.id.mTextView_6);
        mTextView_time_ty = (TextView) findViewById(R.id.mTextView_time_ty);
        mImageView_gx_ty = (ImageView) findViewById(R.id.mImageView_gx_ty);
        mTextView_ts = (TextView) findViewById(R.id.mTextView_ts);
        mImageView_left1 = (ImageView) findViewById(R.id.mImageView_left1);
        mImageView_right2 = (ImageView) findViewById(R.id.mImageView_right2);
        mImageView_right1 = (ImageView) findViewById(R.id.mImageView_right1);
        mImageView_left2 = (ImageView) findViewById(R.id.mImageView_left2);
        mImageView_c = (ImageView) findViewById(R.id.mImageView_c);
        mTextView_left1 = (TextView) findViewById(R.id.mTextView_left1);
        mTextView_left2 = (TextView) findViewById(R.id.mTextView_left2);
        mTextView_right1 = (TextView) findViewById(R.id.mTextView_right1);
        mTextView_right2 = (TextView) findViewById(R.id.mTextView_right2);
        mTextView_time_state = (TextView) findViewById(R.id.mTextView_time_state);
        mImageView_gx_state = (ImageView) findViewById(R.id.mImageView_gx_state);
        mTextView_state1 = (TextView) findViewById(R.id.mTextView_state1);
        mTextView_state2 = (TextView) findViewById(R.id.mTextView_state2);
        mTextView_state3 = (TextView) findViewById(R.id.mTextView_state3);
        mTextView_state4 = (TextView) findViewById(R.id.mTextView_state4);
        mTextView_state5 = (TextView) findViewById(R.id.mTextView_state5);
        mTextView_state6 = (TextView) findViewById(R.id.mTextView_state6);
        mTextView_state7 = (TextView) findViewById(R.id.mTextView_state7);
        mTextView_state8 = (TextView) findViewById(R.id.mTextView_state8);
        mTextView_state9 = (TextView) findViewById(R.id.mTextView_state9);
        mTextView_state10 = (TextView) findViewById(R.id.mTextView_state10);
        mTextView_state11 = (TextView) findViewById(R.id.mTextView_state11);
        mTextView_state12 = (TextView) findViewById(R.id.mTextView_state12);
        mTextView_state13 = (TextView) findViewById(R.id.mTextView_state13);
        mTextView_state14 = (TextView) findViewById(R.id.mTextView_state14);
        mTextView_state15 = (TextView) findViewById(R.id.mTextView_state15);
        mTextView_state16 = (TextView) findViewById(R.id.mTextView_state16);
        mTextView_state17 = (TextView) findViewById(R.id.mTextView_state17);
        mTextView_state18 = (TextView) findViewById(R.id.mTextView_state18);
        mTextView_state19 = (TextView) findViewById(R.id.mTextView_state19);
        mTextView_state20 = (TextView) findViewById(R.id.mTextView_state20);
        mTextView_state21 = (TextView) findViewById(R.id.mTextView_state21);
        mTextView_state22 = (TextView) findViewById(R.id.mTextView_state22);
        mTextView_state23 = (TextView) findViewById(R.id.mTextView_state23);
        mTextView_state24 = (TextView) findViewById(R.id.mTextView_state24);
        mTextView_state_lefts = (TextView) findViewById(R.id.mTextView_state_lefts);
        mTextView_state_rights = (TextView) findViewById(R.id.mTextView_state_rights);
        mImageView_gx.setOnClickListener(this);
        mImageView_gx_ty.setOnClickListener(this);
        mImageView_gx_state.setOnClickListener(this);
        btn_left.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGrzx.class, TitleAct.class);
            }
        }));
        btn_right.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgTzh.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        tv_title1.setText("欢迎您," + F.mModelappLogin.userInfo.phoneNo);
    }

    @Override
    public void onClick(View v) {
        loadJsonUrl(queryState, new BeanVin(F.mModelappLogin.userInfo.vin));
        loadJsonUrlNoshow(queryAirConditionState, new BeanVin(F.mModelappLogin.userInfo.vin));
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(queryState)) {
            modelqueryState = (ModelqueryState) json2Model(content, ModelqueryState.class);
            Frame.HANDLES.sentAll("FrgClzt", 0, null);
        } else if (methodName.equals(queryAirConditionState)) {
            mModelqueryAirConditionState = (ModelqueryAirConditionState) json2Model(content, ModelqueryAirConditionState.class);
            mModelCz.content = (ModelqueryAirConditionState.ContentBean) json2Model(new Gson().toJson(mModelqueryAirConditionState.content), ModelqueryAirConditionState.ContentBean.class);
            Frame.HANDLES.sentAll("FrgClzt", 1, null);
        }
    }
}