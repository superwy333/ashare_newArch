package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "archive_header")
public class ArchiveHeader extends BaseDomain {
    @Column(name = "header_name")
    private String headerName;

    @Column(name = "header_code")
    private String headerCode;

    /**
     * 会计档案是否分册：0：不分册；1：分册；
     */
    @Column(name = "section_flag")
    private String sectionFlag;
    @Column(name = "remarks")
    private String remarks;

    /**
     * @return header_name
     */
    public String getHeaderName() {
        return headerName;
    }

    /**
     * @param headerName
     */
    public void setHeaderName(String headerName) {
        this.headerName = headerName == null ? null : headerName.trim();
    }

    /**
     * @return header_code
     */
    public String getHeaderCode() {
        return headerCode;
    }

    /**
     * @param headerCode
     */
    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode == null ? null : headerCode.trim();
    }

    /**
     * 获取会计档案是否分册：0：不分册；1：分册；
     *
     * @return section_flag - 会计档案是否分册：0：不分册；1：分册；
     */
    public String getSectionFlag() {
        return sectionFlag;
    }

    /**
     * 设置会计档案是否分册：0：不分册；1：分册；
     *
     * @param sectionFlag 会计档案是否分册：0：不分册；1：分册；
     */
    public void setSectionFlag(String sectionFlag) {
        this.sectionFlag = sectionFlag == null ? null : sectionFlag.trim();
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