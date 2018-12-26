package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AreaField;

public interface AreaFieldMapper extends IMapper<AreaField> {
    int deleteByAreaId(Long id);
}