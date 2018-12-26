package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.mapper.BillTypeMapper;
import com.zynsun.platform.edoc.model.BillTypeModel;
import com.zynsun.platform.edoc.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class BillTypeServiceImpl extends BaseServiceImpl<BillType> implements BillTypeService {
    @Autowired
    private BillTypeMapper billTypeMapper;

    @Override
    protected IMapper<BillType> getBaseMapper() {
        return billTypeMapper;
    }

    @Override
    public List<BillType> findBillTypeByBizId(Long bizId) {
        return billTypeMapper.findBillTypeByBizId(bizId);
    }

    @Override
    public Integer createBillType(BillType billType) {
        return this.add (billType);
    }

    @Override
    public BillType queryByCode(String billTypeCode) {
        return billTypeMapper.selectByBillTypeCode(billTypeCode);
    }

    /**
     * 根据id查询业务类型及其关联的单证类型
     * @param id
     * @return
     */
    @Override
    public BillTypeModel queryBillTypeModelById(Long id){
        return billTypeMapper.selectBillTypeModel(id);
    }

    @Override
    public List<BillType> queryBillTypesByBizCode(String bizTypeCode) {
        return billTypeMapper.selectBillTypesByBizCode(bizTypeCode);
    }
}
