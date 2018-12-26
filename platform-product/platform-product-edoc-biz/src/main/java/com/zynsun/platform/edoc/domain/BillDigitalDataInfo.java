package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_digital_data_info")
public class BillDigitalDataInfo extends BaseDomain {

    /**
     * 电子影像编号
     */
    @Column(name = "edoc_no")
    private String edocNo;

    /**
     * 凭证代码
     */
    @Column(name = "voucher_code")
    private String voucherCode;

    /**
     * 账套代码
     */
    @Column(name = "voucher_bookcode")
    private String voucherBookcode;

    /**
     * 批名
     */
    @Column(name = "voucher_batchname")
    private String voucherBatchname;

    /**
     * 总账日期
     */
    @Column(name = "voucher_gldate")
    private Date voucherGldate;

    /**
     * GL凭证号码
     */
    @Column(name = "voucher_glno")
    private String voucherGlno;

    /**
     * 电子凭证类型：自动冲销，US/PRC
     */
    @Column(name = "voucher_type")
    private String voucherType;

    /**
     * 凭证编号
     */
    @Column(name = "voucher_apyno")
    private String voucherApyno;

    /**
     * 制单人工号
     */
    @Column(name = "voucher_maker_no")
    private String voucherMakerNo;

    /**
     * 制单人
     */
    @Column(name = "voucher_maker")
    private String voucherMaker;

    /**
     * 凭证来源:手工，导入
     */
    @Column(name = "voucher_from")
    private String voucherFrom;

    /**
     * AP凭证号
     */
    @Column(name = "voucher_apno")
    private String voucherApno;

    /**
     * 采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     */
    @Column(name = "voucher_puno")
    private String voucherPuno;

    /**
     * 采购合同号：采购合同号（多个以逗号隔开）
     */
    @Column(name = "voucher_con_no")
    private String voucherConNo;

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
     * 获取凭证代码
     *
     * @return voucher_code - 凭证代码
     */
    public String getVoucherCode() {
        return voucherCode;
    }

    /**
     * 设置凭证代码
     *
     * @param voucherCode 凭证代码
     */
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    /**
     * 获取账套代码
     *
     * @return voucher_bookcode - 账套代码
     */
    public String getVoucherBookcode() {
        return voucherBookcode;
    }

    /**
     * 设置账套代码
     *
     * @param voucherBookcode 账套代码
     */
    public void setVoucherBookcode(String voucherBookcode) {
        this.voucherBookcode = voucherBookcode;
    }

    /**
     * 获取批名
     *
     * @return voucher_batchname - 批名
     */
    public String getVoucherBatchname() {
        return voucherBatchname;
    }

    /**
     * 设置批名
     *
     * @param voucherBatchname 批名
     */
    public void setVoucherBatchname(String voucherBatchname) {
        this.voucherBatchname = voucherBatchname;
    }

    /**
     * 获取总账日期
     *
     * @return voucher_gldate - 总账日期
     */
    public Date getVoucherGldate() {
        return voucherGldate;
    }

    /**
     * 设置总账日期
     *
     * @param voucherGldate 总账日期
     */
    public void setVoucherGldate(Date voucherGldate) {
        this.voucherGldate = voucherGldate;
    }

    /**
     * 获取GL凭证号码
     *
     * @return voucher_glno - GL凭证号码
     */
    public String getVoucherGlno() {
        return voucherGlno;
    }

    /**
     * 设置GL凭证号码
     *
     * @param voucherGlno GL凭证号码
     */
    public void setVoucherGlno(String voucherGlno) {
        this.voucherGlno = voucherGlno;
    }

    /**
     * 获取电子凭证类型：自动冲销，US/PRC
     *
     * @return voucher_type - 电子凭证类型：自动冲销，US/PRC
     */
    public String getVoucherType() {
        return voucherType;
    }

    /**
     * 设置电子凭证类型：自动冲销，US/PRC
     *
     * @param voucherType 电子凭证类型：自动冲销，US/PRC
     */
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    /**
     * 获取凭证编号
     *
     * @return voucher_apyno - 凭证编号
     */
    public String getVoucherApyno() {
        return voucherApyno;
    }

    /**
     * 设置凭证编号
     *
     * @param voucherApyno 凭证编号
     */
    public void setVoucherApyno(String voucherApyno) {
        this.voucherApyno = voucherApyno;
    }

    /**
     * 获取制单人工号
     *
     * @return voucher_maker_no - 制单人工号
     */
    public String getVoucherMakerNo() {
        return voucherMakerNo;
    }

    /**
     * 设置制单人工号
     *
     * @param voucherMakerNo 制单人工号
     */
    public void setVoucherMakerNo(String voucherMakerNo) {
        this.voucherMakerNo = voucherMakerNo;
    }

    /**
     * 获取制单人
     *
     * @return voucher_maker - 制单人
     */
    public String getVoucherMaker() {
        return voucherMaker;
    }

    /**
     * 设置制单人
     *
     * @param voucherMaker 制单人
     */
    public void setVoucherMaker(String voucherMaker) {
        this.voucherMaker = voucherMaker;
    }

    /**
     * 获取凭证来源:手工，导入
     *
     * @return voucher_from - 凭证来源:手工，导入
     */
    public String getVoucherFrom() {
        return voucherFrom;
    }

    /**
     * 设置凭证来源:手工，导入
     *
     * @param voucherFrom 凭证来源:手工，导入
     */
    public void setVoucherFrom(String voucherFrom) {
        this.voucherFrom = voucherFrom;
    }

    /**
     * 获取AP凭证号
     *
     * @return voucher_apno - AP凭证号
     */
    public String getVoucherApno() {
        return voucherApno;
    }

    /**
     * 设置AP凭证号
     *
     * @param voucherApno AP凭证号
     */
    public void setVoucherApno(String voucherApno) {
        this.voucherApno = voucherApno;
    }

    /**
     * 获取采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     *
     * @return voucher_puno - 采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     */
    public String getVoucherPuno() {
        return voucherPuno;
    }

    /**
     * 设置采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     *
     * @param voucherPuno 采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     */
    public void setVoucherPuno(String voucherPuno) {
        this.voucherPuno = voucherPuno;
    }

    /**
     * 获取采购合同号：采购合同号（多个以逗号隔开）
     *
     * @return voucher_con_no - 采购合同号：采购合同号（多个以逗号隔开）
     */
    public String getVoucherConNo() {
        return voucherConNo;
    }

    /**
     * 设置采购合同号：采购合同号（多个以逗号隔开）
     *
     * @param voucherConNo 采购合同号：采购合同号（多个以逗号隔开）
     */
    public void setVoucherConNo(String voucherConNo) {
        this.voucherConNo = voucherConNo;
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