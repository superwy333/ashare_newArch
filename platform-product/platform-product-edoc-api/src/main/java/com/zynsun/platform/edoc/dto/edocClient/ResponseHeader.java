package com.zynsun.platform.edoc.dto.edocClient;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 影像采集客户端响应头信息
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 10:55
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"responseMessage", "responseStatus"})
public class ResponseHeader implements Serializable {

    /**
     * 响应状态. Y成功, N失败
     */
    @XmlElement(name = "RESPONSE_STATUS")
    @JsonProperty(value = "RESPONSE_STATUS")
    private String responseStatus;

    /**
     * 响应信息描述
     */
    @XmlElement(name = "RESPONSE_MESSAGE")
    @JsonProperty(value = "RESPONSE_MESSAGE")
    private String responseMessage;

    public ResponseHeader(String responseMessage, boolean isSuccess) {
        super();
        this.responseMessage = responseMessage;
        this.responseStatus = isSuccess ? "Y" : "N";
    }

    public ResponseHeader() {
        super();
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
