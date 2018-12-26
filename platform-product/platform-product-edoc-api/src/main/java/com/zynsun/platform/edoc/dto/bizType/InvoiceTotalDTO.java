package com.zynsun.platform.edoc.dto.bizType;

import java.io.Serializable;

/**
 * Created by tomzhang on 2017/8/17.
 */
public class InvoiceTotalDTO implements Serializable {

    private String itemTotalAmount;

    private String itemTotalTax;

    private String invAmount;

    private String invTax;

    public String getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(String itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public String getItemTotalTax() {
        return itemTotalTax;
    }

    public void setItemTotalTax(String itemTotalTax) {
        this.itemTotalTax = itemTotalTax;
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
}
