package com.zynsun.platform.edoc.dto.dynamicForm;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:52
 **/
public class FormAreaDTO extends PageDTO {

    /**
     * 区域名称
     */
    private String formAreaName;

    /**
     * 区域代码
     */
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
    private String extField01;

    /**
     * 扩展字段2
     */
    private String extField02;

    /**
     * 扩展字段3
     */
    private String extField03;

    /**
     * 扩展字段4
     */
    private String extField04;

    /**
     * 扩展字段5
     */
    private String extField05;

    public String getFormAreaName() {
        return formAreaName;
    }

    public void setFormAreaName(String formAreaName) {
        this.formAreaName = formAreaName;
    }

    public String getFormAreaCode() {
        return formAreaCode;
    }

    public void setFormAreaCode(String formAreaCode) {
        this.formAreaCode = formAreaCode;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getExtField01() {
        return extField01;
    }

    public void setExtField01(String extField01) {
        this.extField01 = extField01;
    }

    public String getExtField02() {
        return extField02;
    }

    public void setExtField02(String extField02) {
        this.extField02 = extField02;
    }

    public String getExtField03() {
        return extField03;
    }

    public void setExtField03(String extField03) {
        this.extField03 = extField03;
    }

    public String getExtField04() {
        return extField04;
    }

    public void setExtField04(String extField04) {
        this.extField04 = extField04;
    }

    public String getExtField05() {
        return extField05;
    }

    public void setExtField05(String extField05) {
        this.extField05 = extField05;
    }
}
