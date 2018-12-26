package com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 获取公司信息JavaBean
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 16:38
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BeforeUploadRequestBodyCompany implements Serializable {

    /**
     * 公司名称
     */
    @XmlElement(name = "COMPANY_NAME")
    @JsonProperty(value = "COMPANY_NAME")
    private String companyName;

    /**
     * 公司代码
     */
    @XmlElement(name = "COMPANY_CODE")
    @JsonProperty(value = "COMPANY_CODE")
    private String companyCode;

    /**
     * 公司税号
     */
    @XmlElement(name = "COMPANY_TAX_CODE")
    @JsonProperty(value = "COMPANY_TAX_CODE")
    private String companyTaxCode;

    /**
     * 扩展字段1
     */
    @XmlElement(name = "EXT_FIELD1")
    @JsonProperty(value = "EXT_FIELD1")
    private String extField1;

    /**
     * 扩展字段2
     */
    @XmlElement(name = "EXT_FIELD2")
    @JsonProperty(value = "EXT_FIELD2")
    private String extField2;

    /**
     * 扩展字段3
     */
    @XmlElement(name = "EXT_FIELD3")
    @JsonProperty(value = "EXT_FIELD3")
    private String extField3;

    /**
     * 扩展字段4
     */
    @XmlElement(name = "EXT_FIELD4")
    @JsonProperty(value = "EXT_FIELD4")
    private String extField4;

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

    public String getCompanyTaxCode() {
        return companyTaxCode;
    }

    public void setCompanyTaxCode(String companyTaxCode) {
        this.companyTaxCode = companyTaxCode;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }
}
