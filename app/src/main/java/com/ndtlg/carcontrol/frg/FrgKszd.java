//
//  FrgKszd
//
//  Created by Administrator on 2018-09-09 15:17:37
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ndtlg.carcontrol.frg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ndtlg.carcontrol.R;
import com.ndtlg.carcontrol.ada.AdaKszd;


public class FrgKszd extends BaseFrg {

    public TextView mTextView_fen;
    public TextView mTextView_fenZ1;
    public com.ndtlg.carcontrol.view.MCircleSeekBar mZJBCircleSeekBar;
    public com.ndtlg.carcontrol.view.ColorfulProgressbar colorful;
    public ImageView mImageView_sao;
    public TextView mImageView_dl;
    public RelativeLayout mRelativeLayout;
    public TextView mTextView_percent;
    public ListView mListView;
    public Handler mHandler = new Handler();
    public Runnable mrun;
    public int max = 100;
    public int min = 1;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_kszd);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_fen = (TextView) findViewById(R.id.mTextView_fen);
        mTextView_fenZ1 = (TextView) findViewById(R.id.mTextView_fenZ1);
        mZJBCircleSeekBar = (com.ndtlg.carcontrol.view.MCircleSeekBar) findViewById(R.id.mZJBCircleSeekBar);
        colorful = (com.ndtlg.carcontrol.view.ColorfulProgressbar) findViewById(R.id.colorful);
        mImageView_sao = (ImageView) findViewById(R.id.mImageView_sao);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLayout);
        mTextView_percent = (TextView) findViewById(R.id.mTextView_percent);
        mListView = (ListView) findViewById(R.id.mListView);


        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgKszd.this.finish();
                Helper.startActivity(getContext(), FrgClzdOver.class, TitleAct.class);
            }
        });
        mZJBCircleSeekBar.setMaxProgress(100);
        colorful.setMaxProgress(100);
    }

    public void loaddata() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, getResources().getDimension(R.dimen.j225dp));
        translateAnimation.setDuration((long) (3000));
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        mImageView_sao.setAnimation(translateAnimation);
        translateAnimation.start();

        mListView.setAdapter(new AdaKszd(getContext(), F.getData()));

        mrun = new Runnable() {
            @Override
            public void run() {
                if (max > 0) {
                    max--;
                    mTextView_fen.setText(max + "");
                    mZJBCircleSeekBar.setProgress(max);
                }
                if (min < 100) {
                    min++;
                    colorful.setProgress(min);
                    mTextView_percent.setText(min + "%");
                } else {
                    FrgKszd.this.finish();
                    Helper.startActivity(getContext(), FrgClzdOver.class, TitleAct.class);
                }
                mHandler.postDelayed(mrun, 1000);
            }
        };
        mHandler.post(mrun);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mrun);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("车辆诊断");
        mHeadlayout.setRightBacgroud(R.drawable.ic_nav_message);
    }
}