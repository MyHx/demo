package com.hx.base.exception;

/**
 * 基本异常
 *
 * @author Administrator
 */
public class BaseException extends RuntimeException {

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
