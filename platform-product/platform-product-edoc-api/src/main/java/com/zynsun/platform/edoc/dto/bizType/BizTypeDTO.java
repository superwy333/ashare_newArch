package com.zynsun.platform.edoc.dto.bizType;

import com.zynsun.openplatform.dto.PageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/6/2 0002 下午 1:50
 * @Modified By：
 */
public class BizTypeDTO extends PageDTO {

    /**
     * 编码
     */
    private String code;

    /**
     * 父业务类型ID，若为根节点则为0
     */
    private Long parentId;

    /**
     * 父业务类型ids
     */
    private String parentIds;

    /**
     * 业务类型名称
     */
    private String name;

    /**
     * 业务类型英文名
     */
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
     * 关联的单证类型ids
     */
    private String billTypeParentIds;

    private List<BillTypeDTO> billTypeDTOList = new ArrayList<>();

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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBillTypeParentIds() {
        return billTypeParentIds;
    }

    public void setBillTypeParentIds(String billTypeParentIds) {
        this.billTypeParentIds = billTypeParentIds;
    }
}
