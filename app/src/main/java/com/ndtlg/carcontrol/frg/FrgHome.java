//
//  FrgHome
//
//  Created by DELL on 2018-09-03 14:11:28
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.framewidget.newMenu.OnCheckChange;
import com.framewidget.newMenu.SlidingFragment;
import com.google.gson.Gson;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;
import com.ndtlg.carcontrol.F;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanVin;
import com.ndtlg.carcontrol.model.ModelCz;
import com.ndtlg.carcontrol.model.ModelqueryAirConditionState;
import com.ndtlg.carcontrol.model.ModelqueryState;
import com.ndtlg.carcontrol.service.MQTTServiceNew;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.F.queryAirConditionState;
import static com.ndtlg.carcontrol.F.queryState;


public class FrgHome extends BaseFrg implements OnCheckChange {
    //    &#8195; 占位
    public LinearLayout mLinearLayout_content;
    public SlidingFragment mSlidingFragment;
    public android.support.v4.app.FragmentManager fragmentManager;
    public static ModelqueryState modelqueryState;
    public static ModelqueryAirConditionState mModelqueryAirConditionState;
    public static ModelCz mModelCz;

    //    https://blog.csdn.net/qq_17250009/article/details/52774472  MQTT
    //    ColorfulProgressBar
//    https://47.104.79.231:443/svn/dunteal/Web/UI/APP
//    用户名
//    用户名: feidai
//    密码： feidai123
//    http://note.youdao.com/noteshare?id=f2a56c0f554177d8166f1737b6bf330a
//    18262386002密码112233
//    07B8101790228768,07B8101790228767,07B8101790228757
//    包经理:
//            15251902022
//
//    包经理:
//    bbq123456
//
//    包经理:
//    pin：1201
    public Handler mHandler = new Handler();
    public Runnable runnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_home);
        modelqueryState = new ModelqueryState();
        mModelqueryAirConditionState = new ModelqueryAirConditionState();
        mModelCz = new ModelCz();
        if (!TextUtils.isEmpty(mModelappLogin.userInfo.vin)) {
            getActivity().startService(new Intent(getActivity(), MQTTServiceNew.class));
        }
        Helper.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, new PermissionRequest() {
            public void onGrant(String[] permissions, int[] grantResults) {

            }
        });
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                loadJsonUrl(queryState, new BeanVin(F.mModelappLogin.userInfo.vin));
                getActivity().startService(new Intent(getActivity(), MQTTServiceNew.class));
                break;
            case 2:
                loadJsonUrlNoshow(queryAirConditionState, new BeanVin(F.mModelappLogin.userInfo.vin));
                break;
            case 1:
                F.saveJson("mModelappLogin", "");
                mModelappLogin = null;
                Helper.startActivity(getContext(), FrgLogin.class, IndexLoginAct.class);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
    }

    public void loaddata() {
        PgyUpdateManager.register(getActivity(),
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {
                        try { // 将新版本信息封装到AppBean中
                            final AppBean appBean = getAppBeanFromString(result);
                            new AlertDialog.Builder(getContext())
                                    .setTitle("版本更新")
                                    .setMessage("检查到新版本，是否更新").setCancelable(false)
                                    .setNegativeButton(
                                            "确定",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    startDownloadTask(
                                                            getActivity(),
                                                            appBean.getDownloadURL());
                                                }
                                            }).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
        mSlidingFragment = new SlidingFragment(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.mLinearLayout_content, mSlidingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        mSlidingFragment.addContentView(new FrgMain(), "车辆状态",
                R.drawable.btn_checked_3);
        mSlidingFragment.addContentView(new FrgClkz(), "远程控制",
                R.drawable.btn_checked_1);
        mSlidingFragment.addContentView(new FrgClzd(), "车辆诊断",
                R.drawable.btn_checked_2);
        mSlidingFragment.addContentView(new FrgLocation(), "位置服务",
                R.drawable.btn_checked_4);
        mSlidingFragment.setOffscreenPageLimit(4);
        try {
            if (!TextUtils.isEmpty(mModelappLogin.userInfo.vin)) {
                loadJsonUrl(queryState, new BeanVin(F.mModelappLogin.userInfo.vin));
                loadJsonUrlNoshow(queryAirConditionState, new BeanVin(F.mModelappLogin.userInfo.vin));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!TextUtils.isEmpty(mModelappLogin.userInfo.vin) && isAppOnForeground()) {
//                        Helper.toast("前台", getContext());
                        loadJsonUrlNoshow(queryState, queryState, new BeanVin(F.mModelappLogin.userInfo.vin));
                    } else {
//                        Helper.toast("后台", getContext());
                    }
                    mHandler.postDelayed(runnable, 35000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        mHandler.postDelayed(runnable, 35000);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
        getActivity().stopService(new Intent(getActivity(), MQTTServiceNew.class));
    }

    @Override
    public boolean onCheckedChanged(int id, int position) {
        if (position > 0 && TextUtils.isEmpty(mModelappLogin.userInfo.vin)) {
            Helper.toast("请先绑定车辆", getContext());
            return true;
        }
        return false;
    }


}