//
//  FrgLocation
//
//  Created by DELL on 2018-09-11 17:04:46
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.framewidget.view.Headlayout;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanfindCar;
import com.ndtlg.carcontrol.bean.BeanfindCarByHonk;
import com.ndtlg.carcontrol.model.ModelPub;
import com.ndtlg.carcontrol.model.ModelfindCar;
import com.ndtlg.carcontrol.model.ModelqueryLatestLocation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.ndtlg.carcontrol.F.findCar;
import static com.ndtlg.carcontrol.F.findCarByFlashLamp;
import static com.ndtlg.carcontrol.F.findCarByHonk;
import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.queryLatestLocation;


public class FrgLocation extends BaseFrg {

    public Headlayout mHeadlayout;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_xun;
    public LinearLayout mLinearLayout_ddh;
    public TextView mTextView_phone;
    public TextView mTextView_call;
    public LinearLayout mLinearLayout_wz;
    public TextView mTextView_location;
    public TextView mTextView_sd;
    public TextView mTextView_md;
    public TextView mTextView_dh;
    public MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    private int mCurrentDirection = 0;
    private static String PATH = "custom_config_dark.json";
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;

    @Override
    protected void create(Bundle savedInstanceState) {
        setMapCustomFile(getActivity(), PATH);
        setContentView(R.layout.frg_location);
        initView(savedInstanceState);
        loaddata();
    }

    private void initView(Bundle savedInstanceState) {
        findVMethod(savedInstanceState);
    }

    private void findVMethod(Bundle savedInstanceState) {

        mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
        mMapView = (MapView) findViewById(R.id.mMapView);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_xun = (TextView) findViewById(R.id.mTextView_xun);
        mLinearLayout_ddh = (LinearLayout) findViewById(R.id.mLinearLayout_ddh);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_call = (TextView) findViewById(R.id.mTextView_call);
        mLinearLayout_wz = (LinearLayout) findViewById(R.id.mLinearLayout_wz);
        mTextView_location = (TextView) findViewById(R.id.mTextView_location);
        mTextView_sd = (TextView) findViewById(R.id.mTextView_sd);
        mTextView_md = (TextView) findViewById(R.id.mTextView_md);
        mTextView_dh = (TextView) findViewById(R.id.mTextView_dh);

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);

        // 定位初始化
        mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        MapView.setMapCustomEnable(true);
        mMapView.showZoomControls(false);
        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
    }

    public void loaddata() {
        mHeadlayout.setTitle("位置服务");
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
        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng ll = new LatLng(mCurrentLat,
                        mCurrentLon);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

                mLinearLayout_ddh.setVisibility(View.GONE);
                mTextView_xun.setVisibility(View.GONE);
                mLinearLayout_wz.setVisibility(View.VISIBLE);
            }
        });
        mTextView_xun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJsonUrl(findCar, new BeanfindCar("1"));
            }
        });
        mTextView_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJsonUrl(findCarByFlashLamp, new BeanfindCarByHonk());
            }
        });
        mTextView_md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJsonUrl(findCarByHonk, new BeanfindCarByHonk());
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLinearLayout_ddh.setVisibility(View.VISIBLE);
                mTextView_xun.setVisibility(View.GONE);
                mLinearLayout_wz.setVisibility(View.GONE);
            }
        });
        mTextView_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.requestPermissions(new String[]{"android.permission.CALL_PHONE", Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + mTextView_phone.getText().toString());
                        intent.setData(data);
                        startActivity(intent);
                    }
                });
            }
        });
        loadJsonUrl(queryLatestLocation, new BeanfindCarByHonk());

    }

    // 设置个性化地图config文件路径
    private void setMapCustomFile(Context context, String PATH) {
        FileOutputStream out = null;
        InputStream inputStream = null;
        String moduleName = null;
        try {
            inputStream = context.getAssets()
                    .open("customConfigdir/" + PATH);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            moduleName = context.getFilesDir().getAbsolutePath();
            File f = new File(moduleName + "/" + PATH);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            out = new FileOutputStream(f);
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MapView.setCustomMapStylePath(moduleName + "/" + PATH);

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
//            mTextView_location.setText(location.getAddrStr());
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(mCurrentLat,
                        mCurrentLon);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(findCar)) {
            ModelfindCar mModelfindCar = (ModelfindCar) json2Model(content, ModelfindCar.class);
            if (mModelfindCar.content.result == 0) {
                Helper.toast("执行成功", getContext());
            }
            if (mModelfindCar.content.result == 1) {
                Helper.toast("命令错误", getContext());
            }
            if (mModelfindCar.content.result == 2) {
                Helper.toast("参数错误", getContext());
            }
            if (mModelfindCar.content.result == 3) {
                Helper.toast("长度错误", getContext());
            }
            if (mModelfindCar.content.result == 4) {
                Helper.toast("设备未没有准备好", getContext());
            }
            if (mModelfindCar.content.result == 5) {
                Helper.toast("设备不支持该这个命令", getContext());
            }
            if (mModelfindCar.content.result == 20) {
                Helper.toast("设备未回复", getContext());
            }
//            //定义Maker坐标点
//            LatLng point = new LatLng(39.963175, 116.400244);
////构建Marker图标
//            BitmapDescriptor bitmap = BitmapDescriptorFactory
//                    .fromResource(R.drawable.ic_location_car);
////构建MarkerOption，用于在地图上添加Marker
//            OverlayOptions option = new MarkerOptions()
//                    .position(point)
//                    .icon(bitmap);
////在地图上添加Marker，并显示
//            mBaiduMap.addOverlay(option);
        } else if (methodName.equals(findCarByHonk) || methodName.equals(findCarByFlashLamp)) {
            ModelPub mModelPub = (ModelPub) json2Model(content, ModelPub.class);
            Helper.toast(mModelPub.info, getContext());
        } else if (methodName.equals(queryLatestLocation)) {
            ModelqueryLatestLocation mModelqueryLatestLocation = (ModelqueryLatestLocation) json2Model(content, ModelqueryLatestLocation.class);
            mTextView_location.setText(mModelqueryLatestLocation.content.address);
            LatLng ll = new LatLng(Double.valueOf(mModelqueryLatestLocation.content.lat),
                    Double.valueOf(mModelqueryLatestLocation.content.lng));
            CoordinateConverter converter = new CoordinateConverter()
                    .from(CoordinateConverter.CoordType.GPS)
                    .coord(ll);

            LatLng desLatLng = converter.convert();
//            LatLng desLatLng = new LatLng(
//                    31.818860807951893,120.00030384723702);
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_location_car);
            OverlayOptions option = new MarkerOptions()
                    .position(desLatLng)
                    .icon(bitmap);
//在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);

            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(desLatLng).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }


}