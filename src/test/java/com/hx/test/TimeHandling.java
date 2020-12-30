package com.hx.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class TimeHandling {

    public static void main(String[] args) {
        // 时间处理
        AssetManage assetManage = new AssetManage();
        assetManage.setCreationTime(DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN));
        System.out.println(assetManage.toString());
    }

}
