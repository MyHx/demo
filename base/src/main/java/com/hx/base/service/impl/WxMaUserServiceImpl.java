package com.hx.base.service.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.lang.Assert;
import com.hx.base.dao.entity.WxMiniappUserVO;
import com.hx.base.exception.ServiceException;
import com.hx.base.service.WxMaUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;


@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class WxMaUserServiceImpl implements WxMaUserService {

    private final WxMaService wxMaService;


    @Override
    public Map<String, Object> login(String appid, String code) throws WxErrorException {
        if (!wxMaService.switchover(appid)) {
            throw new ServiceException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        String accessToken = wxMaService.getAccessToken();
        System.out.println("accessToken:" + accessToken);
        Map<String, Object> res = new HashMap<>();
//        try {
//            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
//            String openid = session.getOpenid();
//
//            res.put("openid", openid);
////            res.put("adminToken", getToken(account, password));
//            res.put("adminToken", "");
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            throw new ServiceException(e.getMessage());
//        } finally {
//            WxMaConfigHolder.remove();//清理ThreadLocal
//        }
        return res;
    }

//    @Override
//    public WxMiniappUserVO getWxUserInfo(String openid) {
//        //先查询openid是否存在
//        WxMiniappUser wxUser = wxMiniappRepository.findByOpenidAndStatus(openid);
//        if (null == wxUser) {
//            wxUser = new WxMiniappUser();
//            wxUser.setOpenid(openid);
//            wxUser.setLoginStatus("0");//未登录
//            wxUser.setCreateTime(new Date());
//            wxUser.setUpdateTime(new Date());
//        }
//
//        User user = getOrCreateUser(openid, null);
//        if (null == wxUser.getUserInfoId()) {
//            wxUser.setUserInfoId(user.getId());
//            wxMiniappRepository.save(wxUser);
//        }
//
//        wxMiniappRepository.save(wxUser);
//        WxMiniappUserVO wxUserVO = single(WxMiniappUserVO.class, wxUser);
//        wxUserVO.setAccessToken(getToken(user.getAccount(), user.getPassword()));
//        return wxUserVO;
//    }

//    /**
//     * 查询或者创建用户
//     * @param account
//     * @param name
//     * @return
//     */
//    private User getOrCreateUser(String account, String name) {
//        User user = businessCenter4User.findByAccount(account);
//        if (Objects.isNull(user)) {
//            //创建用户
//            user = new User();
//            user.setAccount(account);
//            //设置用户姓名
//            user.setUsername(StringUtil.isBlank(name) ? account : name);
//            //随机生成8位数密码
//            user.setPassword(PasswordGenerator.generatePassword(8));
//            user.setTenantId(Constant.TENANT_ID);
//            user = businessCenter4User.createUser(user, new ArrayList<>());
//        }
//        return user;
//    }

//    private String getToken(String account, String password) {
//        String url = String.join("", authServer, "/oauth/token");
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", account);
//        params.put("password", password);
//        params.put("tenantId", Constant.TENANT_ID);
//        params.put("scope", "all");
//        params.put("type", "account");
//        params.put("grant_type", "password");
//        try {
//            HttpResponse httpResponse = HttpRequest.post(url)
//                    .header("Authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0")
//                    .header("Tenant-Id", Constant.TENANT_ID)
//                    .header("Content-type", "application/x-www-form-urlencoded")
//                    .form(params)
//                    .execute();
//            if (httpResponse.getStatus() != HttpStatus.HTTP_OK &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_CREATED &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_ACCEPTED &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_NOT_AUTHORITATIVE &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_NO_CONTENT &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_RESET &&
//                    httpResponse.getStatus() != HttpStatus.HTTP_PARTIAL) {
//                log.error("获取token失败，Http状态码为：{}", httpResponse.getStatus());
//
//                throw new ServiceException("获取token失败");
//            }
//            log.info("获取token成功！");
//
//            JSONObject jsonObject = JSON.parseObject(httpResponse.body());
//            return jsonObject.getString("access_token");
//        } catch (Exception e) {
//            log.error("获取token失败，错误信息为：{}", e);
//            throw new ServiceException("获取token失败，错误信息为：" + e.getMessage());
//        }
//    }

    @Override
    public boolean logout(String openid) {
//        wxMiniappRepository.updateLoginStatus(openid);
        return true;
    }

//    /**
//     * 保存用户姓名和头像
//     */
//    @Override
//    public WxMiniappUserVO saveUserInfo(String openid, String nickName, String avatarUrl) {
//        Assert.notBlank(openid, () -> new ServiceException("获取用户手机号：openid不能为空！"));
//
//        WxMiniappUser wxUser = wxMiniappRepository.findByOpenidAndStatus(openid);
//
//        Assert.notNull(wxUser, () -> new ServiceException("openid=" + openid + "，未查到系统微信用户，请先wx.login登录"));
//
//        if (StringUtils.isNotBlank(nickName)) {
//            wxUser.setNickName(nickName);
//        }
//        if (StringUtils.isNotBlank(avatarUrl)) {
//            wxUser.setAvatarUrl(avatarUrl);
//        }
//        wxUser.setUpdateTime(new Date());
//        wxMiniappRepository.save(wxUser);
//
//        return single(WxMiniappUserVO.class, wxUser);
//    }

    /**
     * 获取用户绑定手机号信息
     */
    @Override
    public WxMiniappUserVO phone(String appid, String openid, String code, String orgCode, Integer consultantId) {
        Assert.notBlank(openid, () -> new ServiceException("获取用户手机号：openid不能为空！"));

        if (!wxMaService.switchover(appid)) {
            throw new ServiceException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        try {
            WxMaPhoneNumberInfo wxMaPhoneNumberInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);
//            if (null != wxMaPhoneNumberInfo && StringUtils.isNotBlank(wxMaPhoneNumberInfo.getPhoneNumber())) {
//                String phoneNumber = wxMaPhoneNumberInfo.getPhoneNumber();
//
//                WxMiniappUser wxUser = wxMiniappRepository.findByOpenidAndStatus(openid);
//                Assert.notNull(wxUser, () -> new ServiceException("openid=" + openid + "，未查到系统微信用户，请先wx.login登录"));
//
//                wxUser.setPhoneNumber(phoneNumber);
//                wxUser.setUpdateTime(new Date());
//                wxUser.setLoginStatus("1");
//                wxMiniappRepository.save(wxUser);
//                return single(WxMiniappUserVO.class, wxUser);
//            } else {
//                log.error("获取微信用户手机号失败：resData={}", wxMaPhoneNumberInfo.toString());
//                throw new ServiceException("获取微信用户手机号失败：resData=" + wxMaPhoneNumberInfo.toString());
//            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
    }

//    @Override
//    public WxMiniappUserVO queryCurrLoginUserInfo() {//获取本人用户id
//        String userId = baseContext.getAccountId();
//        if(StringUtils.isBlank(userId)){
//            throw new ServiceException("未获取到当前登陆人的用户id");
//        }
//        if(baseRedis.exists(String.format(Constant.AI_DOCTOR_USERINFO_REDIS_KEY, userId))){
//            return baseRedis.get(String.format(Constant.AI_DOCTOR_USERINFO_REDIS_KEY, userId));
//        }
//
//        WxMiniappUser wxMiniappUser = wxMiniappRepository.findByUserInfoId(Integer.valueOf(userId));
//        if (null == wxMiniappUser) {
//            log.error("根据当前登录的统一门户用户id="+userId+",未获取到小程序用户id");
//            throw new ServiceException("根据当前登录的统一门户用户id，未获取到小程序用户id");
//        }
//
//        WxMiniappUserVO vo = single(WxMiniappUserVO.class, wxMiniappUser);
//        baseRedis.setEx(String.format(Constant.AI_DOCTOR_USERINFO_REDIS_KEY, userId), vo, Duration.ofMinutes(2));
//        return vo;
//    }


}
