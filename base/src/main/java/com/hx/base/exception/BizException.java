package com.hx.base.exception;

/**
 * 基本异常
 */
public class BizException extends BaseException {

    public BizException(String msg){
        super(msg);
    }

    public BizException(String msg, Throwable throwable){
        super(msg, throwable);
    }

}
