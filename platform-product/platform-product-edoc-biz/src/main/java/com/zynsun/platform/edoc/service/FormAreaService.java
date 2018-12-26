package com.zynsun.platform.edoc.service;

import com.alibaba.fastjson.JSONArray;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AreaField;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.model.FormAreaModel;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:00
 **/
public interface FormAreaService extends BaseService<FormArea> {

    ExecuteResult<String> saveForm(JSONArray jsonArray);

    /**
     * 根据区域id查询区域及区域关联的字段
     *
     * @param id
     * @return
     */
    FormAreaModel queryAreaModelById(Long id);

    FormArea findAreaByCode(String areaCode);
}
