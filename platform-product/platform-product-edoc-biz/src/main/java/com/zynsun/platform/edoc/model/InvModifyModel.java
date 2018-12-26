package com.zynsun.platform.edoc.model;

/**
 * 发票修改记录
 */
public class InvModifyModel implements java.io.Serializable {

    private Long id;
    private String invCode;
    private String invNo;
    private String invDate;
    private String buyerTaxCode;
    private String salerTaxCode;
    private String invAmount;
    private String buyerCompanyCode;
    private String salerCompanyCode;
    private String invTotal;
    private String invTax;
    private String salerName;
    private String buyerName;
    private String invMachineNo;
    private String currency;
    private String buyerNameCn;
    private String buyerNameEn;
    private String salerNameCn;
    private String salerNameEn;
    private String orderNo;
    private String customerNo;
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getBuyerTaxCode() {
        return buyerTaxCode;
    }

    public void setBuyerTaxCode(String buyerTaxCode) {
        this.buyerTaxCode = buyerTaxCode;
    }

    public String getSalerTaxCode() {
        return salerTaxCode;
    }

    public void setSalerTaxCode(String salerTaxCode) {
        this.salerTaxCode = salerTaxCode;
    }

    public String getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(String invAmount) {
        this.invAmount = invAmount;
    }

    public String getBuyerCompanyCode() {
        return buyerCompanyCode;
    }

    public void setBuyerCompanyCode(String buyerCompanyCode) {
        this.buyerCompanyCode = buyerCompanyCode;
    }

    public String getSalerCompanyCode() {
        return salerCompanyCode;
    }

    public void setSalerCompanyCode(String salerCompanyCode) {
        this.salerCompanyCode = salerCompanyCode;
    }

    public String getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(String invTotal) {
        this.invTotal = invTotal;
    }

    public String getInvTax() {
        return invTax;
    }

    public void setInvTax(String invTax) {
        this.invTax = invTax;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getInvMachineNo() {
        return invMachineNo;
    }

    public void setInvMachineNo(String invMachineNo) {
        this.invMachineNo = invMachineNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBuyerNameCn() {
        return buyerNameCn;
    }

    public void setBuyerNameCn(String buyerNameCn) {
        this.buyerNameCn = buyerNameCn;
    }

    public String getBuyerNameEn() {
        return buyerNameEn;
    }

    public void setBuyerNameEn(String buyerNameEn) {
        this.buyerNameEn = buyerNameEn;
    }

    public String getSalerNameCn() {
        return salerNameCn;
    }

    public void setSalerNameCn(String salerNameCn) {
        this.salerNameCn = salerNameCn;
    }

    public String getSalerNameEn() {
        return salerNameEn;
    }

    public void setSalerNameEn(String salerNameEn) {
        this.salerNameEn = salerNameEn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}