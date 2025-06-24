//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hx.base.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;
    private final String code;
    private final String message;

    public ServiceException(String message) {
        super(message);
        this.code = ResultCode.FAILURE.getCode();
        this.message = message;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ServiceException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
