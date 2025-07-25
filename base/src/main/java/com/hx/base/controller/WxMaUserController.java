package com.hx.base.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.hx.base.exception.ServiceException;
import com.hx.base.service.WxMaUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wx")
public class WxMaUserController {

    @Resource
    private WxMaUserService wxMaUserService;

    @PostMapping("/login")
    public Map<String, Object> login(String appid, String code) throws WxErrorException {
        return wxMaUserService.login(appid, code);
    }
}
