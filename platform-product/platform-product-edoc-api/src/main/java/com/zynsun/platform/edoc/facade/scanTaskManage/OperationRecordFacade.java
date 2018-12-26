package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocReportDTO;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:29
 **/
public interface OperationRecordFacade {



    ExecuteResult<List<EdocReportDTO>> parseEdocReport(Map<String,Object> queryCondition);


}
