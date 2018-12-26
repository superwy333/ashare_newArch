package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

import javax.persistence.Column;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
public class ArchiveIdentifiesDTO extends PageDTO {
    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;
    /**
     * 提交时间:单据生成时间，YYYY-MM-DD HH:MM:SS
     */
    private String fileDate;
    private String submitTime;

    private Long idenId;
    private String credentialsType;
    //是否在档
    private String  status;
    //库位号
    private String locNo;
    private String archiveYear;
    // 档案ID
    private Long archivesId;
    // 业务类型：1-付款单，2-报销单

    private String edocType;

    // 关联盒号
    private String boxNo;

    // 关联册号
    private String recordsNo;

    // 保管箱条码
    private String safeBoxCode;
    // 公司代码
    private String companyName;
    //凭证编号
    private String credentialsNo;
    //********************************************************
    //关联报账单id
    private Long edocHeaderId;

    //是否延期 0-销毁 1-延期
    private String identifiesFlag;

    //档案延期年限(与档案年限累加)
    private String identifiesArchiveYear;

    //鉴定人
    private String identifier;

    //鉴定时间
    private String identifiesTime;

    //鉴定机构代码
    private String officeId;

    //鉴定机构名称
    private String officeName;

    //描述
    private String remarks;

    //档
    private String filesNo;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;
    // 凭证编号
    private String credentialsNum;
    /**
     * 电子影像编号
     */
    private String edocNo;

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
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

    public Long getIdenId() {
        return idenId;
    }

    public void setIdenId(Long idenId) {
        this.idenId = idenId;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public String getArchiveYear() {
        return archiveYear;
    }

    public void setArchiveYear(String archiveYear) {
        this.archiveYear = archiveYear;
    }

    public Long getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Long archivesId) {
        this.archivesId = archivesId;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
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

    public String getSafeBoxCode() {
        return safeBoxCode;
    }

    public void setSafeBoxCode(String safeBoxCode) {
        this.safeBoxCode = safeBoxCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCredentialsNo() {
        return credentialsNo;
    }

    public void setCredentialsNo(String credentialsNo) {
        this.credentialsNo = credentialsNo;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
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

    public String getIdentifiesFlag() {
        return identifiesFlag;
    }

    public void setIdentifiesFlag(String identifiesFlag) {
        this.identifiesFlag = identifiesFlag;
    }

    public String getIdentifiesArchiveYear() {
        return identifiesArchiveYear;
    }

    public void setIdentifiesArchiveYear(String identifiesArchiveYear) {
        this.identifiesArchiveYear = identifiesArchiveYear;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifiesTime() {
        return identifiesTime;
    }

    public void setIdentifiesTime(String identifiesTime) {
        this.identifiesTime = identifiesTime;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
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
}
