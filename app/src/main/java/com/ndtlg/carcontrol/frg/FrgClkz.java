//
//  FrgClkz
//
//  Created by DELL on 2018-09-03 16:13:52
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.framewidget.F;
import com.framewidget.view.Headlayout;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanairCondition;
import com.ndtlg.carcontrol.bean.BeanflashLamp;
import com.ndtlg.carcontrol.bean.BeanhighLamp;
import com.ndtlg.carcontrol.bean.Beanlock;
import com.ndtlg.carcontrol.bean.BeanlowLamp;
import com.ndtlg.carcontrol.bean.Beanluggage;
import com.ndtlg.carcontrol.bean.BeanplaceLamp;
import com.ndtlg.carcontrol.bean.BeanvehicleActivate;
import com.ndtlg.carcontrol.bean.Beanwindow;
import com.ndtlg.carcontrol.model.ModelAll;
import com.ndtlg.carcontrol.model.ModelKg;
import com.ndtlg.carcontrol.view.MFragmentAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static com.ndtlg.carcontrol.F.airCondition;
import static com.ndtlg.carcontrol.F.flashLamp;
import static com.ndtlg.carcontrol.F.highLamp;
import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.lock;
import static com.ndtlg.carcontrol.F.lowLamp;
import static com.ndtlg.carcontrol.F.luggage;
import static com.ndtlg.carcontrol.F.placeLamp;
import static com.ndtlg.carcontrol.F.vehicleActivate;
import static com.ndtlg.carcontrol.F.window;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelqueryAirConditionState;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;


public class FrgClkz extends BaseFrg {

    public Headlayout mHeadlayout;
    public XTabLayout mTabLayout;
    public ViewPager mViewPager;
    public List<String> list_title = new ArrayList<>();
    public List<BaseFrg> fragments = new ArrayList();
    public LinkedBlockingQueue<ModelAll> mModelAlls = new LinkedBlockingQueue<ModelAll>();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_clkz);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                doDataNew(mModelCz);
                F.closeSoftKey(getActivity());
                break;
//            case 1:
//                doResult((ModelKg) obj);
//                break;
        }
    }

    public void doDataNew(Object tb) {
        mModelAlls.clear();
        try {
            boolean isCh = true;
            Field[] fields = tb.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.get(tb).toString().equals("-1")) {
                    if (field.getName().equals("highLamp")) {
                        mModelAlls.add(new ModelAll(highLamp, new BeanhighLamp(field.get(tb).toString())));
                    }
                    if (field.getName().equals("lowLamp")) {
                        mModelAlls.add(new ModelAll(lowLamp, new BeanlowLamp(field.get(tb).toString())));
                    }
                    if (field.getName().equals("placeLamp")) {
                        mModelAlls.add(new ModelAll(placeLamp, new BeanplaceLamp(field.get(tb).toString())));
                    }
                    if (field.getName().equals("flashLamp")) {
                        mModelAlls.add(new ModelAll(flashLamp, new BeanflashLamp(field.get(tb).toString())));
                    }
                    if (field.getName().equals("eState")) {
                        mModelAlls.add(new ModelAll(vehicleActivate, new BeanvehicleActivate(field.get(tb).toString())));
                    }
                    if (field.getName().equals("lockCentral")) {
                        mModelAlls.add(new ModelAll(lock, new Beanlock(field.get(tb).toString())));
                    }
                    if (field.getName().equals("trunk")) {
                        mModelAlls.add(new ModelAll(luggage, new Beanluggage(field.get(tb).toString())));
                    }
                    if ((field.getName().equals("leftFrontWindowState") || field.getName().equals("rightFrontWindowState") || field.getName().equals("leftRearWindowState") || field.getName().equals("rightRearWindowState")) && isCh) {
                        mModelAlls.add(new ModelAll(window, new Beanwindow(mModelCz.leftFrontWindowState == -1 ? modelqueryState.content.leftFrontWindowState : mModelCz.leftFrontWindowState, mModelCz.rightFrontWindowState == -1 ? modelqueryState.content.rightFrontWindowState : mModelCz.rightFrontWindowState, mModelCz.leftRearWindowState == -1 ? modelqueryState.content.leftRearWindowState : mModelCz.leftRearWindowState, mModelCz.rightRearWindowState == -1 ? modelqueryState.content.rightRearWindowState : mModelCz.rightRearWindowState)));
                        isCh = false;
                    }
                }
            }
            if (mModelCz.isKtCz) {
                if (mModelCz.content.off == 0 && mModelCz.content.ac == 0 && mModelCz.content.heat == 0 && mModelCz.content.auto == 0) {//关闭
                    mModelCz.content.remainSTime_all = 0;
                    if (mModelqueryAirConditionState.content.off == 0 && mModelqueryAirConditionState.content.ac == 0 && mModelqueryAirConditionState.content.heat == 0 && mModelqueryAirConditionState.content.auto == 0) {
                        mModelCz.content.remainETime_all = 0;//过滤延时关闭
                    }
                }
                mModelAlls.add(new ModelAll(airCondition, new BeanairCondition()));
                mModelCz.isKtCz = false;
            }
            doJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doJson() {
        if (!mModelAlls.isEmpty()) {
            ModelAll mModelAll = mModelAlls.poll();
            loadJsonUrl(mModelAll.method, mModelAll.object);
        } else {
            Frame.HANDLES.sentAll("FrgClzt", 0, null);
        }
    }

    public void doData(Object tb) {
        try {
            boolean isCh = true;
            Field[] fields = tb.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.get(tb).toString().equals("-1")) {
                    if (field.getName().equals("highLamp")) {
                        loadJsonUrl(highLamp, new BeanhighLamp(field.get(tb).toString()));
                    }
                    if (field.getName().equals("lowLamp")) {
                        loadJsonUrl(lowLamp, new BeanlowLamp(field.get(tb).toString()));
                    }
                    if (field.getName().equals("placeLamp")) {
                        loadJsonUrl(placeLamp, new BeanplaceLamp(field.get(tb).toString()));
                    }
                    if (field.getName().equals("flashLamp")) {
                        loadJsonUrl(flashLamp, new BeanflashLamp(field.get(tb).toString()));
                    }
                    if (field.getName().equals("eState")) {
                        loadJsonUrl(vehicleActivate, new BeanvehicleActivate(field.get(tb).toString()));
                    }
                    if (field.getName().equals("lockCentral")) {
                        loadJsonUrl(lock, new Beanlock(field.get(tb).toString()));
                    }
                    if (field.getName().equals("trunk")) {
                        loadJsonUrl(luggage, new Beanluggage(field.get(tb).toString()));
                    }
                    if ((field.getName().equals("leftFrontWindowState") || field.getName().equals("rightFrontWindowState") || field.getName().equals("leftRearWindowState") || field.getName().equals("rightRearWindowState")) && isCh) {
                        loadJsonUrl(window, new Beanwindow(mModelCz.leftFrontWindowState == -1 ? modelqueryState.content.leftFrontWindowState : mModelCz.leftFrontWindowState, mModelCz.rightFrontWindowState == -1 ? modelqueryState.content.rightFrontWindowState : mModelCz.rightFrontWindowState, mModelCz.leftRearWindowState == -1 ? modelqueryState.content.leftRearWindowState : mModelCz.leftRearWindowState, mModelCz.rightRearWindowState == -1 ? modelqueryState.content.rightRearWindowState : mModelCz.rightRearWindowState));
                        isCh = false;
                    }
                }
            }
            if (mModelCz.isKtCz) {
                if (mModelCz.content.off == 0 && mModelCz.content.ac == 0 && mModelCz.content.heat == 0 && mModelCz.content.auto == 0) {//关闭
                    mModelCz.content.remainSTime_all = 0;
                    if (mModelqueryAirConditionState.content.off == 0 && mModelqueryAirConditionState.content.ac == 0 && mModelqueryAirConditionState.content.heat == 0 && mModelqueryAirConditionState.content.auto == 0) {
                        mModelCz.content.remainETime_all = 0;//过滤延时关闭
                    }
                }
                loadJsonUrl(airCondition, new BeanairCondition());
                mModelCz.isKtCz = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doResult(ModelKg mModelKg) {
        if (mModelKg.content.result == 1) {
            Helper.toast("命令错误", getContext());
        }
        if (mModelKg.content.result == 2) {
            Helper.toast("参数错误", getContext());
        }
        if (mModelKg.content.result == 3) {
            Helper.toast("长度错误", getContext());
        }
        if (mModelKg.content.result == 4) {
            Helper.toast("设备未没有准备好", getContext());
        }
        if (mModelKg.content.result == 5) {
            Helper.toast("设备不支持该这个命令", getContext());
        }
        if (mModelKg.content.result == 20) {
            Helper.toast("设备未回复", getContext());
        }


        if (mModelKg.content.methodName.equals(highLamp) || mModelKg.content.type == 3) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.highLamp = mModelCz.highLamp;
            }
            mModelCz.highLamp = -1;
        } else if (mModelKg.content.methodName.equals(lowLamp) || mModelKg.content.type == 4) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.lowLamp = mModelCz.lowLamp;
            }
            mModelCz.lowLamp = -1;
        } else if (mModelKg.content.methodName.equals(placeLamp) || mModelKg.content.type == 5) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.placeLamp = mModelCz.placeLamp;
            }
            mModelCz.placeLamp = -1;
        } else if (mModelKg.content.methodName.equals(flashLamp) || mModelKg.content.type == 6) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.flashLamp = mModelCz.flashLamp;
            }
            mModelCz.flashLamp = -1;
        } else if (mModelKg.content.methodName.equals(vehicleActivate) || mModelKg.content.type == 15) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.eState = mModelCz.eState;
            }
            mModelCz.eState = -1;
        } else if (mModelKg.content.methodName.equals(lock) || mModelKg.content.type == 1) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.lockCentral = mModelCz.lockCentral;
            }
            mModelCz.lockCentral = -1;
        } else if (mModelKg.content.methodName.equals(luggage) || mModelKg.content.type == 8) {
            if (mModelKg.content.result == 0) {
                modelqueryState.content.trunk = mModelCz.trunk;
            }
            mModelCz.trunk = -1;
        } else if (mModelKg.content.methodName.equals(airCondition) || mModelKg.content.type == 16) {
            Frame.HANDLES.sentAll("FrgHome", 2, null);
//            if (mModelKg.content.result == 0) {
//                mModelqueryAirConditionState.content = (ModelqueryAirConditionState.ContentBean) json2Model(new Gson().toJson(mModelCz.content), ModelqueryAirConditionState.ContentBean.class);
//            } else {
//                mModelCz.content = (ModelqueryAirConditionState.ContentBean) json2Model(new Gson().toJson(mModelqueryAirConditionState.content), ModelqueryAirConditionState.ContentBean.class);
//            }
        } else if (mModelKg.content.methodName.equals(window) || mModelKg.content.type == 7) {
            if (mModelKg.content.result == 0) {
                if (mModelCz.leftFrontWindowState != -1) {
                    modelqueryState.content.leftFrontWindowState = mModelCz.leftFrontWindowState;
                }
                if (mModelCz.rightFrontWindowState != -1) {
                    modelqueryState.content.rightFrontWindowState = mModelCz.rightFrontWindowState;
                }
                if (mModelCz.leftRearWindowState != -1) {
                    modelqueryState.content.leftRearWindowState = mModelCz.leftRearWindowState;
                }
                if (mModelCz.rightRearWindowState != -1) {
                    modelqueryState.content.rightRearWindowState = mModelCz.rightRearWindowState;
                }
            }
            mModelCz.leftFrontWindowState = -1;
            mModelCz.rightFrontWindowState = -1;
            mModelCz.leftRearWindowState = -1;
            mModelCz.rightRearWindowState = -1;
        }
        if (mModelKg.content.methodName.equals(airCondition) || mModelKg.content.type == 16) {
//            Frame.HANDLES.sentAll("FrgHome", 2, null);
        } else {
//            Frame.HANDLES.sentAll("FrgClzt", 0, null);
        }
        doJson();
    }

    @Override
    public void onSuccess(String methodName, String content) {
        ModelKg mModelKg = (ModelKg) json2Model(content, ModelKg.class);
        mModelKg.content.methodName = methodName;
        doResult(mModelKg);
    }

    @Override
    public void onFailure(int statusCode, String content, Throwable error, String methodName) {
        if (methodName.equals(highLamp)) {
            mModelCz.highLamp = -1;
        } else if (methodName.equals(lowLamp)) {
            mModelCz.lowLamp = -1;
        } else if (methodName.equals(placeLamp)) {
            mModelCz.placeLamp = -1;
        } else if (methodName.equals(flashLamp)) {
            mModelCz.flashLamp = -1;
        } else if (methodName.equals(vehicleActivate)) {
            mModelCz.eState = -1;
        } else if (methodName.equals(lock)) {
            mModelCz.lockCentral = -1;
        } else if (methodName.equals(luggage)) {
            mModelCz.trunk = -1;
        } else if (methodName.equals(airCondition)) {
//            mModelCz.content = (ModelqueryAirConditionState.ContentBean) json2Model(new Gson().toJson(mModelqueryAirConditionState.content), ModelqueryAirConditionState.ContentBean.class);
        } else if (methodName.equals(window)) {
            mModelCz.leftFrontWindowState = -1;
            mModelCz.rightFrontWindowState = -1;
            mModelCz.leftRearWindowState = -1;
            mModelCz.rightRearWindowState = -1;
        }
        if (methodName.equals(airCondition)) {
//            Frame.HANDLES.sentAll("FrgHome", 2, null);
        } else {
//            Frame.HANDLES.sentAll("FrgClzt", 0, null);
        }
        doJson();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
        mTabLayout = (XTabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mHeadlayout.setTitle("车辆控制");
        mHeadlayout.setLeftBackground(R.drawable.ic_nav_user);
        mHeadlayout.setRightBacgroud(R.drawable.ic_nav_message);
        mHeadlayout.setRightOnclicker(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgTzh.class, TitleAct.class);
            }
        }));
        mHeadlayout.setLeftOnclicker(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGrzx.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        list_title.add("门窗");
        list_title.add("灯光");
        list_title.add("中控");
        list_title.add("其他");
        fragments.add(new FrgMc());
        fragments.add(new FrgDg());
        fragments.add(new FrgZk());
        fragments.add(new FrgQt());
        mViewPager.setAdapter(new MFragmentAdapter(
                getChildFragmentManager(), fragments));
        //将tabLayout与viewpager连起来
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.getTabAt(0).setText(list_title.get(0));
        mTabLayout.getTabAt(1).setText(list_title.get(1));
        mTabLayout.getTabAt(2).setText(list_title.get(2));
        mTabLayout.getTabAt(3).setText(list_title.get(3));
    }

    public static int go2Over(int current, int future, ImageView mImageView, int checked, int nochecked) {
        int end;
        if (future == -1) {
            end = current == 0 ? 1 : 0;
            if (end == 0) {
                mImageView.setBackgroundResource(nochecked);
            } else {
                mImageView.setBackgroundResource(checked);
            }
        } else {
            end = -1;
            if (current == 0) {
                mImageView.setBackgroundResource(nochecked);
            } else {
                mImageView.setBackgroundResource(checked);
            }
        }
        return end;
    }
    public static int go2OverDG(int current, int future, ImageView mImageView, int checked, int nochecked) {
        int end;
        if (future == -1) {
            end = current == 0 ? 0 : 1;
            if (end == 0) {
                mImageView.setBackgroundResource(checked);
            } else {
                mImageView.setBackgroundResource(nochecked);
            }
        } else {
            end = -1;
            if (current == 0) {
                mImageView.setBackgroundResource(nochecked);
            } else {
                mImageView.setBackgroundResource(checked);
            }
        }
        return end;
    }

    public static int go2OverSpecial(int current, int future, ImageView mImageView, int checked, int nochecked, ImageView mImageView2, int checked2, int nochecked2) {
        int end;
        if (future == -1) {
            end = current == 0 ? 1 : 0;
            if (end == 0) {
                mImageView.setBackgroundResource(nochecked);
                mImageView2.setBackgroundResource(checked2);
            } else {
                mImageView.setBackgroundResource(checked);
                mImageView2.setBackgroundResource(nochecked2);
            }
        } else {
            end = -1;
            if (current == 0) {
                mImageView.setBackgroundResource(nochecked);
                mImageView2.setBackgroundResource(checked2);
            } else {
                mImageView.setBackgroundResource(checked);
                mImageView2.setBackgroundResource(nochecked2);
            }
        }
        return end;
    }


    public static int go3Over(int current, int future, ImageView mImageView, int checked, int nochecked, int centerchecked) {
        int end = future;

        if (current == future) {
            end = -1;
        }
        if (future == 1) {
            mImageView.setBackgroundResource(nochecked);
        } else if (future == 2) {
            mImageView.setBackgroundResource(checked);
        } else {
            mImageView.setBackgroundResource(centerchecked);
        }
        return end;
    }

    public static int go3AccOver(int current, int future, ImageView mImageView, int checked, int nochecked, int centerchecked) {
        int end = future;

        if (current == future) {
            end = -1;
        }
        if (future == 0) {
            mImageView.setBackgroundResource(nochecked);
        } else if (future == 2) {
            mImageView.setBackgroundResource(checked);
        } else {
            mImageView.setBackgroundResource(centerchecked);
        }
        return end;
    }

    public static void changeStateImage(int state, ImageView mImageView, int checked, int nochecked) {
        if (state > 0) {
            mImageView.setBackgroundResource(checked);
        } else {
            mImageView.setBackgroundResource(nochecked);
        }
    }

    public static void changeStateImage(int state, ImageView mImageView, int checked, int nochecked, ImageView mImageView2, int checked2, int nochecked2) {
        if (state > 0) {
            mImageView.setBackgroundResource(checked);
            mImageView2.setBackgroundResource(nochecked2);
        } else {
            mImageView.setBackgroundResource(nochecked);
            mImageView2.setBackgroundResource(checked2);
        }
    }

    public static void changeStateImage(int state, ImageView mImageView, int checked, int nochecked, int centerchecked) {
        if (state == 1) {
            mImageView.setBackgroundResource(nochecked);
        } else if (state == 2) {
            mImageView.setBackgroundResource(checked);
        } else if (state == 3) {
            mImageView.setBackgroundResource(centerchecked);
        }
    }

    public static void changeStateImageAcc(int state, ImageView mImageView, int checked, int nochecked, int centerchecked) {
        if (state == 2) {
            mImageView.setBackgroundResource(checked);
        } else if (state == 1) {
            mImageView.setBackgroundResource(centerchecked);
        } else if (state == 0) {
            mImageView.setBackgroundResource(nochecked);
        }
    }


}