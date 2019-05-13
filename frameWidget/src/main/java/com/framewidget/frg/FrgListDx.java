//
//  FrgCxFaxian
//
//  Created by Administrator on 2015-04-21 10:29:49
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.framewidget.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.framewidget.R;
import com.framewidget.ada.AdaXz;
import com.framewidget.model.ModelDx;
import com.mdx.framework.Frame;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;

import java.util.ArrayList;
import java.util.List;

public class FrgListDx extends BaseFrg {

    public MPageListView mMPageListView;
    public String from;
    public String title;
    public int type;
    public List<String> data = new ArrayList<String>();
    public AdaXz mAdaXz;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_cx_sex_list);
        from = getActivity().getIntent().getStringExtra("from");
        title = getActivity().getIntent().getStringExtra("title");
        type = getActivity().getIntent().getIntExtra("type", 0);
        data = (List<String>) getActivity().getIntent().getSerializableExtra("data");
        initView();
        loaddata();
    }

    private void initView() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

    }

    public void loaddata() {
        List mModelDxs = new ArrayList();
        for (String str : data) {
            ModelDx mModelDx = new ModelDx();
            mModelDx.string = str;
            mModelDxs.add(mModelDx);
        }
        mAdaXz = new AdaXz(getContext(), mModelDxs);
        mMPageListView.setAdapter(mAdaXz);
    }

    @Override
    public void onClick(View arg0) {
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(title);
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "";
                for (ModelDx mModelDx : mAdaXz.getList()) {
                    if (mModelDx.isChecked) {
                        string += mModelDx.string + ",";
                    }
                }
                if (string.length() > 0) {
                    string = string.substring(0, string.length() - 1);
                }
                Frame.HANDLES.sentAll(from, type, string);
                FrgListDx.this.finish();
            }
        });
    }

}