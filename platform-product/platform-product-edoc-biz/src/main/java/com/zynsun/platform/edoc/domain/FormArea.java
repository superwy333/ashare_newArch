package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "form_area")
public class FormArea extends BaseDomain {

    /**
     * 区域名称
     */
    @Column(name = "form_area_name")
    private String formAreaName;

    /**
     * 区域代码
     */
    @Column(name = "form_area_code")
    private String formAreaCode;

    /**
     * 是否展开  0:不展开,1:展开
     */
    private Integer open;

    /**
     * 是否展示(0:不展示,1:展示)
     */
    private Integer display;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 扩展字段1
     */
    @Column(name = "ext_field_01")
    private String extField01;

    /**
     * 扩展字段2
     */
    @Column(name = "ext_field_02")
    private String extField02;

    /**
     * 扩展字段3
     */
    @Column(name = "ext_field_03")
    private String extField03;

    /**
     * 扩展字段4
     */
    @Column(name = "ext_field_04")
    private String extField04;

    /**
     * 扩展字段5
     */
    @Column(name = "ext_field_05")
    private String extField05;

    /**
     * 获取区域名称
     *
     * @return form_area_name - 区域名称
     */
    public String getFormAreaName() {
        return formAreaName;
    }

    /**
     * 设置区域名称
     *
     * @param formAreaName 区域名称
     */
    public void setFormAreaName(String formAreaName) {
        this.formAreaName = formAreaName;
    }

    /**
     * 获取区域代码
     *
     * @return form_area_code - 区域代码
     */
    public String getFormAreaCode() {
        return formAreaCode;
    }

    /**
     * 设置区域代码
     *
     * @param formAreaCode 区域代码
     */
    public void setFormAreaCode(String formAreaCode) {
        this.formAreaCode = formAreaCode;
    }

    /**
     * 获取是否展开  0:不展开,1:展开
     *
     * @return open - 是否展开  0:不展开,1:展开
     */
    public Integer getOpen() {
        return open;
    }

    /**
     * 设置是否展开  0:不展开,1:展开
     *
     * @param open 是否展开  0:不展开,1:展开
     */
    public void setOpen(Integer open) {
        this.open = open;
    }

    /**
     * 获取是否展示(0:不展示,1:展示)
     *
     * @return display - 是否展示(0:不展示,1:展示)
     */
    public Integer getDisplay() {
        return display;
    }

    /**
     * 设置是否展示(0:不展示,1:展示)
     *
     * @param display 是否展示(0:不展示,1:展示)
     */
    public void setDisplay(Integer display) {
        this.display = display;
    }

    /**
     * 获取排序字段
     *
     * @return sort - 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
     * 获取扩展字段1
     *
     * @return ext_field_01 - 扩展字段1
     */
    public String getExtField01() {
        return extField01;
    }

    /**
     * 设置扩展字段1
     *
     * @param extField01 扩展字段1
     */
    public void setExtField01(String extField01) {
        this.extField01 = extField01;
    }

    /**
     * 获取扩展字段2
     *
     * @return ext_field_02 - 扩展字段2
     */
    public String getExtField02() {
        return extField02;
    }

    /**
     * 设置扩展字段2
     *
     * @param extField02 扩展字段2
     */
    public void setExtField02(String extField02) {
        this.extField02 = extField02;
    }

    /**
     * 获取扩展字段3
     *
     * @return ext_field_03 - 扩展字段3
     */
    public String getExtField03() {
        return extField03;
    }

    /**
     * 设置扩展字段3
     *
     * @param extField03 扩展字段3
     */
    public void setExtField03(String extField03) {
        this.extField03 = extField03;
    }

    /**
     * 获取扩展字段4
     *
     * @return ext_field_04 - 扩展字段4
     */
    public String getExtField04() {
        return extField04;
    }

    /**
     * 设置扩展字段4
     *
     * @param extField04 扩展字段4
     */
    public void setExtField04(String extField04) {
        this.extField04 = extField04;
    }

    /**
     * 获取扩展字段5
     *
     * @return ext_field_05 - 扩展字段5
     */
    public String getExtField05() {
        return extField05;
    }

    /**
     * 设置扩展字段5
     *
     * @param extField05 扩展字段5
     */
    public void setExtField05(String extField05) {
        this.extField05 = extField05;
    }
}