package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;


/**
 * Created by WZH on 2017/8/21.
 */
public class AccountingSectionItemDTO extends PageDTO {
    /**
     * 会计分册id
     */
    private String accountingSection;

    /**
     * 单证编号
     */
    private String edocNo;

    /**
     * 单证ID
     */
    private String edocId;

    /**
     * 附件数量
     */
    private Integer attachmentNum;

    /**
     * 描述
     */
    private String remarks;

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
     * 获取单证编号
     *
     * @return edoc_no - 单证编号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置单证编号
     *
     * @param edocNo 单证编号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo == null ? null : edocNo.trim();
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
    public Integer getAttachmentNum() {
        return attachmentNum;
    }

    /**
     * 设置附件数量
     *
     * @param attachmentNum 附件数量
     */
    public void setAttachmentNum(Integer attachmentNum) {
        this.attachmentNum = attachmentNum;
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
}
