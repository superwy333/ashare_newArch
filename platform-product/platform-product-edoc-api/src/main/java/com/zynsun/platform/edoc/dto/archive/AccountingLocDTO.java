package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;

public class AccountingLocDTO extends PageDTO {
    /**
     * 档案室id
     */
    private Long storeId;

    /**
     * 档案室名称
     */
    private String storeName;

    /**
     * 库位编号
     */
    private String locNo;

    /**
     * 库位名称
     */
    private String locName;

    /**
     * 状态 0:正常，1：停用
     */
    private Integer statusId;

    /**
     * 类型 0：文档，1：册，2：盒，3：箱
     */
    private Integer typeId;

    /**
     * 最大数量
     */
    private Integer maxQyt;

    /**
     * 最大重量
     */
    private Float maxWeight;

    /**
     * 最大体积
     */
    private Float maxVolume;

    /**
     * 备注
     */
    private String remarks;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    /**
     * 获取档案室id
     *
     * @return store_id - 档案室id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置档案室id
     *
     * @param storeId 档案室id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取库位编号
     *
     * @return loc_no - 库位编号
     */
    public String getLocNo() {
        return locNo;
    }

    /**
     * 设置库位编号
     *
     * @param locNo 库位编号
     */
    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    /**
     * 获取库位名称
     *
     * @return loc_name - 库位名称
     */
    public String getLocName() {
        return locName;
    }

    /**
     * 设置库位名称
     *
     * @param locName 库位名称
     */
    public void setLocName(String locName) {
        this.locName = locName;
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
     * 获取类型 0：文档，1：册，2：盒，3：箱
     *
     * @return type_id - 类型 0：文档，1：册，2：盒，3：箱
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型 0：文档，1：册，2：盒，3：箱
     *
     * @param typeId 类型 0：文档，1：册，2：盒，3：箱
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取最大数量
     *
     * @return max_qyt - 最大数量
     */
    public Integer getMaxQyt() {
        return maxQyt;
    }

    /**
     * 设置最大数量
     *
     * @param maxQyt 最大数量
     */
    public void setMaxQyt(Integer maxQyt) {
        this.maxQyt = maxQyt;
    }

    /**
     * 获取最大重量
     *
     * @return max_weight - 最大重量
     */
    public Float getMaxWeight() {
        return maxWeight;
    }

    /**
     * 设置最大重量
     *
     * @param maxWeight 最大重量
     */
    public void setMaxWeight(Float maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * 获取最大体积
     *
     * @return max_volume - 最大体积
     */
    public Float getMaxVolume() {
        return maxVolume;
    }

    /**
     * 设置最大体积
     *
     * @param maxVolume 最大体积
     */
    public void setMaxVolume(Float maxVolume) {
        this.maxVolume = maxVolume;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}