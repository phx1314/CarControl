package com.ndtlg.carcontrol.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.mdx.framework.Frame;
import com.ndtlg.carcontrol.model.ModelKg;
import com.ndtlg.carcontrol.model.ModelqueryAirConditionState;
import com.ndtlg.carcontrol.model.ModelqueryState;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.json.JSONTokener;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.mModelappLogin;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;
import static com.ndtlg.carcontrol.frg.FrgHome.mModelqueryAirConditionState;
import static com.ndtlg.carcontrol.frg.FrgHome.modelqueryState;

/**
 * Created by DELL on 2018/9/17.
 */

public class MQTTServiceNew extends Service {
    public static final String TAG = MQTTServiceNew.class.getSimpleName();

    private MqttAndroidClient client;
    private MqttConnectOptions conOpt;

    //    private String host = "tcp://10.0.2.2:61613";
    private String host = "tcp://39.104.84.146:1883";
    private String userName = "admin";
    private String passWord = "111111";
    public String myTopic = "/test";
    private String clientId = Build.BOARD + Build.SERIAL;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    public void publish(String msg) {
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        try {
            client.publish(topic, msg.getBytes(), qos.intValue(), retained.booleanValue());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        myTopic = "/device/" + mModelappLogin.userInfo.vin;
//        myTopic = "/OBD_PUSH";
        // 服务器地址（协议+地址+端口号）
        String uri = host;
        client = new MqttAndroidClient(this, uri, clientId);
        // 设置MQTT监听并且接受消息
        client.setCallback(mqttCallback);

        conOpt = new MqttConnectOptions();
        // 清除缓存
        conOpt.setCleanSession(true);
        // 设置超时时间，单位：秒
        conOpt.setConnectionTimeout(10);
        // 心跳包发送间隔，单位：秒
        conOpt.setKeepAliveInterval(5);
        // 用户名
        conOpt.setUserName(userName);
        // 密码
        conOpt.setPassword(passWord.toCharArray());
        conOpt.setAutomaticReconnect(true);
        conOpt.setCleanSession(false);

        // last will message
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + clientId + "\"}";
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            try {
                conOpt.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                doConnect = false;
                iMqttActionListener.onFailure(null, e);
            }
        }

        if (doConnect) {
            doClientConnection();
        }

    }

    @Override
    public void onDestroy() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        if (!client.isConnected() && isConnectIsNomarl()) {
            try {
                client.connect(conOpt, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            Log.i(TAG, "连接成功 ");
            try {
                // 订阅myTopic话题
                client.subscribe(myTopic, 1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            doClientConnection();
        }
    };

    // MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            String content = new String(message.getPayload());
            Log.i(TAG, "messageArrived:" + content);
            if (!message.isRetained() && topic.equals(myTopic)) {
                if (!TextUtils.isEmpty(content)) {
                    Object json = new JSONTokener(content).nextValue();
                    if (json instanceof JSONObject) {
                        JSONObject mJSONObject = new JSONObject(content);
                        Log.i(TAG, "messageArrived:" + content);
                        if (mJSONObject.has("type")) {
                            if (mJSONObject.getInt("type") == 128) {//车辆状态
                                modelqueryState.content = (ModelqueryState.ContentBean) json2Model(content, ModelqueryState.ContentBean.class);
                                Frame.HANDLES.sentAll("FrgClzt", 0, null);
                            } else if (mJSONObject.getInt("type") == 144) {//空调状态
                                mModelqueryAirConditionState.content = (ModelqueryAirConditionState.ContentBean) json2Model(content, ModelqueryAirConditionState.ContentBean.class);
                                mModelCz.content = (ModelqueryAirConditionState.ContentBean) json2Model(content, ModelqueryAirConditionState.ContentBean.class);
                                Frame.HANDLES.sentAll("FrgClzt", 1, null);
                            } else {
                                ModelKg mModelKg = new ModelKg();
                                mModelKg.content = (ModelKg.ContentBean) json2Model(content, ModelKg.ContentBean.class);
//                                Frame.HANDLES.sentAll("FrgClkz", 1, mModelKg); 不需要
                            }
                        }
                    }
                }
            } else {
                Log.e("收到老消息", "。。。。。");
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            // 失去连接，重连
        }
    };

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "MQTT当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "MQTT 没有可用网络");
            return false;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
