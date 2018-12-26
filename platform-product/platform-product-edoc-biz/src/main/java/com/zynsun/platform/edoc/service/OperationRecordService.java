package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.OperationRecord;
import com.zynsun.platform.edoc.model.EdocReportModel;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:26
 **/
public interface OperationRecordService extends BaseService<OperationRecord> {

    List<EdocReportModel> selectEdocReport(Map<String,Object> queryTime);

}
