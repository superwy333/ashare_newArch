package com.zynsun.platform.edoc.vo;

/**
 * @author jary
 * @creatTime 2018/12/3 3:49 PM
 */
public class VoucherDetailsVo extends InterReqVo {

    /**
     *
     */
    private Long id;
    /**
     * 凭证号
     */
    private String credentialsNum;

    /**
     *
     */
    private String erpId;

    /**
     * 凭证状态
     * 1：正常
     * 2：冲销
     */
    private String credentialStatus;

    /**
     * 凭证类型
     * 1-收入凭证；
     * 2-库存凭证；
     * 3-记账凭证；
     * 4-结算凭证
     */
    private String type;

    /**
     * 会计帐套
     */
    private String accountingLedger;

    /**
     * 凭证开单日期
     */
    private String createDate;

    /**
     * 回写状态标识
     * 1:保存；
     * 2：删除
     */
    private String writeBackStatus;


    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountingLedger() {
        return accountingLedger;
    }

    public void setAccountingLedger(String accountingLedger) {
        this.accountingLedger = accountingLedger;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCredentialStatus() {
        return credentialStatus;
    }

    public void setCredentialStatus(String credentialStatus) {
        this.credentialStatus = credentialStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriteBackStatus() {
        return writeBackStatus;
    }

    public void setWriteBackStatus(String writeBackStatus) {
        this.writeBackStatus = writeBackStatus;
    }
}
