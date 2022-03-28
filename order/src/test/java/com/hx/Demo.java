package com.hx;


import org.apache.commons.lang.StringUtils;


public class Demo {
    public static void main(String[] args) {

        String strKey = "fab_cc_dd";

//        System.out.println(StringUtils.substring(str, 1, str.length()).replaceAll("_",""));
        if (strKey.contains("_")) {
            String[] keyArray = strKey.split("_");
            StringBuffer sb = new StringBuffer();
            boolean flag = false;
            for (String key : keyArray) {
                if (flag) {
                    //下划线后的首字母大写
                    sb.append(StringUtils.capitalize(key));
                } else {
                    flag = true;
                    sb.append(key);
                }
            }
            strKey = sb.toString();
        }
        System.out.println(StringUtils.substring(strKey, 1, strKey.length()));
    }
}