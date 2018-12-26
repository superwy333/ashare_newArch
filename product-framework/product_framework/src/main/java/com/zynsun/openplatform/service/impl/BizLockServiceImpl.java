package com.zynsun.openplatform.service.impl;

import com.zynsun.openplatform.constants.BizLockConstant;
import com.zynsun.openplatform.context.SpringApplicationContextHolder;
import com.zynsun.openplatform.domain.BizLockBaseDomain;
import com.zynsun.openplatform.exception.BizLockException;
import com.zynsun.openplatform.exception.BusinessException;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.service.BizLockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 带业务锁的基础Service抽象类
 * 注意：实体类对应数据库表中必须有is_locked 业务锁定字段才可以继承该抽象类，否则 lock()和 unlock()报错；
 * Created by yechuan on 2017/7/28.
 *
 * @modify david [2017/08/04 16:12] 增强锁，可以支持多个不同的实体一起锁定
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BizLockServiceImpl implements BizLockService {

    /**
     * 锁定业务对象
     *
     * @param entities 需要锁定的业务对象
     */
    @Override
    public void lock(Object... entities) {
        List<BizLockBaseDomain> domainList = unwrap(entities);
        for (BizLockBaseDomain domain : domainList) {
            if (BizLockConstant.BIZ_LOCKED.equals(domain.getIsLocked())) {
                throw new BizLockException("业务实体" + domain.getClass().getSimpleName() + "[ID=" + domain.getId() + "]已被锁定，不允许重复锁定。");
            }
            domain.setIsLocked(BizLockConstant.BIZ_LOCKED); // 1锁定
            this.getBaseService(domain).updateById(domain);
        }
    }

    /**
     * 解锁业务对象
     *
     * @param entities 需要解锁的业务对象
     */
    @Override
    public void unlock(Object... entities) {
        List<BizLockBaseDomain> domainList = unwrap(entities);
        for (BizLockBaseDomain domain : domainList) {
            domain.setIsLocked(BizLockConstant.BIZ_UNLOCKED); // 0解锁
            this.getBaseService(domain).updateById(domain);
        }
    }


    /**
     * 检查业务对象是否锁定，如果已锁定会出现异常
     *
     * @param entities 需要检查的业务对象
     */
    @Override
    public void checkLock(Object... entities) {
        List<BizLockBaseDomain> domainList = unwrap(entities);
        for (BizLockBaseDomain domain : domainList) {
            if (BizLockConstant.BIZ_LOCKED.equals(domain.getIsLocked())) {
                throw new BizLockException("业务实体" + domain.getClass().getSimpleName() + "[ID=" + domain.getId() + "]被业务锁定。");
            }
        }
    }

    /**
     * 尝试锁定业务对象，如果未锁定则锁定业务对象
     *
     * @param entities 需要尝试锁定的业务对象
     */
    @Override
    public boolean tryLock(Object... entities) {
        List<BizLockBaseDomain> domainList = unwrap(entities);
        for (BizLockBaseDomain domain : domainList) {
            if (BizLockConstant.BIZ_LOCKED.equals(domain.getIsLocked())) {
                return false;
            }
        }
        for (BizLockBaseDomain domain : domainList){
            domain.setIsLocked(BizLockConstant.BIZ_LOCKED); // 1锁定
            this.getBaseService(domain).updateById(domain);
        }
        return true;
    }

    /**
     * 将传递过来的对象解压出来
     *
     * @param objects 任意对象（包括数组/集合）
     * @return BizLockBaseDomain对象集合（全部是该类型的对象）
     */
    private static List<BizLockBaseDomain> unwrap(Object... objects) throws IllegalArgumentException {
        List<BizLockBaseDomain> domainList = new ArrayList<>();
        if (objects != null && objects.length > 0) {
            for (Object obj : objects) {
                if (obj instanceof Object[]) {
                    for (Object obj1 : (Object[]) obj) {
                        Assert.isTrue(obj1 instanceof BizLockBaseDomain, "必须继承 BizLockBaseDomain");
                        domainList.add((BizLockBaseDomain) obj1);
                    }
                } else if (obj instanceof Collection) {
                    for (Object obj1 : (Collection) obj) {
                        Assert.isTrue(obj1 instanceof BizLockBaseDomain, "必须继承 BizLockBaseDomain");
                        domainList.add((BizLockBaseDomain) obj1);
                    }
                } else {
                    Assert.isTrue(obj instanceof BizLockBaseDomain, "必须继承 BizLockBaseDomain");
                    domainList.add((BizLockBaseDomain) obj);
                }
            }
        }
        return domainList;
    }

    private BaseService<BizLockBaseDomain> getBaseService(BizLockBaseDomain entity) {
        Class<? extends BizLockBaseDomain> domainClass = entity.getClass();
        try {
            String domainServiceName = domainClass.getSimpleName().substring(0,1).toLowerCase() + domainClass.getSimpleName().substring(1) + "ServiceImpl";
            // 获取对应的Service Bean
            return (BaseService<BizLockBaseDomain>) SpringApplicationContextHolder.getBean(domainServiceName);
        } catch (Exception e) {
            throw new BusinessException("未找到" + domainClass.getName() + "对应的默认Service");
        }
    }
}
