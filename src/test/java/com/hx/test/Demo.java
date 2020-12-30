package com.hx.test;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] arg) throws Exception {
//        String str = "{\"creator\":\"tyehe\",\"dealType\":10,\"min\":\"123456\"}";
//        String queryCriteria = JSONObject.toJSONString(str);
//        // 生成md5
//        String md5QueryCriteria = DigestUtils.md5Hex(queryCriteria);
//        System.out.println(md5QueryCriteria);
        Map<Object, Object> map = new HashMap<>();
        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
            System.out.println(objectObjectEntry);
        }
    }

}



