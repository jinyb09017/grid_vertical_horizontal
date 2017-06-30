package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.loopeer.android.librarys.bean.ViewBean;

import java.util.ArrayList;
import java.util.List;

public class CustomTableView extends View {
    private static final String TAG = "CustomTableView";

    private Paint mPaintTextNormal;
    private Paint mPaintItemBg;

    private int mTextUnableColor;
    private int mTextNormalColor;
    private int mTextSelectColor;
    private int mItemBgNormalColor;
    private float mTextNormal;

    private int mItemHeight;
    private int mItemWidth;
    private int mItemMargin;

    private OnPositionDataClickListener mOnPositionDataClickListener;
    List<List<ViewBean>> beans = new ArrayList<>();

    private Rect rect = new Rect(); // it just for store the width and height;

    public CustomTableView(Context context) {
        this(context, null);
    }

    public CustomTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.table_gap_color));
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initData();
        initPaint();

        //init data beans;
        initViewBean(rect);
    }

    private void initData() {
        mItemHeight = getResources().getDimensionPixelSize(R.dimen.table_item_height);
        mItemWidth = getResources().getDimensionPixelSize(R.dimen.table_item_width);
        mItemMargin = getResources().getDimensionPixelSize(R.dimen.table_item_margin);
    }

    private void initPaint() {
        mTextNormalColor = ContextCompat.getColor(getContext(), R.color.table_text_normal_color);
        mTextUnableColor = ContextCompat.getColor(getContext(), R.color.table_text_unable_color);
        mTextSelectColor = ContextCompat.getColor(getContext(), R.color.table_text_select_color);
        mItemBgNormalColor = ContextCompat.getColor(getContext(), R.color.table_item_bg_normal_color);
        mTextNormal = getResources().getDimension(R.dimen.table_default_text_size);

        mPaintTextNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTextNormal.setColor(mTextNormalColor);
        mPaintTextNormal.setTextSize(mTextNormal);

        mPaintItemBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintItemBg.setColor(mItemBgNormalColor);
        mPaintItemBg.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(rect.right, rect.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawItem(canvas);

    }

    /**
     * 1、confirm the coordinate of every viewBena
     * 2、get maxWidth and maxHeight;
     *
     * @param rect
     */
    public void initViewBean(Rect rect) {
        int maxHeight = 0;
        int maxWidth = 0;


        int row = beans.size();

        for (int rowIndex = 0; rowIndex < row; rowIndex++) {

            int column = beans.get(rowIndex).size();
            List<ViewBean> rowBeans = beans.get(rowIndex);

            for (int columnIndex = 0; columnIndex < column; columnIndex++) {

                ViewBean viewBean = rowBeans.get(columnIndex);

                int allUnit = (int) (viewBean.getRightTopUnit() - viewBean.getWidth());//距离左边的正常单元格数，用于计算itemMargin数目
                int left = allUnit * (mItemWidth + mItemMargin) + mItemMargin + convert(viewBean.getRightTopUnit() - viewBean.getWidth() - allUnit, mItemWidth);//整数单元 + 拆分单元

                float right;
                if (viewBean.getWidth() > 1) {
                    right = left + convert(viewBean.getWidth(), mItemWidth) + convert((viewBean.getWidth() - 1), mItemMargin);
                } else {
                    right = left + convert(viewBean.getWidth(), mItemWidth);
                }

                float top = rowIndex * (mItemHeight) + mItemMargin * (rowIndex + 1);
                float bottom = top + mItemHeight * viewBean.getHeight() + mItemMargin * (viewBean.getHeight() - 1);

                viewBean.left = left;
                viewBean.right = right;
                viewBean.top = top;
                viewBean.bottom = bottom;

                if (maxHeight < viewBean.bottom) {
                    maxHeight = (int) (viewBean.bottom + 0.5);
                }

                if (maxWidth < viewBean.right) {
                    maxWidth = (int) (viewBean.right + 0.5);
                }


            }
        }

        rect.right = maxWidth;
        rect.bottom = maxHeight + mItemMargin;
    }


    private void drawItem(Canvas canvas) {

        int row = beans.size();

        for (int rowIndex = 0; rowIndex < row; rowIndex++) {

            int column = beans.get(rowIndex).size();
            List<ViewBean> rowBeans = beans.get(rowIndex);

            for (int columnIndex = 0; columnIndex < column; columnIndex++) {

                ViewBean viewBean = rowBeans.get(columnIndex);


                float left = viewBean.left;
                float right = viewBean.right;
                float top = viewBean.top;
                float bottom = viewBean.bottom;


                //这里画大背景
                mPaintItemBg.setColor(Color.parseColor(viewBean.getState().getColor()));
                canvas.drawRect(left, top, right, bottom, mPaintItemBg);
                mPaintItemBg.setColor(Color.parseColor(viewBean.getState().getBorder()));
                //这里画border
                changePaint(mPaintItemBg, viewBean, canvas);

                String content = viewBean.getText();
                float viewWidth = right - left;
                float viewHeight = bottom - top;
                Paint.FontMetrics fontMetrics = mPaintTextNormal.getFontMetrics();
                float fontHeight = fontMetrics.bottom - fontMetrics.top;
                float textWidth = mPaintTextNormal.measureText(content);
                float y = top + viewHeight - (viewHeight - fontHeight) / 2 - fontMetrics.bottom;
                float x = left + viewWidth / 2 - textWidth / 2;

                canvas.drawText(content, x, y, mPaintTextNormal);
            }
        }
    }

    //画四条边线
    private void changePaint(Paint mPaintItemBg, ViewBean viewBean, Canvas canvas) {
        canvas.drawLine(viewBean.left, viewBean.top, viewBean.right, viewBean.top, mPaintItemBg);//上边线
        canvas.drawLine(viewBean.left, viewBean.bottom, viewBean.right, viewBean.bottom, mPaintItemBg);//下边线
        canvas.drawLine(viewBean.left, viewBean.top, viewBean.left, viewBean.bottom, mPaintItemBg);//左边线
        canvas.drawLine(viewBean.right, viewBean.top, viewBean.right, viewBean.bottom, mPaintItemBg);//右边线

    }


    //将获得.3，.6，.9等小数转换成精度更高的1/3,2/3的小数
    private int convert(double value, int unit) {
        int intPart = (int) value;
        double doublePart = value - intPart;
        int result = (int) (value * unit);
        if (Math.abs(doublePart - 0.33) < 0.0001) {
            result = intPart * unit + unit / 3;
        } else if (Math.abs(doublePart - 0.66) < 0.0001) {
            result = intPart * unit + 2 * unit / 3;
        }
        return result;
    }


    public void setDatas(List<List<ViewBean>> data) {
        beans = data;
        initViewBean(rect);
        requestLayout();//这里需要重新测量
    }


    public void setOnPositionDataClickListener(OnPositionDataClickListener listener) {
        mOnPositionDataClickListener = listener;
    }


    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                ViewBean viewBean = getViewBean(event.getX(), event.getY());

                if (viewBean == null) {
                    return true;
                }

                if (mOnPositionDataClickListener != null) {
                    mOnPositionDataClickListener.onPositionClick(viewBean);
                }
                break;
        }
        return true;
    }


    /**
     * 查找响应的单元格
     *
     * @param x
     * @param y
     * @return
     */
    public ViewBean getViewBean(float x, float y) {
        int row = beans.size();
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {

            int column = beans.get(rowIndex).size();
            List<ViewBean> rowBeans = beans.get(rowIndex);

            for (int columnIndex = 0; columnIndex < column; columnIndex++) {

                ViewBean viewBean = rowBeans.get(columnIndex);

                boolean hit = x > viewBean.left && x < viewBean.right && y > viewBean.top && y < viewBean.bottom;
                if (hit) {
                    return viewBean;
                }

            }
        }

        return null;
    }

    public interface OnPositionDataClickListener {
        void onPositionClick(ViewBean viewBean);

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


    public void setTextNormalColor(int color) {
        mTextNormalColor = color;
        invalidate();
    }

    public void setTextSelectColor(int color) {
        mTextSelectColor = color;
        invalidate();
    }

    public void setTextUnableColor(int color) {
        mTextUnableColor = color;
        invalidate();
    }

    public void setUpAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) return;
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollTableView, defStyleAttr, 0);
        if (a == null) return;

        setItemHeight(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemHeight, mItemHeight));
        setItemWidth(a.getDimensionPixelSize(R.styleable.ScrollTableView_itemWidth, mItemWidth));
        setItemMargin(a.getDimensionPixelSize(R.styleable.ScrollTableView_dataMargin, mItemMargin));
        setTextNormalColor(a.getColor(R.styleable.ScrollTableView_textNormalColor, mTextNormalColor));
        setTextSelectColor(a.getColor(R.styleable.ScrollTableView_textSelectColor, mTextSelectColor));
        setTextUnableColor(a.getColor(R.styleable.ScrollTableView_textUnableColor, mTextUnableColor));

    }
}
