package com.zynsun.openplatform.shiro;

import java.io.Serializable;

/**
 * 数据权限对象
 * @author david
 * @create 2017/5/15 14:47
 */
public class DataPrivilege implements Serializable {

    private String privilegeType;
    private String privilegeValue;

    public DataPrivilege(String privilegeType, String privilegeValue) {
        this.privilegeType = privilegeType;
        this.privilegeValue = privilegeValue;
    }

    public DataPrivilege() {
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public String getPrivilegeValue() {
        return privilegeValue;
    }

    public void setPrivilegeValue(String privilegeValue) {
        this.privilegeValue = privilegeValue;
    }
}

