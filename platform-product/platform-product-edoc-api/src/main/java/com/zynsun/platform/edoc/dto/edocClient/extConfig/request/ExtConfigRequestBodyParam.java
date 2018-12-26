package com.zynsun.platform.edoc.dto.edocClient.extConfig.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端扩展配置请求可变参数
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ExtConfigRequestBodyParam implements Serializable {

    /**
     * 业务类型代码
     */
    @XmlElement(name = "CATEGORY_CODE")
    @JsonProperty(value = "CATEGORY_CODE")
    private String categoryCode;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
