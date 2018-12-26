package com.zynsun.platform.edoc.dto.bizType;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/13 10:12
 * @Modified By：
 */
public class BillTypeAttrDTO extends PageDTO {
    /**
     * 单证类型ID
     */
    private Long billTypeId;

    /**
     * 编码
     */
    private String code;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性英文名称
     */
    private String nameEn;

    /**
     * 属性类型
     */
    private String type;

    /**
     * 属性长度
     */
    private Integer length;

    /**
     * 是否可为空
     */
    private String isNull;

    /**
     * 验证规则
     */
    private String validRegex;

    /**
     * 描述
     */
    private String remarks;

    public Long getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(Long billTypeId) {
        this.billTypeId = billTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getValidRegex() {
        return validRegex;
    }

    public void setValidRegex(String validRegex) {
        this.validRegex = validRegex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
