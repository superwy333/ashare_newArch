package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 14:56
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyCategory implements Serializable {

    /**
     * 业务代码
     */
    @XmlElement(name = "CODE")
    @JsonProperty(value = "CODE")
    private String code;

    /**
     * 业务名称
     */
    @XmlElement(name = "NAME")
    @JsonProperty(value = "NAME")
    private String name;

    /**
     * 子业务信息
     */
    @XmlElement(name = "SUB_CATEGORIES")
    @JsonProperty(value = "SUB_CATEGORIES")
    private BaseConfigResponseBodyCategories subCategories;

    /**
     * 关联单证集合
     */
    @XmlElement(name = "DOC_TYPE_RELATION")
    @JsonProperty(value = "DOC_TYPE_RELATION")
    private BaseConfigResponseBodyDocTypeRelation docTypeRelation;

    /**
     * 业务扩展属性集合
     */
    @XmlElement(name = "FIELDS")
    @JsonProperty(value = "FIELDS")
    private BaseConfigResponseBodyCategoryFields fields;

    public BaseConfigResponseBodyCategory() {
        super();
    }

    public BaseConfigResponseBodyCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

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

    public BaseConfigResponseBodyCategories getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(BaseConfigResponseBodyCategories subCategories) {
        this.subCategories = subCategories;
    }

    public BaseConfigResponseBodyDocTypeRelation getDocTypeRelation() {
        return docTypeRelation;
    }

    public void setDocTypeRelation(BaseConfigResponseBodyDocTypeRelation docTypeRelation) {
        this.docTypeRelation = docTypeRelation;
    }

    public BaseConfigResponseBodyCategoryFields getFields() {
        return fields;
    }

    public void setFields(BaseConfigResponseBodyCategoryFields fields) {
        this.fields = fields;
    }
}
