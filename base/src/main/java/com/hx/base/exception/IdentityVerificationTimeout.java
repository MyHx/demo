package com.hx.base.exception;


public class IdentityVerificationTimeout extends BaseException {

    public IdentityVerificationTimeout(String msg) {
        super(msg);
    }

    public IdentityVerificationTimeout(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
