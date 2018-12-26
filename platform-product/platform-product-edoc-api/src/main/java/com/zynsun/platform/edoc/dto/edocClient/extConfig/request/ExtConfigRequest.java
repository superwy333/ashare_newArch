package com.zynsun.platform.edoc.dto.edocClient.extConfig.request;

import com.zynsun.platform.edoc.dto.edocClient.RequestHeader;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 客户端获取扩展配置信息请求
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
@XmlType(name = "", propOrder = {"header", "body"})
public class ExtConfigRequest implements Serializable {

    /**
     * 请求头信息
     */
    @XmlElement(name = "HEADER")
    @JsonProperty(value = "HEADER")
    private RequestHeader header;

    /**
     * 请求参数信息
     */
    @XmlElement(name = "PARAMETER")
    @JsonProperty(value = "PARAMETER")
    private ExtConfigRequestBody body;

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public ExtConfigRequestBody getBody() {
        return body;
    }

    public void setBody(ExtConfigRequestBody body) {
        this.body = body;
    }
}
