package com.hx.base.constant.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hbf
 * @CreateTime: 2019-08-13 11:31
 * @Description:
 */
public class ErrorCodeConstant {
    public static final Map<Integer, String> codeMap = new HashMap<>();
    public static final int SESSION_TIME_OUT_CODE = 1000001;
    public static final String SESSION_TIME_OUT_MSG = "session time out.";
    public static final int NO_AUTHENTICATION_CODE = 1000002;
    public static final String NO_AUTHENTICATION_MSG = "you have no authentication.";
    public static final int SYSTEM_EXCEPTION_CODE = -1;
    public static final String SYSTEM_EXCEPTION_MSG = "system failed!";
    // 身份校验超时 Identity verification timeout
    public static final int IDENTITY_VERIFICATION_TIMEOUT_CODE = 99999;
    public static final String IDENTITY_VERIFICATION_TIMEOUT_MSG = "身份校验超时";

    static {
        codeMap.put(SESSION_TIME_OUT_CODE, SESSION_TIME_OUT_MSG);
        codeMap.put(NO_AUTHENTICATION_CODE, NO_AUTHENTICATION_MSG);
        codeMap.put(IDENTITY_VERIFICATION_TIMEOUT_CODE, IDENTITY_VERIFICATION_TIMEOUT_MSG);
        codeMap.put(SYSTEM_EXCEPTION_CODE, SYSTEM_EXCEPTION_MSG);
    }
//
//    static {
//        codeMap.put(SESSION_TIME_OUT_CODE, SESSION_TIME_OUT_MSG);
//        codeMap.put(NO_AUTHENTICATION_CODE, NO_AUTHENTICATION_MSG);
//        codeMap.put(NO_AUTHORIZATION_CODE, NO_AUTHORIZATION_MSG);
//        codeMap.put(CONTACT_BOOK_UNINSTALLED_CODE, CONTACT_BOOK_UNINSTALLED_MSG);
//        codeMap.put(MINI_PROGRAM_UNINSTALLED_CODE, MINI_PROGRAM_UNINSTALLED_MSG);
//        codeMap.put(AUTH_ROLE_EXISTS_ROLE_NAME_CODE, AUTH_ROLE_EXISTS_ROLE_NAME_MSG);
//        codeMap.put(AUTH_ROLE_NOT_EXISTS_COPY_ROLE_CODE, AUTH_ROLE_NOT_EXISTS_COPY_ROLE_MSG);
//        codeMap.put(AUTH_ROLE_NOT_EXISTS_CODE, AUTH_ROLE_NOT_EXISTS_MSG);
//        codeMap.put(AUTH_ROLE_UPDATE_SUPERADMIN_EMPTY_CODE, AUTH_ROLE_UPDATE_SUPERADMIN_EMPTY_MSG);
//        codeMap.put(AUTH_ROLE_NO_UPDATE_SUPERADMIN_CODE, AUTH_ROLE_NO_UPDATE_SUPERADMIN_MSG);
//        codeMap.put(AUTH_ROLE_USER_NO_EXISTS_CODE, AUTH_ROLE_USER_NO_EXISTS_MSG);
//        codeMap.put(AUTH_ROLE_NO_PERMISSION_TYPE_CODE, AUTH_ROLE_NO_PERMISSION_TYPE_MSG);
//        codeMap.put(AUTH_ROLE_RESOURCE_NO_EXISTS_CODE, AUTH_ROLE_RESOURCE_NO_EXISTS_MSG);
//        codeMap.put(AUTH_ROLE_NOT_CREATOR_CODE, AUTH_ROLE_NOT_CREATOR_MSG);
//        codeMap.put(AUTH_ROLE_ADD_SELF_CODE, AUTH_ROLE_ADD_SELF_MSG);
//        codeMap.put(AUTH_ROLE_DELETE_SUPER_ADMIN_CODE, AUTH_ROLE_DELETE_SUPER_ADMIN_MSG);
//        codeMap.put(SYSTEM_EXCEPTION_CODE, SYSTEM_EXCEPTION_MSG);
//        codeMap.put(UPDATE_TOKEN_EXCEPTION_CODE, GET_PROVIDER_TOKEN_EXCEPTION_MSG);
//        codeMap.put(PARAMS_ERROR, "参数非法");
//        codeMap.put(STAFF_INFO_NOT_EXIST, "员工信息不存在或者已经删除");
//        codeMap.put(PERMISSION_DENIED, "没有权限");
//        codeMap.put(ILLEGAL_USERS, "非法用户");
//        codeMap.put(CORP_TASK_NOT_EXIST, CORP_TASK_NOT_EXIST_MSG);
//        codeMap.put(CORP_TASK_EXIST, "任务已经存在");
//        codeMap.put(CORP_PRODUCT_NOT_EXIST, "产品不存在或已删除");
//        codeMap.put(CORP_BANNER_NOT_EXIST, "活动不存在或已删除");
//        codeMap.put(CORP_VIRTUAL_DEPT_EXIST, "虚拟部门已经存在");
//        codeMap.put(CORP_VIRTUAL_DEPT_NOT_EXIST, "虚拟部门不存在或已删除");
//        codeMap.put(ACTIVITY_IS_NOT_EXIST, "活动不存在");
//        codeMap.put(ACTIVITY_NOT_START, "活动未开始，无法参与抽奖");
//        codeMap.put(ACTIVITY_HAS_OVER, "活动已结束，无法参与抽奖");
//        codeMap.put(USER_NOT_AUTH, "用户没有授权基本信息");
//        codeMap.put(USER_NOT_AUTH_PHONE, "用户没有授权电话");
//        codeMap.put(NOT_ADD_STAFF, "没有添加客户经理");
//        codeMap.put(STAFF_CAN_NOT_LOTTERY, "当前活动已设置员工不能抽奖");
//        codeMap.put(USER_HAS_LOTTERY, "您已经没有抽奖机会");
//        codeMap.put(NOT_AUTH_INFO_AND_PHONE, "用户信息和电话都没有");
//        codeMap.put(NOT_EXIST_USER, "用户信息不存在");
//        codeMap.put(ACTIVITY_NOT_PUBLISHED, "活动未发布，无法参与抽奖。");
//        codeMap.put(PHONE_ERROR, "电话非法.");
//        codeMap.put(PLEASE_OPEN_IN_WECHAT, "需在微信客户端才能抽奖");
//        codeMap.put(PRIZE_IS_INVALID, "奖品核销失败，需在奖品有效期内进行核销");
//        codeMap.put(AUTH_CODE_ERROR, "auth code error.");
//        codeMap.put(NEWS_INFO_URL_NOT_EXIT_CODE, NEWS_INFO_URL_NOT_EXIT_MSG);
//    }


}
