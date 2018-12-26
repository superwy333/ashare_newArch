package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.BaseDTO;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:48 2018/1/4
 * @Modified By:
 */
public class TaskParamDTO extends BaseDTO {

    private String taskId;

    private String taskKey;

    private String businessKey;

    private String sequenceFlow;

    private String message;

    private String edocImageIds;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getSequenceFlow() {
        return sequenceFlow;
    }

    public void setSequenceFlow(String sequenceFlow) {
        this.sequenceFlow = sequenceFlow;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEdocImageIds() {
        return edocImageIds;
    }

    public void setEdocImageIds(String edocImageIds) {
        this.edocImageIds = edocImageIds;
    }
}
