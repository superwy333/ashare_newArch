package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.PageModel;

import javax.persistence.Column;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:27
 **/
public class EdocImageMark extends PageModel {

    @Column(name = "edoc_image_id")
    private Long edocImageId;
    @Column(name = "mark_name")
    private String markName;
    @Column(name = "mark_remark")
    private String markRemark;
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

    public Long getEdocImageId() {
        return edocImageId;
    }

    public void setEdocImageId(Long edocImageId) {
        this.edocImageId = edocImageId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getMarkRemark() {
        return markRemark;
    }

    public void setMarkRemark(String markRemark) {
        this.markRemark = markRemark;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    public String getExtField5() {
        return extField5;
    }

    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

}
