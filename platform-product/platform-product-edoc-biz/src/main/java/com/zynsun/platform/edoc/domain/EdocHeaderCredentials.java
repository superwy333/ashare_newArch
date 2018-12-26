package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;

/**
 * @author jary
 * @creatTime 2018/12/4 8:59 AM
 */
public class EdocHeaderCredentials extends BaseDomain {

    /**
     * 对应的影像任务ID
     */
    //@NotBlank(message = "图片对应影像任务ID不能空")
    @Column(name = "edoc_header_id")
    private Long edocHeaderId;

    @Column(name = "edoc_credentials_id")
    private Long edocCredentialsId;

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

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public Long getEdocCredentialsId() {
        return edocCredentialsId;
    }

    public void setEdocCredentialsId(Long edocCredentialsId) {
        this.edocCredentialsId = edocCredentialsId;
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
