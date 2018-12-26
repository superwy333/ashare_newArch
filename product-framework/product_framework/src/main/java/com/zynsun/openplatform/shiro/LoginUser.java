package com.zynsun.openplatform.shiro;

import java.io.Serializable;
import java.util.*;

public class LoginUser implements Serializable {

    private static final long serialVersionUID = -2751333016184303988L;

    // 用户ID
    private Long userId;

    // 登录用户名（帐号）
    private String loginName;

    // 用户代码
    private String userCode;

    // 用户姓名
    private String realName;

    // 所属机构ID
    private Long orgId;

    // 所属机构名称
    private String orgName;

    // 所属机构代码
    private String orgCode;

    // 税号
    private String taxCode;

    // 机构地址、电话
    private String bankName;

    // 机构开户行地址、电话
    private String bankAccountName;

    // 是否为系统管理员用户
    private boolean isSuperUser = false;

    //冻结
    private Integer locked;

    //最近登录时间
    private Date lastLoginTime;

    //当前登录用户的数据权限对象
    private List<DataPrivilege> dataPrivilegeList = new ArrayList<DataPrivilege>();

    private Map<String, Object> properties = new HashMap<String, Object>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<DataPrivilege> getDataPrivilegeList() {
        return dataPrivilegeList;
    }

    public void setDataPrivilegeList(List<DataPrivilege> dataPrivilegeList) {
        this.dataPrivilegeList = dataPrivilegeList;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
