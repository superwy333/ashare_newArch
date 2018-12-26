package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 15:18
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyCategoryFieldInParam implements Serializable {

    /**
     * 参数名称
     */
    @XmlElement(name = "PARAM_NAME")
    @JsonProperty(value = "PARAM_NAME")
    private String paramName;

    /**
     * 参数类型
     */
    @XmlElement(name = "PARAM_TYPE")
    @JsonProperty(value = "PARAM_TYPE")
    private String paramType;

    /**
     * 是否必输
     */
    @XmlElement(name = "IS_REQUIRED")
    @JsonProperty(value = "IS_REQUIRED")
    private String isReqired;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getIsReqired() {
        return isReqired;
    }

    public void setIsReqired(String isReqired) {
        this.isReqired = isReqired;
    }
}
