package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "bill_type_attr")
public class BillTypeAttr extends BaseDomain {
    /**
     * 单证类型ID
     */
    @Column(name = "bill_type_id")
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
    @Column(name = "name_en")
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
    @Column(name = "is_null")
    private String isNull;

    /**
     * 验证规则
     */
    @Column(name = "valid_regex")
    private String validRegex;

    /**
     * 描述
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
     * 获取属性名称
     *
     * @return name - 属性名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性名称
     *
     * @param name 属性名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取属性英文名称
     *
     * @return name_en - 属性英文名称
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置属性英文名称
     *
     * @param nameEn 属性英文名称
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * 获取属性类型
     *
     * @return type - 属性类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置属性类型
     *
     * @param type 属性类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取属性长度
     *
     * @return length - 属性长度
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置属性长度
     *
     * @param length 属性长度
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取是否可为空
     *
     * @return is_null - 是否可为空
     */
    public String getIsNull() {
        return isNull;
    }

    /**
     * 设置是否可为空
     *
     * @param isNull 是否可为空
     */
    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
    }

    /**
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getValidRegex() {
        return validRegex;
    }

    public void setValidRegex(String validRegex) {
        this.validRegex = validRegex;
    }
}