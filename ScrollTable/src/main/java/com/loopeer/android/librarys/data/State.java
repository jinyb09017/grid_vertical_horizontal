package com.loopeer.android.librarys.data;

/**
 * @author jyb jyb_96@sina.com on 2017/6/29.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public enum State {

    to("待售", "#e3e3e3", "#ffffff"),
    pre("预售", "#e2b838", "#ddaa10"),//ddaa10
    done("已售", "#ec675d", "#e74d4d"),
    limit("限售", "#87888a", "#7d7f82"),
    sample("样板", "#24aec3", "#1aa1b6");

    private String name;
    private String color;
    private String border;

//    private State(String name) {
//        this.name = name;
//    }

    private State(String name, String color, String border) {
        this.name = name;
        this.color = color;
        this.border = border;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getBorder() {
        return border;
    }


}
