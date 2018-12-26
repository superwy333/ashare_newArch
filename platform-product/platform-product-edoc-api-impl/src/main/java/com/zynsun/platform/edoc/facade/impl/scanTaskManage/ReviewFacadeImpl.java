package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.fastjson.JSON;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.TaskParamDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.ReviewFacade;
import com.zynsun.platform.edoc.service.EdocImageService;
//import com.zynsun.platform.workflow.facade.WfTaskFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 16:30 2018/1/4
 * @Modified By:
 */
@Service("reviewFacade")
public class ReviewFacadeImpl implements ReviewFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewFacadeImpl.class);

//    @Autowired
//    private WfTaskFacade wfTaskFacade;
    @Autowired
    private EdocImageService edocImageService;

    @Override
    public ExecuteResult<Boolean> review(TaskParamDTO taskParamDTO) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try{
            Map<String,Object> variables = new HashMap<>();
            if(StringUtils.isNotEmpty(taskParamDTO.getSequenceFlow())){
                variables.put("sequenceFlow",taskParamDTO.getSequenceFlow());
            }

            if(StringUtils.isNotBlank(taskParamDTO.getEdocImageIds())){
                List<Long> ids = JSON.parseArray(taskParamDTO.getEdocImageIds(),Long.class);
                if(!ids.isEmpty()){
                    variables.put("edocImageIds",ids);
                }
            }

            if(StringUtils.isNotEmpty(taskParamDTO.getMessage())&&StringUtils.isNotEmpty(taskParamDTO.getTaskId())){
                variables.put("taskId",taskParamDTO.getTaskId());
                variables.put("message",taskParamDTO.getMessage());
            }
//            result = wfTaskFacade.completeTask(taskParamDTO.getBusinessKey(),taskParamDTO.getTaskId(),taskParamDTO.getTaskKey(),variables);
        }catch (Exception e){
            LOGGER.error("评价失败",e);
            result.addErrorMessage("评价失败");
        }
        return result;
    }
}
