package com.zynsun.platform.edoc.dto.edocClient.dataUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadRequestBodyItemInvoice implements Serializable {

    /**
     * 本地唯一标识
     */
    @XmlElement(name = "GUID")
    @JsonProperty(value = "GUID")
    private String guid;

    /**
     * 发票类型
     */
    @XmlElement(name = "INV_TYPE")
    @JsonProperty(value = "INV_TYPE")
    private String invType;

    /**
     * 发票号码
     */
    @XmlElement(name = "INV_NO")
    @JsonProperty(value = "INV_NO")
    private String invNo;

    /**
     * 发票代码
     */
    @XmlElement(name = "INV_CODE")
    @JsonProperty(value = "INV_CODE")
    private String invCode;

    /**
     * 发票日期
     */
    @XmlElement(name = "INV_DATE")
    @JsonProperty(value = "INV_DATE")
    private String invDate;

    /**
     * 机器编码
     */
    @XmlElement(name = "INV_MACHINE_NO")
    @JsonProperty(value = "INV_MACHINE_NO")
    private String invMachineNo;

    /**
     * 购方税号
     */
    @XmlElement(name = "BUYER_TAX_CODE")
    @JsonProperty(value = "BUYER_TAX_CODE")
    private String buyerTaxCode;

    /**
     * 辅购方税号
     */
    @XmlElement(name = "BUYER_TAX_CODE_B")
    @JsonProperty(value = "BUYER_TAX_CODE_B")
    private String buyerTaxCodeB;

    /**
     * 购方名称
     */
    @XmlElement(name = "BUYER_NAME")
    @JsonProperty(value = "BUYER_NAME")
    private String buyerName;

    /**
     * 销方税号
     */
    @XmlElement(name = "SALER_TAX_CODE")
    @JsonProperty(value = "SALER_TAX_CODE")
    private String salerTaxCode;

    /**
     * 辅销方税号
     */
    @XmlElement(name = "SALER_TAX_CODE_B")
    @JsonProperty(value = "SALER_TAX_CODE_B")
    private String salerTaxCodeB;

    /**
     * 销方名称
     */
    @XmlElement(name = "SALLER_NAME")
    @JsonProperty(value = "SALLER_NAME")
    private String salerName;

    /**
     * 金额
     */
    @XmlElement(name = "INV_AMOUNT")
    @JsonProperty(value = "INV_AMOUNT")
    private String invAmount;

    /**
     * 税额
     */
    @XmlElement(name = "INV_TAX")
    @JsonProperty(value = "INV_TAX")
    private String invTax;

    /**
     * 合计
     */
    @XmlElement(name = "INV_TOTAL")
    @JsonProperty(value = "INV_TOTAL")
    private String invTotal;

    /**
     * 币种
     */
    @XmlElement(name = "INV_CURRENCY")
    @JsonProperty(value = "INV_CURRENCY")
    private String invCurrency;

    /**
     * 发票PO
     */
    @XmlElement(name = "INV_PO")
    @JsonProperty(value = "INV_PO")
    private String invPo;

    /**
     * 增值税普通发票校验码
     */
    @XmlElement(name = "INV_CHECKCODE")
    @JsonProperty(value = "INV_CHECKCODE")
    private String invCheckCode;

    /**
     * 密文区
     */
    @XmlElement(name = "INV_TAXBOX")
    @JsonProperty(value = "INV_TAXBOX")
    private String invTaxbox;

    /**
     * 备注栏
     */
    @XmlElement(name = "INV_REMARK")
    @JsonProperty(value = "INV_REMARK")
    private String invRemark;

    /***
     * 是否是红票
     */
    @XmlElement(name = "INV_IS_RED")
    @JsonProperty(value = "INV_IS_RED")
    private String invIsRed;

    /**
     * 是否有红章
     */
    @XmlElement(name = "INV_RED_CHOP")
    @JsonProperty(value = "INV_RED_CHOP")
    private String invRedChop;

    /**
     * 识别校验标记
     */
    @XmlElement(name = "OCR_FLAG")
    @JsonProperty(value = "OCR_FLAG")
    private String ocrFlag;

    /**
     * 发票明细集合
     */
    @XmlElement(name = "DETAILS")
    @JsonProperty(value = "DETAILS")
    private DataUploadRequestBodyItemInvoiceDetails details;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getInvMachineNo() {
        return invMachineNo;
    }

    public void setInvMachineNo(String invMachineNo) {
        this.invMachineNo = invMachineNo;
    }

    public String getBuyerTaxCode() {
        return buyerTaxCode;
    }

    public void setBuyerTaxCode(String buyerTaxCode) {
        this.buyerTaxCode = buyerTaxCode;
    }

    public String getBuyerTaxCodeB() {
        return buyerTaxCodeB;
    }

    public void setBuyerTaxCodeB(String buyerTaxCodeB) {
        this.buyerTaxCodeB = buyerTaxCodeB;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSalerTaxCode() {
        return salerTaxCode;
    }

    public void setSalerTaxCode(String salerTaxCode) {
        this.salerTaxCode = salerTaxCode;
    }

    public String getSalerTaxCodeB() {
        return salerTaxCodeB;
    }

    public void setSalerTaxCodeB(String salerTaxCodeB) {
        this.salerTaxCodeB = salerTaxCodeB;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(String invAmount) {
        this.invAmount = invAmount;
    }

    public String getInvTax() {
        return invTax;
    }

    public void setInvTax(String invTax) {
        this.invTax = invTax;
    }

    public String getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(String invTotal) {
        this.invTotal = invTotal;
    }

    public String getInvCurrency() {
        return invCurrency;
    }

    public void setInvCurrency(String invCurrency) {
        this.invCurrency = invCurrency;
    }

    public String getInvPo() {
        return invPo;
    }

    public void setInvPo(String invPo) {
        this.invPo = invPo;
    }

    public String getInvCheckCode() {
        return invCheckCode;
    }

    public void setInvCheckCode(String invCheckCode) {
        this.invCheckCode = invCheckCode;
    }

    public String getInvTaxbox() {
        return invTaxbox;
    }

    public void setInvTaxbox(String invTaxbox) {
        this.invTaxbox = invTaxbox;
    }

    public String getInvRemark() {
        return invRemark;
    }

    public void setInvRemark(String invRemark) {
        this.invRemark = invRemark;
    }

    public String getInvIsRed() {
        return invIsRed;
    }

    public void setInvIsRed(String invIsRed) {
        this.invIsRed = invIsRed;
    }

    public String getInvRedChop() {
        return invRedChop;
    }

    public void setInvRedChop(String invRedChop) {
        this.invRedChop = invRedChop;
    }

    public String getOcrFlag() {
        return ocrFlag;
    }

    public void setOcrFlag(String ocrFlag) {
        this.ocrFlag = ocrFlag;
    }

    public DataUploadRequestBodyItemInvoiceDetails getDetails() {
        return details;
    }

    public void setDetails(DataUploadRequestBodyItemInvoiceDetails details) {
        this.details = details;
    }
}
