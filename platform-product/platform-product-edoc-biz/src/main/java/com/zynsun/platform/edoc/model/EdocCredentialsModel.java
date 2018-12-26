package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:50
 * @Modified By:
 */
public class EdocCredentialsModel extends PageModel {

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    //所属edocHeaderId
    private Long edocHeaderId;
    //凭证类型
    private String credentialsType;
    //edocHeader来源
    private String edocSource;
    // 公司Code
    private String companyCode;
    // 公司
    private String companyName;

    // 制单日期
    private String createDate;

    // 凭证编号
    private String credentialsNum;

    // 凭证状态 4：待分册 5：已分册-待编盒 6：已编盒-待装箱 7：已归档
    private  String voucherStatus;

    private String edocNo;

    private String edocType;

    private Long printCount;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    private String pdfFileUrl;

    private String edocCreateBy;

    private String edocPublicType;

    private String edocVoucherNo;

    private String uploadType;

    private String recordsNo;

    private String boxNo;

    private String erpId;

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

//    /**
//     * 创建时间开始
//     */
//    private Date createTimeStart;
//
//    /**
//     * 创建时间结束
//     */
//    private Date createTimeEnd;
//
//    public Date getCreateTimeStart() {
//        return createTimeStart;
//    }
//
//    public void setCreateTimeStart(Date createTimeStart) {
//        this.createTimeStart = createTimeStart;
//    }
//
//    public Date getCreateTimeEnd() {
//        return createTimeEnd;
//    }
//
//    public void setCreateTimeEnd(Date createTimeEnd) {
//        this.createTimeEnd = createTimeEnd;
//    }

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    private  String edocTaskStatus;

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }

    public String getRecordsNo() {
        return recordsNo;
    }

    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getEdocVoucherNo() {
        return edocVoucherNo;
    }

    public void setEdocVoucherNo(String edocVoucherNo) {
        this.edocVoucherNo = edocVoucherNo;
    }

    public String getEdocCreateBy() {
        return edocCreateBy;
    }

    public void setEdocCreateBy(String edocCreateBy) {
        this.edocCreateBy = edocCreateBy;
    }

    public String getEdocPublicType() {
        return edocPublicType;
    }

    public void setEdocPublicType(String edocPublicType) {
        this.edocPublicType = edocPublicType;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
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

    public Long getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Long printCount) {
        this.printCount = printCount;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    public String getEdocSource() {
        return edocSource;
    }

    public void setEdocSource(String edocSource) {
        this.edocSource = edocSource;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String createTimeStr;

    private String credentialsStatus;
    private String accountinLedger;

    private String createDateStart;
    private String createDateEnd;

    private List<EdocHeaderModel> edocHeaderModelList;

    public List<EdocHeaderModel> getEdocHeaderModelList() {
        return edocHeaderModelList;
    }

    public void setEdocHeaderModelList(List<EdocHeaderModel> edocHeaderModelList) {
        this.edocHeaderModelList = edocHeaderModelList;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getAccountinLedger() {
        return accountinLedger;
    }

    public void setAccountinLedger(String accountinLedger) {
        this.accountinLedger = accountinLedger;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getCredentialsStatus() {
        return credentialsStatus;
    }

    public void setCredentialsStatus(String credentialsStatus) {
        this.credentialsStatus = credentialsStatus;
    }
}
