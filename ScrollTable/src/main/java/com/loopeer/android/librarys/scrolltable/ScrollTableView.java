package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopeer.android.librarys.bean.ViewBean;

import java.util.ArrayList;

public class ScrollTableView extends LinearLayout implements CustomTableView.OnPositionDataClickListener {

    private LeftTitleView headerVertical;
    private CustomTableView contentView;
    private ArrayList<String> topTitles;
    private ArrayList<String> leftTitles;
    private ArrayList<ArrayList<String>> datas;

    public ScrollTableView(Context context) {
        this(context, null);
    }

    public ScrollTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_container, this);
        setUpView();
        setUpData();


        if (attrs == null) return;
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollTableView, defStyleAttr, 0);
        if (a == null) return;

        headerVertical.setUpAttrs(context, attrs, defStyleAttr);
        contentView.setUpAttrs(context, attrs, defStyleAttr);

        a.recycle();
    }

    private void setUpView() {
        headerVertical = (LeftTitleView) findViewById(R.id.header_vertical);
        contentView = (CustomTableView) findViewById(R.id.content_view);

    }

    public LeftTitleView getLeftTitleView() {
        return headerVertical;
    }



    public CustomTableView getContentView() {
        return contentView;
    }

    private void setUpData() {
        leftTitles = new ArrayList<>();
        topTitles = new ArrayList<>();
        datas = new ArrayList<>();
        contentView.setRowAndColumn(leftTitles.size(), topTitles.size());
        contentView.setOnPositionDataClickListener(this);
    }

    public void setDatas(ArrayList<String> topTitlesData, ArrayList<String> leftTitlesData, ArrayList<ArrayList<String>> itemData) {
        topTitles.clear();
        leftTitles.clear();
        datas.clear();
        topTitles.addAll(topTitlesData);
        leftTitles.addAll(leftTitlesData);
        datas.addAll(itemData);
        updateView();
    }

    private void updateView() {
        headerVertical.updateTitles(leftTitles);
        contentView.setRowAndColumn(leftTitles.size(), topTitles.size());
        contentView.setDatas(datas);
    }


    @Override
    public void onPositionClick(ViewBean viewBean) {
        Toast.makeText(this.getContext(),viewBean.getText(),Toast.LENGTH_LONG).show();
    }
}
