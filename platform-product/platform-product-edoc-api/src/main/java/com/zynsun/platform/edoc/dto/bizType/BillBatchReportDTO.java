package com.zynsun.platform.edoc.dto.bizType;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/9.
 */
public class BillBatchReportDTO implements Serializable{
    private String batchNo;
    private String companyName;
    private String companyCode;
    private String scannerName;
    private String scanDate;
    private String invTotal;

    private String flag1;
    private String flag1Val;

    private String flag2;
    private String flag2Val;

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag1Val() {
        return flag1Val;
    }

    public void setFlag1Val(String flag1Val) {
        this.flag1Val = flag1Val;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getFlag2Val() {
        return flag2Val;
    }

    public void setFlag2Val(String flag2Val) {
        this.flag2Val = flag2Val;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getScannerName() {
        return scannerName;
    }

    public void setScannerName(String scannerName) {
        this.scannerName = scannerName;
    }

    public String getScanDate() {
        return scanDate;
    }

    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
    }

    public String getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(String invTotal) {
        this.invTotal = invTotal;
    }
}
