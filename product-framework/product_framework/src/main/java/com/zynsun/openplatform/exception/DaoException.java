package com.zynsun.openplatform.exception;

/**
 * Created by nicyea on 2016/4/27.
 */
public class DaoException extends RuntimeException {

    private static final long serialVersionUID = -4112849140658382969L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
