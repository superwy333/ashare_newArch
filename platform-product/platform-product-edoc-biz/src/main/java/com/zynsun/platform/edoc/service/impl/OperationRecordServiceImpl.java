package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.OperationRecord;
import com.zynsun.platform.edoc.mapper.OperationRecordMapper;
import com.zynsun.platform.edoc.model.EdocReportModel;
import com.zynsun.platform.edoc.service.OperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:27
 **/
@Service
public class OperationRecordServiceImpl extends BaseServiceImpl<OperationRecord> implements OperationRecordService {

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    @Override
    protected IMapper<OperationRecord> getBaseMapper() {
        return operationRecordMapper;
    }

    @Override
    public List<EdocReportModel> selectEdocReport(Map<String, Object> queryTime) {
        return operationRecordMapper.selectEdocReportNew(queryTime);
    }
}
