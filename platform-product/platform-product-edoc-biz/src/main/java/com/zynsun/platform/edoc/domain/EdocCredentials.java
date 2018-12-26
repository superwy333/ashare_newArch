package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:51
 * @Modified By:
 */
@Table(name = "edoc_credentials")
public class EdocCredentials extends BaseDomain {
    //所属edocHeaderId
    @Column(name = "edoc_header_id")
    private Long edocHeaderId;
    //凭证类型
    @Column(name = "credentials_type")
    private String credentialsType;
    // 公司
    @Column(name = "company_name")
    private String companyName;
    // 制单日期
    @Column(name = "create_date")
    private String createDate;
    // 币种
    @Column(name = "currency")
    private String currency;
    // 凭证名
    @Column(name = "credentials_name")
    private String credentialsName;
    // 凭证批名
    @Column(name = "credentials_batch_name")
    private String credentialsBatchName;
    // 凭证编号
    @Column(name = "credentials_num")
    private String credentialsNum;
    // 附件张数
    @Column(name = "attachment_count")
    private String attachmentCount;
    // 摘要
    @Column(name = "digest")
    private String digest;
    // 会计科目
    @Column(name = "accounting_subject")
    private String accountingSubject;
    // 数量
    @Column(name = "acount")
    private String acount;
    // 借方金额
    @Column(name = "debtor_money")
    private String debtorMoney;
    // 贷方金额
    @Column(name = "creditor_money")
    private String creditorMoney;
    // 制单人
    @Column(name = "creater")
    private String creater;
    // 审核人
    @Column(name = "auditor")
    private String auditor;
    // 记账人
    @Column(name = "c_accounter")
    private String cAccounter;
    // 出纳人
    @Column(name = "cashier")
    private String cashier;
    // 借方金额合计
    @Column(name = "debtor_money_total")
    private String debtorMoneyTotal;
    // 贷方金额合计
    @Column(name = "creditor_money_total")
    private String creditorMoneyTotal;
    // 大写
    @Column(name = "upper_money")
    private String upperMoney;
    // 当前页码
    @Column(name = "current_page")
    private String currentPage;
    // 总页码
    @Column(name = "total_page")
    private String totalPage;
    // 总数量
    @Column(name = "total_acount")
    private String totalAcount;

    @Column(name = "pdf_file_url")
    private String pdfFileUrl;

    @Column(name = "print_count")
    private Long printCount;

    @Column(name = "ext_field1")
    private String extField1;

    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    @Column(name = "ext_field5")
    private String extField5;

    @Column(name = "voucher_status")
    private String VoucherStatus;

    @Column(name = "erp_id")
    private String erpId;

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

//    @Column(name = "sys_flag")
//    private String sysFlag;

//    @Column(name = "voucher_account")
//    private String voucherAccount;

//    @Column(name = "sys_chk")
//    private String sysChk;

//    @Column(name = "edoc_no")
//    private String edocNo;

//    @Column(name = "comp_code")
//    private String companyCode;
//
//    public String getCompanyCode() {
//        return companyCode;
//    }
//
//    public void setCompanyCode(String companyCode) {
//        this.companyCode = companyCode;
//    }

//    public String getSysFlag() {
//        return sysFlag;
//    }
//
//    public void setSysFlag(String sysFlag) {
//        this.sysFlag = sysFlag;
//    }

//    public String getVoucherAccount() {
//        return voucherAccount;
//    }
//
//    public void setVoucherAccount(String voucherAccount) {
//        this.voucherAccount = voucherAccount;
//    }

//    public String getSysChk() {
//        return sysChk;
//    }
//
//    public void setSysChk(String sysChk) {
//        this.sysChk = sysChk;
//    }

//    public String getEdocNo() {
//        return edocNo;
//    }
//
//    public void setEdocNo(String edocNo) {
//        this.edocNo = edocNo;
//    }

    public String getVoucherStatus() {
        return VoucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        VoucherStatus = voucherStatus;
    }

    public Long getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Long printCount) {
        this.printCount = printCount;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    /*private List<EbsCredentialsDetailDTO> subListValue;

    public List<EbsCredentialsDetailDTO> getSubListValue() {
        return subListValue;
    }

    public void setSubListValue(List<EbsCredentialsDetailDTO> subListValue) {
        this.subListValue = subListValue;
    }*/

    public String getTotalAcount() {
        return totalAcount;
    }

    public void setTotalAcount(String totalAcount) {
        this.totalAcount = totalAcount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCredentialsName() {
        return credentialsName;
    }

    public void setCredentialsName(String credentialsName) {
        this.credentialsName = credentialsName;
    }

    public String getCredentialsBatchName() {
        return credentialsBatchName;
    }

    public void setCredentialsBatchName(String credentialsBatchName) {
        this.credentialsBatchName = credentialsBatchName;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(String accountingSubject) {
        this.accountingSubject = accountingSubject;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getDebtorMoney() {
        return debtorMoney;
    }

    public void setDebtorMoney(String debtorMoney) {
        this.debtorMoney = debtorMoney;
    }

    public String getCreditorMoney() {
        return creditorMoney;
    }

    public void setCreditorMoney(String creditorMoney) {
        this.creditorMoney = creditorMoney;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getcAccounter() {
        return cAccounter;
    }

    public void setcAccounter(String cAccounter) {
        this.cAccounter = cAccounter;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getDebtorMoneyTotal() {
        return debtorMoneyTotal;
    }

    public void setDebtorMoneyTotal(String debtorMoneyTotal) {
        this.debtorMoneyTotal = debtorMoneyTotal;
    }

    public String getCreditorMoneyTotal() {
        return creditorMoneyTotal;
    }

    public void setCreditorMoneyTotal(String creditorMoneyTotal) {
        this.creditorMoneyTotal = creditorMoneyTotal;
    }

    public String getUpperMoney() {
        return upperMoney;
    }

    public void setUpperMoney(String upperMoney) {
        this.upperMoney = upperMoney;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    public String getExtField5() {
        return extField5;
    }

    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }


    @Column(name = "credentials_status")
    private String credentialsStatus;
    @Column(name = "accounting_ledger")
    private String accountinLedger;

    public String getAccountinLedger() {
        return accountinLedger;
    }

    public void setAccountinLedger(String accountinLedger) {
        this.accountinLedger = accountinLedger;
    }

    public String getCredentialsStatus() {
        return credentialsStatus;
    }

    public void setCredentialsStatus(String credentialsStatus) {
        this.credentialsStatus = credentialsStatus;
    }
}
