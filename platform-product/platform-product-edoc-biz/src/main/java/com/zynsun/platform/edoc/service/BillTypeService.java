package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.model.BillTypeModel;

import java.util.List;

/**
 * Created by Administrator on 2017-06-01 .
 */
public interface BillTypeService extends BaseService<BillType> {

    List<BillType> findBillTypeByBizId(Long bizId);

    Integer createBillType(BillType billType);

    public BillType queryByCode(String billTypeCode);

    public BillTypeModel queryBillTypeModelById(Long id);

    public List<BillType> queryBillTypesByBizCode(String bizTypeCode);

}
