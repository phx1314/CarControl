package com.ndtlg.carcontrol;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ab.util.AbMd5;
import com.google.gson.Gson;
import com.mdx.framework.Frame;
import com.ndtlg.carcontrol.model.ModelappLogin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class F {
    public static String api1 = "http://www.dt-tbox.com:8030";
    public static String api2 = "http://39.104.22.46/upgrade";
    public static String appLogin = api1 + "/appUser/appLogin";
    public static String appRegister = api1 + "/appUser/appRegister";
    public static String resetPwd = api1 + "/appUser/resetPwd";
    public static String resetPin = api1 + "/appUser/resetPin";
    public static String setPlateNo = api1 + "/appUser/setPlateNo";
    public static String resetPhoneNO = api1 + "/appUser/resetPhoneNo";
    public static String checkVin = api1 + "/appUser/checkVin";
    public static String checkRealName = api1 + "/appUser/checkRealName";
    public static String bindCar = api1 + "/appUser/bindCar";
    public static String queryState = api2 + "/rest/cmd/queryState";
    public static String lock = api2 + "/rest/cmd/lock";
    public static String honk = api2 + "/rest/cmd/honk";
    public static String highLamp = api2 + "/rest/cmd/highLamp";
    public static String lowLamp = api2 + "/rest/cmd/lowLamp";
    public static String placeLamp = api2 + "/rest/cmd/placeLamp";
    public static String flashLamp = api2 + "/rest/cmd/flashLamp";
    public static String vehicleActivate = api2 + "/rest/cmd/vehicleActivate";
    public static String window = api2 + "/rest/cmd/window";
    public static String findCar = api2 + "rest/cmd/findCar";
    public static String airCondition = api2 + "/rest/cmd/airCondition";
    public static String queryAirConditionState = api2 + "/rest/cmd/queryAirConditionState";
    public static String luggage = api2 + "/rest/cmd/luggage";
    public static ModelappLogin mModelappLogin;


    public static void init() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        mModelappLogin = new Gson().fromJson(getJson("mModelappLogin"), ModelappLogin.class);
    }

    public static void saveJson(String key, String json) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(key, json).commit();

    }

    public static String getJson(String key) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getString(key, "");
    }

    public static Object json2Model(String json, Class<?> mclass) {
        return new Gson().fromJson(json, mclass);

    }

    public static String changePhone(String code) {
        if (!TextUtils.isEmpty(code) && code.length() == 11) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);
                if (i >= 3 && i < code.length() - 4) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return code;
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int minute = 0;
        int second = 0;
        minute = time / 60;
        second = time % 60;
        if (minute > 0) {
            timeStr = minute + "分" + second + "秒";
        } else {
            timeStr = second + "秒";
        }

        return timeStr;
    }

    public static void changeFonts(ViewGroup root, Activity act) {

        Typeface tf = Typeface.createFromAsset(act.getAssets(),
                "fonts/xxx.ttf");

        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                changeFonts((ViewGroup) v, act);
            }
        }

    }

    /**
     * 用来遍历对象属性和属性值
     */
    public static String readClassAttr(Object tb) {
        HashMap map = new HashMap();
        List<String> list = new ArrayList<>();
        String str = "";
        try {
            Field[] fields = tb.getClass().getDeclaredFields();
            System.out.println(fields.length);
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equals("sign")) {
                    map.put(field.getName(), TextUtils.isEmpty(field.get(tb).toString()) ? "" : field.get(tb).toString());
                    if (!field.getName().equals("timestamp"))
                        list.add(field.getName());
                }
            }
            if (tb.getClass().getSuperclass() != null && (tb.getClass().getSuperclass().getSimpleName().equals("BeanBase") || tb.getClass().getSuperclass().getSimpleName().equals("BeanListBase"))) {
                Field[] fields_father = tb.getClass().getSuperclass().getDeclaredFields();
                for (Field field : fields_father) {
                    field.setAccessible(true);
                    if (!field.getName().equals("sign")) {
                        map.put(field.getName(), TextUtils.isEmpty(field.get(tb).toString()) ? "" : field.get(tb).toString());
                        if (!field.getName().equals("timestamp"))
                            list.add(field.getName());
                    }
                }
            }
            Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
            for (String key : list) {
                str += key + "=" + map.get(key) + "&";
            }
            str += "timestamp=" + map.get("timestamp");
            Log.i("sign=", str);
            return AbMd5.MD5(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String changeSFZ(String code) {
        if (!TextUtils.isEmpty(code) && code.length() > 10) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);
                if (i >= 6 && i < code.length() - 4) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return code;
    }

}
