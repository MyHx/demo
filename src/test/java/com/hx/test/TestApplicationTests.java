package com.hx.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        String str  = "{\n" +
                "    \"reconciliationClass\":\"preAllToOneCommonCounterofferReconciliationManager\",\n" +
                "    \"compareFields\":\"repayDate,repayTerm,repayPrincipal,repayFee,repayMuclt,repayTotal,repayType\"\n" +
                "}";
        String checkPattern = getCheckPatternConfigJson(str).getString("checkPattern");
        System.out.println(StringUtils.isEmpty(checkPattern) ? "0" : checkPattern);
    }
    public JSONObject getCheckPatternConfigJson(String withDrawCompareConfig) {
        if (StringUtils.isEmpty(withDrawCompareConfig)) {
            return new JSONObject();
        }
        try {
            return JSONObject.parseObject(withDrawCompareConfig);
        } catch (Exception e) {

        }
        return new JSONObject();
    }


    String[] cmds = {"curl", "-H", "Host: www.chineseconverter.com", "-H", "Cache-Control: max-age=0",
            "--compressed", "https://www.chineseconverter.com/zh-cn/convert/chinese-stroke-order-tool"};

    public static String execCurl(String[] cmds){
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void test() {
        String str  = "{\n" +
                "\t\"Fid1\": \"36512\",\n" +
                "\t\"Fid2\": \"36512\",\n" +
                "\t\"Fid3\": \"36512\"\n" +
                "}";

        for (Map.Entry<String, Object> entry : JSONObject.parseObject(str).entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }

    @Test
    void test1() {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("Fid1", "36511");
        map.put("Fid2", "36512");
        map.put("Fid3", "36513");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);

        Object[] objects = list.toArray();
        System.out.println(list.toString());
        System.out.println(objects.toString());
    }


    @Test
    void test3() {
        BigDecimal bigDecimal = new BigDecimal(200.0000);
        System.out.println(bigDecimal);
    }


    @Test
    void test4() {
        String str = "[{\"imgName\":\"老结算迁移同步.png\",\"imgUrl\":\"http://img1.fenqile.com/eleccontract/M00/ex/20200919130336-1e77ad88-9039-4312-aeb7-4624afba55bb.png?fileName=老结算迁移同步.png\"},{\"imgName\":\"同步.png\",\"imgUrl\":\"http://img1.fenqile.com/20200919130336-1e77ad88\"}]";

        System.out.println(JSON.toJSONString(str));

        List<Handy> objects = JSON.parseArray(str,Handy.class);
        System.out.println(objects);
    }

    static class Handy {
        public String system;
        public String handler;

        public Handy(String system, String handler) {
            this.system = system;
            this.handler = handler;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }
    }

}
