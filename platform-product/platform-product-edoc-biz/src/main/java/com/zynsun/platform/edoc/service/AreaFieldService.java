package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AreaField;
import com.zynsun.platform.edoc.domain.FormArea;

import java.util.List;

/**
 * @Description：区域字段关联接口
 * @Author：YongChen
 * @Date：Created in 2018/5/23 14:13
 * @Modified By：
 */
public interface AreaFieldService extends BaseService<AreaField> {

    /**
     * 新增区域和关联关系
     * @param waitAddArea
     * @param areaFieldList
     * @return
     */
    int AddAreaAndField(FormArea waitAddArea, List<AreaField> areaFieldList);

    /**
     * 编辑区域和关联关系
     * @param waitEditArea
     * @param areaFieldList
     * @return
     */
    int editAreaAndField(FormArea waitEditArea, List<AreaField> areaFieldList);
}
