package com.zynsun.openplatform.domain;

import com.fasterxml.jackson.databind.deser.Deserializers;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 使用业务锁的基础实体类
 * Created by yechuan on 2017/7/28.
 */
public class BizLockBaseDomain extends BaseDomain {

    /** 业务锁  严禁设置默认值 */
    @Column(name = "is_locked")
    private Integer isLocked;

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }
}
