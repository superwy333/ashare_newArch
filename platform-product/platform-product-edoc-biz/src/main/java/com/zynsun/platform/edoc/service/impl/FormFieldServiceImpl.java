package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.domain.FormField;
import com.zynsun.platform.edoc.mapper.FormFieldMapper;
import com.zynsun.platform.edoc.service.FormAreaService;
import com.zynsun.platform.edoc.service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service
public class FormFieldServiceImpl extends BaseServiceImpl<FormField> implements FormFieldService {

    @Autowired
    private FormFieldMapper formFieldMapper;

    @Override
    protected IMapper<FormField> getBaseMapper() {
        return formFieldMapper;
    }

    @Override
    public List<FormField> findFormFields(Long formAreaId) {
        return  formFieldMapper.findFormFields(formAreaId);
    }

    @Override
    public FormField queryByCode(String formFieldCode) {
        return formFieldMapper.selectByCode(formFieldCode);
    }
}
