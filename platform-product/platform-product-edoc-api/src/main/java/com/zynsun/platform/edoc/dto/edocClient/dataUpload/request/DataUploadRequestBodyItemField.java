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
public class DataUploadRequestBodyItemField implements Serializable {

    /**
     * 属性编码
     */
    @XmlElement(name = "FIELD_CODE")
    @JsonProperty(value = "FIELD_CODE")
    private String fieldCode;

    /**
     * 属性名
     */
    @XmlElement(name = "FIELD_NAME_EN")
    @JsonProperty(value = "FIELD_NAME_EN")
    private String fieldNameEn;

    /**
     * 属性值
     */
    @XmlElement(name = "FIELD_VALUE")
    @JsonProperty(value = "FIELD_VALUE")
    private String fieldValue;

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldNameEn() {
        return fieldNameEn;
    }

    public void setFieldNameEn(String fieldNameEn) {
        this.fieldNameEn = fieldNameEn;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
