package com.hx.base.exception;


/**
 * @author Administrator
 */
public class SessionTimeoutException extends BaseException {

    public SessionTimeoutException(String msg) {
        super(msg);
    }

    public SessionTimeoutException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
