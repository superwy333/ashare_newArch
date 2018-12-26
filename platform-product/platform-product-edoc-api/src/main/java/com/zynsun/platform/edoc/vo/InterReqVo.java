package com.zynsun.platform.edoc.vo;

/**
 * @author jary
 * @creatTime 2018/11/30 10:29 AM
 */
public class InterReqVo {

    /**
     * 接口调用标识
     */
    private String interfaceFlag;

    /**
     * 封面条码
     */
    private String coverNo;

    /**
     * 任务 id
     */
    private Long taskId;

    /**
     * 审核状态
     * 1=未审核、2=审核中、3=审核通过、4=审核不通过
     */
    private String status;

    /**
     * 任务状态
     * 07=待采集、00=采集中、01=已采集、02=待补采、20=待作废、21=作废、32=待重采
     */
    private String edocTaskStatus;


    public String getCoverNo() {
        return coverNo;
    }

    public void setCoverNo(String coverNo) {
        this.coverNo = coverNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getInterfaceFlag() {
        return interfaceFlag;
    }

    public void setInterfaceFlag(String interfaceFlag) {
        this.interfaceFlag = interfaceFlag;
    }

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }
}
