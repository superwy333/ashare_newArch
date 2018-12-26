package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.BizTypeStyle;
import com.zynsun.platform.edoc.mapper.BizTypeStyleMapper;
import com.zynsun.platform.edoc.service.BizTypeStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/8 17:18
 * @Modified By：
 */
@Service
public class BizTypeStyleServiceImpl extends BaseServiceImpl<BizTypeStyle> implements BizTypeStyleService {
    @Autowired
    private BizTypeStyleMapper bizTypeStyleMapper;

    @Override
    protected IMapper<BizTypeStyle> getBaseMapper() {
        return bizTypeStyleMapper;
    }
}
