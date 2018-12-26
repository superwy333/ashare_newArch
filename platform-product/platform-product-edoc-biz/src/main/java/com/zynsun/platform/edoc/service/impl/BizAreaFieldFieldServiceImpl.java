package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.mapper.BizAreaFieldMapper;
import com.zynsun.platform.edoc.model.BizAreaFieldModel;
import com.zynsun.platform.edoc.service.BizAreaFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:03
 **/
@Service
public class BizAreaFieldFieldServiceImpl extends BaseServiceImpl<BizAreaField> implements BizAreaFieldService {

    @Autowired
    private BizAreaFieldMapper bizAreaFieldMapper;

    @Override
    protected IMapper<BizAreaField> getBaseMapper() {
        return bizAreaFieldMapper;
    }

    @Override
    public List<BizAreaFieldModel> selectFieldByBizIdAndAreaId(BizAreaField record) {
        return bizAreaFieldMapper.selectFieldByBizIdAndAreaId(record);
    }
}
