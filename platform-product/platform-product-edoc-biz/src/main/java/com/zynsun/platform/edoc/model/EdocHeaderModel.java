package com.zynsun.platform.edoc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zynsun.openplatform.domain.PageModel;
import com.zynsun.platform.edoc.domain.EdocImage;

import java.util.Date;
import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:35 2018/1/2
 * @Modified By:
 */
public class EdocHeaderModel extends PageModel {
    /**
     * 电子影像编号
     */
    private String edocNo;

    /**
     * 凭证号
     */
    private String edocVoucherNo;

    /**
     * 业务类型（报账单/合同等，详细见数据字典配置）
     */
    private String edocType;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 影像任务的业务状态（具体视不通业务而定）
     */
    private String edocTaskStatus;

    /**
     * 影像任务父级ID（为满足影像任务树状结构设计）
     */
    private Long parentId;

    /**
     * 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    private String edocIsMatched;

    /**
     * 实物状态（实体单证的状态）
     */
    private String edocPhysicalStatus;

    /**
     * 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     */
    private String sysSource;

    /**
     * 影像任务对应的影像的总张数（用来匹配客户端上传的影像张数）
     */
    private Integer totalNum;

    /**
     * 是否锁定（影像任务锁定）
     */
    private String isLocked;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0: 未处理 1：待认证
     */
    private Integer edocCertStatus;

    /**
     * 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等）
     */
    private String edocSource;

    /**
     * 0:未处理 1.已获取费用凭证 2.已获取付款凭证 3：已获取pdf
     */
    private Integer edocVoucherStatus;

    /**
     * 0:未处理 1：待归档
     */
    private Integer edocArchiveStatus;

    private String edocImageType;//单证类型


    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    //做影像删除标注
    //当该单据有‘标记记录’，只能删除带有标记的影像图片（删除is_replace==1的），否则可以全部都删除
    //当有标记时，把有标记的影像全删了后，就相当于的没有标记记录了
    //这样是不行的，需要在流程再次发起前一直不可以删除
    private String extField5;

    private String jsonData;

    // formTemplate表的模版字段
    private String formTemplateJson;

    private Date taskCreateTime;

    /**
     * 流程实力ID
     */
    private String processInstanceId;

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

    private String createTimeStr;

    private String lastModifyTimeStr;

    private String fondsCode;

    private String voucherCode;

    private String expenseDate;

    private String borrowCreateBy;

    private String filesNo;

    private String status;

    private String publicType;

    private String uploadType;

    private String sysFlag;

    private String uploadFinished;

    private String scanFinished;

    private String edocPhysicalReceiver;

    private Long imgId;//edoc_image的主键id

    /**
     * 创建时间开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeStart;

    /**
     * 创建时间结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeEnd;

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    /**
     * 是否需要完成扫描  0：是， 1：否
     */
    private String scanFlag;
    /**
     * id
     */
    private Long sectionId;
    /**
     * 凭证编号
     */
    private String credentialsNum;

    /**
     * 凭证类型
     */
    private String credentialsType;

    /**
     * 归档id
     */
    private Long archivesId;

    private List<EdocImageModel> edocImageList;

    private String edocHeaderId;

    private String bizNo;
    private String edocTaxCode;
    private String isAbnormal;

    private String code;

    private String msg;

    public EdocHeaderModel() {
    }

    public EdocHeaderModel(String edocNo, String code,String msg) {
        this.edocNo = edocNo;
        this.code = code;
        this.msg = msg;
    }
    public EdocHeaderModel(String edocNo, String code) {
        this.edocNo = edocNo;
        this.code = code;
    }

    public String getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(String isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getEdocTaxCode() {
        return edocTaxCode;
    }

    public void setEdocTaxCode(String edocTaxCode) {
        this.edocTaxCode = edocTaxCode;
    }

    public String getEdocSource() {
        return edocSource;
    }

    public void setEdocSource(String edocSource) {
        this.edocSource = edocSource;
    }

    public Long getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Long archivesId) {
        this.archivesId = archivesId;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getEdocImageType() {
        return edocImageType;
    }

    public void setEdocImageType(String edocImageType) {
        this.edocImageType = edocImageType;
    }

    public String getEdocPhysicalReceiver() {
        return edocPhysicalReceiver;
    }

    public void setEdocPhysicalReceiver(String edocPhysicalReceiver) {
        this.edocPhysicalReceiver = edocPhysicalReceiver;
    }

    public String getUploadFinished() {
        return uploadFinished;
    }

    public void setUploadFinished(String uploadFinished) {
        this.uploadFinished = uploadFinished;
    }

    public String getScanFinished() {
        return scanFinished;
    }

    public void setScanFinished(String scanFinished) {
        this.scanFinished = scanFinished;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public String getBorrowCreateBy() {
        return borrowCreateBy;
    }

    public void setBorrowCreateBy(String borrowCreateBy) {
        this.borrowCreateBy = borrowCreateBy;
    }

    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getFondsCode() {
        return fondsCode;
    }

    public void setFondsCode(String fondsCode) {
        this.fondsCode = fondsCode;
    }

    public String getLastModifyTimeStr() {
        return lastModifyTimeStr;
    }

    public void setLastModifyTimeStr(String lastModifyTimeStr) {
        this.lastModifyTimeStr = lastModifyTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
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

    public String getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(String scanFlag) {
        this.scanFlag = scanFlag;
    }

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

    public String getFormTemplateJson() {
        return formTemplateJson;
    }

    public void setFormTemplateJson(String formTemplateJson) {
        this.formTemplateJson = formTemplateJson;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    /**
     * 挂起状态 2为已挂起 1为正常
     */
    private String suspensionState;

    public String getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(String suspensionState) {
        this.suspensionState = suspensionState;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    private String taskId;

    private String taskKey;

    private String taskName;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    private String businessKey;

    private String executionId;

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    private String tenantId;

    public String getTaskAssingee() {
        return taskAssingee;
    }

    public void setTaskAssingee(String taskAssingee) {
        this.taskAssingee = taskAssingee;
    }

    private String taskAssingee;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public boolean isStartProcessFlag() {
        return startProcessFlag;
    }

    public void setStartProcessFlag(boolean startProcessFlag) {
        this.startProcessFlag = startProcessFlag;
    }

    private boolean startProcessFlag;

    /**
     * 获取电子影像编号
     *
     * @return edoc_no - 电子影像编号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置电子影像编号
     *
     * @param edocNo 电子影像编号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getEdocVoucherNo() {
        return edocVoucherNo;
    }

    public void setEdocVoucherNo(String edocVoucherNo) {
        this.edocVoucherNo = edocVoucherNo;
    }

    /**
     * 获取业务类型（报账单/合同等，详细见数据字典配置）
     *
     * @return biz_type - 业务类型（报账单/合同等，详细见数据字典配置）
     */
    public String getEdocType() {
        return edocType;
    }

    /**
     * 设置业务类型（报账单/合同等，详细见数据字典配置）
     *
     * @param edocType 业务类型（报账单/合同等，详细见数据字典配置）
     */
    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    /**
     * 获取影像任务的业务状态（具体视不通业务而定）
     *
     * @return edoc_task_status - 影像任务的业务状态（具体视不通业务而定）
     */
    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    /**
     * 设置影像任务的业务状态（具体视不通业务而定）
     *
     * @param edocTaskStatus 影像任务的业务状态（具体视不通业务而定）
     */
    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }

    /**
     * 获取影像任务父级ID（为满足影像任务树状结构设计）
     *
     * @return parent_id - 影像任务父级ID（为满足影像任务树状结构设计）
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置影像任务父级ID（为满足影像任务树状结构设计）
     *
     * @param parentId 影像任务父级ID（为满足影像任务树状结构设计）
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     *
     * @return edoc_is_matched - 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    public String getEdocIsMatched() {
        return edocIsMatched;
    }

    /**
     * 设置是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     *
     * @param edocIsMatched 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    public void setEdocIsMatched(String edocIsMatched) {
        this.edocIsMatched = edocIsMatched;
    }

    /**
     * 获取实物状态（实体单证的状态）
     *
     * @return edoc_physical_status - 实物状态（实体单证的状态）
     */
    public String getEdocPhysicalStatus() {
        return edocPhysicalStatus;
    }

    /**
     * 设置实物状态（实体单证的状态）
     *
     * @param edocPhysicalStatus 实物状态（实体单证的状态）
     */
    public void setEdocPhysicalStatus(String edocPhysicalStatus) {
        this.edocPhysicalStatus = edocPhysicalStatus;
    }

    /**
     * 获取系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     *
     * @return edoc_source - 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     */
    public String getSysSource() {
        return sysSource;
    }

    /**
     * 设置系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     *
     * @param sysSource 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     */
    public void setSysSource(String sysSource) {
        this.sysSource = sysSource;
    }

    /**
     * 获取影像任务对应的影像的总张数（用来匹配客户端上传的影像张数）
     *
     * @return total_num - 影像任务对应的影像的总张数（用来匹配客户端上传的影像张数）
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 设置影像任务对应的影像的总张数（用来匹配客户端上传的影像张数）
     *
     * @param totalNum 影像任务对应的影像的总张数（用来匹配客户端上传的影像张数）
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取是否锁定（影像任务锁定）
     *
     * @return is_locked - 是否锁定（影像任务锁定）
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否锁定（影像任务锁定）
     *
     * @param isLocked 是否锁定（影像任务锁定）
     */
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return ext_field1
     */
    public String getExtField1() {
        return extField1;
    }

    /**
     * @param extField1
     */
    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    /**
     * @return ext_field2
     */
    public String getExtField2() {
        return extField2;
    }

    /**
     * @param extField2
     */
    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    /**
     * @return ext_field3
     */
    public String getExtField3() {
        return extField3;
    }

    /**
     * @param extField3
     */
    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    /**
     * @return ext_field4
     */
    public String getExtField4() {
        return extField4;
    }

    /**
     * @param extField4
     */
    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    /**
     * @return ext_field5
     */
    public String getExtField5() {
        return extField5;
    }

    /**
     * @param extField5
     */
    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEdocCertStatus() {
        return edocCertStatus;
    }

    public void setEdocCertStatus(Integer edocCertStatus) {
        this.edocCertStatus = edocCertStatus;
    }

    public Integer getEdocVoucherStatus() {
        return edocVoucherStatus;
    }

    public void setEdocVoucherStatus(Integer edocVoucherStatus) {
        this.edocVoucherStatus = edocVoucherStatus;
    }

    public Integer getEdocArchiveStatus() {
        return edocArchiveStatus;
    }

    public void setEdocArchiveStatus(Integer edocArchiveStatus) {
        this.edocArchiveStatus = edocArchiveStatus;
    }

    //人人乐 start
    private String seal;
    private String review;
    private String queryTimeStart;
    private String queryTimeEnd;
    private String abnormalType;

    public String getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getQueryTimeStart() {
        return queryTimeStart;
    }

    public void setQueryTimeStart(String queryTimeStart) {
        this.queryTimeStart = queryTimeStart;
    }

    public String getQueryTimeEnd() {
        return queryTimeEnd;
    }

    public void setQueryTimeEnd(String queryTimeEnd) {
        this.queryTimeEnd = queryTimeEnd;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<EdocImageModel> getEdocImageList() {
        return edocImageList;
    }

    public void setEdocImageList(List<EdocImageModel> edocImageList) {
        this.edocImageList = edocImageList;
    }

    public String getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(String edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }
    //人人乐 end


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
