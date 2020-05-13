package com.dtz.crowd.exception;

/**
 *  当更新admin是发现账户已被使用时发生的异常
 * @author EWRT
 * @createTime 2020/5/13
 */
public class LoginAcctAlreadyInUseException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
