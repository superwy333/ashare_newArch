package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

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
public class BaseConfigResponseBody implements Serializable {

    /**
     * 业务类型集合
     */
    @XmlElement(name = "CATEGORIES")
    @JsonProperty(value = "CATEGORIES")
    private BaseConfigResponseBodyCategories categories;

    public BaseConfigResponseBodyCategories getCategories() {
        return categories;
    }

    public void setCategories(BaseConfigResponseBodyCategories categories) {
        this.categories = categories;
    }
}
