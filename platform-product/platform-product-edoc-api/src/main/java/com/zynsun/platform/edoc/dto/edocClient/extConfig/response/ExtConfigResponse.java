package com.zynsun.platform.edoc.dto.edocClient.extConfig.response;

import com.zynsun.platform.edoc.dto.edocClient.AbstractResponse;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端获取扩展配置信息应答
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlRootElement(name = "RESPONSE")
@XmlType(name = "", propOrder = {"header", "body"})
public class ExtConfigResponse extends AbstractResponse implements Serializable {

    /**
     * 应答头信息
     */
    @XmlElement(name = "RESPONSE_HEADER")
    @JsonProperty(value = "RESPONSE_HEADER")
    private ResponseHeader header;

    /**
     * 应答内容
     */
    @XmlElement(name = "RESPONSE_CONTEXT")
    @JsonProperty(value = "RESPONSE_CONTEXT")
    private ExtConfigResponseBody body;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public ExtConfigResponseBody getBody() {
        return body;
    }

    public void setBody(ExtConfigResponseBody body) {
        this.body = body;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        setHeader(header);
    }
}
