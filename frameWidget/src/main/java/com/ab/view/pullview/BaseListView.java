//
//  BaseFrg
//
//  Created by JinQu on 2017-03-22 11:26:45
//  Copyright (c) JinQu All rights reserved.


/**

 */

package com.ab.view.pullview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ListView;

import com.ab.http.AbStringHttpResponseListener;
import com.ab.http.HttpUtil;
import com.mdx.framework.log.MLog;
import com.mdx.framework.utility.Helper;

import org.json.JSONObject;
import org.json.JSONTokener;

public class BaseListView extends ListView {


    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public class HttpResponseListener extends AbStringHttpResponseListener {
        public String methodName;
        public boolean isreFreash = true;

        public HttpResponseListener(String methodName, boolean isreFreash) {
            this.methodName = methodName;
            this.isreFreash = isreFreash;
        }


        @Override
        public void onStart() {
        }

        @Override
        public void onFinish() {
            try {
                BaseListView.this.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, String content, Throwable error) {
            Helper.toast("请求服务器失败", getContext());
            MLog.D("请求服务器失败方法名：" + methodName);
            error.printStackTrace();
            try {
                BaseListView.this.onFailure();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSuccess(int statusCode, String content) {
            MLog.I(content);
            try {
                if (!TextUtils.isEmpty(content)) {
                    Object json = new JSONTokener(content).nextValue();
                    if (json instanceof JSONObject) {
                        JSONObject mJSONObject = new JSONObject(content);
                        if (mJSONObject.has("code") && !TextUtils.isEmpty(mJSONObject.getString("code")) && !mJSONObject.getString("code").equals("000000")) {
                            Helper.toast(mJSONObject.getString("msg"), getContext());
                            return;
                        }
                        if (mJSONObject.has("status") && mJSONObject.getInt("status") == 0) {
                            Helper.toast(mJSONObject.getString("info"), getContext());
                            return;
                        }
                    }
                }
                BaseListView.this.onSuccess(methodName, content, isreFreash);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSuccess(String methodName, String content, boolean isreFreash) {
    }

    public void onFailure() {
    }

    public void onFinish() {
    }


    public void loadUrlGet(String methodName, boolean isreFreash, Object... mparams) {
        HttpUtil.loadUrl(getContext(), "GET", methodName, new HttpResponseListener(methodName, isreFreash), mparams);
    }


    public void loadUrlPost(String methodName, boolean isreFreash, Object... mparams) {
        HttpUtil.loadUrl(getContext(), "POST", methodName, new HttpResponseListener(methodName, isreFreash), mparams);
    }

    public void loadJsonUrl(String methodName, boolean isreFreash, String json) {
        HttpUtil.loadJsonUrl(getContext(), methodName, json, new HttpResponseListener(methodName, isreFreash));
    }

}
