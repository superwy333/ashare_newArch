package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "bill_type_style")
public class BillTypeStyle extends BaseDomain {
    /**
     * 单证类型ID
     */
    @Column(name = "bill_type_id")
    private Long billTypeId;

    /**
     * 表单名称
     */
    private String name;

    /**
     * 表单英文名
     */
    @Column(name = "name_en")
    private String nameEn;

    /**
     * 表单描述
     */
    private String remarks;

    /**
     * 获取单证类型ID
     *
     * @return bill_type_id - 单证类型ID
     */
    public Long getBillTypeId() {
        return billTypeId;
    }

    /**
     * 设置单证类型ID
     *
     * @param billTypeId 单证类型ID
     */
    public void setBillTypeId(Long billTypeId) {
        this.billTypeId = billTypeId;
    }

    /**
     * 获取表单名称
     *
     * @return name - 表单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置表单名称
     *
     * @param name 表单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取表单英文名
     *
     * @return name_en - 表单英文名
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置表单英文名
     *
     * @param nameEn 表单英文名
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * 获取表单描述
     *
     * @return remarks - 表单描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置表单描述
     *
     * @param remarks 表单描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}