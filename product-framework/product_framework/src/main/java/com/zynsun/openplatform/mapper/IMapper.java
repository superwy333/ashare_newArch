package com.zynsun.openplatform.mapper;

import com.zynsun.openplatform.mapper.base.insert.AShareInsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Marker;

/**
 * 继承基础Mapper接口和MySQL专用接口
 * @ClassName: IMapper 
 * @Description: 平台基础Mapper接口
 * @author yechuan
 * @date 2016年5月19日 下午8:39:58 
 * 
 * @param <T>
 */
public interface IMapper<T> extends 
    AShareBaseSelectMapper<T>,
    AShareBaseInsertMapper<T>,
    AShareInsertListMapper<T>,
    AShareBaseUpdateMapper<T>,
    ConditionMapper<T>,Marker{
}