package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.BaseDTO;

/**
 * @Description：快递详情
 * @Author：YongChen
 * @Date：Created in 2018/1/9 14:35
 * @Modified By：
 */
public class ExpressDetailDTO extends BaseDTO {
    /**
     * 邮包id
     */
    private Long expRecId;

    /**
     * 单据id
     */
    private String billId;

    /**
     * 任务号
     */
    private String billNo;

    /**
     * 单据类型：报账单，合同，发票
     */
    private String billType;

    /**
     * 数据来源
     */
    private String sysSource;

    /**
     * 单据状态 0：待寄送；1：待签收；2：已签收
     */
    private Integer billStatus;

    /**
     * 是否退票邮包（0: 否，1：是）
     */
    private Integer billReturnFlag;

    /**
     * {"level": "层级","code":"发票代码"，"no"："发票号码"}
     */
    private String billInfo;

    /**
     * 备注
     */
    private String remarks;

    private Integer signExceptionType;

    public Integer getSignExceptionType() {
        return signExceptionType;
    }

    public void setSignExceptionType(Integer signExceptionType) {
        this.signExceptionType = signExceptionType;
    }

    public Long getExpRecId() {
        return expRecId;
    }

    public void setExpRecId(Long expRecId) {
        this.expRecId = expRecId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getSysSource() {
        return sysSource;
    }

    public void setSysSource(String sysSource) {
        this.sysSource = sysSource;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getBillReturnFlag() {
        return billReturnFlag;
    }

    public void setBillReturnFlag(Integer billReturnFlag) {
        this.billReturnFlag = billReturnFlag;
    }

    public String getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(String billInfo) {
        this.billInfo = billInfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
