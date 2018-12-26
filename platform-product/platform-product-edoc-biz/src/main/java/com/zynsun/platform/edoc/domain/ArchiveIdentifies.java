package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.PageModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
@Table(name = "accounting_archives_identifies")
public class ArchiveIdentifies extends PageModel {


    //关联报账单id
    @Column(name = "edoc_header_id")
    private Long edocHeaderId;

    //是否延期 0-销毁 1-延期
    @Column(name = "identifies_flag")
    private String identifiesFlag;

    //档案延期年限(与档案年限累加)
    @Column(name = "identifies_archive_year")
    private String identifiesArchiveYear;

    //鉴定人
    @Column(name = "identifier")
    private String identifier;

    //鉴定时间
    @Column(name = "identifies_time")
    private String identifiesTime;

    //鉴定机构代码
    @Column(name = "office_id")
    private String officeId;

    //鉴定机构名称
    @Column(name = "office_name")
    private String officeName;

    //描述
    @Column(name = "remarks")
    private String remarks;

    //档号
    @Column(name = "files_no")
    private String filesNo;

    //
    @Column(name = "ext_field1")
    private String extField1;

    //
    @Column(name = "ext_field2")
    private String extField2;

    //
    @Column(name = "ext_field3")
    private String extField3;

    //
    @Column(name = "ext_field4")
    private String extField4;

    //
    @Column(name = "ext_field5")
    private String extField5;

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getIdentifiesFlag() {
        return identifiesFlag;
    }

    public void setIdentifiesFlag(String identifiesFlag) {
        this.identifiesFlag = identifiesFlag;
    }

    public String getIdentifiesArchiveYear() {
        return identifiesArchiveYear;
    }

    public void setIdentifiesArchiveYear(String identifiesArchiveYear) {
        this.identifiesArchiveYear = identifiesArchiveYear;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifiesTime() {
        return identifiesTime;
    }

    public void setIdentifiesTime(String identifiesTime) {
        this.identifiesTime = identifiesTime;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
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
