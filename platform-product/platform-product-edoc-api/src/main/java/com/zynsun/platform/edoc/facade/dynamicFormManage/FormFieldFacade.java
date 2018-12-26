package com.zynsun.platform.edoc.facade.dynamicFormManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.dynamicForm.FormFieldDTO;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:00
 **/
public interface FormFieldFacade {

    /**
     * 查询字段列表
     * @param fieldDTO
     * @return
     */
    ExecuteResult<List<FormFieldDTO>> findField(FormFieldDTO fieldDTO);

    /**
     * 新增或修改字段
     * @param fieldDTO
     * @return
     */
    ExecuteResult<Long> createFormField(FormFieldDTO fieldDTO);

    /**
     * 根据表单字段id查询
     * @param id
     * @return
     */
    ExecuteResult<FormFieldDTO> findFieldById(Long id);

    /**
     * 根据id删除表单字段
     * @param id
     * @return
     */
    ExecuteResult<String> deleteFormFieldById(Long id);

    ExecuteResult<FormFieldDTO> findOneFormField(String fieldCode);
}
