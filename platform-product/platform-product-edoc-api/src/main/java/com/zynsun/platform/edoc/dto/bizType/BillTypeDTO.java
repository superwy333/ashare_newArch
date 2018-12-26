package com.zynsun.platform.edoc.dto.bizType;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/6/2 0002 下午 7:59
 * @Modified By：
 */
public class BillTypeDTO extends PageDTO {

    /**
     * 编码
     */
    private String code;

    /**
     * 父单证类型id
     */
    private Long parentId;

    /**
     * 父单证类型ids
     */
    private String parentIds;

    /**
     * 单证类型名称
     */
    private String name;

    /**
     * 单证类型英文名
     */
    private String nameEn;

    private String ocrType;

    private String ocrArea;

    /**
     * 是否封面：0：否；1：是
     */
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getOcrType() {
        return ocrType;
    }

    public void setOcrType(String ocrType) {
        this.ocrType = ocrType;
    }

    public String getOcrArea() {
        return ocrArea;
    }

    public void setOcrArea(String ocrArea) {
        this.ocrArea = ocrArea;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
