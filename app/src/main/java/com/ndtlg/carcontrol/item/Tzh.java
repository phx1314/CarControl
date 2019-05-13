//
//  Tzh
//
//  Created by DELL on 2018-09-07 13:23:16
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
import android.widget.TextView;



public class Tzh extends BaseItem{
    public TextView mTextView_title;
    public TextView mTextView_time;
    public TextView mTextView_content;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_tzh,null);
	     convertView.setTag( new Tzh(convertView));
	     return convertView;
	}

	public Tzh(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mTextView_title=(TextView)contentview.findViewById(R.id.mTextView_title);
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_content=(TextView)contentview.findViewById(R.id.mTextView_content);


    }

    public void set(String item){

    }
    
    

}