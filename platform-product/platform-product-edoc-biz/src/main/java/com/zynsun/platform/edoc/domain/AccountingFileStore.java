package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "accounting_file_store")
public class AccountingFileStore extends BaseDomain {
    /**
     * 室编号
     */
    @Column(name = "store_no")
    private String storeNo;

    /**
     * 室名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 地址位置
     */
    private String address;

    /**
     * 状态 0:正常，1：停用
     */
    @Column(name = "status_id")
    private Integer statusId;

    /**
     * 负责人
     */
    @Column(name = "charge_person")
    private String chargePerson;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * QQ号码
     */
    @Column(name = "QQ")
    private String qq;

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
     * 获取室编号
     *
     * @return store_no - 室编号
     */
    public String getStoreNo() {
        return storeNo;
    }

    /**
     * 设置室编号
     *
     * @param storeNo 室编号
     */
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * 获取室名称
     *
     * @return store_name - 室名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置室名称
     *
     * @param storeName 室名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取地址位置
     *
     * @return address - 地址位置
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址位置
     *
     * @param address 地址位置
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取状态 0:正常，1：停用
     *
     * @return status_id - 状态 0:正常，1：停用
     */
    public Integer getStatusId() {
        return statusId;
    }

    /**
     * 设置状态 0:正常，1：停用
     *
     * @param statusId 状态 0:正常，1：停用
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * 获取负责人
     *
     * @return charge_person - 负责人
     */
    public String getChargePerson() {
        return chargePerson;
    }

    /**
     * 设置负责人
     *
     * @param chargePerson 负责人
     */
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    /**
     * 获取联系电话
     *
     * @return tel - 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系电话
     *
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取QQ号码
     *
     * @return QQ - QQ号码
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号码
     *
     * @param qq QQ号码
     */
    public void setQq(String qq) {
        this.qq = qq;
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