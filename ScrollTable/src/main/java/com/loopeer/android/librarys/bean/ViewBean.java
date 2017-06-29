package com.loopeer.android.librarys.bean;

import com.loopeer.android.librarys.data.Cell;

/**
 * @author jyb jyb_96@sina.com on 2017/6/29.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class ViewBean extends Cell {

    private String text;//文字
    private double width;//因为width可以拆分。
    private int height;
    private int floor;//层数
    private int type;//1是正常 2是合并 3是拆分
    private double rightTopUnit;//右上角距离最左边的距离


    public double getRightTopUnit() {
        return rightTopUnit;
    }

    public void setRightTopUnit(double rightTopUnit) {
        this.rightTopUnit = rightTopUnit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
