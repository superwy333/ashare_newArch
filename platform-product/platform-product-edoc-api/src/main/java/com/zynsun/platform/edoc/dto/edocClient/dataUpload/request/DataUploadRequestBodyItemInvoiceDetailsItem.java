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
public class DataUploadRequestBodyItemInvoiceDetailsItem implements Serializable {

    /**
     * 行号
     */
    @XmlElement(name = "LINE_NO")
    @JsonProperty(value = "LINE_NO")
    private String lineNo;

    /**
     * 商品名称
     */
    @XmlElement(name = "GOODS_NAME")
    @JsonProperty(value = "GOODS_NAME")
    private String goodsName;

    /**
     * 规格型号
     */
    @XmlElement(name = "GOODS_SPEC")
    @JsonProperty(value = "GOODS_SPEC")
    private String goodsSpec;

    /**
     * 单价
     */
    @XmlElement(name = "GOODS_PRICE")
    @JsonProperty(value = "GOODS_PRICE")
    private String goodsPrice;

    /**
     * 数量
     */
    @XmlElement(name = "GOODS_QUANTITY")
    @JsonProperty(value = "GOODS_QUANTITY")
    private String goodsQuantity;

    /**
     * 金额
     */
    @XmlElement(name = "GOODS_AMOUNT")
    @JsonProperty(value = "GOODS_AMOUNT")
    private String goodsAmount;

    /**
     * 税额
     */
    @XmlElement(name = "GOODS_TAX")
    @JsonProperty(value = "GOODS_TAX")
    private String goodsTax;

    /**
     * 税率
     */
    @XmlElement(name = "GOODS_TAX_RATE")
    @JsonProperty(value = "GOODS_TAX_RATE")
    private String goodsTaxRate;

    /**
     * 单位
     */
    @XmlElement(name = "GOODS_UNIT")
    @JsonProperty(value = "GOODS_UNIT")
    private String goodsUnit;

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsTax() {
        return goodsTax;
    }

    public void setGoodsTax(String goodsTax) {
        this.goodsTax = goodsTax;
    }

    public String getGoodsTaxRate() {
        return goodsTaxRate;
    }

    public void setGoodsTaxRate(String goodsTaxRate) {
        this.goodsTaxRate = goodsTaxRate;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
}
