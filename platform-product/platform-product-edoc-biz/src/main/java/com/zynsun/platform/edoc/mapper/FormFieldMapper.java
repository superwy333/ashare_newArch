package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.FormField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FormFieldMapper extends IMapper<FormField> {

    List<FormField> findFormFields(@Param("formAreaId") Long formAreaId);

    /**
     * 根据字段代码查询字段
     * @param formFieldCode
     * @return
     */
    FormField selectByCode(String formFieldCode);
}