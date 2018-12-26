package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 15:28
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyDocTypeField implements Serializable {

    /**
     * 属性名
     */
    @XmlElement(name = "FIELD_NAME")
    @JsonProperty(value = "FIELD_NAME")
    private String fieldName;

    /**
     * 属性英文名
     */
    @XmlElement(name = "FIELD_NAME_EN")
    @JsonProperty(value = "FIELD_NAME_EN")
    private String fieldNameEn;

    /**
     * 类型
     */
    @XmlElement(name = "FIELD_TYPE")
    @JsonProperty(value = "FIELD_TYPE")
    private String fieldType;

    /**
     * 长度
     */
    @XmlElement(name = "FIELD_LENGTH")
    @JsonProperty(value = "FIELD_LENGTH")
    private String fieldLength;

    /**
     * 验证格式
     */
    @XmlElement(name = "VALID_REGEX")
    @JsonProperty(value = "VALID_REGEX")
    private String validRegex;

    /**
     * 必输
     */
    @XmlElement(name = "IS_REQUIRED")
    @JsonProperty(value = "IS_REQUIRED")
    private String isRequired;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldNameEn() {
        return fieldNameEn;
    }

    public void setFieldNameEn(String fieldNameEn) {
        this.fieldNameEn = fieldNameEn;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getValidRegex() {
        return validRegex;
    }

    public void setValidRegex(String validRegex) {
        this.validRegex = validRegex;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }
}
