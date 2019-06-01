//
//  Jsxw
//
//  Created by DELL on 2018-09-11 10:31:32
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.ada.AdaJsxw;
import com.ndtlg.carcontrol.model.ModelqueryDiagInfoList;


public class Jsxw extends BaseItem {
    public ImageView mImageView_l1;
    public ImageView mImageView_l2;
    public LinearLayout mLinearLayout1;
    public TextView mTextView_time;
    public TextView mTextView_start;
    public TextView mTextView_end;
    public TextView mTextView_xssj;
    public TextView mTextView_xslc;
    public TextView mTextView_sd;
    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public TextView mTextView_csh1;
    public TextView mTextView_csh2;
    public LatLng mLatLng_s;
    public LatLng mLatLng_e;
    public AdaJsxw mAdaJsxw;
    public ModelqueryDiagInfoList.TripListBean item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_jsxw, null);
        convertView.setTag(new Jsxw(convertView));
        return convertView;
    }

    public Jsxw(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView_l1 = (ImageView) contentview.findViewById(R.id.mImageView_l1);
        mImageView_l2 = (ImageView) contentview.findViewById(R.id.mImageView_l2);
        mLinearLayout1 = (LinearLayout) contentview.findViewById(R.id.mLinearLayout1);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_start = (TextView) contentview.findViewById(R.id.mTextView_start);
        mTextView_end = (TextView) contentview.findViewById(R.id.mTextView_end);
        mTextView_xssj = (TextView) contentview.findViewById(R.id.mTextView_xssj);
        mTextView_xslc = (TextView) contentview.findViewById(R.id.mTextView_xslc);
        mTextView_sd = (TextView) contentview.findViewById(R.id.mTextView_sd);
        mTextView_fen = (TextView) contentview.findViewById(R.id.mTextView_fen);
        mTextView_fenZ1 = (TextView) contentview.findViewById(R.id.mTextView_fenZ1);
        mTextView_csh1 = (TextView) contentview.findViewById(R.id.mTextView_csh1);
        mTextView_csh2 = (TextView) contentview.findViewById(R.id.mTextView_csh2);


    }

    public void set(final ModelqueryDiagInfoList.TripListBean item, final AdaJsxw mAdaJsxw) {
        this.mAdaJsxw = mAdaJsxw;
        this.item = item;
        CoordinateConverter converter_s = new CoordinateConverter()
                .from(CoordinateConverter.CoordType.GPS)
                .coord(new LatLng(Double.valueOf(item.startLocation.lat),
                        Double.valueOf((item.startLocation.lng))));

        mLatLng_s = converter_s.convert();
        CoordinateConverter converter_e = new CoordinateConverter()
                .from(CoordinateConverter.CoordType.GPS)
                .coord(new LatLng(Double.valueOf(item.endLocation.lat),
                        Double.valueOf((item.endLocation.lng))));

        mLatLng_e = converter_e.convert();
        mTextView_time.setText(item.startTime + "");
        mTextView_start.setText(item.startLocation.address + "");
        mTextView_end.setText(item.endLocation.address + "");
        mTextView_xssj.setText(item.driveTime + "");
        mTextView_xslc.setText(item.driveMileage + "");
        mTextView_sd.setText(item.socUse + "");
        mTextView_csh1.setText(item.totalAccelerateCount + "");
        mTextView_csh2.setText(item.totalDecelerateCount + "");
        if (TextUtils.isEmpty(item.startLocation.address)) {
            final GeoCoder mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                    item.startLocation.address = reverseGeoCodeResult.getAddress();
                    mCoder.destroy();
                    mAdaJsxw.notifyDataSetChanged();
                }
            });
            mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(mLatLng_s)
            );
        }
        if (TextUtils.isEmpty(item.endLocation.address)) {
            final GeoCoder mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                    item.endLocation.address = reverseGeoCodeResult.getAddress();
                    mCoder.destroy();
                    mAdaJsxw.notifyDataSetChanged();
                }
            });
            mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(mLatLng_e)
            );
        }
    }


}