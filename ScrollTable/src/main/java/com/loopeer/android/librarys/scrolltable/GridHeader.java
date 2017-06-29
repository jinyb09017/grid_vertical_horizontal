package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * @author jyb jyb_96@sina.com on 2017/6/29.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class GridHeader extends RelativeLayout {
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

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_header, this);
//        setUpView();
//        setUpData();
//        selectPositions = new ArrayList<>();


//        if (attrs == null) return;
//        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollTableView, defStyleAttr, 0);
//        if (a == null) return;
//
//        headerVertical.setUpAttrs(context, attrs, defStyleAttr);
//        headerHorizontal.setUpAttrs(context, attrs, defStyleAttr);
//        contentView.setUpAttrs(context, attrs, defStyleAttr);

//        a.recycle();
    }

}
