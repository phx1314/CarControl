package com.ndtlg.carcontrol.pop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.ndtlg.carcontrol.R;


public class PopShowLeftDm implements OnClickListener {

    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowLeftDm(Context context, View view) {
        super();
        this.view = view;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_dm_left_pop, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextView_1 = (TextView) popview
                .findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) popview
                .findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) popview
                .findViewById(R.id.mTextView_3);

        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);
        mTextView_1.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_2.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_3.setOnClickListener(Helper.delayClickLitener(this));
    }


    @SuppressLint("NewApi")
    public void show() {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        popwindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth(), location[1]);
    }

    public void hide() {
        popwindow.dismiss();
    }

    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onClick(View arg0) {
        hide();
        switch (arg0.getId()) {
            case R.id.mTextView_1:
                Frame.HANDLES.sentAll("FrgZk", 0, 2);
                break;
            case R.id.mTextView_2:
                Frame.HANDLES.sentAll("FrgZk", 0, 1);
                break;
            case R.id.mTextView_3:
                Frame.HANDLES.sentAll("FrgZk", 0, 0);
                break;
        }
    }

}
