package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jyb jyb_96@sina.com on 2017/6/29.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class GridHeader extends RelativeLayout implements View.OnClickListener {
    private TextView tv_left;
    private TextView tv_center;
    private TextView tv_right;
    private OnPositionCallBack mOnPositionCallBack;

    private List<String> units = new ArrayList<>();
    private int position = 0;

    public GridHeader(Context context) {
        this(context, null);
    }

    public GridHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void setOnPositionCallBack(OnPositionCallBack listener) {
        this.mOnPositionCallBack = listener;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_header, this);

        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_center = (TextView) findViewById(R.id.tv_center);


        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_left) {
            if (position <= 0) {
                return;
            }
            position--;

        } else if (v == tv_right) {
            if (position >= units.size() - 1) {
                return;
            }
            position++;
        }

        if (mOnPositionCallBack != null) {
            mOnPositionCallBack.click(position);
        }
        notifyChange();
    }

    public void notifyChange() {
        if (position <= 0) {
            tv_left.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_left_none));
            if (units.size() == 0) {
                tv_right.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_right_none));
            } else {
                tv_right.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_right));
            }
        } else if (position > 0 && position < units.size() - 1) {
            tv_left.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_left));
            tv_right.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_right));
        } else {

            tv_left.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_left));
            tv_right.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.arrow_right_none));
        }

        String center = units.get(position);
        tv_center.setText(center);
    }

    interface OnPositionCallBack {
        void click(int position);
    }
}
