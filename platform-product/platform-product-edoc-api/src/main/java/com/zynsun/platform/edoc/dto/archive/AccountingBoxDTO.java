package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

import java.util.List;

/**
 * Created by WZH on 2017/7/26.
 */
public class AccountingBoxDTO extends PageDTO {

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    /**
     * 盒号
     */
    private String boxNo;

    /**
     * 任务id（单据分盒时必填）
     */
    private String billHeaderId;

    /**
     * 分册号（分册后分盒时必填）
     */
    private String sectionId;

    /**
     * 1.单据分盒2.分册后分盒
     */
    private String boxStatus;

    private String status;

    private String remarks;

    private String time;

    private String userName;

    private String companyName;

    private String companyCode;

    private String startDate;

    private String endDate;

    private String fondsCode;

    private String volumesNo;

    private String idString;

    private String volumes;

    private String bizType;

    private List<SectionItemForIreport>  boxItemForIreportList;

    public List<SectionItemForIreport> getBoxItemForIreportList() {
        return boxItemForIreportList;
    }

    public void setBoxItemForIreportList(List<SectionItemForIreport> boxItemForIreportList) {
        this.boxItemForIreportList = boxItemForIreportList;
    }

    /**
     * 分盒归档标志：0：未归档；1：已归档
     */
    private String boxFlag;

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBillHeaderId() {
        return billHeaderId;
    }

    public void setBillHeaderId(String billHeaderId) {
        this.billHeaderId = billHeaderId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(String boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBoxFlag() {
        return boxFlag;
    }

    public void setBoxFlag(String boxFlag) {
        this.boxFlag = boxFlag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFondsCode() {
        return fondsCode;
    }

    public void setFondsCode(String fondsCode) {
        this.fondsCode = fondsCode;
    }

    public String getVolumesNo() {
        return volumesNo;
    }

    public void setVolumesNo(String volumesNo) {
        this.volumesNo = volumesNo;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }
}
