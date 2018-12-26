package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.EdocHeaderCredentials;
import com.zynsun.platform.edoc.mapper.EdocHeaderCredentialsMapper;
import com.zynsun.platform.edoc.service.EdocHeaderCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jary
 * @creatTime 2018/12/4 9:07 AM
 */
@Service
public class EdocHeaderCredentialsServiceImpl  extends BaseServiceImpl<EdocHeaderCredentials> implements EdocHeaderCredentialsService {
    @Autowired
    EdocHeaderCredentialsMapper edocHeaderCredentialsMapper;
    @Override
    protected IMapper<EdocHeaderCredentials> getBaseMapper() {
        return edocHeaderCredentialsMapper;
    }
}
