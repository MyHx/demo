package com.hx.base.exception;


import static com.hx.base.constant.exception.ErrorCodeConstant.NO_AUTHENTICATION_MSG;

/**
 * 当前用户未经授权
 * @author Administrator
 */

public class NoAuthenticationException extends Exception {
    private static final long serialVersionUID = 7507750445064953635L;


    public NoAuthenticationException() {
        super(NO_AUTHENTICATION_MSG);
    }

    public NoAuthenticationException(String msg) {
        super(msg);
    }
}
