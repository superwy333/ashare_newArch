package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_contract_info")
public class BillContractInfo extends BaseDomain {
    /**
     * 电子影像编号
     */
    @Column(name = "edoc_no")
    private String edocNo;

    /**
     * 合同名称
     */
    @Column(name = "con_name")
    private String conName;

    /**
     * 合同号
     */
    @Column(name = "con_code")
    private String conCode;

    /**
     * 合同版本
     */
    @Column(name = "con_version")
    private String conVersion;

    /**
     * 合同类型（具体合同类型）
     */
    @Column(name = "con_type")
    private String conType;

    /**
     * 合同我方主体
     */
    @Column(name = "con_ou_subject")
    private String conOuSubject;

    /**
     * 合同对方主体
     */
    @Column(name = "con_op_subject")
    private String conOpSubject;

    /**
     * 合同提交时间
     */
    @Column(name = "con_submit_time")
    private Date conSubmitTime;

    /**
     * 合同开始时间
     */
    @Column(name = "con_begin_time")
    private Date conBeginTime;

    /**
     * 合同终止时间
     */
    @Column(name = "con_end_time")
    private Date conEndTime;

    /**
     * 合同金额
     */
    @Column(name = "con_value")
    private BigDecimal conValue;

    /**
     * 备注
     */
    private String remarks;


    @Column(name = "ext_field1")
    private String extField1;

    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    @Column(name = "ext_field5")
    private String extField5;

    /**
     * 获取电子影像编号
     *
     * @return edoc_no - 电子影像编号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置电子影像编号
     *
     * @param edocNo 电子影像编号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    /**
     * 获取合同名称
     *
     * @return con_name - 合同名称
     */
    public String getConName() {
        return conName;
    }

    /**
     * 设置合同名称
     *
     * @param conName 合同名称
     */
    public void setConName(String conName) {
        this.conName = conName;
    }

    /**
     * 获取合同号
     *
     * @return con_code - 合同号
     */
    public String getConCode() {
        return conCode;
    }

    /**
     * 设置合同号
     *
     * @param conCode 合同号
     */
    public void setConCode(String conCode) {
        this.conCode = conCode;
    }

    /**
     * 获取合同版本
     *
     * @return con_version - 合同版本
     */
    public String getConVersion() {
        return conVersion;
    }

    /**
     * 设置合同版本
     *
     * @param conVersion 合同版本
     */
    public void setConVersion(String conVersion) {
        this.conVersion = conVersion;
    }

    /**
     * 获取合同类型（具体合同类型）
     *
     * @return con_type - 合同类型（具体合同类型）
     */
    public String getConType() {
        return conType;
    }

    /**
     * 设置合同类型（具体合同类型）
     *
     * @param conType 合同类型（具体合同类型）
     */
    public void setConType(String conType) {
        this.conType = conType;
    }

    /**
     * 获取合同我方主体
     *
     * @return con_ou_subject - 合同我方主体
     */
    public String getConOuSubject() {
        return conOuSubject;
    }

    /**
     * 设置合同我方主体
     *
     * @param conOuSubject 合同我方主体
     */
    public void setConOuSubject(String conOuSubject) {
        this.conOuSubject = conOuSubject;
    }

    /**
     * 获取合同对方主体
     *
     * @return con_op_subject - 合同对方主体
     */
    public String getConOpSubject() {
        return conOpSubject;
    }

    /**
     * 设置合同对方主体
     *
     * @param conOpSubject 合同对方主体
     */
    public void setConOpSubject(String conOpSubject) {
        this.conOpSubject = conOpSubject;
    }

    /**
     * 获取合同提交时间
     *
     * @return con_submit_time - 合同提交时间
     */
    public Date getConSubmitTime() {
        return conSubmitTime;
    }

    /**
     * 设置合同提交时间
     *
     * @param conSubmitTime 合同提交时间
     */
    public void setConSubmitTime(Date conSubmitTime) {
        this.conSubmitTime = conSubmitTime;
    }

    /**
     * 获取合同开始时间
     *
     * @return con_begin_time - 合同开始时间
     */
    public Date getConBeginTime() {
        return conBeginTime;
    }

    /**
     * 设置合同开始时间
     *
     * @param conBeginTime 合同开始时间
     */
    public void setConBeginTime(Date conBeginTime) {
        this.conBeginTime = conBeginTime;
    }

    /**
     * 获取合同终止时间
     *
     * @return con_end_time - 合同终止时间
     */
    public Date getConEndTime() {
        return conEndTime;
    }

    /**
     * 设置合同终止时间
     *
     * @param conEndTime 合同终止时间
     */
    public void setConEndTime(Date conEndTime) {
        this.conEndTime = conEndTime;
    }

    /**
     * 获取合同金额
     *
     * @return con_value - 合同金额
     */
    public BigDecimal getConValue() {
        return conValue;
    }

    /**
     * 设置合同金额
     *
     * @param conValue 合同金额
     */
    public void setConValue(BigDecimal conValue) {
        this.conValue = conValue;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    /**
     * @return ext_field1
     */
    public String getExtField1() {
        return extField1;
    }

    /**
     * @param extField1
     */
    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    /**
     * @return ext_field2
     */
    public String getExtField2() {
        return extField2;
    }

    /**
     * @param extField2
     */
    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    /**
     * @return ext_field3
     */
    public String getExtField3() {
        return extField3;
    }

    /**
     * @param extField3
     */
    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    /**
     * @return ext_field4
     */
    public String getExtField4() {
        return extField4;
    }

    /**
     * @param extField4
     */
    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    /**
     * @return ext_field5
     */
    public String getExtField5() {
        return extField5;
    }

    /**
     * @param extField5
     */
    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }
}