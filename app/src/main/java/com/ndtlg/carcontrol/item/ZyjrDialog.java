//
//  ZyjrDialog
//
//  Created by DELL on 2019-05-13 18:52:26
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ndtlg.carcontrol.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ndtlg.carcontrol.R;



public class ZyjrDialog extends BaseItem{
    public TextView mTextView_title;
    public ImageView mImageView_close;
    public TextView mTextView_jsz;
    public ImageView mImageView_jian_1;
    public ImageView mImageView_kuai_1;
    public ImageView mImageView_kuai_2;
    public ImageView mImageView_kuai_3;
    public ImageView mImageView_kuai_4;
    public ImageView mImageView_jia_1;
    public TextView mTextView_fsset;
    public TextView mTextView_fs;
    public ImageView mImageView_jian;
    public ImageView mImageView_kuai1;
    public ImageView mImageView_kuai2;
    public ImageView mImageView_kuai3;
    public ImageView mImageView_kuai4;
    public ImageView mImageView_jia;
    public TextView mImageView_dl;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_zyjr_dialog,null);
	     convertView.setTag( new ZyjrDialog(convertView));
	     return convertView;
	}

	public ZyjrDialog(View view){
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
        mImageView_close=(ImageView)contentview.findViewById(R.id.mImageView_close);
        mTextView_jsz=(TextView)contentview.findViewById(R.id.mTextView_jsz);
        mImageView_jian_1=(ImageView)contentview.findViewById(R.id.mImageView_jian_1);
        mImageView_kuai_1=(ImageView)contentview.findViewById(R.id.mImageView_kuai_1);
        mImageView_kuai_2=(ImageView)contentview.findViewById(R.id.mImageView_kuai_2);
        mImageView_kuai_3=(ImageView)contentview.findViewById(R.id.mImageView_kuai_3);
        mImageView_kuai_4=(ImageView)contentview.findViewById(R.id.mImageView_kuai_4);
        mImageView_jia_1=(ImageView)contentview.findViewById(R.id.mImageView_jia_1);
        mTextView_fsset=(TextView)contentview.findViewById(R.id.mTextView_fsset);
        mTextView_fs=(TextView)contentview.findViewById(R.id.mTextView_fs);
        mImageView_jian=(ImageView)contentview.findViewById(R.id.mImageView_jian);
        mImageView_kuai1=(ImageView)contentview.findViewById(R.id.mImageView_kuai1);
        mImageView_kuai2=(ImageView)contentview.findViewById(R.id.mImageView_kuai2);
        mImageView_kuai3=(ImageView)contentview.findViewById(R.id.mImageView_kuai3);
        mImageView_kuai4=(ImageView)contentview.findViewById(R.id.mImageView_kuai4);
        mImageView_jia=(ImageView)contentview.findViewById(R.id.mImageView_jia);
        mImageView_dl=(TextView)contentview.findViewById(R.id.mImageView_dl);


    }

    public void set(String item){

    }
    
    

}