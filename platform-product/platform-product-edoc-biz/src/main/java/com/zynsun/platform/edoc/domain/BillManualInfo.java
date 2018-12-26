package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_manual_info")
public class BillManualInfo extends BaseDomain {

    /**
     * 电子影像编号
     */
    @Column(name = "edoc_no")
    private String edocNo;

    /**
     * 账套编号
     */
    @Column(name = "vou_bookcode")
    private String vouBookcode;

    /**
     * 凭证号
     */
    @Column(name = "vou_no")
    private String vouNo;

    /**
     * 采购单据号：关联的采购单据号（付款单/报销单）（多个以逗号隔开）
     */
    @Column(name = "voucher_puno")
    private String voucherPuno;

    /**
     * 关联的原始凭证（多个以逗号隔开）
     */
    @Column(name = "voucher_orig_no")
    private String voucherOrigNo;

    /**
     * 采购合同号：采购合同号（多个以逗号隔开）
     */
    @Column(name = "voucher_con_no")
    private String voucherConNo;

    /**
     * 总账日期YYYYMM
     */
    @Column(name = "vou_date")
    private Date vouDate;

    /**
     * 日记账批名
     */
    @Column(name = "vou_batchname")
    private String vouBatchname;

    /**
     * 日记账名称
     */
    @Column(name = "vou_journalname")
    private String vouJournalname;

    /**
     * 制单人
     */
    @Column(name = "vou_maker_name")
    private String vouMakerName;

    /**
     * 制单人工号
     */
    @Column(name = "vou_maker_code")
    private String vouMakerCode;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    private Byte deleted;

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
     * 获取账套编号
     *
     * @return vou_bookcode - 账套编号
     */
    public String getVouBookcode() {
        return vouBookcode;
    }

    /**
     * 设置账套编号
     *
     * @param vouBookcode 账套编号
     */
    public void setVouBookcode(String vouBookcode) {
        this.vouBookcode = vouBookcode;
    }

    /**
     * 获取凭证号
     *
     * @return vou_no - 凭证号
     */
    public String getVouNo() {
        return vouNo;
    }

    /**
     * 设置凭证号
     *
     * @param vouNo 凭证号
     */
    public void setVouNo(String vouNo) {
        this.vouNo = vouNo;
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
     * 获取关联的原始凭证（多个以逗号隔开）
     *
     * @return voucher_orig_no - 关联的原始凭证（多个以逗号隔开）
     */
    public String getVoucherOrigNo() {
        return voucherOrigNo;
    }

    /**
     * 设置关联的原始凭证（多个以逗号隔开）
     *
     * @param voucherOrigNo 关联的原始凭证（多个以逗号隔开）
     */
    public void setVoucherOrigNo(String voucherOrigNo) {
        this.voucherOrigNo = voucherOrigNo;
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
     * 获取总账日期YYYYMM
     *
     * @return vou_date - 总账日期YYYYMM
     */
    public Date getVouDate() {
        return vouDate;
    }

    /**
     * 设置总账日期YYYYMM
     *
     * @param vouDate 总账日期YYYYMM
     */
    public void setVouDate(Date vouDate) {
        this.vouDate = vouDate;
    }

    /**
     * 获取日记账批名
     *
     * @return vou_batchname - 日记账批名
     */
    public String getVouBatchname() {
        return vouBatchname;
    }

    /**
     * 设置日记账批名
     *
     * @param vouBatchname 日记账批名
     */
    public void setVouBatchname(String vouBatchname) {
        this.vouBatchname = vouBatchname;
    }

    /**
     * 获取日记账名称
     *
     * @return vou_journalname - 日记账名称
     */
    public String getVouJournalname() {
        return vouJournalname;
    }

    /**
     * 设置日记账名称
     *
     * @param vouJournalname 日记账名称
     */
    public void setVouJournalname(String vouJournalname) {
        this.vouJournalname = vouJournalname;
    }

    /**
     * 获取制单人
     *
     * @return vou_maker_name - 制单人
     */
    public String getVouMakerName() {
        return vouMakerName;
    }

    /**
     * 设置制单人
     *
     * @param vouMakerName 制单人
     */
    public void setVouMakerName(String vouMakerName) {
        this.vouMakerName = vouMakerName;
    }

    /**
     * 获取制单人工号
     *
     * @return vou_maker_code - 制单人工号
     */
    public String getVouMakerCode() {
        return vouMakerCode;
    }

    /**
     * 设置制单人工号
     *
     * @param vouMakerCode 制单人工号
     */
    public void setVouMakerCode(String vouMakerCode) {
        this.vouMakerCode = vouMakerCode;
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