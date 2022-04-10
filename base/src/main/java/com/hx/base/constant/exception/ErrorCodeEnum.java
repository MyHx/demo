package com.hx.base.constant.exception;

/**
 * 错误码枚举
 *
 * @author Administrator
 */

public enum ErrorCodeEnum {

    SUCCESS(0, "success"),

    PARAM_EMPTY(1001, "必填参数为空"),

    PARAM_ERROR(1002, "参数格式错误"),

    UNKNOWN_ERROR(9999, "系统繁忙，请稍后再试....");

    private int code;

    private String desc;

    ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }


    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.desc;
    }

}
