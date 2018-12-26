package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "archive_company")
public class ArchiveCompany extends BaseDomain {
    @Column(name = "archive_id")
    private String archiveId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "parentId")
    private String parentid;

    private String remarks;

    /**
     * @return archive_id
     */
    public String getArchiveId() {
        return archiveId;
    }

    /**
     * @param archiveId
     */
    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId == null ? null : archiveId.trim();
    }

    /**
     * @return company_id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * @return company_code
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * @param companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * @return parentId
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
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
}