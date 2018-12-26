package com.zynsun.platform.edoc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.domain.FormField;
import com.zynsun.platform.edoc.domain.FormTemplate;
import com.zynsun.platform.edoc.dto.dynamicForm.FormAreaDTO;
import com.zynsun.platform.edoc.mapper.BizAreaFieldMapper;
import com.zynsun.platform.edoc.mapper.FormAreaMapper;
import com.zynsun.platform.edoc.model.FormAreaModel;
import com.zynsun.platform.edoc.mapper.*;
import com.zynsun.platform.edoc.service.*;
import com.zynsun.platform.edoc.vo.FormAreaVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service
public class FormAreaServiceImpl extends BaseServiceImpl<FormArea> implements FormAreaService{

    private static final Logger logger = LoggerFactory.getLogger(FormAreaServiceImpl.class);

    @Autowired
    private FormAreaMapper formAreaMapper;
    @Autowired
    private BizAreaFieldService bizAreaFieldService;
    @Autowired
    private BizAreaFieldMapper bizAreaFieldMapper;
    @Autowired
    private FormFieldService formFieldService;
    @Autowired
    private BizTypeMapper bizTypeMapper;
    @Autowired
    private FormTemplateService formTemplateService;
    @Autowired
    private FormTemplateMapper formTemplateMapper;

    @Override
    protected IMapper<FormArea> getBaseMapper() {
        return formAreaMapper;
    }

    @Override
    public ExecuteResult<String> saveForm(JSONArray jsonArray) {
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try {
            // 根据bizId 和 areaID删除
            JSONObject jsonOb = jsonArray.getJSONObject(0);
            BizAreaField bizAreaFie = new BizAreaField();
            bizAreaFie.setBizId(jsonOb.getLong("bizId"));
            bizAreaFie.setFormAreaId(jsonOb.getLong("formAreaId"));
            bizAreaFieldMapper.deleteBizAreaField(bizAreaFie);
            Iterator<Object> iterator = jsonArray.iterator();
            List<FormField> formFields = new ArrayList<>();
            // 循环插入 biz_area_field , 然后添加json 模版
            while (iterator.hasNext()){
                JSONObject jsonObject =  (JSONObject)iterator.next();
                Long bizId = Long.valueOf(jsonObject.getString("bizId"));
                Long formAreaId = Long.valueOf(jsonObject.getString("formAreaId"));
                Long formFieldId = Long.valueOf(jsonObject.getString("formFieldId"));
                BizAreaField bizAreaField = new BizAreaField();
                bizAreaField.setBizId(bizId);
                bizAreaField.setFormAreaId(formAreaId);
                bizAreaField.setFormFieldId(formFieldId);
                bizAreaFieldService.add(bizAreaField);
                FormField formField = formFieldService.queryById(formFieldId);
                formFields.add(formField);
            }
            String tempJson = this.handlerTempJson(formFields); // 处理模版json
            FormTemplate formTemplate = new FormTemplate();
            formTemplate.setBizId(jsonOb.getLong("bizId"));
            formTemplate.setBizName(bizTypeMapper.selectByPrimaryKey(jsonOb.getLong("bizId")).getName());
            formTemplate.setFormTemplateJson(tempJson);
            formTemplateMapper.deleteByBizId(jsonOb.getLong("bizId")); // 先删除在新增
            formTemplateService.add(formTemplate);
            executeResult.setSuccessMessage("新增字段成功");
            executeResult.setResult("新增字段成功");
        }catch (Exception e){
            logger.error("修改表单字段异常,{} " , e);
            executeResult.addErrorMessage("修改表单字段异常,请联系管理员!");
        }
        return executeResult;
    }

    public String handlerTempJson( List<FormField> formFields){
        JSONObject jsonObject = new JSONObject();
        for(FormField formField: formFields){
            jsonObject.put(formField.getFormFieldName() , formField.getFormFieldCode());
        }
        return jsonObject.toString();
    }

    @Override
    public FormAreaModel queryAreaModelById(Long id) {
        return formAreaMapper.selectAreaModelById(id);
    }

    @Override
    public FormArea findAreaByCode(String areaCode) {
        return formAreaMapper.findAreaByCode(areaCode);
    }
}
