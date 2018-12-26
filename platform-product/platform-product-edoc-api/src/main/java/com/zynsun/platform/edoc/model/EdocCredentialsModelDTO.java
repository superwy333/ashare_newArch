package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.dto.EdocCredentialsDTO;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:42
 * @Modified By:
 */
public class EdocCredentialsModelDTO extends EdocCredentialsDTO {

    private String edocNo;

    private String edocType;

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    private  String voucherStatus;

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
    }
    private  String edocTaskStatus;

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }
}
