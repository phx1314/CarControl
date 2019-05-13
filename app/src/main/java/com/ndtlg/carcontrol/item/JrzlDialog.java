//
//  JrzlDialog
//
//  Created by DELL on 2018-09-04 17:06:39
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ndtlg.carcontrol.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.framewidget.F;
import com.mdx.framework.Frame;
import com.ndtlg.carcontrol.R;

import static com.ndtlg.carcontrol.frg.FrgHome.mModelCz;


public class JrzlDialog extends BaseItem implements RadioGroup.OnCheckedChangeListener {
    public TextView mTextView_title;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public TextView mTextView_wd;
    public SeekBar mSeekBar;
    public TextView mTextView_fs;
    public ImageView mImageView_jian;
    public ImageView mImageView_kuai1;
    public ImageView mImageView_kuai2;
    public ImageView mImageView_kuai3;
    public ImageView mImageView_kuai4;
    public ImageView mImageView_kuai5;
    public ImageView mImageView_jia;
    public TextView mImageView_dl;
    public RadioGroup mRadioGroup;
    public TextView mTextView_wdset;
    public TextView mTextView_fsset;
    public ImageView mImageView_bg;
    public int dang;
    public ImageView mImageView_close;
    public Dialog item;
    public RadioButton mRadioButton3;
    public ImageView mImageView_kuai6;
    public RadioButton mRadioButton4;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_jrzl_dialog, null);
        convertView.setTag(new JrzlDialog(convertView));
        return convertView;
    }

    public JrzlDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mRadioButton1 = (RadioButton) contentview.findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) contentview.findViewById(R.id.mRadioButton2);
        mTextView_wd = (TextView) contentview.findViewById(R.id.mTextView_wd);
        mSeekBar = (SeekBar) contentview.findViewById(R.id.mSeekBar);
        mTextView_fs = (TextView) contentview.findViewById(R.id.mTextView_fs);
        mImageView_jian = (ImageView) contentview.findViewById(R.id.mImageView_jian);
        mImageView_kuai1 = (ImageView) contentview.findViewById(R.id.mImageView_kuai1);
        mImageView_kuai2 = (ImageView) contentview.findViewById(R.id.mImageView_kuai2);
        mImageView_kuai3 = (ImageView) contentview.findViewById(R.id.mImageView_kuai3);
        mImageView_kuai4 = (ImageView) contentview.findViewById(R.id.mImageView_kuai4);
        mImageView_kuai5 = (ImageView) contentview.findViewById(R.id.mImageView_kuai5);
        mImageView_jia = (ImageView) contentview.findViewById(R.id.mImageView_jia);
        mImageView_dl = (TextView) contentview.findViewById(R.id.mImageView_dl);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mTextView_wdset = (TextView) findViewById(R.id.mTextView_wdset);
        mTextView_fsset = (TextView) findViewById(R.id.mTextView_fsset);
        mImageView_bg = (ImageView) findViewById(R.id.mImageView_bg);
        mImageView_close = (ImageView) findViewById(R.id.mImageView_close);
        mRadioButton3 = (RadioButton) findViewById(R.id.mRadioButton3);
        mImageView_kuai6 = (ImageView) findViewById(R.id.mImageView_kuai6);
        mRadioButton4 = (RadioButton) findViewById(R.id.mRadioButton4);
        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRadioButton1.isChecked()) {
                    mModelCz.content.off = 0;
                    mModelCz.content.ac = 0;
                    mModelCz.content.heat = 0;
                    mModelCz.content.auto = 0;
                } else if (mRadioButton2.isChecked()) {
                    mModelCz.content.off = 1;
                    mModelCz.content.ac = 1;
                    mModelCz.content.heat = 0;
                    mModelCz.content.auto = 0;
                } else if (mRadioButton3.isChecked()) {
                    mModelCz.content.off = 1;
                    mModelCz.content.ac = 0;
                    mModelCz.content.heat = 1;
                    mModelCz.content.auto = 0;
                } else if (mRadioButton4.isChecked()) {
                    mModelCz.content.off = 1;
                    mModelCz.content.ac = 0;
                    mModelCz.content.heat = 0;
                    mModelCz.content.auto = 1;
                }
                mModelCz.content.av = dang;
                mModelCz.content.sTemp = Double.valueOf(mTextView_wd.getText().toString().split("℃")[0]);
                mModelCz.isKtCz = true;
                item.dismiss();
                Frame.HANDLES.sentAll("FrgZk", 122, null);
            }
        });
        mRadioGroup.setOnCheckedChangeListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mTextView_wd.setText(F.go1Wei((16 + (double) i / 2)) + "℃");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mImageView_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dang > 1) {
                    --dang;
                    refresshD();
                }
            }
        });
        mImageView_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dang < 5) {
                    ++dang;
                    refresshD();
                }
            }
        });
        mImageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
            }
        });
    }

    public void close() {
        dang = 0;
        mSeekBar.setThumb(context.getResources().getDrawable(R.drawable.ic_bar_arrow_disable));
        mTextView_wdset.setTextColor(context.getResources().getColor(R.color.gray));
        mTextView_fsset.setTextColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai4.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai5.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_jia.setImageResource(R.drawable.ic_add__disable);
        mImageView_jian.setImageResource(R.drawable.ic_minus__disable);
        mImageView_jia.setClickable(false);
        mImageView_jian.setClickable(false);
        mTextView_wd.setVisibility(View.INVISIBLE);
        mTextView_fs.setVisibility(View.INVISIBLE);
    }

    public void open() {
        if (dang == 0) {
            dang = 1;
        }
        mTextView_wdset.setTextColor(context.getResources().getColor(R.color.white));
        mTextView_fsset.setTextColor(context.getResources().getColor(R.color.white));
        mSeekBar.setThumb(context.getResources().getDrawable(R.drawable.ic_bar_arrow));
        mImageView_jia.setImageResource(R.drawable.ic_add);
        mImageView_jian.setImageResource(R.drawable.ic_minus);
        mTextView_wd.setVisibility(View.VISIBLE);
        mTextView_fs.setVisibility(View.VISIBLE);
        mImageView_jia.setClickable(true);
        mImageView_jian.setClickable(true);
        refresshD();
    }

    public void refresshD() {
        mTextView_fs.setText(dang + "档");
        mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai4.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai5.setBackgroundColor(context.getResources().getColor(R.color.gray));
        mImageView_kuai6.setBackgroundColor(context.getResources().getColor(R.color.gray));
        switch (dang) {
            case 0:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
            case 1:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
            case 2:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
            case 3:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai4.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
            case 4:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai4.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai5.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
            case 5:
                mImageView_kuai1.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai2.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai3.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai4.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai5.setBackgroundColor(context.getResources().getColor(R.color.A));
                mImageView_kuai6.setBackgroundColor(context.getResources().getColor(R.color.A));
                break;
        }
    }

    public void set(Dialog item) {
        this.item = item;
        mRadioGroup.setOnCheckedChangeListener(null);
        if (mModelCz.content.off == 0) {
            mRadioGroup.check(R.id.mRadioButton1);
            close();
        } else if (mModelCz.content.ac > 0) {
            mRadioGroup.check(R.id.mRadioButton2);
        } else if (mModelCz.content.heat > 0) {
            mRadioGroup.check(R.id.mRadioButton3);
        } else if (mModelCz.content.auto > 0) {
            mRadioGroup.check(R.id.mRadioButton4);
        }
        mRadioGroup.setOnCheckedChangeListener(this);
        mSeekBar.setProgress((int) (((mModelCz.content.sTemp - 16) <= 0 ? 0 : (mModelCz.content.sTemp - 16)) * 2));
        mTextView_wd.setText(mModelCz.content.sTemp + "℃");
        dang = mModelCz.content.av;
        refresshD();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.mRadioButton1) {
            close();
        } else if (i == R.id.mRadioButton2) {
            open();
        } else if (i == R.id.mRadioButton3) {
            open();
        } else if (i == R.id.mRadioButton4) {
            open();
        }
    }
}