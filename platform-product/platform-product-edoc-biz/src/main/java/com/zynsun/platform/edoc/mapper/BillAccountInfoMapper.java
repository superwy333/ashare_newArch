package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BillAccountInfo;
import com.zynsun.platform.edoc.model.BillAccountModel;

public interface BillAccountInfoMapper extends IMapper<BillAccountInfo> {
    /**
     * 根据实体属性查询分页数据
     * @param billAccountModel
     * @return
     */
    Page<BillAccountModel> selectBillAccountPageList(BillAccountModel billAccountModel);
}