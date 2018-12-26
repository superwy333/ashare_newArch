package com.zynsun.platform.edoc.vo;

/**
 * @author jary
 * @creatTime 2018/12/6 10:30 AM
 */
public class InvoiceReqVo extends InterReqVo {


    /**
     * 发票代码
     */
    private String invCode;

    /**
     * 发票号码
     */
    private String invNo;

    /**
     * 发票日期开始时间
     */
    private String invDateBegin;

    /**
     * 发票日期结束时间
     */
    private String invDateEnd;

    /**
     * 扫描开始时间
     */
    private String scanDateBegin;

    /**
     * 扫描结束时间
     */
    private String scanDateEnd;

    /**
     * 销方税号
     */
    private String salerTaxCode;

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

    public String getInvDateBegin() {
        return invDateBegin;
    }

    public void setInvDateBegin(String invDateBegin) {
        this.invDateBegin = invDateBegin;
    }

    public String getInvDateEnd() {
        return invDateEnd;
    }

    public void setInvDateEnd(String invDateEnd) {
        this.invDateEnd = invDateEnd;
    }

    public String getScanDateBegin() {
        return scanDateBegin;
    }

    public void setScanDateBegin(String scanDateBegin) {
        this.scanDateBegin = scanDateBegin;
    }

    public String getScanDateEnd() {
        return scanDateEnd;
    }

    public void setScanDateEnd(String scanDateEnd) {
        this.scanDateEnd = scanDateEnd;
    }

    public String getSalerTaxCode() {
        return salerTaxCode;
    }

    public void setSalerTaxCode(String salerTaxCode) {
        this.salerTaxCode = salerTaxCode;
    }

}
