package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

import java.util.Date;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 3:22
 * @Modified By:
 */
public class ArchivedAccountDTO extends PageDTO {
    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;


    /**
     * iD和凭证编号
     * 例如：id-no
     */
    private String idAndNo;

    /**
     * 单证编号，报账单、付款单的单据号，唯一
     */
    private String edocNo;

    private String billHeaderId;

    /**
     * 业务类型：1-付款单，2-报销单
     */
    private String edocType;


    /**
     * 公司代码
     */
    private String companyName;

    /**
     * 提交人姓名
     */
    private String userName;

    /**
     * 提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     */
    private String submitTime;

    /**
     * 关联采购合同号
     */
    private String billContract;

    /**
     * 关联AP凭证号
     */
    private String apNo;

    /**
     * 关联gl凭证号
     */
    private String glNo;


    /**
     * 关联装箱号
     */
    private String filesNo;

    /**
     * 关联盒号
     */
    private String boxNo;

    /**
     * 关联册号
     */
    private String recordsNo;

    /**
     * 保管箱条码
     */
    private String safeBoxCode;

    //AP付款凭证(多个逗号隔开)
    private String payProofNo;

    // 档案ID
    private Long archivesId;

    private String credentialsNo;

    //档案公司
    private String officeName;

    private String credentialsType;

    // 归档日期
    private Date fileDate;


    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNo() {
        return credentialsNo;
    }

    public void setCredentialsNo(String credentialsNo) {
        this.credentialsNo = credentialsNo;
    }

    public Long getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Long archivesId) {
        this.archivesId = archivesId;
    }

    private String archiveYear;

    private String locNo;

    public String getArchiveYear() {
        return archiveYear;
    }

    public void setArchiveYear(String archiveYear) {
        this.archiveYear = archiveYear;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    /**
     * 获取单证编号，报账单、付款单的单据号，唯一
     *
     * @return edoc_no - 单证编号，报账单、付款单的单据号，唯一
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置单证编号，报账单、付款单的单据号，唯一
     *
     * @param edocNo 单证编号，报账单、付款单的单据号，唯一
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo == null ? null : edocNo.trim();
    }

    /**
     * 获取业务类型：1-付款单，2-报销单
     *
     * @return edoc_type - 业务类型：1-付款单，2-报销单
     */
    public String getEdocType() {
        return edocType;
    }

    /**
     * 设置业务类型：1-付款单，2-报销单
     *
     * @param edocType 业务类型：1-付款单，2-报销单
     */
    public void setEdocType(String edocType) {
        this.edocType = edocType == null ? null : edocType.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    /**
     * 获取提交人姓名
     *
     * @return user_name - 提交人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置提交人姓名
     *
     * @param userName 提交人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     *
     * @return submit_time - 提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     */
    public String getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     *
     * @param submitTime 提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     */
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime == null ? null : submitTime.trim();
    }

    /**
     * 获取关联采购合同号
     *
     * @return bill_contract - 关联采购合同号
     */
    public String getBillContract() {
        return billContract;
    }

    /**
     * 设置关联采购合同号
     *
     * @param billContract 关联采购合同号
     */
    public void setBillContract(String billContract) {
        this.billContract = billContract == null ? null : billContract.trim();
    }


    public String getApNo() {
        return apNo;
    }

    public void setApNo(String apNo) {
        this.apNo = apNo;
    }

    public String getGlNo() {
        return glNo;
    }

    public void setGlNo(String glNo) {
        this.glNo = glNo;
    }

    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getRecordsNo() {
        return recordsNo;
    }

    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo;
    }

    public String getBillHeaderId() {
        return billHeaderId;
    }

    public void setBillHeaderId(String billHeaderId) {
        this.billHeaderId = billHeaderId;
    }

    public String getSafeBoxCode() {
        return safeBoxCode;
    }

    public void setSafeBoxCode(String safeBoxCode) {
        this.safeBoxCode = safeBoxCode;
    }

    public String getPayProofNo() {
        return payProofNo;
    }

    public void setPayProofNo(String payProofNo) {
        this.payProofNo = payProofNo;
    }
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdAndNo() {
        return idAndNo;
    }

    public void setIdAndNo(String idAndNo) {
        this.idAndNo = idAndNo;
    }
}
