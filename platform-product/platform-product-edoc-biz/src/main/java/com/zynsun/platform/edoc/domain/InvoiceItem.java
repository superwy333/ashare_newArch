package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "invoice_item")
public class InvoiceItem extends BaseDomain {

    /**
     * 发票关联ID
     */
    @Column(name = "inv_id")
    private Long invId;

    /**
     * 图像-发票关联关系
     */
    @Column(name = "edoc_image_id")
    private Long edocImageId;

    /**
     * 对应的影像任务ID
     */
    @Column(name = "edoc_header_id")
    private Long edocHeaderId;

    /**
     * 明细行号
     */
    @Column(name = "line_no")
    private String lineNo;

    /**
     * 明细名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 明细规格
     */
    @Column(name = "item_spec")
    private String itemSpec;

    /**
     * 明细单价
     */
    @Column(name = "item_price")
    private String itemPrice;

    /**
     * 数量
     */
    @Column(name = "item_quantity")
    private String itemQuantity;

    /**
     * 税额
     */
    @Column(name = "item_tax")
    private String itemTax;

    /**
     * 税率
     */
    @Column(name = "item_tax_rate")
    private String itemTaxRate;

    /**
     * 金额
     */
    @Column(name = "item_amount")
    private String itemAmount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 备注
     */
    private String remarks;


    @Column(name = "ext_field1")
    private String extField1;

    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    @Column(name = "ext_field5")
    private String extField5;

    @Column(name = "item_total")
    private String itemTotal;

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
    }

    /**
     * 获取发票关联ID
     *
     * @return inv_id - 发票关联ID
     */
    public Long getInvId() {
        return invId;
    }

    /**
     * 设置发票关联ID
     *
     * @param invId 发票关联ID
     */
    public void setInvId(Long invId) {
        this.invId = invId;
    }

    /**
     * 获取图像-发票关联关系
     *
     * @return edoc_image_id - 图像-发票关联关系
     */
    public Long getEdocImageId() {
        return edocImageId;
    }

    /**
     * 设置图像-发票关联关系
     *
     * @param edocImageId 图像-发票关联关系
     */
    public void setEdocImageId(Long edocImageId) {
        this.edocImageId = edocImageId;
    }

    /**
     * 获取对应的影像任务ID
     *
     * @return edoc_header_id - 对应的影像任务ID
     */
    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    /**
     * 设置对应的影像任务ID
     *
     * @param edocHeaderId 对应的影像任务ID
     */
    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    /**
     * 获取明细行号
     *
     * @return line_no - 明细行号
     */
    public String getLineNo() {
        return lineNo;
    }

    /**
     * 设置明细行号
     *
     * @param lineNo 明细行号
     */
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    /**
     * 获取明细名称
     *
     * @return item_name - 明细名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置明细名称
     *
     * @param itemName 明细名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取明细规格
     *
     * @return item_spec - 明细规格
     */
    public String getItemSpec() {
        return itemSpec;
    }

    /**
     * 设置明细规格
     *
     * @param itemSpec 明细规格
     */
    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    /**
     * 获取明细单价
     *
     * @return item_price - 明细单价
     */
    public String getItemPrice() {
        return itemPrice;
    }

    /**
     * 设置明细单价
     *
     * @param itemPrice 明细单价
     */
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * 获取数量
     *
     * @return item_quantity - 数量
     */
    public String getItemQuantity() {
        return itemQuantity;
    }

    /**
     * 设置数量
     *
     * @param itemQuantity 数量
     */
    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * 获取税额
     *
     * @return item_tax - 税额
     */
    public String getItemTax() {
        return itemTax;
    }

    /**
     * 设置税额
     *
     * @param itemTax 税额
     */
    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }

    /**
     * 获取税率
     *
     * @return item_tax_rate - 税率
     */
    public String getItemTaxRate() {
        return itemTaxRate;
    }

    /**
     * 设置税率
     *
     * @param itemTaxRate 税率
     */
    public void setItemTaxRate(String itemTaxRate) {
        this.itemTaxRate = itemTaxRate;
    }

    /**
     * 获取金额
     *
     * @return item_amount - 金额
     */
    public String getItemAmount() {
        return itemAmount;
    }

    /**
     * 设置金额
     *
     * @param itemAmount 金额
     */
    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    /**
     * @return ext_field1
     */
    public String getExtField1() {
        return extField1;
    }

    /**
     * @param extField1
     */
    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    /**
     * @return ext_field2
     */
    public String getExtField2() {
        return extField2;
    }

    /**
     * @param extField2
     */
    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    /**
     * @return ext_field3
     */
    public String getExtField3() {
        return extField3;
    }

    /**
     * @param extField3
     */
    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    /**
     * @return ext_field4
     */
    public String getExtField4() {
        return extField4;
    }

    /**
     * @param extField4
     */
    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    /**
     * @return ext_field5
     */
    public String getExtField5() {
        return extField5;
    }

    /**
     * @param extField5
     */
    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }
}