package com.zynsun.platform.edoc.facade.impl.dynamicFormManage;

import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AreaField;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.domain.FormField;
import com.zynsun.platform.edoc.dto.dynamicForm.FormAreaDTO;
import com.zynsun.platform.edoc.facade.dynamicFormManage.FormAreaFacade;
import com.zynsun.platform.edoc.model.FormAreaModel;
import com.zynsun.platform.edoc.model.FormAreaModelDTO;
import com.zynsun.platform.edoc.service.AreaFieldService;
import com.zynsun.platform.edoc.model.BizAreaFieldModel;
import com.zynsun.platform.edoc.service.BizAreaFieldService;
import com.zynsun.platform.edoc.service.FormAreaService;
import com.zynsun.platform.edoc.service.FormFieldService;
import com.zynsun.platform.edoc.vo.FormAreaVO;
import com.zynsun.platform.edoc.vo.FormFieldVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service("formAreaFacade")
public class FormAreaFacadeImpl implements FormAreaFacade{
    private static final Logger AREA_LOGGER = LoggerFactory.getLogger(FormAreaFacadeImpl.class);

    @Autowired
    private FormAreaService formAreaService;

    @Autowired
    private FormFieldService formFieldService;

    @Autowired
    private BizAreaFieldService bizAreaFieldService;

    @Autowired
    private AreaFieldService areaFieldService;

    @Override
    public Map<String ,Object > findFormAreas() {
        Map<String ,Object > map = new HashMap<>();
        ExecuteResult<List<FormAreaVO>> result = new ExecuteResult<>();
        List<FormAreaVO> FormAreaVOList = new ArrayList<>();
        List<FormArea> formAreaLists = formAreaService.queryAll();
        if(!formAreaLists.isEmpty()){
            for(FormArea formArea : formAreaLists){
                FormAreaVO formAreaVO = new FormAreaVO();
                formAreaVO.setId(formArea.getId());
                formAreaVO.setFormAreaName(formArea.getFormAreaName());
                // 根据区域id 查字段
                List<FormField> formFields = formFieldService.findFormFields(formArea.getId());
                if(!formFields.isEmpty()){
                    formAreaVO.setFormFieldVOList(DozerUtil.mapList(formFields , FormFieldVO.class));
                }
                FormAreaVOList.add(formAreaVO);
           /*     // 暂时先做主表区
                if("主表区".equals(formAreaVO.getFormAreaName())){
                    Map<String , Object> resultMap = this.handlerFieldStr(formAreaVO.getFormFieldVOList());
                    map.put("fieldNameStr" , resultMap.get("fieldNameStr"));
                    map.put("fieldIdStr" , resultMap.get("fieldIdStr"));
                    map.put("areaId" , formAreaVO.getId());
                }*/

            }
        }
        result.setResult(FormAreaVOList);
        result.setSuccessMessage("查询所有区域和对应的字段信息成功");
        map.put("executeResult" , result);
        return map;
    }

    /**
     * 根据区域id  和 单据ID 查 字段
     * @param bizTypeId
     * @return
     */
    @Override
    public Map<String, Object> findFileds(Long bizTypeId) {
        Map<String ,Object > map = new HashMap<>();
        ExecuteResult<List<FormAreaVO>> result = new ExecuteResult<>();
        FormArea newFormArea = new FormArea();
        newFormArea.setFormAreaName("主表区");
        FormArea formArea = formAreaService.queryOne(newFormArea);
        BizAreaField bizAreaField = new BizAreaField();
        bizAreaField.setBizId(bizTypeId);
        bizAreaField.setFormAreaId(formArea.getId());
        List<BizAreaFieldModel> fields = bizAreaFieldService.selectFieldByBizIdAndAreaId(bizAreaField);
        Map<String, Object> newMap = this.handlerFieldStrTw(fields);
        map.put("areaId" , formArea.getId());
        map.put("fields" , fields);
        map.put("fieldNameStr" , newMap.get("fieldNameStr"));
        map.put("fieldIdStr" , newMap.get("fieldIdStr"));
        return map;

    }

    public  Map<String , Object> handlerFieldStrTw(List<BizAreaFieldModel> fields){
        Map<String , Object> map = new HashMap<>();
        String fieldNameStr = "";
        String fieldIdStr = "";
        for(BizAreaFieldModel bizAreaFieldModel : fields){
            fieldNameStr += bizAreaFieldModel.getFormFieldName() + ";";
            fieldIdStr += bizAreaFieldModel.getFormFieldId() + ";";
        }
        map.put("fieldNameStr",fieldNameStr);
        map.put("fieldIdStr",fieldIdStr);
        return map;
    }

    public  Map<String , Object> handlerFieldStr(List<FormFieldVO> formFieldVOList){
        Map<String , Object> map = new HashMap<>();
        String fieldNameStr = "";
        String fieldIdStr = "";
        for(FormFieldVO formFieldVO : formFieldVOList){
            fieldNameStr += formFieldVO.getFormFieldName() + ";";
            fieldIdStr += formFieldVO.getId() + ";";
        }
        map.put("fieldNameStr",fieldNameStr);
        map.put("fieldIdStr",fieldIdStr);
        return map;
    }

    @Override
    public ExecuteResult<List<FormAreaDTO>> findFormArea(FormAreaDTO formAreaDTO) {
        ExecuteResult<List<FormAreaDTO>> result = new ExecuteResult<>();
        try {
            FormArea formArea = DozerUtil.map(formAreaDTO, FormArea.class);
            List<FormArea> formAreaList = formAreaService.query(formArea);
            result.setResult(DozerUtil.mapList(formAreaList, FormAreaDTO.class));
            result.setSuccessMessage("查询表单区域成功");
        } catch (Exception e) {
            AREA_LOGGER.error("[查询表单区域]异常, 原因:{}", e);
            result.addErrorMessage("查询区域失败");
        }

        return result;
    }

    @Override
    public ExecuteResult<String> deleteFormAreaById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            FormArea formArea = formAreaService.queryById(id);
            if (formArea == null) {
                result.addErrorMessage("删除失败, 没有找到对应区域");
                result.setResult("该区域可能已被其他人删除, 请刷新后重试");
                return result;
            }
            formAreaService.remove(formArea);
        } catch (Exception e) {
            AREA_LOGGER.error("[删除表单区域]异常, 原因:{}", e);
            result.addErrorMessage("删除区域失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createFormArea(FormAreaDTO formAreaDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(formAreaDTO, "保存的区域不能为空");
            FormArea waitAddArea = DozerUtil.map(formAreaDTO, FormArea.class);
            // 区域关联的字段id
            String formFields = waitAddArea.getExtField01();

            // 情况1: 未选择关联字段, 只保存区域
            if (StringUtils.isEmpty(formFields)) {
                formAreaService.add(waitAddArea);
                result.setSuccessMessage("新增区域成功");
                result.setResult("新增区域成功");
                return result;
            }

            // 情况2: 已选择关联字段, 需要保存区域及区域和字段关联关系
            String[] fieldIdArray = formFields.split(";");
            if (BeanUtil.isEmpty(fieldIdArray)) {
                result.addErrorMessage("保存失败");
                return result;
            }
            // 组装区域和字段关联关系
            List<AreaField> areaFieldList = new ArrayList<>();
            for (String fieldId : fieldIdArray) {
                AreaField areaField = new AreaField();
                areaField.setFormFieldId(Long.valueOf(fieldId));
                areaFieldList.add(areaField);
            }
            // 新增区域及区域和字段关联关系
            int resultFlag = areaFieldService.AddAreaAndField(waitAddArea, areaFieldList);
            if (1 == resultFlag) {
                result.setSuccessMessage("新增区域成功");
                result.setResult("新增区域成功");
                return result;
            }
            result.addErrorMessage("新增区域失败");
        } catch (NumberFormatException e) {
            AREA_LOGGER.error("[新增表单区域]异常, 原因:{}", e);
            result.addErrorMessage("新增区域失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<FormAreaModelDTO> queryAreaModelDTOById(Long id) {
        ExecuteResult<FormAreaModelDTO> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            FormAreaModel areaModel = formAreaService.queryAreaModelById(id);
            FormAreaModelDTO areaModelDTO = DozerUtil.map(areaModel, FormAreaModelDTO.class);

            if (!BeanUtil.isEmpty(areaModel) || !BeanUtil.isEmpty(areaModel.getFormFieldList())) {
                StringBuilder fieldIds = new StringBuilder();
                StringBuilder fieldNames = new StringBuilder();
                for (FormField field : areaModel.getFormFieldList()) {
                    fieldIds.append(String.valueOf(field.getId())).append(";");
                    fieldNames.append(field.getFormFieldName()).append(";");
                }
                areaModelDTO.setFormFieldIds(fieldIds.toString());
                areaModelDTO.setFormFieldNames(fieldNames.toString());
            }

            result.setSuccessMessage("查询表单区域成功");
            result.setResult(areaModelDTO);
        } catch (Exception e) {
            AREA_LOGGER.error("[查询表单区域]异常, 原因:{}", e);
            result.addErrorMessage("查询表单区域失败");
        }

        return result;
    }

    @Override
    public ExecuteResult<String> editArea(FormAreaModelDTO formAreaModelDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(formAreaModelDTO, "编辑对象不能为空");
            FormArea waitEditArea = DozerUtil.map(formAreaModelDTO, FormArea.class);
            String fieldIds = formAreaModelDTO.getFormFieldIds();
            List<AreaField> areaFieldList = new ArrayList<>();
            if (!StringUtils.isEmpty(fieldIds)) {
                String[] fieldArray = fieldIds.split(";");
                if (!BeanUtil.isEmpty(fieldArray)) {
                    for (String fieldId : fieldArray) {
                        AreaField areaField = new AreaField();
                        areaField.setFormFieldId(Long.valueOf(fieldId));
                        areaFieldList.add(areaField);
                    }
                }
            }
            int resultFlag = areaFieldService.editAreaAndField(waitEditArea, areaFieldList);
            if (1 == resultFlag) {
                result.setSuccessMessage("编辑区域成功");
                result.setResult("编辑区域成功");
                return result;
            }
            result.addErrorMessage("编辑区域失败");
        } catch (NumberFormatException e) {
            AREA_LOGGER.error("[编辑表单区域]异常, 原因:{}", e);
            result.addErrorMessage("编辑表单区域失败");
        }

        return result;
    }

    @Override
    public ExecuteResult<FormAreaDTO> findOneFormArea(String areaCode) {
        ExecuteResult<FormAreaDTO> result = new ExecuteResult<>();

        try {
            Assert.notNull(areaCode, "区域代码不能为空");
            FormArea formArea = formAreaService.findAreaByCode(areaCode);
            if (formArea == null) {
                result.addErrorMessage("根据代码查询区域为空");
                return result;
            }
            FormAreaDTO areaDTO = DozerUtil.map(formArea, FormAreaDTO.class);
            result.setResult(areaDTO);
            result.setSuccessMessage("查询成功");
        } catch (Exception e) {
            AREA_LOGGER.error("[根据代码查询区域]异常, 原因:{}", e);
            result.addErrorMessage("查询区域失败");
        }

        return result;
    }

}
