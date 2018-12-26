package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 14:56
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyCategoryField implements Serializable {

    /**
     * 业务扩展属性名
     */
    @XmlElement(name = "FIELD_NAME")
    @JsonProperty(value = "FIELD_NAME")
    private String fieldName;

    /**
     * 业务扩展属性英文名
     */
    @XmlElement(name = "FIELD_NAME_EN")
    @JsonProperty(value = "FIELD_NAME_EN")
    private String fieldNameEn;

    /**
     * 是否必须
     */
    @XmlElement(name = "IS_REQUIRED")
    @JsonProperty(value = "IS_REQUIRED")
    private String isrRequired;

    /**
     * 获取该参数的方法
     */
    @XmlElement(name = "FUNCITON")
    @JsonProperty(value = "FUNCITON")
    private String funciton;

    /**
     * 展示方式
     */
    @XmlElement(name = "VIEW_STYLE")
    @JsonProperty(value = "VIEW_STYLE")
    private String viewStyle;

    /**
     * funciton对应入参
     */
    @XmlElement(name = "IN_PARAM")
    @JsonProperty(value = "IN_PARAM")
    private List<BaseConfigResponseBodyCategoryFieldInParam> inParams;

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

    public String getIsrRequired() {
        return isrRequired;
    }

    public void setIsrRequired(String isrRequired) {
        this.isrRequired = isrRequired;
    }

    public String getFunciton() {
        return funciton;
    }

    public void setFunciton(String funciton) {
        this.funciton = funciton;
    }

    public String getViewStyle() {
        return viewStyle;
    }

    public void setViewStyle(String viewStyle) {
        this.viewStyle = viewStyle;
    }

    public List<BaseConfigResponseBodyCategoryFieldInParam> getInParams() {
        return inParams;
    }

    public void setInParams(List<BaseConfigResponseBodyCategoryFieldInParam> inParams) {
        this.inParams = inParams;
    }
}
