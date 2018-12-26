package com.zynsun.openplatform.mapper.base.insert;

import org.apache.ibatis.annotations.InsertProvider;

import com.zynsun.openplatform.mapper.provider.AShareBaseInsertProvider;

/**
 * 通用Mapper接口,插入
 *
 * @param <T> 不能为空
 * @author liwuyang
 */
public interface AShareInsertSelectiveMapper<T> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @InsertProvider(type = AShareBaseInsertProvider.class, method = "dynamicSQL")
    int insertSelective(T record);
}