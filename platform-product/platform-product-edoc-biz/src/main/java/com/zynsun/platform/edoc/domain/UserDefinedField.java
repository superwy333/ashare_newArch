package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "user_defined_field")
public class UserDefinedField extends BaseDomain {

    /**
     * 关联设计表主键
     */
    @Column(name = "table_id")
    private Long tableId;

    /**
     * 控件id属性
     */
    @Column(name = "field_id")
    private String fieldId;

    /**
     * 控件name属性
     */
    @Column(name = "field_name")
    private String fieldName;

    /**
     * 控件名称
     */
    @Column(name = "control_name")
    private String controlName;

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

    private String remark;


    /**
     * 获取关联设计表主键
     *
     * @return table_id - 关联设计表主键
     */
    public Long getTableId() {
        return tableId;
    }

    /**
     * 设置关联设计表主键
     *
     * @param tableId 关联设计表主键
     */
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    /**
     * 获取控件id属性
     *
     * @return field_id - 控件id属性
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * 设置控件id属性
     *
     * @param fieldId 控件id属性
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * 获取控件name属性
     *
     * @return field_name - 控件name属性
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置控件name属性
     *
     * @param fieldName 控件name属性
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 获取控件名称
     *
     * @return control_name - 控件名称
     */
    public String getControlName() {
        return controlName;
    }

    /**
     * 设置控件名称
     *
     * @param controlName 控件名称
     */
    public void setControlName(String controlName) {
        this.controlName = controlName;
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

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}