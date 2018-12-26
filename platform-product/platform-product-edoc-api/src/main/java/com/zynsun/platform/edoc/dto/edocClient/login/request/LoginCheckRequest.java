package com.zynsun.platform.edoc.dto.edocClient.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.zynsun.platform.edoc.dto.edocClient.RequestHeader;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 客户端登录请求
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 11:07
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
@XmlType(name = "", propOrder = {"header", "body"})
public class LoginCheckRequest implements Serializable {

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
    private LoginCheckRequestBody body;

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public LoginCheckRequestBody getBody() {
        return body;
    }

    public void setBody(LoginCheckRequestBody body) {
        this.body = body;
    }
}
