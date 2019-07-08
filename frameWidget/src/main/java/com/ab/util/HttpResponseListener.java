package com.ab.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.ab.http.AbStringHttpResponseListener;
import com.mdx.framework.Frame;
import com.mdx.framework.log.MLog;
import com.mdx.framework.utility.Helper;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by DELL on 2017/3/29.
 */

public class HttpResponseListener extends AbStringHttpResponseListener {
    public String methodName;
    public boolean isShow = true;
    public Context context;
    public HttpResponseListenerSon mHttpResponseListenerSon;
    public ProgressDialog dialog;


    public HttpResponseListener(Context context, HttpResponseListenerSon mHttpResponseListenerSon, String methodName, boolean isShow) {
        this.context = context;
        this.mHttpResponseListenerSon = mHttpResponseListenerSon;
        this.methodName = methodName;
        this.isShow = isShow;
        try {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            dialog.setMessage("数据加载中，请稍后...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        if (isShow) {
            dialog.show();
        }
        try {
            if (mHttpResponseListenerSon != null)
                mHttpResponseListenerSon.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        try {
            dismissProgressDialog();
            if (mHttpResponseListenerSon != null)
                mHttpResponseListenerSon.onFinish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onFailure(int statusCode, String content, Throwable error) {
        try {
            Helper.toast("请求服务器失败", context);
            MLog.D("请求服务器失败方法名：" + methodName);
            dismissProgressDialog();
            if (mHttpResponseListenerSon != null)
                mHttpResponseListenerSon.onFailure(statusCode, content, error, methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(int statusCode, String content) {
        try {
            dismissProgressDialog();
            MLog.I(content);
            if (!TextUtils.isEmpty(content)) {
                Object json = new JSONTokener(content).nextValue();
                if (json instanceof JSONObject) {
                    JSONObject mJSONObject = new JSONObject(content);
                    if (mJSONObject.has("code") && !TextUtils.isEmpty(mJSONObject.getString("code")) && !mJSONObject.getString("code").equals("null") && !mJSONObject.getString("code").equals("000000")) {
                        Helper.toast(mJSONObject.getString("msg"), context);
                        if (mJSONObject.getString("code").equals("999902")) {
                            Frame.HANDLES.sentAll("FrgHome", 1, null);
                        }
                        return;
                    }
                    if (mJSONObject.has("status") && mJSONObject.getInt("status") == 0) {
                        Helper.toast(mJSONObject.getString("info"), context);
                        return;
                    }
                }
            }
            if (mHttpResponseListenerSon != null) {
                mHttpResponseListenerSon.onSuccess(methodName, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            if (mHttpResponseListenerSon != null) {
//                mHttpResponseListenerSon.onSuccess(methodName, content);
//            }
        }
    }

}
