package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 15:23
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyDocType implements Serializable {

    /**
     * 单证代码
     */
    @XmlElement(name = "CODE")
    @JsonProperty(value = "CODE")
    private String code;

    /**
     * 单证名称
     */
    @XmlElement(name = "NAME")
    @JsonProperty(value = "NAME")
    private String name;

    /**
     * 识别类型
     */
    @XmlElement(name = "OCR_TYPE")
    @JsonProperty(value = "OCR_TYPE")
    private String ocrType;

    /**
     * 识别位置
     */
    @XmlElement(name = "OCR_AREA")
    @JsonProperty(value = "OCR_AREA")
    private String ocrArea;

    /**
     * 是否封面：0：否；1：是
     */
    @XmlElement(name = "IS_COVER")
    @JsonProperty(value = "IS_COVER")
    private String isCover;

    /**
     * 属性集合
     */
    @XmlElement(name = "FIELDS")
    @JsonProperty(value = "FIELDS")
    private BaseConfigResponseBodyDocTypeFields fields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOcrType() {
        return ocrType;
    }

    public void setOcrType(String ocrType) {
        this.ocrType = ocrType;
    }

    public String getOcrArea() {
        return ocrArea;
    }

    public void setOcrArea(String ocrArea) {
        this.ocrArea = ocrArea;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public BaseConfigResponseBodyDocTypeFields getFields() {
        return fields;
    }

    public void setFields(BaseConfigResponseBodyDocTypeFields fields) {
        this.fields = fields;
    }
}
