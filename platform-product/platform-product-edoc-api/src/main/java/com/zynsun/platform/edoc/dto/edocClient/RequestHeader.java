package com.zynsun.platform.edoc.dto.edocClient;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 采集客户端请求头信息
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 10:27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"clientMac", "clientIp", "clientPc", "method"})
public class RequestHeader implements Serializable {

    /**
     * 客户端MAC
     */
    @XmlElement(name = "CLIENT_MAC")
    @JsonProperty(value = "CLIENT_MAC")
    private String clientMac;

    /**
     * 客户端IP
     */
    @XmlElement(name = "CLIENT_IP")
    @JsonProperty(value = "CLIENT_IP")
    private String clientIp;

    /**
     * 计算机名称
     */
    @XmlElement(name = "CLIENT_PC")
    @JsonProperty(value = "CLIENT_PC")
    private String clientPc;

    /**
     * 请求方法
     */
    @XmlElement(name = "METHOD")
    @JsonProperty(value = "METHOD")
    private String method;

    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPc() {
        return clientPc;
    }

    public void setClientPc(String clientPc) {
        this.clientPc = clientPc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
