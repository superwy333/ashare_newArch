package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

import java.util.Date;

public class AccountingArchivesBorrowDTO extends PageDTO {
    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    private Long edocHeadId;

    private String edocNo;

    private Long accountingArchivesId;

    private String status;
    private String isSaveBack;

    private Date saveBackTime;

    private String edocHeaderId;

    private Long sectionId;

    private String sectionNo;

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

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

    public String getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(String edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
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
