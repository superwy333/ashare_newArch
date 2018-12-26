package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.domain.FormTemplate;

public interface FormTemplateMapper extends IMapper<FormTemplate> {

    Integer deleteByBizId(Long bizId);

}