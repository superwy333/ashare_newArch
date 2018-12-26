package com.zynsun.platform.edoc.dto.dynamicForm;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 19:16
 **/
public class FormFieldDTO extends PageDTO {
    /**
     * 字段名称
     */
    private String formFieldName;

    /**
     * 字段代码
     */
    private String formFieldCode;

    /**
     * 字段显示类型: 1单行文本型 2下拉型 3图片 4多行文本 5动支借支 7货币型
     */
    private Integer formFieldDisplayType;

    /**
     * 数据类型: 1字符型 2货币型 3数值型 4日期型 5二进制型
     */
    private String formFieldDataType;

    /**
     * 是否展示 0:不展示,1:展示
     */
    private Integer display;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 是否可编辑 0：不可编辑；1：可编辑
     */
    private Integer editable;

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

    /**
     * 获取字段名称
     *
     * @return form_field_name - 字段名称
     */
    public String getFormFieldName() {
        return formFieldName;
    }

    /**
     * 设置字段名称
     *
     * @param formFieldName 字段名称
     */
    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    /**
     * 获取字段代码
     *
     * @return form_field_code - 字段代码
     */
    public String getFormFieldCode() {
        return formFieldCode;
    }

    /**
     * 设置字段代码
     *
     * @param formFieldCode 字段代码
     */
    public void setFormFieldCode(String formFieldCode) {
        this.formFieldCode = formFieldCode;
    }

    /**
     * 获取字段显示类型: 1单行文本型 2下拉型 3图片 4多行文本 5动支借支 7货币型
     *
     * @return form_field_display_type - 字段显示类型: 1单行文本型 2下拉型 3图片 4多行文本 5动支借支 7货币型
     */
    public Integer getFormFieldDisplayType() {
        return formFieldDisplayType;
    }

    /**
     * 设置字段显示类型: 1单行文本型 2下拉型 3图片 4多行文本 5动支借支 7货币型
     *
     * @param formFieldDisplayType 字段显示类型: 1单行文本型 2下拉型 3图片 4多行文本 5动支借支 7货币型
     */
    public void setFormFieldDisplayType(Integer formFieldDisplayType) {
        this.formFieldDisplayType = formFieldDisplayType;
    }

    /**
     * 获取数据类型: 1字符型 2货币型 3数值型 4日期型 5二进制型
     *
     * @return form_field_data_type - 数据类型: 1字符型 2货币型 3数值型 4日期型 5二进制型
     */
    public String getFormFieldDataType() {
        return formFieldDataType;
    }

    /**
     * 设置数据类型: 1字符型 2货币型 3数值型 4日期型 5二进制型
     *
     * @param formFieldDataType 数据类型: 1字符型 2货币型 3数值型 4日期型 5二进制型
     */
    public void setFormFieldDataType(String formFieldDataType) {
        this.formFieldDataType = formFieldDataType;
    }

    /**
     * 获取是否展示 0:不展示,1:展示
     *
     * @return display - 是否展示 0:不展示,1:展示
     */
    public Integer getDisplay() {
        return display;
    }

    /**
     * 设置是否展示 0:不展示,1:展示
     *
     * @param display 是否展示 0:不展示,1:展示
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
     * 获取是否可编辑 0：不可编辑；1：可编辑
     *
     * @return editable - 是否可编辑 0：不可编辑；1：可编辑
     */
    public Integer getEditable() {
        return editable;
    }

    /**
     * 设置是否可编辑 0：不可编辑；1：可编辑
     *
     * @param editable 是否可编辑 0：不可编辑；1：可编辑
     */
    public void setEditable(Integer editable) {
        this.editable = editable;
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
