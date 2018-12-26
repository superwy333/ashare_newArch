package com.zynsun.platform.edoc.dto;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-06-20 15:16
 **/
public class EbsCredentialsDetailDTO {
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
    // 借方金额合计
    private String debtorMoneyTotal;
    // 贷方金额合计
    private String creditorMoneyTotal;
    // 大写
    private String upperMoney;
    // 总数量
    private String totalAcount;

    public String getTotalAcount() {
        return totalAcount;
    }

    public void setTotalAcount(String totalAcount) {
        this.totalAcount = totalAcount;
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
