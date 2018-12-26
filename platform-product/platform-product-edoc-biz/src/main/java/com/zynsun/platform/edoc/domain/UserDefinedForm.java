package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "user_defined_form")
public class UserDefinedForm extends BaseDomain {

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_version")
    private String tableVersion;

    private String available;

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

    @Column(name = "html_code")
    private String htmlCode;

    @Column(name = "relate_table")
    private String relateTable;

    private String remark;


    public String getRelateTable() {
        return relateTable;
    }

    public void setRelateTable(String relateTable) {
        this.relateTable = relateTable;
    }

    /**
     * @return table_name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return table_version
     */
    public String getTableVersion() {
        return tableVersion;
    }

    /**
     * @param tableVersion
     */
    public void setTableVersion(String tableVersion) {
        this.tableVersion = tableVersion;
    }

    /**
     * @return available
     */
    public String getAvailable() {
        return available;
    }

    /**
     * @param available
     */
    public void setAvailable(String available) {
        this.available = available;
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

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}