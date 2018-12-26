package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "biz_type")
public class BizType extends BaseDomain {
    /**
     * 编码
     */
    private String code;

    /**
     * 父业务类型ID，若为根节点则为0
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 父业务类型ids
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 业务类型名称
     */
    private String name;

    /**
     * 业务类型英文名
     */
    @Column(name = "name_en")
    private String nameEn;

    /**
     * 排序码
     */
    private Long sort;

    /**
     * 业务描述
     */
    private String remarks;

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取父业务类型ID，若为根节点则为0
     *
     * @return parent_id - 父业务类型ID，若为根节点则为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父业务类型ID，若为根节点则为0
     *
     * @param parentId 父业务类型ID，若为根节点则为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId == null ? null : parentId;
    }

    /**
     * 获取父业务类型ids
     *
     * @return parent_ids - 父业务类型ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置父业务类型ids
     *
     * @param parentIds 父业务类型ids
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * 获取业务类型名称
     *
     * @return name - 业务类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置业务类型名称
     *
     * @param name 业务类型名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取业务类型英文名
     *
     * @return name_en - 业务类型英文名
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置业务类型英文名
     *
     * @param nameEn 业务类型英文名
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * 获取排序码
     *
     * @return sort - 排序码
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 设置排序码
     *
     * @param sort 排序码
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 获取业务描述
     *
     * @return remarks - 业务描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置业务描述
     *
     * @param remarks 业务描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}