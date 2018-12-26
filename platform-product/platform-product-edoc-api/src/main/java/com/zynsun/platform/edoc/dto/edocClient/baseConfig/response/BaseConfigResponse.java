package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.zynsun.platform.edoc.dto.edocClient.AbstractResponse;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 客户端获取基础配置信息应答
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlRootElement(name = "RESPONSE")
@XmlType(name = "", propOrder = {"header", "body"})
public class BaseConfigResponse extends AbstractResponse implements Serializable {

    /**
     * 响应头信息
     */
    @XmlElement(name = "RESPONSE_HEADER")
    @JsonProperty(value = "RESPONSE_HEADER")
    private ResponseHeader header;

    /**
     * 响应内容
     */
    @XmlElement(name = "RESPONSE_CONTEXT")
    @JsonProperty(value = "RESPONSE_CONTEXT")
    private BaseConfigResponseBody body;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public BaseConfigResponseBody getBody() {
        return body;
    }

    public void setBody(BaseConfigResponseBody body) {
        this.body = body;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        setHeader(header);
    }
}
