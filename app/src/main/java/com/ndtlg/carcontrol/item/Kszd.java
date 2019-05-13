//
//  Kszd
//
//  Created by Administrator on 2018-09-09 18:44:38
//  Copyright (c) Administrator All rights reserved.


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
import android.widget.TextView;



public class Kszd extends BaseItem{
    public ImageView mImageView;
    public TextView mTextView;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_kszd,null);
	     convertView.setTag( new Kszd(convertView));
	     return convertView;
	}

	public Kszd(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mImageView=(ImageView)contentview.findViewById(R.id.mImageView);
        mTextView=(TextView)contentview.findViewById(R.id.mTextView);


    }

    public void set(String item){

    }
    
    

}