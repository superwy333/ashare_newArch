package com.zynsun.platform.edoc.facade.impl.scanTaskManage;


import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocReportDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.OperationRecordFacade;
import com.zynsun.platform.edoc.model.EdocReportModel;
import com.zynsun.platform.edoc.service.OperationRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:30
 **/
@Service("operationRecordFacade")
public class OperationRecordFacadeImpl implements OperationRecordFacade {

    private static final Logger LOGGER  = LoggerFactory.getLogger(OperationRecordFacadeImpl.class);
    @Autowired
    private OperationRecordService operationRecordService;

    @Override
    public ExecuteResult<List<EdocReportDTO>> parseEdocReport(Map<String,Object> queryTime) {
        ExecuteResult<List<EdocReportDTO>> result = new ExecuteResult<>();
        try {
            List<EdocReportModel> edocReportModelList = operationRecordService.selectEdocReport(queryTime);
            List<EdocReportDTO> edocReportDTOList = DozerUtil.mapList(edocReportModelList,EdocReportDTO.class);
            result.setResult(edocReportDTOList);
            result.setSuccessMessage("生成成功");
        }catch (Exception e) {
            result.addErrorMessage("生成失败！");
            LOGGER.error("生成失败！", e);
        }
        return result;
    }
}
