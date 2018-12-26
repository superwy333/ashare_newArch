package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.OperationRecord;
import com.zynsun.platform.edoc.model.EdocReportModel;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:24
 **/
public interface OperationRecordMapper extends IMapper<OperationRecord> {

    List<EdocReportModel> selectEdocReportNew(Map<String,Object> map);
    
}
