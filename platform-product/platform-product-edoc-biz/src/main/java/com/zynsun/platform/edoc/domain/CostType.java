package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "cost_type")
public class CostType extends BaseDomain {
    /**
     * 编号
     */
    @Column(name = "f_id")
    private String fId;

    /**
     * 名称
     */
    @Column(name = "f_name")
    private String fName;

    /**
     * 级数 1、2、3，每级的宽度是3
     */
    @Column(name = "f_level")
    private String fLevel;

    /**
     * 0 非明细 1 明细，明细级位最末级
     */
    @Column(name = "f_leaf")
    private String fLeaf;

    /**
     * 父级类型
     */
    @Column(name = "f_parent")
    private String fParent;

    /**
     * 有效否 1：有效
     */
    @Column(name = "f_enable")
    private String fEnable;

    /**
     * 备注
     */
    private String remarks;

    @Column(name = "ext_field1")
    private String extField1;

    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    @Column(name = "ext_field5")
    private String extField5;

    /**
     * 获取编号
     *
     * @return f_id - 编号
     */
    public String getfId() {
        return fId;
    }

    /**
     * 设置编号
     *
     * @param fId 编号
     */
    public void setfId(String fId) {
        this.fId = fId;
    }

    /**
     * 获取名称
     *
     * @return f_name - 名称
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置名称
     *
     * @param fName 名称
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * 获取级数 1、2、3，每级的宽度是3
     *
     * @return f_level - 级数 1、2、3，每级的宽度是3
     */
    public String getfLevel() {
        return fLevel;
    }

    /**
     * 设置级数 1、2、3，每级的宽度是3
     *
     * @param fLevel 级数 1、2、3，每级的宽度是3
     */
    public void setfLevel(String fLevel) {
        this.fLevel = fLevel;
    }

    /**
     * 获取0 非明细 1 明细，明细级位最末级
     *
     * @return f_leaf - 0 非明细 1 明细，明细级位最末级
     */
    public String getfLeaf() {
        return fLeaf;
    }

    /**
     * 设置0 非明细 1 明细，明细级位最末级
     *
     * @param fLeaf 0 非明细 1 明细，明细级位最末级
     */
    public void setfLeaf(String fLeaf) {
        this.fLeaf = fLeaf;
    }

    /**
     * 获取父级类型
     *
     * @return f_parent - 父级类型
     */
    public String getfParent() {
        return fParent;
    }

    /**
     * 设置父级类型
     *
     * @param fParent 父级类型
     */
    public void setfParent(String fParent) {
        this.fParent = fParent;
    }

    /**
     * 获取有效否 1：有效
     *
     * @return f_enable - 有效否 1：有效
     */
    public String getfEnable() {
        return fEnable;
    }

    /**
     * 设置有效否 1：有效
     *
     * @param fEnable 有效否 1：有效
     */
    public void setfEnable(String fEnable) {
        this.fEnable = fEnable;
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
}