package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * Created by WZH on 2017/8/2.
 */
public class ArchivedOutInvoiceDTO extends PageDTO {

    private String edocNo;

    private String invNo;

    private String invCode;

    private String invType;

    private String invRegularcode;

    private String invSalerName;

    private String invBuyerName;

    private String invInfo;

    private String invAmount;

    private String invTax;

    private String invTotal;

    private String safeBoxCode;

    private String recordsNo;

    private String boxNo;

    private String invDate;

    private String invDateStart;

    private String invDateEnd;

    private String invStyle;

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
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

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvRegularcode() {
        return invRegularcode;
    }

    public void setInvRegularcode(String invRegularcode) {
        this.invRegularcode = invRegularcode;
    }

    public String getInvSalerName() {
        return invSalerName;
    }

    public void setInvSalerName(String invSalerName) {
        this.invSalerName = invSalerName;
    }

    public String getInvBuyerName() {
        return invBuyerName;
    }

    public void setInvBuyerName(String invBuyerName) {
        this.invBuyerName = invBuyerName;
    }

    public String getInvInfo() {
        return invInfo;
    }

    public void setInvInfo(String invInfo) {
        this.invInfo = invInfo;
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

    public String getRecordsNo() {
        return recordsNo;
    }

    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getInvDateStart() {
        return invDateStart;
    }

    public void setInvDateStart(String invDateStart) {
        this.invDateStart = invDateStart;
    }

    public String getInvDateEnd() {
        return invDateEnd;
    }

    public void setInvDateEnd(String invDateEnd) {
        this.invDateEnd = invDateEnd;
    }

    public String getSafeBoxCode() {
        return safeBoxCode;
    }

    public void setSafeBoxCode(String safeBoxCode) {
        this.safeBoxCode = safeBoxCode;
    }

    public String getInvStyle() {
        return invStyle;
    }

    public void setInvStyle(String invStyle) {
        this.invStyle = invStyle;
    }
}
