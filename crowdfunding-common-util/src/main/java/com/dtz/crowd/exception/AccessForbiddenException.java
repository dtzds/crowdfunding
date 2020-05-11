package com.dtz.crowd.exception;


/**
 * 表示用户没有登陆就访问受保护的资源异常
 */
public class AccessForbiddenException extends RuntimeException {
    private static final long serialVersionUID = -7034897190745766939L;
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
