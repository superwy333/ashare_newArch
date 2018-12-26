package com.zynsun.openplatform.mapper;

import com.zynsun.openplatform.mapper.base.select.*;

/**
 * Created by liwuyang on 2017/3/17.
 */
public interface AShareBaseSelectMapper<T> extends
        AShareSelectOneMapper<T> ,
        AShareSelectMapper<T>,
        AShareSelectAllMapper<T>,
        AShareSelectCountMapper<T>,
        AShareSelectByPrimaryKeyMapper<T>,
        AShareExistsWithPrimaryKeyMapper<T> {
}
