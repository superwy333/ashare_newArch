package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "edoc_inv_diff")
public class EdocInvDiff extends BaseDomain {
    /**
     * 发票修改字段
     */
    @Column(name = "inv_column")
    private String invColumn;

    /**
     * 老值
     */
    @Column(name = "old_val")
    private String oldVal;

    /**
     * 新值
     */
    @Column(name = "new_val")
    private String newVal;


    /**
     * 新值
     */
    @Column(name = "inv_id")
    private Long invId;

    /**
     * 获取发票ID
     *
     * @return inv_column - 发票ID
     */
    public Long getInvId() {
        return invId;
    }

    /**
     * 设置发票ID
     *
     * @param invId 发票ID
     */
    public void setInvId(Long invId) {
        this.invId = invId;
    }

    /**
     * 获取发票修改字段
     *
     * @return inv_column - 发票修改字段
     */
    public String getInvColumn() {
        return invColumn;
    }

    /**
     * 设置发票修改字段
     *
     * @param invColumn 发票修改字段
     */
    public void setInvColumn(String invColumn) {
        this.invColumn = invColumn == null ? null : invColumn.trim();
    }

    /**
     * 获取老值
     *
     * @return old_val - 老值
     */
    public String getOldVal() {
        return oldVal;
    }

    /**
     * 设置老值
     *
     * @param oldVal 老值
     */
    public void setOldVal(String oldVal) {
        this.oldVal = oldVal == null ? null : oldVal.trim();
    }

    /**
     * 获取新值
     *
     * @return new_val - 新值
     */
    public String getNewVal() {
        return newVal;
    }

    /**
     * 设置新值
     *
     * @param newVal 新值
     */
    public void setNewVal(String newVal) {
        this.newVal = newVal == null ? null : newVal.trim();
    }
}