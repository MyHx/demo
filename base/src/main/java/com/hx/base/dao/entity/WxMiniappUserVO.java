package com.hx.base.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Builder
@Getter
@Setter
public class WxMiniappUserVO implements Serializable {

    private Integer id;

    private String openid;

    private String unionid;

    private String phoneNumber;

    private String nickName;

    private String avatarUrl;

    private Integer userInfoId;

    private Integer consultantId;

    private String loginStatus;

    private String accessToken;

    private String orgCode;

    private String showName;

    private Boolean isConsultant;

}
