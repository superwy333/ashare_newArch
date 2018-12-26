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
public class BaseConfigResponseBodyCategoryFields implements Serializable {

    /**
     * 业务扩展属性字段
     */
    @XmlElement(name = "FIELD")
    @JsonProperty(value = "FIELD")
    private List<BaseConfigResponseBodyCategoryField> field;

    public List<BaseConfigResponseBodyCategoryField> getField() {
        return field;
    }

    public void setField(List<BaseConfigResponseBodyCategoryField> field) {
        this.field = field;
    }
}
