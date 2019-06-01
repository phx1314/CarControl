//
//  FrgJsxw
//
//  Created by Administrator on 2018-09-09 10:08:52
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.framewidget.newMenu.DateDfSelectDialog;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.ada.AdaJsxw;
import com.ndtlg.carcontrol.bean.BeanqueryDiagInfoList;
import com.ndtlg.carcontrol.bean.BeanqueryDiagInfoList1;
import com.ndtlg.carcontrol.bean.BeanqueryDiagInfoList_e;
import com.ndtlg.carcontrol.bean.BeanqueryDiagInfoList_s;
import com.ndtlg.carcontrol.model.ModelqueryDiagInfoList;

import java.util.ArrayList;

import static com.ndtlg.carcontrol.F.json2Model;
import static com.ndtlg.carcontrol.F.queryDiagInfoList;


public class FrgJsxw extends BaseFrg {

    public AbPullListView mAbPullListView;
    public TextView mImageView_dl;
    public TextView mTextView_x1;
    public TextView mTextView_x2;
    public DateDfSelectDialog mDateSelectDialog1;
    public DateDfSelectDialog mDateSelectDialog2;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_jsxw);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        mTextView_x1 = (TextView) findViewById(R.id.mTextView_x1);
        mTextView_x2 = (TextView) findViewById(R.id.mTextView_x2);
        mDateSelectDialog1 = new DateDfSelectDialog(getContext(), null, 3);
        mDateSelectDialog2 = new DateDfSelectDialog(getContext(), null, 3);
        mTextView_x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDateSelectDialog1.clear();
                mDateSelectDialog1.show();
            }
        });
        mTextView_x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDateSelectDialog2.clear();
                mDateSelectDialog2.show();
            }
        });
        mDateSelectDialog1.setOnSelected(new DateDfSelectDialog.OnSelected() {
            @Override
            public void onSelected(Dialog dia, String selected) {
                mTextView_x1.setText(selected);
            }
        });
        mDateSelectDialog2.setOnSelected(new DateDfSelectDialog.OnSelected() {
            @Override
            public void onSelected(Dialog dia, String selected) {
                mTextView_x2.setText(selected);
            }
        });
        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mTextView_x1.getText().toString().trim()) && !TextUtils.isEmpty(mTextView_x2.getText().toString().trim())) {
                    BeanqueryDiagInfoList mBeanqueryDiagInfoList = new BeanqueryDiagInfoList(mTextView_x1.getText().toString(), mTextView_x2.getText().toString());
                    mAbPullListView.setJsonApiLoadParams(queryDiagInfoList, mBeanqueryDiagInfoList);
                } else if (TextUtils.isEmpty(mTextView_x1.getText().toString().trim()) && !TextUtils.isEmpty(mTextView_x2.getText().toString().trim())) {
                    BeanqueryDiagInfoList_e mBeanqueryDiagInfoList = new BeanqueryDiagInfoList_e(mTextView_x2.getText().toString());
                    mAbPullListView.setJsonApiLoadParams(queryDiagInfoList, mBeanqueryDiagInfoList);
                } else if (!TextUtils.isEmpty(mTextView_x1.getText().toString().trim()) && TextUtils.isEmpty(mTextView_x2.getText().toString().trim())) {
                    BeanqueryDiagInfoList_s mBeanqueryDiagInfoList = new BeanqueryDiagInfoList_s(mTextView_x1.getText().toString());
                    mAbPullListView.setJsonApiLoadParams(queryDiagInfoList, mBeanqueryDiagInfoList);
                } else {
                    BeanqueryDiagInfoList1 mBeanqueryDiagInfoList = new BeanqueryDiagInfoList1();
                    mAbPullListView.setJsonApiLoadParams(queryDiagInfoList, mBeanqueryDiagInfoList);
                }
            }
        });
    }

    public void loaddata() {
        BeanqueryDiagInfoList1 mBeanqueryDiagInfoList = new BeanqueryDiagInfoList1();
        mAbPullListView.setJsonApiLoadParams(queryDiagInfoList, mBeanqueryDiagInfoList);
        mAbPullListView.setAdapter(new AdaJsxw(getContext(), new ArrayList<ModelqueryDiagInfoList.TripListBean>()));
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelqueryDiagInfoList mModelWDJK = (ModelqueryDiagInfoList) json2Model(content, ModelqueryDiagInfoList.class);
                return new AdaJsxw(getContext(), mModelWDJK.tripList);
            }
        });


    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("驾驶行为分析");
    }
}