package com.zynsun.platform.edoc.dto.edocClient.extConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 14:10
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ExtConfigResponseBody implements Serializable {

    /**
     * 扩展业务属性集合
     */
    @XmlElement(name = "EXT_CATEGORIES")
    @JsonProperty(value = "EXT_CATEGORIES")
    private ExtConfigResponseBodyCategories extCategories;

    public ExtConfigResponseBodyCategories getExtCategories() {
        return extCategories;
    }

    public void setExtCategories(ExtConfigResponseBodyCategories extCategories) {
        this.extCategories = extCategories;
    }
}
