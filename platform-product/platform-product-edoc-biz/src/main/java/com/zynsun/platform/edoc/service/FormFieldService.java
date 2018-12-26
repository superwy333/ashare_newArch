package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.FormField;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:00
 **/
public interface FormFieldService extends BaseService<FormField> {
    List<FormField> findFormFields (Long formAreaId);

    /**
     * 根据字段代码查询字段
     * @param formFieldCode
     * @return
     */
    FormField queryByCode(String formFieldCode);
}
