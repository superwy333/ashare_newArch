package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.model.TreeModel;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.CostType;

import java.util.List;

/**
 * @Description: 费用类型
 * @Auther: YongChen
 * @Date: Create in 2018/7/24 15:21
 * @Modified By:
 */
public interface CostTypeService extends BaseService<CostType> {
    List<TreeModel> queryCostTypeToTree(Integer pageNum, Integer pageSize, String fNameOrFid);
}
