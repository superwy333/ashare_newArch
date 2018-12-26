package com.zynsun.openplatform.exception;

/**
 * Created by nicyea on 2016/4/27.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5646108980585371451L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
