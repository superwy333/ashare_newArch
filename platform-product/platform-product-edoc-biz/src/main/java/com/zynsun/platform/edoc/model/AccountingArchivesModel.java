package com.zynsun.platform.edoc.model;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by WZH on 2017/7/31.
 */
public class AccountingArchivesModel {

    /**
     * 档号
     */
    private String filesNo;

    /**
     * 报账单号
     */
    private String edocNo;

    /**
     * 保管箱条码
     */
    private String safeBoxCode;

    /**
     * 报账提交人/经办人编码
     */
    private String expensePersonCode;

    /**
     * 报账提交人/经办人姓名
     */
    private String expensePersonName;

    /**
     * 报账日期
     */
    private Date expenseDate;

    /**
     * 凭证编号
     */
    private String voucherCode;

    /**
     * 过账日期
     */
    private String voucherDate;

    /**
     * 报账单附件总数
     */
    private Integer attachmentCount;

    /**
     * 档案分类
     */
    private String fileClassify;

    /**
     * 归档时间
     */
    private Date fileDate;

    /**
     * 保管期限
     */
    private String fileStoragePeriod;

    /**
     * 密级
     */
    private String fileSecretLevel;

    /**
     * 所属机构代码
     */
    private String officeId;

    /**
     * 所属机构名称
     */
    @Column(name = "office_name")
    private String officeName;

    /**
     * 档案所属档案盒编号
     */
    @Column(name = "records_box_no")
    private String recordsBoxNo;

    /**
     * 档案所属案卷/册的编号
     */
    @Column(name = "records_no")
    private String recordsNo;

    /**
     * 档案状态：1：已归档；2：调档
     */
    private String status;

    /**
     * 描述
     */
    private String remarks;

    private String archiveYear;

    private String locNo;

    public String getArchiveYear() {
        return archiveYear;
    }

    public void setArchiveYear(String archiveYear) {
        this.archiveYear = archiveYear;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    /**
     * 获取档号
     *
     * @return files_no - 档号
     */
    public String getFilesNo() {
        return filesNo;
    }

    /**
     * 设置档号
     *
     * @param filesNo 档号
     */
    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo == null ? null : filesNo.trim();
    }

    /**
     * 获取报账单号
     *
     * @return edoc_no - 报账单号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置报账单号
     *
     * @param edocNo 报账单号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo == null ? null : edocNo.trim();
    }

    /**
     * 获取报账提交人/经办人编码
     *
     * @return expense_person_code - 报账提交人/经办人编码
     */
    public String getExpensePersonCode() {
        return expensePersonCode;
    }

    /**
     * 设置报账提交人/经办人编码
     *
     * @param expensePersonCode 报账提交人/经办人编码
     */
    public void setExpensePersonCode(String expensePersonCode) {
        this.expensePersonCode = expensePersonCode == null ? null : expensePersonCode.trim();
    }

    /**
     * 获取报账提交人/经办人姓名
     *
     * @return expense_person_name - 报账提交人/经办人姓名
     */
    public String getExpensePersonName() {
        return expensePersonName;
    }

    /**
     * 设置报账提交人/经办人姓名
     *
     * @param expensePersonName 报账提交人/经办人姓名
     */
    public void setExpensePersonName(String expensePersonName) {
        this.expensePersonName = expensePersonName == null ? null : expensePersonName.trim();
    }

    /**
     * 获取报账日期
     *
     * @return expense_date - 报账日期
     */
    public Date getExpenseDate() {
        return expenseDate;
    }

    /**
     * 设置报账日期
     *
     * @param expenseDate 报账日期
     */
    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    /**
     * 获取凭证编号
     *
     * @return voucher_code - 凭证编号
     */
    public String getVoucherCode() {
        return voucherCode;
    }

    /**
     * 设置凭证编号
     *
     * @param voucherCode 凭证编号
     */
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode == null ? null : voucherCode.trim();
    }

    /**
     * 获取过账日期
     *
     * @return voucher_date - 过账日期
     */
    public String getVoucherDate() {
        return voucherDate;
    }

    /**
     * 设置过账日期
     *
     * @param voucherDate 过账日期
     */
    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate == null ? null : voucherDate.trim();
    }

    /**
     * 获取报账单附件总数
     *
     * @return attachment_count - 报账单附件总数
     */
    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    /**
     * 设置报账单附件总数
     *
     * @param attachmentCount 报账单附件总数
     */
    public void setAttachmentCount(Integer attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    /**
     * 获取档案分类
     *
     * @return file_classify - 档案分类
     */
    public String getFileClassify() {
        return fileClassify;
    }

    /**
     * 设置档案分类
     *
     * @param fileClassify 档案分类
     */
    public void setFileClassify(String fileClassify) {
        this.fileClassify = fileClassify == null ? null : fileClassify.trim();
    }

    /**
     * 获取归档时间
     *
     * @return file_date - 归档时间
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * 设置归档时间
     *
     * @param fileDate 归档时间
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * 获取保管期限
     *
     * @return file_storage_period - 保管期限
     */
    public String getFileStoragePeriod() {
        return fileStoragePeriod;
    }

    /**
     * 设置保管期限
     *
     * @param fileStoragePeriod 保管期限
     */
    public void setFileStoragePeriod(String fileStoragePeriod) {
        this.fileStoragePeriod = fileStoragePeriod == null ? null : fileStoragePeriod.trim();
    }

    /**
     * 获取密级
     *
     * @return file_secret_level - 密级
     */
    public String getFileSecretLevel() {
        return fileSecretLevel;
    }

    /**
     * 设置密级
     *
     * @param fileSecretLevel 密级
     */
    public void setFileSecretLevel(String fileSecretLevel) {
        this.fileSecretLevel = fileSecretLevel == null ? null : fileSecretLevel.trim();
    }

    /**
     * 获取所属机构代码
     *
     * @return office_id - 所属机构代码
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * 设置所属机构代码
     *
     * @param officeId 所属机构代码
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }

    /**
     * 获取所属机构名称
     *
     * @return office_name - 所属机构名称
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * 设置所属机构名称
     *
     * @param officeName 所属机构名称
     */
    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    /**
     * 获取档案所属档案盒编号
     *
     * @return records_box_no - 档案所属档案盒编号
     */
    public String getRecordsBoxNo() {
        return recordsBoxNo;
    }

    /**
     * 设置档案所属档案盒编号
     *
     * @param recordsBoxNo 档案所属档案盒编号
     */
    public void setRecordsBoxNo(String recordsBoxNo) {
        this.recordsBoxNo = recordsBoxNo;
    }

    /**
     * 获取档案所属案卷/册的编号
     *
     * @return records_no - 档案所属案卷/册的编号
     */
    public String getRecordsNo() {
        return recordsNo;
    }

    /**
     * 设置档案所属案卷/册的编号
     *
     * @param recordsNo 档案所属案卷/册的编号
     */
    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo == null ? null : recordsNo.trim();
    }

    /**
     * 获取档案状态：1：已归档；2：调档
     *
     * @return status - 档案状态：1：已归档；2：调档
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置档案状态：1：已归档；2：调档
     *
     * @param status 档案状态：1：已归档；2：调档
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getSafeBoxCode() {
        return safeBoxCode;
    }

    public void setSafeBoxCode(String safeBoxCode) {
        this.safeBoxCode = safeBoxCode;
    }
}
