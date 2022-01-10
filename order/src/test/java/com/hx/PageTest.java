package com.hx;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageTest {

    public static void main(String[] arg) {

        long offSet = 0;
        long pageSize = 5;
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
        List<Integer> all = new ArrayList<>();
        List<Integer> collect;
        while (true) {
            collect = a.stream().skip((offSet - 1 + 1) * pageSize).limit(pageSize).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                break;
            }
            all.addAll(collect);
            offSet++;
        }
        all.forEach(System.out::println);
    }

}



