package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "accounting_box_item")
public class AccountingBoxItem extends BaseDomain {
    /**
     * 盒号id
     */
    @Column(name = "accounting_box")
    private String accountingBox;

    /**
     * 会计分册id
     */
    @Column(name = "accounting_section")
    private String accountingSection;

    /**
     * 单证ID
     */
    @Column(name = "edoc_id")
    private String edocId;

    /**
     * 附件数量
     */
    @Column(name = "attachment_num")
    private String attachmentNum;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 全宗号
     */
    @Column(name = "fonds_code")
    private String fondsCode;

    /**
     * 获取盒号id
     *
     * @return accounting_box - 盒号id
     */
    public String getAccountingBox() {
        return accountingBox;
    }

    /**
     * 设置盒号id
     *
     * @param accountingBox 盒号id
     */
    public void setAccountingBox(String accountingBox) {
        this.accountingBox = accountingBox == null ? null : accountingBox.trim();
    }

    /**
     * 获取会计分册id
     *
     * @return accounting_section - 会计分册id
     */
    public String getAccountingSection() {
        return accountingSection;
    }

    /**
     * 设置会计分册id
     *
     * @param accountingSection 会计分册id
     */
    public void setAccountingSection(String accountingSection) {
        this.accountingSection = accountingSection == null ? null : accountingSection.trim();
    }

    /**
     * 获取单证ID
     *
     * @return edoc_id - 单证ID
     */
    public String getEdocId() {
        return edocId;
    }

    /**
     * 设置单证ID
     *
     * @param edocId 单证ID
     */
    public void setEdocId(String edocId) {
        this.edocId = edocId == null ? null : edocId.trim();
    }

    /**
     * 获取附件数量
     *
     * @return attachment_num - 附件数量
     */
    public String getAttachmentNum() {
        return attachmentNum;
    }

    /**
     * 设置附件数量
     *
     * @param attachmentNum 附件数量
     */
    public void setAttachmentNum(String attachmentNum) {
        this.attachmentNum = attachmentNum == null ? null : attachmentNum.trim();
    }

    /**
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取全宗号
     *
     * @return fonds_code - 全宗号
     */
    public String getFondsCode() {
        return fondsCode;
    }

    /**
     * 设置全宗号
     *
     * @param fondsCode 全宗号
     */
    public void setFondsCode(String fondsCode) {
        this.fondsCode = fondsCode == null ? null : fondsCode.trim();
    }
}