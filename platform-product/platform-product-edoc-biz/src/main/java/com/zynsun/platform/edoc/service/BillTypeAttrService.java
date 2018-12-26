package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.BillTypeAttr;

import java.util.List;

/**
 * Created by Administrator on 2017-06-01 .
 */
public interface BillTypeAttrService extends BaseService<BillTypeAttr> {
    /****
     * 根据单证类型ID查询对应的的属性信息
     * @param billId
     * @return
     */
    List<BillTypeAttr> findBillAttrByBillId(Long billId);

    public BillTypeAttr queryByCode(String billTypeAttrCode);
}
