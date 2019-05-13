//
//  ClzdOver
//
//  Created by Administrator on 2018-09-09 14:49:03
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



public class ClzdOver extends BaseItem{
    public ImageView mImageView_l;
    public TextView mTextView_title;
    public TextView mTextView_wt;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_clzd_over,null);
	     convertView.setTag( new ClzdOver(convertView));
	     return convertView;
	}

	public ClzdOver(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mImageView_l=(ImageView)contentview.findViewById(R.id.mImageView_l);
        mTextView_title=(TextView)contentview.findViewById(R.id.mTextView_title);
        mTextView_wt=(TextView)contentview.findViewById(R.id.mTextView_wt);


    }

    public void set(String item){

    }
    
    

}