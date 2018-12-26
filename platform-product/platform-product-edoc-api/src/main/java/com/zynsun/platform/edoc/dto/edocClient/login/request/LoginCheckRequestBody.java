package com.zynsun.platform.edoc.dto.edocClient.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端登录请求body
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 11:09
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class LoginCheckRequestBody implements Serializable {

    /**
     * 登录名
     */
    @XmlElement(name = "LOGIN_NAME")
    @JsonProperty(value = "LOGIN_NAME")
    private String loginName;

    /**
     * 密码
     */
    @XmlElement(name = "LOGIN_PWD")
    @JsonProperty(value = "LOGIN_PWD")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
