package com.zynsun.openplatform.service;

/**
 * 业务锁操作接口
 * Created by yechuan on 2017/7/28.
 *
 * @modify david [2017/08/04 16:12] 增强锁，可以支持多个不同的实体一起锁定
 */
public interface BizLockService {

    /**
     * 锁定业务对象
     *
     * @param entities 需要锁定的业务对象
     */
    void lock(Object... entities);

    /**
     * 解锁业务对象
     *
     * @param entities 需要解锁的业务对象
     */
    void unlock(Object... entities);

    /**
     * 检查业务对象是否锁定，如果已锁定会出现异常
     *
     * @param entities 需要检查的业务对象
     */
    void checkLock(Object... entities);

    /**
     * 尝试锁定业务对象，如果未锁定则锁定业务对象
     *
     * @param entities 需要尝试锁定的业务对象
     *
     * @return true: 锁定成功，false：未锁定成功
     */
    boolean tryLock(Object... entities);
}
