package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.FormTemplate;
import com.zynsun.platform.edoc.mapper.FormTemplateMapper;
import com.zynsun.platform.edoc.service.FormTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service
public class FormTemplateServiceImpl extends BaseServiceImpl<FormTemplate> implements FormTemplateService{

    @Autowired
    private FormTemplateMapper formTemplateMapper;

    @Override
    protected IMapper<FormTemplate> getBaseMapper() {
        return formTemplateMapper;
    }
}
