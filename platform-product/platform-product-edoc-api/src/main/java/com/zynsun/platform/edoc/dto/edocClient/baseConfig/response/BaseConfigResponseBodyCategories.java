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
 * @Date：Created in 2018/01/03 14:16
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyCategories implements Serializable {

    /**
     * 业务定义
     */
    @XmlElement(name = "CATEGORY")
    @JsonProperty(value = "CATEGORY")
    private List<BaseConfigResponseBodyCategory> category;

    public List<BaseConfigResponseBodyCategory> getCategory() {
        return category;
    }

    public void setCategory(List<BaseConfigResponseBodyCategory> category) {
        this.category = category;
    }
}
