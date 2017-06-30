package com.loopeer.android.librarys.scrolltable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopeer.android.librarys.bean.ViewBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScrollTableView extends LinearLayout implements CustomTableView.OnPositionDataClickListener,GridHeader.OnPositionCallBack {

    private LeftTitleView headerVertical;
    private CustomTableView contentView;
    private GridHeader gridHeader;
    private ArrayList<String> topTitles;
    private ArrayList<String> leftTitles;
    private List<List<ViewBean>> datas;

    LinkedHashMap<String,List<List<ViewBean>>> allUnitHashMap;

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
        gridHeader = (GridHeader) findViewById(R.id.gridHeader);
        gridHeader.setOnPositionCallBack(this);

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
        contentView.setOnPositionDataClickListener(this);
    }

    public void setDatas(ArrayList<String> leftTitlesData, LinkedHashMap<String,List<List<ViewBean>>> allUnitHashMap) {
        topTitles.clear();
        leftTitles.clear();
        datas.clear();

        this.allUnitHashMap = allUnitHashMap;
        leftTitles.addAll(leftTitlesData);

        List<String> allUnits = getAllUnit(allUnitHashMap);
        gridHeader.setDatas(allUnits);

    }

    public List<String> getAllUnit(LinkedHashMap<String,List<List<ViewBean>>> allUnitHashMap){
        List<String> allUnit = new ArrayList<>();
        Iterator<Map.Entry<String,List<List<ViewBean>>>> it = allUnitHashMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,List<List<ViewBean>>> e = it.next();
            allUnit.add(e.getKey());
        }

        return allUnit;
    }



    @Override
    public void onPositionClick(ViewBean viewBean) {
        Toast.makeText(this.getContext(),viewBean.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void click(String unit) {

        //update the customTableView;
        List<List<ViewBean>> datas = allUnitHashMap.get(unit);
        contentView.setDatas(datas);

        int rows = datas.size();
        headerVertical.updateTitles(leftTitles.subList(0,rows));

    }
}
