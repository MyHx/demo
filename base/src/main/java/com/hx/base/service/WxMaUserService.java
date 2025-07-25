package com.hx.base.service;



import com.hx.base.dao.entity.WxMiniappUserVO;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Map;

public interface WxMaUserService {

    /**
     * 登录
     * @param appid 小程序appid
     * @param code 小程序端调用wx.login() 获取 js_code
     * @return
     */
    Map<String, Object> login(String appid, String code) throws WxErrorException;

//    WxMiniappUserVO getWxUserInfo(String openid, String orgCode, Integer consultantId);

    boolean logout(String openid);

//    WxMiniappUserVO saveUserInfo(String openid, String nickName, String avatarUrl);

    WxMiniappUserVO phone(String appid, String openid, String code, String orgCode, Integer consultantId);

//    WxMiniappUserVO queryCurrLoginUserInfo();

//    WxMiniappUserVO getWxUserInfoByUserId(Integer userId);


}
