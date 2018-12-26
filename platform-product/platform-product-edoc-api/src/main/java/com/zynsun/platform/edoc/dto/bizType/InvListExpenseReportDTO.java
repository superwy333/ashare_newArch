package com.zynsun.platform.edoc.dto.bizType;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/9.
 */
public class InvListExpenseReportDTO implements Serializable{
    private String invNumber;
    private String invCode;
    private String edocNo;

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

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }
}
