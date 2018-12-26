package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.model.TreeModel;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.CostType;
import com.zynsun.platform.edoc.mapper.CostTypeMapper;
import com.zynsun.platform.edoc.service.CostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 费用类型
 * @Auther: YongChen
 * @Date: Create in 2018/7/24 15:22
 * @Modified By:
 */
@Service
public class CostTypeServiceImpl extends BaseServiceImpl<CostType> implements CostTypeService {

    @Autowired
    private CostTypeMapper costTypeMapper;

    @Override
    protected IMapper<CostType> getBaseMapper() {
        return costTypeMapper;
    }

    @Override
    public List<TreeModel> queryCostTypeToTree(Integer pageNum, Integer pageSize, String fNameOrFid) {
        return costTypeMapper.selectCostTypeToTree((pageNum - 1) * pageSize, pageSize, fNameOrFid);
    }
}
