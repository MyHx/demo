//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hx.base.exception;

public enum ResultCode implements IResultCode {
    FAILURE("base.0000", "业务异常"),
    SUCCESS("success", "业务正常"),
    INTERNAL_SERVER_ERROR("base.500", "内部错误"),
    UN_AUTHORIZED("base.0001", "请求未授权"),
    NOT_FOUND("base.0002", "404 没找到请求"),
    REQ_REJECT("base.0003", "请求被拒绝"),
    PARAM_MISS("base.0004", "缺少必要的请求参数"),
    PARAM_TYPE_ERROR("base.0005", "请求参数类型错误"),
    RESUBMIT("base.0006", "重复提交"),
    EXPIRED("base.0007", "令牌已失效");

    final String code;
    final String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
