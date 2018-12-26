package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.EdocScanRemarks;
import com.zynsun.platform.edoc.mapper.EdocScanRemarksMapper;
import com.zynsun.platform.edoc.model.EdocScanRemarksModel;
import com.zynsun.platform.edoc.service.EdocScanRemarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:10
 **/
@Service
public class EdocScanRemarksServiceImpl extends BaseServiceImpl<EdocScanRemarks> implements EdocScanRemarksService {

    @Autowired
    private EdocScanRemarksMapper edocScanRemarksMapper;

    @Override
    protected IMapper<EdocScanRemarks> getBaseMapper() {
        return edocScanRemarksMapper;
    }


    @Override
    public Page<EdocScanRemarksModel> queryByPage(EdocScanRemarksModel edocScanRemarksModel) {
        return edocScanRemarksMapper.selectByPage(edocScanRemarksModel);
    }
}
