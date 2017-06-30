package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LeftTitleView extends View {

    private Paint mPaintTextNormal;
    private Paint mPaintItemIndicatorCircle;
    private Paint mPaintItemIndicatorLine;

    private int mTextLeftTitleColor;
    private int mItemIndicatorColor;
    private float mTextTitleSize;

    private int mItemHeight;
    private int mItemWidth;
    private int mItemMargin;
    private int row;
//    private int mItemIndicatorCircleRadius;
//    private int mItemIndicatorLineWidth;

    ArrayList<String> titles;

    public LeftTitleView(Context context) {
        this(context, null);
    }

    public LeftTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.table_bg));
        initData();
        initPaint();
        titles = new ArrayList<>();
    }

    private void initData() {
        mItemHeight = getResources().getDimensionPixelSize(R.dimen.table_item_height);
        mItemWidth = getResources().getDimensionPixelSize(R.dimen.table_item_width);
        mItemMargin = getResources().getDimensionPixelSize(R.dimen.table_item_margin);
//        mItemIndicatorCircleRadius = getResources().getDimensionPixelSize(R.dimen.table_header_circle_radius);
//        mItemIndicatorLineWidth = getResources().getDimensionPixelSize(R.dimen.table_header_indicator_width);
    }

    private void initPaint() {
        mTextLeftTitleColor = ContextCompat.getColor(getContext(), R.color.table_text_secondary_color);
        mItemIndicatorColor = ContextCompat.getColor(getContext(), R.color.table_diver_color);
        mTextTitleSize = getResources().getDimension(R.dimen.table_default_title_size);

        mPaintTextNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTextNormal.setColor(mTextLeftTitleColor);
        mPaintTextNormal.setTextSize(mTextTitleSize);

        mPaintItemIndicatorCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintItemIndicatorCircle.setColor(mItemIndicatorColor);
        mPaintItemIndicatorCircle.setStyle(Paint.Style.FILL);


        mPaintItemIndicatorLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintItemIndicatorLine.setColor(mItemIndicatorColor);
        mPaintItemIndicatorLine.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        //这里加1，是为了与title上的一根汇合
        mItemWidth = sizeWidth+1;
        setMeasuredDimension(mItemWidth, row * mItemHeight + (row + 1) * mItemMargin);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawItem(canvas);
    }

    private void drawItem(Canvas canvas) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            String content = titles.get(rowIndex);
            Paint.FontMetrics fontMetrics = mPaintTextNormal.getFontMetrics();
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            float textWidth = mPaintTextNormal.measureText(content);

            float top = mItemHeight * rowIndex + mItemMargin * (rowIndex + 1);
            float y = top + mItemHeight - (mItemHeight - fontHeight) / 2 - fontMetrics.bottom;
//            float y = rowIndex * (mItemHeight + mItemMargin) + mItemHeight - (mItemHeight - fontHeight) / 2 - fontMetrics.bottom - mItemHeight / 2 + getResources().getDimension(R.dimen.table_default_margin_top) - mItemMargin / 2 + mItemMargin;

            float x = mItemWidth / 2 - textWidth / 2;

            canvas.drawText(content, x, y, mPaintTextNormal);

//            canvas.drawCircle(mItemWidth - mItemIndicatorLineWidth - mItemIndicatorCircleRadius,
//                    rowIndex * (mItemHeight + mItemMargin) + getResources().getDimension(R.dimen.table_default_margin_top) - mItemMargin / 2 + mItemMargin,
//                    mItemIndicatorCircleRadius, mPaintItemIndicatorCircle);
//
//            canvas.drawRect(mItemWidth - mItemIndicatorLineWidth,
//                    rowIndex * (mItemHeight + mItemMargin) + getResources().getDimension(R.dimen.table_default_margin_top) - mItemMargin / 2 - mItemMargin / 2 + mItemMargin,
//                    mItemWidth,
//                    rowIndex * (mItemHeight + mItemMargin) + getResources().getDimension(R.dimen.table_default_margin_top) - mItemMargin / 2 + mItemMargin / 2 + mItemMargin,
//                    mPaintItemIndicatorLine);


        }

        //这里画右边的线
        int startX, startY, stopX, stopY;
        startX = mItemWidth - 1;
        stopX = mItemWidth;
        startY = 0  ;
        stopY = getHeight();

        canvas.drawLine(startX,startY,stopX,stopY,mPaintItemIndicatorLine);
    }

    public void updateTitles(List<String> data) {
        titles.clear();
        titles.addAll(data);
        updateView();
    }

    private void updateView() {
        invalidate();
        row = titles.size();
    }

    public void setItemHeight(int height) {
        mItemHeight = height;
        invalidate();
    }

    public void setItemWidth(int width) {
        mItemWidth = width;
        invalidate();
    }

    public void setItemMargin(int margin) {
        mItemMargin = margin;
        invalidate();
    }

    public void setTextLeftTitleColor(int color) {
        mTextLeftTitleColor = color;
        mPaintTextNormal.setColor(mTextLeftTitleColor);
        invalidate();
    }

    public void setItemIndicatorColor(int color) {
        mItemIndicatorColor = color;
        mPaintItemIndicatorLine.setColor(mItemIndicatorColor);
        mPaintItemIndicatorCircle.setColor(mItemIndicatorColor);
        invalidate();
    }
//
//    public void setIndicatorCircleRadius(int radius) {
//        mItemIndicatorCircleRadius = radius;
//        invalidate();
//    }
//
//    public void setItemIndicatorLineWidth(int width) {
//        mItemIndicatorLineWidth = width;
//        invalidate();
//    }

    public void setPaintTextNormalSize(float size) {
        mTextTitleSize = size;
        mPaintTextNormal.setTextSize(mTextTitleSize);
        invalidate();
    }

    public void setUpAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) return;
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollTableView, defStyleAttr, 0);
        if (a == null) return;

        setItemHeight(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemHeight, mItemHeight));
        setItemWidth(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemWidth, mItemWidth));
        setItemMargin(a.getDimensionPixelSize(R.styleable.ScrollTableView_dataMargin, mItemMargin));
        setTextLeftTitleColor(a.getColor(R.styleable.ScrollTableView_textLeftTitleColor, mTextLeftTitleColor));
        setItemIndicatorColor(a.getColor(R.styleable.ScrollTableView_itemIndicatorColor, mItemIndicatorColor));
//        setIndicatorCircleRadius(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemIndicatorCircleRadius, mItemIndicatorCircleRadius));
//        setItemIndicatorLineWidth(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemIndicatorLineWidth, mItemIndicatorLineWidth));
        setPaintTextNormalSize(a.getDimension(R.styleable.ScrollTableView_textTitleSize, mTextTitleSize));

    }
}
