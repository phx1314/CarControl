//
//  Jsxw
//
//  Created by DELL on 2018-09-11 10:31:32
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.item;

import com.ndtlg.carcontrol.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Jsxw extends BaseItem{
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
    public TextView mTextView_csh3;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_jsxw,null);
	     convertView.setTag( new Jsxw(convertView));
	     return convertView;
	}

	public Jsxw(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mImageView_l1=(ImageView)contentview.findViewById(R.id.mImageView_l1);
        mImageView_l2=(ImageView)contentview.findViewById(R.id.mImageView_l2);
        mLinearLayout1=(LinearLayout)contentview.findViewById(R.id.mLinearLayout1);
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_start=(TextView)contentview.findViewById(R.id.mTextView_start);
        mTextView_end=(TextView)contentview.findViewById(R.id.mTextView_end);
        mTextView_xssj=(TextView)contentview.findViewById(R.id.mTextView_xssj);
        mTextView_xslc=(TextView)contentview.findViewById(R.id.mTextView_xslc);
        mTextView_sd=(TextView)contentview.findViewById(R.id.mTextView_sd);
        mTextView_fen=(TextView)contentview.findViewById(R.id.mTextView_fen);
        mTextView_fenZ1=(TextView)contentview.findViewById(R.id.mTextView_fenZ1);
        mTextView_csh1=(TextView)contentview.findViewById(R.id.mTextView_csh1);
        mTextView_csh2=(TextView)contentview.findViewById(R.id.mTextView_csh2);
        mTextView_csh3=(TextView)contentview.findViewById(R.id.mTextView_csh3);


    }

    public void set(String item){

    }
    
    

}