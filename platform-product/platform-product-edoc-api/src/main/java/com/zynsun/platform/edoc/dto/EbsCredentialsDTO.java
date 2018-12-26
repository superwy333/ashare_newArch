package com.zynsun.platform.edoc.dto;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-06-20 14:41
 **/
public class EbsCredentialsDTO {
    // 公司
    private String companyName;
    // 制单日期
    private String createDate;
    // 币种
    private String currency;
    // 凭证名
    private String credentialsName;
    // 凭证批名
    private String credentialsBatchName;
    // 凭证编号
    private String credentialsNum;
    // 附件张数
    private String attachmentCount;
    // 摘要
    private String digest;
    // 会计科目
    private String accountingSubject;
    // 数量
    private String acount;
    // 借方金额
    private String debtorMoney;
    // 贷方金额
    private String creditorMoney;
    // 制单人
    private String creater;
    // 审核人
    private String auditor;
    // 记账人
    private String cAccounter;
    // 出纳人
    private String cashier;
    // 借方金额合计
    private String debtorMoneyTotal;
    // 贷方金额合计
    private String creditorMoneyTotal;
    // 大写
    private String upperMoney;
    // 当前页码
    private String currentPage;
    // 总页码
    private String totalPage;
    // 总数量
    private String totalAcount;

    private List<EbsCredentialsDetailDTO> subListValue;

    public List<EbsCredentialsDetailDTO> getSubListValue() {
        return subListValue;
    }

    public void setSubListValue(List<EbsCredentialsDetailDTO> subListValue) {
        this.subListValue = subListValue;
    }

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
}
