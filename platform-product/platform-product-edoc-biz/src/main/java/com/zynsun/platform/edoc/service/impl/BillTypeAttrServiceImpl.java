package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.BillTypeAttr;
import com.zynsun.platform.edoc.mapper.BillTypeAttrMapper;
import com.zynsun.platform.edoc.service.BillTypeAttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-06-01 .
 */

@Service
public class BillTypeAttrServiceImpl extends BaseServiceImpl<BillTypeAttr> implements BillTypeAttrService {
    @Resource
    private BillTypeAttrMapper billTypeAttrMapper;
    @Override
    public IMapper<BillTypeAttr> getBaseMapper() {
        return billTypeAttrMapper;
    }

    @Override
    public List<BillTypeAttr> findBillAttrByBillId(Long billId) {
        return billTypeAttrMapper.findBillAttrByBillId(billId);
    }

    @Override
    public BillTypeAttr queryByCode(String billTypeCode) {
        return billTypeAttrMapper.selectByCode(billTypeCode);
    }
}

