package com.zynsun.platform.edoc.dto.edocClient.login.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端登录响应Body
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 11:15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class LoginCheckResponseBody implements Serializable {

    /**
     * 用户姓名
     */
    @XmlElement(name = "USER_NAME_CN")
    @JsonProperty(value = "USER_NAME_CN")
    private String userNameCn;

    /**
     * 用户代码
     */
    @XmlElement(name = "USER_CODE")
    @JsonProperty(value = "USER_CODE")
    private String userCode;

    /**
     * 部门名称
     */
    @XmlElement(name = "DEPT_NAME")
    @JsonProperty(value = "DEPT_NAME")
    private String deptName;

    /**
     * 部门代码
     */
    @XmlElement(name = "DEPT_CODE")
    @JsonProperty(value = "DEPT_CODE")
    private String deptCode;

    public LoginCheckResponseBody() {
        super();
    }

    public LoginCheckResponseBody(String userNameCn, String userCode, String deptCode, String deptName) {
        super();
        userNameCn = userNameCn;
        userCode = userCode;
        deptCode = deptCode;
        deptName = deptName;
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
