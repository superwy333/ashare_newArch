package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.EdocImageMarkDetails;
import com.zynsun.platform.edoc.mapper.EdocImageMarkDetailsMapper;
import com.zynsun.platform.edoc.service.EdocImageMarkDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-07 11:16
 **/
@Service
public class EdocImageMarkDetailsServiceImpl extends BaseServiceImpl<EdocImageMarkDetails> implements EdocImageMarkDetailsService {

    @Autowired
    private EdocImageMarkDetailsMapper edocImageMarkDetailsMapper;

    @Override
    protected IMapper<EdocImageMarkDetails> getBaseMapper() {
        return edocImageMarkDetailsMapper;
    }
}
