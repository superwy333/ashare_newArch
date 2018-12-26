package com.zynsun.openplatform.exception;

/**
 * 业务锁异常
 * Created by yechuan on 2017/7/28.
 */
public class BizLockException extends RuntimeException {

    public BizLockException() {
        super();
    }

    public BizLockException(String message) {
        super(message);
    }

    public BizLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizLockException(Throwable cause) {
        super(cause);
    }

    protected BizLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
