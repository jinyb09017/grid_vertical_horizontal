package com.loopeer.android.librarys.utils;

import com.loopeer.android.librarys.bean.ViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jyb jyb_96@sina.com on 2017/6/29.
 * @version V1.0
 * @Description: add comment
 * @date 16-4-21 11:21
 * @copyright www.tops001.com
 */

public class DataStrcture {
    /**
     * 保存表格数据。内部List保存一层的数据。外部保存所有list
     *
     * @return
     */
    public static List<List<ViewBean>> getViewDatas() {
        List<List<ViewBean>> beans = new ArrayList<>();

        //二层
//        for (int floor = 0; floor < 2; floor++) {

            //first floor
            List<ViewBean> viewBeans1 = new ArrayList<>();


            ViewBean viewBean1 = new ViewBean();
            viewBean1.setText(1 + "");
            viewBean1.setFloor(1);
            viewBean1.setHeight(2);
            viewBean1.setWidth(1);
            viewBean1.setType(2);
            viewBean1.setRightTopUnit(1);
            viewBeans1.add(viewBean1);

            ViewBean viewBean2 = new ViewBean();
            viewBean2.setText(2 + "");
            viewBean2.setFloor(1);
            viewBean2.setHeight(1);
            viewBean2.setWidth(3);
            viewBean2.setType(2);
            viewBean2.setRightTopUnit(4);
            viewBeans1.add(viewBean2);

            ViewBean viewBean13 = new ViewBean();
            viewBean13.setText(3 + "");
            viewBean13.setFloor(1);
            viewBean13.setHeight(2);
            viewBean13.setWidth(2);
            viewBean13.setType(2);
            viewBean13.setRightTopUnit(6);
            viewBeans1.add(viewBean13);


            beans.add(viewBeans1);


            //second floor
            List<ViewBean> viewBeans2 = new ArrayList<>();


            ViewBean viewBean3 = new ViewBean();
            viewBean3.setText(3 + "");
            viewBean3.setFloor(2);
            viewBean3.setHeight(1);
            viewBean3.setWidth(0.33);
            viewBean3.setType(3);
            viewBean3.setRightTopUnit(1.33);
            viewBeans2.add(viewBean3);

            ViewBean viewBean32 = new ViewBean();
            viewBean32.setText(3 + "");
            viewBean32.setFloor(2);
            viewBean32.setHeight(1);
            viewBean32.setWidth(0.33);
            viewBean32.setType(3);
            viewBean32.setRightTopUnit(1.66);
            viewBeans2.add(viewBean32);


            ViewBean viewBean4 = new ViewBean();
            viewBean4.setText(4 + "");
            viewBean4.setFloor(2);
            viewBean4.setHeight(1);
            viewBean4.setWidth(0.33);
            viewBean4.setType(3);
            viewBean4.setRightTopUnit(2);
            viewBeans2.add(viewBean4);

            ViewBean viewBean5 = new ViewBean();
            viewBean5.setText(5 + "");
            viewBean5.setFloor(2);
            viewBean5.setHeight(1);
            viewBean5.setWidth(1);
            viewBean5.setType(1);
            viewBean5.setRightTopUnit(3);
            viewBeans2.add(viewBean5);

            ViewBean viewBean6 = new ViewBean();
            viewBean6.setText(6 + "");
            viewBean6.setFloor(2);
            viewBean6.setHeight(1);
            viewBean6.setWidth(1);
            viewBean6.setType(1);
            viewBean6.setRightTopUnit(4);
            viewBeans2.add(viewBean6);


            beans.add(viewBeans2);
//        }

        return beans;

    }
}
