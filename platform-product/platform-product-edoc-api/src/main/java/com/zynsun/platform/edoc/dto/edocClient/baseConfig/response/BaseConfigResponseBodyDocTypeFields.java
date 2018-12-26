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
 * @Date：Created in 2018/01/03 15:25
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyDocTypeFields implements Serializable {

    /**
     * 单证属性集合
     */
    @XmlElement(name = "FIELD")
    @JsonProperty(value = "FIELD")
    private List<BaseConfigResponseBodyDocTypeField> field;

    public List<BaseConfigResponseBodyDocTypeField> getField() {
        return field;
    }

    public void setField(List<BaseConfigResponseBodyDocTypeField> field) {
        this.field = field;
    }
}
