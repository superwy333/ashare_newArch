package com.zynsun.platform.edoc.facade.impl.dynamicFormManage;

import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.FormField;
import com.zynsun.platform.edoc.dto.dynamicForm.FormFieldDTO;
import com.zynsun.platform.edoc.facade.dynamicFormManage.FormFieldFacade;
import com.zynsun.platform.edoc.service.FormFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service("formFieldFacade")
public class FormFieldFacadeImpl implements FormFieldFacade {

    private static final Logger Field_LOGGER = LoggerFactory.getLogger(FormFieldFacadeImpl.class);

    @Autowired
    private FormFieldService formFieldService;

    @Override
    public ExecuteResult<List<FormFieldDTO>> findField(FormFieldDTO fieldDTO) {
        ExecuteResult<List<FormFieldDTO>> result = new ExecuteResult<>();
        try {
            FormField formField = DozerUtil.map(fieldDTO, FormField.class);
            List<FormField> formFieldResult = formFieldService.query(formField);
            result.setResult(DozerUtil.mapList(formFieldResult, FormFieldDTO.class));
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            result.addErrorMessage("查询字段失败");
            Field_LOGGER.error("[表单字段查询]异常, 原因:{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<Long> createFormField(FormFieldDTO fieldDTO) {
        ExecuteResult<Long> result = new ExecuteResult<>();
        try {
            Assert.notNull(fieldDTO, "新增字段信息不能为空");
            FormField waitHandleField = DozerUtil.map(fieldDTO, FormField.class);

            if (BeanUtil.isEmpty(waitHandleField.getId())) {
                // 不存在新增
                FormField isExistFieldCode = formFieldService.queryByCode(waitHandleField.getFormFieldCode());
                if (isExistFieldCode == null || isExistFieldCode.getId() == null) {
                    formFieldService.add(waitHandleField);
                    result.setSuccessMessage("新增字段信息成功!");
                    result.setResult(waitHandleField.getId());
                }
            } else {
                // 编辑
                formFieldService.updateByIdSelective(waitHandleField);
                result.setSuccessMessage("编辑字段信息成功!");
                result.setResult(waitHandleField.getId());
            }

        } catch (Exception e) {
            result.addErrorMessage("处理失败!");
            Field_LOGGER.error("[字段新增或编辑]异常, 原因:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<FormFieldDTO> findFieldById(Long id) {
        ExecuteResult<FormFieldDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(id, "id不能为空");
            FormField formField = formFieldService.queryById(id);

            if (formField == null) {
                result.addErrorMessage("查询失败");
                return result;
            }
            result.setResult(DozerUtil.map(formField, FormFieldDTO.class));
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            result.addErrorMessage("处理失败!");
            Field_LOGGER.error("[字段新增或编辑]异常, 原因:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> deleteFormFieldById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            FormField formField = formFieldService.queryById(id);
            if (formField == null) {
                result.setResult("该字段可能已被其他人删除, 请刷新后重试");
                result.addErrorMessage("删除失败");
                return result;
            }
            formFieldService.remove(formField);
            result.setResult("删除字段成功");
            result.setSuccessMessage("删除字段成功");
        } catch (Exception e) {
            result.addErrorMessage("删除失败!");
            Field_LOGGER.error("[删除字段]异常, 原因:{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<FormFieldDTO> findOneFormField(String fieldCode) {
        ExecuteResult<FormFieldDTO> result = new ExecuteResult<>();

        try {
            Assert.notNull(fieldCode, "字段代码不能为空");
            FormField formField = formFieldService.queryByCode(fieldCode);
            if (formField == null) {
                result.addErrorMessage("根据字段代码查询字段失败");
                return result;
            }
            FormFieldDTO fieldDTO = DozerUtil.map(formField, FormFieldDTO.class);
            result.setResult(fieldDTO);
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            result.addErrorMessage("查询失败!");
            Field_LOGGER.error("[根据字段代码查询字段信息]异常, 原因:{}", e);
        }
        return result;
    }

}
