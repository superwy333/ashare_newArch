package com.zynsun.platform.edoc.dto.edocClient.baseConfig.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端基础配置请求Body
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigRequestBody implements Serializable {

    /**
     * 用户代码
     */
    @XmlElement(name = "USER_CODE")
    @JsonProperty(value = "USER_CODE")
    private String userCode;

    /**
     * 业务类型代码
     */
    @XmlElement(name = "CATEGORY_CODE")
    @JsonProperty(value = "CATEGORY_CODE")
    private String categoryCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
