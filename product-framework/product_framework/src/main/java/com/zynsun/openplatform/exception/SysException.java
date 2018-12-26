package com.zynsun.openplatform.exception;


/**
 * Created by nicyea on 2016/4/27.
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = -197875331440826479L;

    public SysException() {
        super();
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    protected SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
