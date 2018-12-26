package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "accounting_archives_borrow")
public class AccountingArchivesBorrow extends BaseDomain {

    @Column(name = "edoc_head_id")
    private Long edocHeadId;

    @Column(name = "edoc_no")
    private String edocNo;

    @Column(name = "accounting_archives_id")
    private Long accountingArchivesId;

    @Column(name = "status")
    private String status;

    @Column(name = "is_save_back")
    private String isSaveBack;

    @Column(name = "save_back_time")
    private Date saveBackTime;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "section_no")
    private String sectionNo;

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getIsSaveBack() {
        return isSaveBack;
    }

    public void setIsSaveBack(String isSaveBack) {
        this.isSaveBack = isSaveBack;
    }

    public Date getSaveBackTime() {
        return saveBackTime;
    }

    public void setSaveBackTime(Date saveBackTime) {
        this.saveBackTime = saveBackTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEdocHeadId() {
        return edocHeadId;
    }

    public void setEdocHeadId(Long edocHeadId) {
        this.edocHeadId = edocHeadId;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public Long getAccountingArchivesId() {
        return accountingArchivesId;
    }

    public void setAccountingArchivesId(Long accountingArchivesId) {
        this.accountingArchivesId = accountingArchivesId;
    }
}
