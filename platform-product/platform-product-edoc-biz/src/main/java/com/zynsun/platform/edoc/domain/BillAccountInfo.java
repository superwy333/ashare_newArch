package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.platform.edoc.annotation.Validata;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_account_info")
@Validata
public class BillAccountInfo extends BaseDomain {

    /**
     * 对应影像任务edoc_levle
     */
    @Transient
    private String edocLevel = "0";

    /**
     * 对应影像任务bizType
     */
    @Transient
    private String bizType = "01";

    /**
     * 电子影像编号
     */
    //@NotBlank(message = "单证编号不能为空")
    @Column(name = "edoc_no")
    private String edocNo;

    /**
     * 单证类型（报账单/合同等下面的单证子类型）
     */
    @Column(name = "edoc_type")
    private String edocType;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交人代码
     */
    @Column(name = "submitter_code")
    private String submitterCode;

    /**
     * 提交时间
     */
    @Column(name = "submitter_time")
    private Date submitterTime;

    @Column(name = "bill_amount_total")
    private BigDecimal billAmountTotal;

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
     * 获取单证类型（报账单/合同等下面的单证子类型）
     *
     * @return edoc_type - 单证类型（报账单/合同等下面的单证子类型）
     */
    public String getEdocType() {
        return edocType;
    }

    /**
     * 设置单证类型（报账单/合同等下面的单证子类型）
     *
     * @param edocType 单证类型（报账单/合同等下面的单证子类型）
     */
    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    /**
     * 获取提交人
     *
     * @return submitter - 提交人
     */
    public String getSubmitter() {
        return submitter;
    }

    /**
     * 设置提交人
     *
     * @param submitter 提交人
     */
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    /**
     * 获取提交人代码
     *
     * @return submitter_code - 提交人代码
     */
    public String getSubmitterCode() {
        return submitterCode;
    }

    /**
     * 设置提交人代码
     *
     * @param submitterCode 提交人代码
     */
    public void setSubmitterCode(String submitterCode) {
        this.submitterCode = submitterCode;
    }

    /**
     * 获取提交时间
     *
     * @return submitter_time - 提交时间
     */
    public Date getSubmitterTime() {
        return submitterTime;
    }

    /**
     * 设置提交时间
     *
     * @param submitterTime 提交时间
     */
    public void setSubmitterTime(Date submitterTime) {
        this.submitterTime = submitterTime;
    }

    /**
     * @return bill_amount_total
     */
    public BigDecimal getBillAmountTotal() {
        return billAmountTotal;
    }

    /**
     * @param billAmountTotal
     */
    public void setBillAmountTotal(BigDecimal billAmountTotal) {
        this.billAmountTotal = billAmountTotal;
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

    public String getEdocLevel() {
        return edocLevel;
    }

    public String getBizType() {
        return bizType;
    }
}