package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BizTypeStyle;

public interface BizTypeStyleMapper extends IMapper<BizTypeStyle> {

    int deleteByBizTypeId(Long bizTypeId);
}