package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.EdocImageMark;
import com.zynsun.platform.edoc.mapper.EdocImageMarkMapper;
import com.zynsun.platform.edoc.model.EdocImageMarkModel;
import com.zynsun.platform.edoc.service.EdocImageMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:41
 **/
@Service
public class EdocImageMarkServiceImpl extends BaseServiceImpl<EdocImageMark> implements EdocImageMarkService {

    @Autowired
    private EdocImageMarkMapper edocImageMarkMapper;

    @Override
    protected IMapper<EdocImageMark> getBaseMapper() {
        return edocImageMarkMapper;
    }

    public Page<EdocImageMarkModel> queryMarkRecordsPageList(EdocImageMarkModel edocImageMarkModel) {
        return edocImageMarkMapper.queryMarkRecordsPageList(edocImageMarkModel);

    }


}
