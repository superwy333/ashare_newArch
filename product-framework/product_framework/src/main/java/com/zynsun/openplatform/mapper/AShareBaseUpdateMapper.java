package com.zynsun.openplatform.mapper;

import com.zynsun.openplatform.mapper.base.update.AShareUpdateByPrimaryKeyMapper;
import com.zynsun.openplatform.mapper.base.update.AShareUpdateByPrimaryKeySelectiveMapper;

/**
 * 通用Mapper接口,基础查询 (处理乐观锁)
 *
 * @param <T> 不能为空
 * @author create by liwuyang on 2016-07-17 16:11
 */
public interface AShareBaseUpdateMapper<T> extends
    AShareUpdateByPrimaryKeyMapper<T>,
    AShareUpdateByPrimaryKeySelectiveMapper<T> {
}

