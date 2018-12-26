package com.zynsun.platform.edoc.dto;

import java.io.Serializable;

/**
 * Created by tomzhang on 2017/8/17.
 */
public class ItemDTO implements Serializable {

    private String amount;

    private String rate;

    private String itemTax;
    /**
     * 明细行号
     */
    private String lineNo;

    /**
     * 明细名称
     */
    private String itemName;

    /**
     * 明细规格
     */
    private String itemSpec;

    /**
     * 明细单价
     */
    private String itemPrice;

    /**
     * 数量
     */
    private String itemQuantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 备注
     */
    private String remarks;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getItemTax() {
        return itemTax;
    }

    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "amount='" + amount + '\'' +
                ", rate='" + rate + '\'' +
                ", itemTax='" + itemTax + '\'' +
                ", lineNo='" + lineNo + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemSpec='" + itemSpec + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", unit='" + unit + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
