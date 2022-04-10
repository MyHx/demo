package com.hx.base.exception;

/**
 * 基本异常
 * @author zhangmc
 * @create 2020-05-19 11:50
 */
public class BizException extends BaseException {

    public BizException(String msg){
        super(msg);
    }

    public BizException(String msg, Throwable throwable){
        super(msg, throwable);
    }

}
