//
//  FrgJsxwDetail
//
//  Created by DELL on 2019-05-17 09:20:24
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.bean.BeanqueryLocationList;
import com.ndtlg.carcontrol.model.ModelqueryLocationList;

import java.util.ArrayList;
import java.util.List;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.queryLocationList;


public class FrgJsxwDetail extends BaseFrg {

    public TextView mTextView_xssj;
    public TextView mTextView_xslc;
    public TextView mTextView_sd;
    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public TextView mTextView_csh1;
    public TextView mTextView_csh2;
    public TextView mTextView_csh3;
    public MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    public String start_time;
    public String end_time;

    @Override
    protected void create(Bundle savedInstanceState) {
        start_time = getActivity().getIntent().getStringExtra("start_time");
        end_time = getActivity().getIntent().getStringExtra("end_time");
        setContentView(R.layout.frg_jsxw_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_xssj = (TextView) findViewById(R.id.mTextView_xssj);
        mTextView_xslc = (TextView) findViewById(R.id.mTextView_xslc);
        mTextView_sd = (TextView) findViewById(R.id.mTextView_sd);
        mTextView_fen = (TextView) findViewById(R.id.mTextView_fen);
        mTextView_fenZ1 = (TextView) findViewById(R.id.mTextView_fenZ1);
        mTextView_csh1 = (TextView) findViewById(R.id.mTextView_csh1);
        mTextView_csh2 = (TextView) findViewById(R.id.mTextView_csh2);
        mTextView_csh3 = (TextView) findViewById(R.id.mTextView_csh3);
        mMapView = (MapView) findViewById(R.id.mMapView);


    }

    public void loaddata() {
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

//        loadJsonUrl(queryLocationList, new BeanqueryLocationList("2019-05-17 00:00:00", "2019-05-17 10:03:27"));
        loadJsonUrl(queryLocationList, new BeanqueryLocationList(start_time, end_time));
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
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(queryLocationList)) {
            ModelqueryLocationList mModelqueryLocationList = (ModelqueryLocationList) json2Model(content, ModelqueryLocationList.class);
            List<LatLng> points = new ArrayList<LatLng>();
            for (int i = 0; i < mModelqueryLocationList.content.size(); i++) {
                CoordinateConverter converter = new CoordinateConverter()
                        .from(CoordinateConverter.CoordType.GPS)
                        .coord(new LatLng(Double.valueOf(mModelqueryLocationList.content.get(i).lat),
                                Double.valueOf(mModelqueryLocationList.content.get(i).lng)));

                LatLng desLatLng = converter.convert();
                points.add(desLatLng);
                if (i == 0) {
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_starting);
                    OverlayOptions option = new MarkerOptions()
                            .position(desLatLng)
                            .icon(bitmap);
                    mBaiduMap.addOverlay(option);
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(desLatLng).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                } else if (i == mModelqueryLocationList.content.size() - 1) {
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_destination);
                    OverlayOptions option = new MarkerOptions()
                            .position(desLatLng)
                            .icon(bitmap);
                    mBaiduMap.addOverlay(option);
                }
            }

            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(10)
                    .color(Color.parseColor("#00A2EE"))
                    .points(points);
            Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
    }
}