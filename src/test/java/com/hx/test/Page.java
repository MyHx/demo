package com.hx.test;


import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public static void main(String[] arg) {

        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        a.add(10);
        a.add(11);
        a.add(12);

        // 查询批次下的明细
        int offset = 0;
        int limit = 2;
        do {
            System.out.println("offset：" + offset + "limit：" + limit);
            PageHelper.offsetPage(offset, limit, false);
            offset += limit;

        } while (CollectionUtil.isNotEmpty(a));

    }

}



