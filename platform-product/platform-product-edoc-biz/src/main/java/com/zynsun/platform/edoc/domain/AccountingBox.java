package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "accounting_box")
public class AccountingBox extends BaseDomain {
    /**
     * 盒号
     */
    @Column(name = "box_no")
    private String boxNo;

    /**
     * 任务id（单据分盒时必填）
     */
    @Column(name = "bill_header_id")
    private String billHeaderId;

    /**
     * 分册号（分册后分盒时必填）
     */
    @Column(name = "section_id")
    private String sectionId;

    /**
     * 1.单据分盒2.分册后分盒
     */
    @Column(name = "box_status")
    private String boxStatus;

    private String status;

    private String remarks;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_code")
    private String companyCode;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="fonds_code")
    private String fondsCode;

    @Column(name="volumes_no")
    private String volumesNo;

    /**
     * 分盒归档标志：0：未归档；1：已归档
     */
    @Column(name = "box_flag")
    private String boxFlag;

    @Column(name = "biz_type")
    private String bizType;

    /**
     * 获取盒号
     *
     * @return box_no - 盒号
     */
    public String getBoxNo() {
        return boxNo;
    }

    /**
     * 设置盒号
     *
     * @param boxNo 盒号
     */
    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    /**
     * 获取任务id（单据分盒时必填）
     *
     * @return bill_header_id - 任务id（单据分盒时必填）
     */
    public String getBillHeaderId() {
        return billHeaderId;
    }

    /**
     * 设置任务id（单据分盒时必填）
     *
     * @param billHeaderId 任务id（单据分盒时必填）
     */
    public void setBillHeaderId(String billHeaderId) {
        this.billHeaderId = billHeaderId == null ? null : billHeaderId.trim();
    }

    /**
     * 获取分册号（分册后分盒时必填）
     *
     * @return section_id - 分册号（分册后分盒时必填）
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * 设置分册号（分册后分盒时必填）
     *
     * @param sectionId 分册号（分册后分盒时必填）
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    /**
     * 获取1.单据分盒2.分册后分盒
     *
     * @return box_status - 1.单据分盒2.分册后分盒
     */
    public String getBoxStatus() {
        return boxStatus;
    }

    /**
     * 设置1.单据分盒2.分册后分盒
     *
     * @param boxStatus 1.单据分盒2.分册后分盒
     */
    public void setBoxStatus(String boxStatus) {
        this.boxStatus = boxStatus == null ? null : boxStatus.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取分盒归档标志：0：未归档；1：已归档
     *
     * @return box_flag - 分盒归档标志：0：未归档；1：已归档
     */
    public String getBoxFlag() {
        return boxFlag;
    }

    /**
     * 设置分盒归档标志：0：未归档；1：已归档
     *
     * @param boxFlag 分盒归档标志：0：未归档；1：已归档
     */
    public void setBoxFlag(String boxFlag) {
        this.boxFlag = boxFlag == null ? null : boxFlag.trim();
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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
}