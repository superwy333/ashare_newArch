package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.BaseDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-10 15:23
 **/
public class SectionItemForIreport extends BaseDTO {


    private String edocType;

    private String edocNo;

    //如果按照分册中的凭证来打印封面，则使用下面两个凭证属性
    private String credentialsNo;

    private String credentialsType;

    // 如果是编盒封面，使用下面属性
    private String recordsNo;

    public String getRecordsNo() {
        return recordsNo;
    }

    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getCredentialsNo() {
        return credentialsNo;
    }

    public void setCredentialsNo(String credentialsNo) {
        this.credentialsNo = credentialsNo;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }
}
