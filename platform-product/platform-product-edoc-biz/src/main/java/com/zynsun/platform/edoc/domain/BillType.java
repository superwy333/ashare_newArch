package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "bill_type")
public class BillType extends BaseDomain {
    /**
     * 编码
     */
    private String code;

    /**
     * 父单证类型id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 父单证类型ids
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 单证类型名称
     */
    private String name;

    /**
     * 单证类型英文名
     */
    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "ocr_type")
    private String ocrType;

    @Column(name = "ocr_area")
    private String ocrArea;

    /**
     * 是否封面：0：否；1：是
     */
    @Column(name = "is_cover")
    private String isCover;

    /**
     * 单证类型描述
     */
    private String remarks;

    /**
     * 排序码
     */
    private Long sort;

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    /**
     * 流程定义key
     */
    private String processDefinitionKey;

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
     * 获取父单证类型id
     *
     * @return parent_id - 父单证类型id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父单证类型id
     *
     * @param parentId 父单证类型id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父单证类型ids
     *
     * @return parent_ids - 父单证类型ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置父单证类型ids
     *
     * @param parentIds 父单证类型ids
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * 获取单证类型名称
     *
     * @return name - 单证类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置单证类型名称
     *
     * @param name 单证类型名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取单证类型英文名
     *
     * @return name_en - 单证类型英文名
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置单证类型英文名
     *
     * @param nameEn 单证类型英文名
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * @return ocr_type
     */
    public String getOcrType() {
        return ocrType;
    }

    /**
     * @param ocrType
     */
    public void setOcrType(String ocrType) {
        this.ocrType = ocrType == null ? null : ocrType.trim();
    }

    /**
     * @return ocr_area
     */
    public String getOcrArea() {
        return ocrArea;
    }

    /**
     * @param ocrArea
     */
    public void setOcrArea(String ocrArea) {
        this.ocrArea = ocrArea == null ? null : ocrArea.trim();
    }

    /**
     * 获取是否封面：0：否；1：是
     *
     * @return is_cover - 是否封面：0：否；1：是
     */
    public String getIsCover() {
        return isCover;
    }

    /**
     * 设置是否封面：0：否；1：是
     *
     * @param isCover 是否封面：0：否；1：是
     */
    public void setIsCover(String isCover) {
        this.isCover = isCover == null ? null : isCover.trim();
    }

    /**
     * 获取单证类型描述
     *
     * @return remarks - 单证类型描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置单证类型描述
     *
     * @param remarks 单证类型描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
}