package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "archive_biz")
public class ArchiveBiz extends BaseDomain {
    @Column(name = "archive_company_id")
    private String archiveCompanyId;

    /**
     * 参考业务类型
     */
    @Column(name = "archive_type")
    private String archiveType;

    private String remarks;

    /**
     * @return archive_company_id
     */
    public String getArchiveCompanyId() {
        return archiveCompanyId;
    }

    /**
     * @param archiveCompanyId
     */
    public void setArchiveCompanyId(String archiveCompanyId) {
        this.archiveCompanyId = archiveCompanyId == null ? null : archiveCompanyId.trim();
    }

    /**
     * 获取参考业务类型
     *
     * @return archive_type - 参考业务类型
     */
    public String getArchiveType() {
        return archiveType;
    }

    /**
     * 设置参考业务类型
     *
     * @param archiveType 参考业务类型
     */
    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType == null ? null : archiveType.trim();
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