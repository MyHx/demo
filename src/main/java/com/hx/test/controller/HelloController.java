package com.hx.test.controller;

import com.hx.test.AssetManage;
import com.hx.test.enums.DepartmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String getHello(AssetManage assetManage) {
        if (log.isDebugEnabled()) {
            log.debug("资产管理数据：{}", assetManage);
        }
        String desc = DepartmentEnum.SETTLEMENT.getDesc();
        return "hello" + desc;
    }
}
