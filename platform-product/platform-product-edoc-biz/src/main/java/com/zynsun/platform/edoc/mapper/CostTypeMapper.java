package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.model.TreeModel;
import com.zynsun.platform.edoc.domain.CostType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostTypeMapper extends IMapper<CostType> {

    /**
     * 查询树
     *
     */
    List<TreeModel> selectCostTypeToTree(@Param(value = "beginSize") Integer beginSize, @Param(value = "pageSize") Integer pageSize, @Param(value = "fNameOrFid") String fNameOrFid);

}