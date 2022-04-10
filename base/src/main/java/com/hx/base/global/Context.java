package com.hx.base.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    private String SessionKey;

    private String staffId;

    private String openId;

    private String IP;

    private Integer platform;

    private String corpId;

//    private Integer adminType;

    private String unionId;

    private Integer verifyStatus;

    private String wechatKey;

    private String userId;

    /**
     * 微信用户登录  有个最近联系的客户经理id
     */
    private String  lastTouchStaffId;

    private String appId;

    private Integer userType;

    private String visitUserId;

    private String fromAppId;

    private String  loginFrom;

    private Integer staffVisitSource;

    /**
     * 是否同一企业，1：是
     */
    private Integer sameCorp;
    /**
     * 埋点时客户的unionid或externalid
     */
    private String customerId;
    /**
     * 登录平台(微信/企业微信，小程序/H5)
     */
    private String application;
    /**
     * 访客平台唯一id
     */
    private Long visitorId;
    /**
     * 外部联系人id
     */
    private String externalUserId;

    /**
     * 外部联系人id
     */
    private Integer souce;

    private String sharedStaffId;

    private String requestId;
}
