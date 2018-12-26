package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * Created by WZH on 2017/8/2.
 */
public class ArchivedManualDTO extends PageDTO {
    /**
     * 单证编号:<账套代码+凭证号码>确定唯一
     */
    private String edocNo;

    /**
     * 账套编号
     */
    private String vouBookcode;

    /**
     * 凭证号
     */
    private String vouNo;

    /**
     * 总账日期YYYYMM
     */
    private String vouDate;

    /**
     * 日记账批名
     */
    private String vouBatchname;

    /**
     * 日记账名称
     */
    private String vouJournalname;

    /**
     * 制单人
     */
    private String vouMakerName;

    /**
     * 公司名称
     */
    private String vouCompanyname;


    /**
     * 期间
     */
    private String vouPeriod;

    private String vouDateStart;

    private String vouDateEnd;

    private String safeBoxCode;

    private String boxNo;

    private String recordsNo;

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getVouBookcode() {
        return vouBookcode;
    }

    public void setVouBookcode(String vouBookcode) {
        this.vouBookcode = vouBookcode;
    }

    public String getVouNo() {
        return vouNo;
    }

    public void setVouNo(String vouNo) {
        this.vouNo = vouNo;
    }

    public String getVouDate() {
        return vouDate;
    }

    public void setVouDate(String vouDate) {
        this.vouDate = vouDate;
    }

    public String getVouBatchname() {
        return vouBatchname;
    }

    public void setVouBatchname(String vouBatchname) {
        this.vouBatchname = vouBatchname;
    }

    public String getVouJournalname() {
        return vouJournalname;
    }

    public void setVouJournalname(String vouJournalname) {
        this.vouJournalname = vouJournalname;
    }

    public String getVouMakerName() {
        return vouMakerName;
    }

    public void setVouMakerName(String vouMakerName) {
        this.vouMakerName = vouMakerName;
    }

    public String getVouCompanyname() {
        return vouCompanyname;
    }

    public void setVouCompanyname(String vouCompanyname) {
        this.vouCompanyname = vouCompanyname;
    }

    public String getVouPeriod() {
        return vouPeriod;
    }

    public void setVouPeriod(String vouPeriod) {
        this.vouPeriod = vouPeriod;
    }

    public String getVouDateStart() {
        return vouDateStart;
    }

    public void setVouDateStart(String vouDateStart) {
        this.vouDateStart = vouDateStart;
    }

    public String getVouDateEnd() {
        return vouDateEnd;
    }

    public void setVouDateEnd(String vouDateEnd) {
        this.vouDateEnd = vouDateEnd;
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
}
