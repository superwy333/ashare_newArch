package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 9:57
 * @Modified By:
 */
public class ArchiveMoveDTO extends PageDTO {

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    //关联档案id
    private Long archiveId;

    //关联档案标识号，如果是册，就是册号，如果是盒，就是盒子号
    private String archiveNo;

    //移交类型：单据、册、盒、箱
    private String archiveType;

    //描述
    private String remark;

    //转入机构
    private String toCompany;

    //转入全宗
    private String toArchiveHeaderName;

    //转出机构
    private String fromCompany;

    //转出全宗
    private String fromArchiveHeaderName;

    //转出责任人
    private String fromChargePerson;

    //转入责任人
    private String toChargePerson;

    //状态
    private String status;

    //流程操作状态
    private String processStatus;

    //移交日期
    private String moveDate;

    private String taskAssingee;

    private String tenantId;

    //工作流专用属性 start
    private String taskName;

    private String taskId;

    private String taskKey;

    private String businessKey;

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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

    //工作流装用属性 end


    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }

    public String getTaskAssingee() {
        return taskAssingee;
    }

    public void setTaskAssingee(String taskAssingee) {
        this.taskAssingee = taskAssingee;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(String moveDate) {
        this.moveDate = moveDate;
    }

    public String getFromChargePerson() {
        return fromChargePerson;
    }

    public void setFromChargePerson(String fromChargePerson) {
        this.fromChargePerson = fromChargePerson;
    }

    public String getToChargePerson() {
        return toChargePerson;
    }

    public void setToChargePerson(String toChargePerson) {
        this.toChargePerson = toChargePerson;
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToCompany() {
        return toCompany;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public String getToArchiveHeaderName() {
        return toArchiveHeaderName;
    }

    public void setToArchiveHeaderName(String toArchiveHeaderName) {
        this.toArchiveHeaderName = toArchiveHeaderName;
    }

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public String getFromArchiveHeaderName() {
        return fromArchiveHeaderName;
    }

    public void setFromArchiveHeaderName(String fromArchiveHeaderName) {
        this.fromArchiveHeaderName = fromArchiveHeaderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
}
