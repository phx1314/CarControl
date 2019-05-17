//
//  FrgQt
//
//  Created by DELL on 2019-05-13 18:38:06
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.frg;
import android.os.Bundle;

import com.ndtlg.carcontrol.R;

import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;



public class FrgQt extends BaseFrg{

    public LinearLayout mLinearLayout_1;
    public ImageView mImageView1;
    public TextView mTextView_1;
    public TextView mTextView_state;
    public TextView mImageView_cz;
    public LinearLayout mLinearLayout_3;
    public ImageView mImageView3;
    public TextView mTextView_3;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_qt);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mLinearLayout_1=(LinearLayout)findViewById(R.id.mLinearLayout_1);
        mImageView1=(ImageView)findViewById(R.id.mImageView1);
        mTextView_1=(TextView)findViewById(R.id.mTextView_1);
        mTextView_state=(TextView)findViewById(R.id.mTextView_state);
        mImageView_cz=(TextView)findViewById(R.id.mImageView_cz);
        mLinearLayout_3=(LinearLayout)findViewById(R.id.mLinearLayout_3);
        mImageView3=(ImageView)findViewById(R.id.mImageView3);
        mTextView_3=(TextView)findViewById(R.id.mTextView_3);


    }
    
    public void loaddata(){

    }
    
   
 
}