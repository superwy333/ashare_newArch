package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BillTypeAttr;

import java.util.List;

public interface BillTypeAttrMapper extends IMapper<BillTypeAttr> {
    /****
     * 根据单证类型ID查询对应的属性
     * @param billId
     * @return
     */
    List<BillTypeAttr> findBillAttrByBillId(Long billId);

    BillTypeAttr selectByCode(String code);
}