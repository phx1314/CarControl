//
//  Zdjl
//
//  Created by DELL on 2018-09-11 10:05:29
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



public class Zdjl extends BaseItem{
    public ImageView mImageView_l1;
    public ImageView mImageView_l2;
    public LinearLayout mLinearLayout1;
    public TextView mTextView_time;
    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public TextView mTextView_count;
    public TextView mTextView_wt;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_zdjl,null);
	     convertView.setTag( new Zdjl(convertView));
	     return convertView;
	}

	public Zdjl(View view){
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
        mTextView_fen=(TextView)contentview.findViewById(R.id.mTextView_fen);
        mTextView_fenZ1=(TextView)contentview.findViewById(R.id.mTextView_fenZ1);
        mTextView_count=(TextView)contentview.findViewById(R.id.mTextView_count);
        mTextView_wt=(TextView)contentview.findViewById(R.id.mTextView_wt);


    }

    public void set(String item){

    }
    
    

}