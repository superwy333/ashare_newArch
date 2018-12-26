package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "biz_type_style")
public class BizTypeStyle extends BaseDomain {
    /**
     * 业务类型ID
     */
    @Column(name = "biz_type_id")
    private Long bizTypeId;

    /**
     * 单证类型id
     */
    @Column(name = "bill_type_id")
    private Long billTypeId;

    /**
     * 表单id
     */
    @Column(name = "style_id")
    private Long styleId;

    /**
     * 获取业务类型ID
     *
     * @return biz_type_id - 业务类型ID
     */
    public Long getBizTypeId() {
        return bizTypeId;
    }

    /**
     * 设置业务类型ID
     *
     * @param bizTypeId 业务类型ID
     */
    public void setBizTypeId(Long bizTypeId) {
        this.bizTypeId = bizTypeId;
    }

    /**
     * 获取单证类型id
     *
     * @return bill_type_id - 单证类型id
     */
    public Long getBillTypeId() {
        return billTypeId;
    }

    /**
     * 设置单证类型id
     *
     * @param billTypeId 单证类型id
     */
    public void setBillTypeId(Long billTypeId) {
        this.billTypeId = billTypeId;
    }

    /**
     * 获取表单id
     *
     * @return style_id - 表单id
     */
    public Long getStyleId() {
        return styleId;
    }

    /**
     * 设置表单id
     *
     * @param styleId 表单id
     */
    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }
}