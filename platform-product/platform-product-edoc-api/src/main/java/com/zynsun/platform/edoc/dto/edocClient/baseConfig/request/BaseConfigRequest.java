package com.zynsun.platform.edoc.dto.edocClient.baseConfig.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zynsun.platform.edoc.dto.edocClient.RequestHeader;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Map;

/**
 * 客户端获取基础配置信息请求
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
@XmlType(name = "", propOrder = {"header", "body"})
public class BaseConfigRequest implements Serializable {

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
    private BaseConfigRequestBody body;

    private Map<String,Object> authoritiesMaps;

    public Map<String, Object> getAuthoritiesMaps() {
        return authoritiesMaps;
    }

    public void setAuthoritiesMaps(Map<String, Object> authoritiesMaps) {
        this.authoritiesMaps = authoritiesMaps;
    }

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public BaseConfigRequestBody getBody() {
        return body;
    }

    public void setBody(BaseConfigRequestBody body) {
        this.body = body;
    }
}
