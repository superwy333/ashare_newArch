package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.UserDefinedActivity;
import com.zynsun.platform.edoc.mapper.UserDefinedActivityMapper;
import com.zynsun.platform.edoc.service.UserDefinedActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 13:43 2018/2/9
 * @Modified By:
 */
@Service
public class UserDefinedActivityServiceImpl extends BaseServiceImpl<UserDefinedActivity> implements UserDefinedActivityService {

    @Autowired
    private UserDefinedActivityMapper userDefinedActivityMapper;

    @Override
    protected IMapper<UserDefinedActivity> getBaseMapper() {
        return userDefinedActivityMapper;
    }
}
