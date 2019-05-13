//
//  BaseFrg
//
//  Created by DELL on 2018-09-03 14:11:28
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.ab.http.AbRequestParams;
import com.ab.http.HttpUtil;
import com.ab.util.HttpResponseListener;
import com.ab.util.HttpResponseListenerSon;
import com.framewidget.view.Headlayout;
import com.google.gson.Gson;
import com.mdx.framework.activity.MFragment;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public abstract class BaseFrg extends MFragment implements View.OnClickListener, HttpResponseListenerSon {
    public Headlayout mHeadlayout;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFailure(int statusCode, String content, Throwable error, String methodName) {

    }

    @Override
    public void onSuccess(String methodName, String content) {
    }



    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        mHeadlayout = new Headlayout(context);
        mHeadlayout.setLeftBackground(R.drawable.ic_nav_back);
        mHeadlayout.setGoBack(getActivity());
        actionBar.addView(mHeadlayout);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void loadJsonUrl(String methodName, Object json) {
        HttpUtil.loadJsonUrl(getContext(), methodName, new Gson().toJson(json), new HttpResponseListener(getContext(), this, methodName, true));
    }

    public void loadJsonUrl(String methodName, String name, Object json) {
        HttpUtil.loadJsonUrl(getContext(), methodName, new Gson().toJson(json), new HttpResponseListener(getContext(), this, name, true));
    }

    public void loadJsonUrlNoshow(String methodName, String name, Object json) {
        HttpUtil.loadJsonUrl(getContext(), methodName, new Gson().toJson(json), new HttpResponseListener(getContext(), this, name, false));
    }

    public void loadJsonUrlNoshow(String methodName, Object json) {
        HttpUtil.loadJsonUrl(getContext(), methodName, new Gson().toJson(json), new HttpResponseListener(getContext(), this, methodName, false));
    }

    /**
     * 判断app是否处于前台
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getActivity()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getActivity().getPackageName();
        /**
         * 获取Android设备中所有正在运行的App
         */
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用来遍历对象属性和属性值
     */
    public static AbRequestParams readClassAttr(String json, Object tb) {
        AbRequestParams map = new AbRequestParams();
        try {
            if (tb != null) {
                Field[] fields = tb.getClass().getDeclaredFields();
                System.out.println(fields.length);
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(tb) == null ? "" : field.get(tb).toString());
                }
            }
            if (!TextUtils.isEmpty(json)) {
                JSONObject jsonObject = new JSONObject(json);
                Iterator iterator = jsonObject.keys();                       // joData是JSONObject对象
                while (iterator.hasNext()) {
                    String key = iterator.next() + "";
                    map.put(key, jsonObject.optString(key));
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
