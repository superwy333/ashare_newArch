package com.zynsun.platform.edoc.dto;

import com.zynsun.platform.edoc.dto.baseDTO.BaseEdocDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @create 2017/12/27
 * @modify
 */
public class BillAccountModelDTO extends BaseEdocDTO {

    /**
     * 影像任务ID
     */
    private Long edocHeaderId;
    /**
     * 电子影像编号
     */
    private String edocNo;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交人代码
     */
    private String submitterCode;

    /**
     * 提交时间
     */
    private Date submitterTime;

    private BigDecimal billAmountTotal;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 流程实力ID
     */
    private String processInstanceId;
    /**
     * 流程业务主键
     */
    private String businessKey;
    /**
     * 流程定义ID
     */
    private String processDefinitionId;
    /**
     * 流程启动时间
     */
    private Date startTime;

    /**
     * 流程结束时间
     */
    private Date endTime;
    /**
     * 流程启动人code
     */
    private String startUserId;
    /**
     * 完成状态
     */
    private String finish;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    /**
     * edocHeader的version
     */
    private Long hVersion;

    public Long gethVersion() {
        return hVersion;
    }

    public void sethVersion(Long hVersion) {
        this.hVersion = hVersion;
    }
    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getSubmitterCode() {
        return submitterCode;
    }

    public void setSubmitterCode(String submitterCode) {
        this.submitterCode = submitterCode;
    }

    public Date getSubmitterTime() {
        return submitterTime;
    }

    public void setSubmitterTime(Date submitterTime) {
        this.submitterTime = submitterTime;
    }

    public BigDecimal getBillAmountTotal() {
        return billAmountTotal;
    }

    public void setBillAmountTotal(BigDecimal billAmountTotal) {
        this.billAmountTotal = billAmountTotal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    public String getExtField5() {
        return extField5;
    }

    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

}
