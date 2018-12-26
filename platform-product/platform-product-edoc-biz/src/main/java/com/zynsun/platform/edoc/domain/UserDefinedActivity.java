package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import javax.persistence.*;

@Table(name = "user_defined_activity")
public class UserDefinedActivity extends BaseDomain {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "activity_id")
    private String activityId;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "process_defined_id")
    private String processDefinedId;

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
     * @return table_id
     */
    public Long getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     */
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    /**
     * @return activity_id
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * @return activity_name
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * @return process_defined_id
     */
    public String getProcessDefinedId() {
        return processDefinedId;
    }

    /**
     * @param processDefinedId
     */
    public void setProcessDefinedId(String processDefinedId) {
        this.processDefinedId = processDefinedId;
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