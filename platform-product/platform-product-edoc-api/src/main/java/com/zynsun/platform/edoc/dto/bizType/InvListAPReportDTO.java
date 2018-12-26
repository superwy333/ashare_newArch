package com.zynsun.platform.edoc.dto.bizType;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/9.
 */
public class InvListAPReportDTO implements Serializable {
    private String invNumber;
    private String invCode;
    private String saleCompanyName;
    private String saleCompanyCode;
    private String invType;

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getSaleCompanyName() {
        return saleCompanyName;
    }

    public void setSaleCompanyName(String saleCompanyName) {
        this.saleCompanyName = saleCompanyName;
    }

    public String getSaleCompanyCode() {
        return saleCompanyCode;
    }

    public void setSaleCompanyCode(String saleCompanyCode) {
        this.saleCompanyCode = saleCompanyCode;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }
}
