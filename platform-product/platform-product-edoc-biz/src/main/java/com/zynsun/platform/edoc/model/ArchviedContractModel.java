package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * Created by WZH on 2017/8/1.
 */
public class ArchviedContractModel extends PageModel {
    /**
     * 合同编号
     */
    private String edocNo;

    private String boxNo;

    private  String fileDate;

    private String filesNo;

    /**
     * 合同全称
     */
    private String conName;

    /**
     * 合同来源系统：0-手工创建、1-采购系统、2-媒介系统、3-其他、4-EBS
     */
    private String conSys;

    /**
     * 合同类型
     */
    private String conType;

    /**
     * 我方主体名称
     */
    private String conOuSubject;

    /**
     * 对方主体名称
     */
    private String conOpSubject;

    /**
     * 提交时间 yyyy-MM-dd hh:mm:ss
     */
    private String conSubTime;

    /**
     * 收入合同必输，其他合同非必输
     */
    private String conSigndata;

    /**
     * PR单号:支出合同必输，其他合同非必输
     */
    private String conPrcode;

    /**
     * 合同起始日期，YYYY-MM-DD
     */
    private String conBegin;

    /**
     * 合同终止日期，YYYY-MM-DD
     */
    private String conEnd;

    /**
     * 合同签订金额
     */
    private String conValue;

    /**
     * 是否匹配,初入的时候值设为 ‘0’ 标识未匹配。
     */
    private String isMatched;

    /**
     * 业务状态
     */
    private String bizStatus;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 合同编号
     */
    private String conNo;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    private String conSubTimeStart;

    private String conSubTimeEnd;

    private String conBeginStart;

    private String conBeginEnd;

    private String conEndStart;

    private String conEndEnd;

    private String conSignDataStart;

    private String conSignDataEnd;

    private String conVersion;

    private String scannerName;

    private String scanDate;

    private String safeBoxCode;

    /**
     * 获取合同编号
     *
     * @return edoc_no - 合同编号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置合同编号
     *
     * @param edocNo 合同编号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo == null ? null : edocNo.trim();
    }

    /**
     * 获取合同全称
     *
     * @return con_name - 合同全称
     */
    public String getConName() {
        return conName;
    }

    /**
     * 设置合同全称
     *
     * @param conName 合同全称
     */
    public void setConName(String conName) {
        this.conName = conName == null ? null : conName.trim();
    }

    /**
     * 获取合同来源系统：0-手工创建、1-采购系统、2-媒介系统、3-其他、4-EBS
     *
     * @return con_sys - 合同来源系统：0-手工创建、1-采购系统、2-媒介系统、3-其他、4-EBS
     */
    public String getConSys() {
        return conSys;
    }

    /**
     * 设置合同来源系统：0-手工创建、1-采购系统、2-媒介系统、3-其他、4-EBS
     *
     * @param conSys 合同来源系统：0-手工创建、1-采购系统、2-媒介系统、3-其他、4-EBS
     */
    public void setConSys(String conSys) {
        this.conSys = conSys == null ? null : conSys.trim();
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    /**
     * 获取我方主体名称
     *
     * @return con_ou_subject - 我方主体名称
     */
    public String getConOuSubject() {
        return conOuSubject;
    }

    /**
     * 设置我方主体名称
     *
     * @param conOuSubject 我方主体名称
     */
    public void setConOuSubject(String conOuSubject) {
        this.conOuSubject = conOuSubject == null ? null : conOuSubject.trim();
    }

    /**
     * 获取对方主体名称
     *
     * @return con_op_subject - 对方主体名称
     */
    public String getConOpSubject() {
        return conOpSubject;
    }

    /**
     * 设置对方主体名称
     *
     * @param conOpSubject 对方主体名称
     */
    public void setConOpSubject(String conOpSubject) {
        this.conOpSubject = conOpSubject == null ? null : conOpSubject.trim();
    }

    /**
     * 获取提交时间 yyyy-MM-dd hh:mm:ss
     *
     * @return con_sub_time - 提交时间 yyyy-MM-dd hh:mm:ss
     */
    public String getConSubTime() {
        return conSubTime;
    }

    /**
     * 设置提交时间 yyyy-MM-dd hh:mm:ss
     *
     * @param conSubTime 提交时间 yyyy-MM-dd hh:mm:ss
     */
    public void setConSubTime(String conSubTime) {
        this.conSubTime = conSubTime == null ? null : conSubTime.trim();
    }

    /**
     * 获取收入合同必输，其他合同非必输
     *
     * @return con_signdata - 收入合同必输，其他合同非必输
     */
    public String getConSigndata() {
        return conSigndata;
    }

    /**
     * 设置收入合同必输，其他合同非必输
     *
     * @param conSigndata 收入合同必输，其他合同非必输
     */
    public void setConSigndata(String conSigndata) {
        this.conSigndata = conSigndata == null ? null : conSigndata.trim();
    }

    /**
     * 获取PR单号:支出合同必输，其他合同非必输
     *
     * @return con_prcode - PR单号:支出合同必输，其他合同非必输
     */
    public String getConPrcode() {
        return conPrcode;
    }

    /**
     * 设置PR单号:支出合同必输，其他合同非必输
     *
     * @param conPrcode PR单号:支出合同必输，其他合同非必输
     */
    public void setConPrcode(String conPrcode) {
        this.conPrcode = conPrcode == null ? null : conPrcode.trim();
    }

    /**
     * 获取合同起始日期，YYYY-MM-DD
     *
     * @return con_begin - 合同起始日期，YYYY-MM-DD
     */
    public String getConBegin() {
        return conBegin;
    }

    /**
     * 设置合同起始日期，YYYY-MM-DD
     *
     * @param conBegin 合同起始日期，YYYY-MM-DD
     */
    public void setConBegin(String conBegin) {
        this.conBegin = conBegin == null ? null : conBegin.trim();
    }

    /**
     * 获取合同终止日期，YYYY-MM-DD
     *
     * @return con_end - 合同终止日期，YYYY-MM-DD
     */
    public String getConEnd() {
        return conEnd;
    }

    /**
     * 设置合同终止日期，YYYY-MM-DD
     *
     * @param conEnd 合同终止日期，YYYY-MM-DD
     */
    public void setConEnd(String conEnd) {
        this.conEnd = conEnd == null ? null : conEnd.trim();
    }

    /**
     * 获取合同签订金额
     *
     * @return con_value - 合同签订金额
     */
    public String getConValue() {
        return conValue;
    }

    /**
     * 设置合同签订金额
     *
     * @param conValue 合同签订金额
     */
    public void setConValue(String conValue) {
        this.conValue = conValue == null ? null : conValue.trim();
    }

    /**
     * 获取是否匹配,初入的时候值设为 ‘0’ 标识未匹配。
     *
     * @return is_matched - 是否匹配,初入的时候值设为 ‘0’ 标识未匹配。
     */
    public String getIsMatched() {
        return isMatched;
    }

    /**
     * 设置是否匹配,初入的时候值设为 ‘0’ 标识未匹配。
     *
     * @param isMatched 是否匹配,初入的时候值设为 ‘0’ 标识未匹配。
     */
    public void setIsMatched(String isMatched) {
        this.isMatched = isMatched == null ? null : isMatched.trim();
    }

    /**
     * 获取业务状态
     *
     * @return biz_status - 业务状态
     */
    public String getBizStatus() {
        return bizStatus;
    }

    /**
     * 设置业务状态
     *
     * @param bizStatus 业务状态
     */
    public void setBizStatus(String bizStatus) {
        this.bizStatus = bizStatus == null ? null : bizStatus.trim();
    }

    /**
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }



    /**
     * 获取描述
     *
     * @return con_no - 合同编号
     */
    public String getConNo() {
        return conNo;
    }

    /**
     * 设置描述
     *
     * @param conNo 合同编号
     */
    public void setConNo(String conNo) {
        this.conNo = conNo == null ? null : conNo.trim();
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
        this.extField1 = extField1 == null ? null : extField1.trim();
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
        this.extField2 = extField2 == null ? null : extField2.trim();
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
        this.extField3 = extField3 == null ? null : extField3.trim();
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
        this.extField4 = extField4 == null ? null : extField4.trim();
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
        this.extField5 = extField5 == null ? null : extField5.trim();
    }

    public String getConSubTimeStart() {
        return conSubTimeStart;
    }

    public void setConSubTimeStart(String conSubTimeStart) {
        this.conSubTimeStart = conSubTimeStart;
    }

    public String getConSubTimeEnd() {
        return conSubTimeEnd;
    }

    public void setConSubTimeEnd(String conSubTimeEnd) {
        this.conSubTimeEnd = conSubTimeEnd;
    }

    public String getScannerName() {
        return scannerName;
    }

    public void setScannerName(String scannerName) {
        this.scannerName = scannerName;
    }

    public String getScanDate() {
        return scanDate;
    }

    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }


    public String getFilesNo() {
        return filesNo;
    }

    public void setFilesNo(String filesNo) {
        this.filesNo = filesNo;
    }

    public String getConBeginStart() {
        return conBeginStart;
    }

    public void setConBeginStart(String conBeginStart) {
        this.conBeginStart = conBeginStart;
    }

    public String getConBeginEnd() {
        return conBeginEnd;
    }

    public void setConBeginEnd(String conBeginEnd) {
        this.conBeginEnd = conBeginEnd;
    }

    public String getConEndStart() {
        return conEndStart;
    }

    public void setConEndStart(String conEndStart) {
        this.conEndStart = conEndStart;
    }

    public String getConEndEnd() {
        return conEndEnd;
    }

    public void setConEndEnd(String conEndEnd) {
        this.conEndEnd = conEndEnd;
    }

    public String getConSignDataStart() {
        return conSignDataStart;
    }

    public void setConSignDataStart(String conSignDataStart) {
        this.conSignDataStart = conSignDataStart;
    }

    public String getConSignDataEnd() {
        return conSignDataEnd;
    }

    public void setConSignDataEnd(String conSignDataEnd) {
        this.conSignDataEnd = conSignDataEnd;
    }

    public String getConVersion() {
        return conVersion;
    }

    public void setConVersion(String conVersion) {
        this.conVersion = conVersion;
    }

    public String getSafeBoxCode() {
        return safeBoxCode;
    }

    public void setSafeBoxCode(String safeBoxCode) {
        this.safeBoxCode = safeBoxCode;
    }
}
