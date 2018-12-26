package com.zynsun.platform.edoc.dto.dynamicForm;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 19:17
 **/
public class FormTemplateDTO extends PageDTO {
    /**
     * 单据名称
     */
    private String bizName;

    /**
     * 单据代码
     */
    private Long bizCode;

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
     * 表单模板
     */
    private String formTemplate;

    /**
     * 获取单据名称
     *
     * @return biz_name - 单据名称
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * 设置单据名称
     *
     * @param bizName 单据名称
     */
    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    /**
     * 获取单据代码
     *
     * @return biz_code - 单据代码
     */
    public Long getBizCode() {
        return bizCode;
    }

    /**
     * 设置单据代码
     *
     * @param bizCode 单据代码
     */
    public void setBizCode(Long bizCode) {
        this.bizCode = bizCode;
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

    /**
     * 获取表单模板
     *
     * @return form_template - 表单模板
     */
    public String getFormTemplate() {
        return formTemplate;
    }

    /**
     * 设置表单模板
     *
     * @param formTemplate 表单模板
     */
    public void setFormTemplate(String formTemplate) {
        this.formTemplate = formTemplate;
    }
}
