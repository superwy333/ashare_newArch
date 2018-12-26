package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.PageDTO;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.EdocLoadConfig;
import com.zynsun.platform.edoc.dto.baseDTO.BaseEnum;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description: 凭证DTO
 * @Date:Created in 2018-06-25 下午 1:57
 * @Modified By:
 */
public class EdocCredentialsDTO extends PageDTO {

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    //edocHeader来源
    private String edocSource;
    //所属edocHeaderId
    private Long edocHeaderId;
    //凭证类型
    private String credentialsType;
    // 公司Code
    private String companyCode;
    // 公司
    private String companyName;
    // 制单日期
    private String createDate;
    // 币种
    private String currency;
    // 凭证名
    private String credentialsName;
    // 凭证批名
    private String credentialsBatchName;
    // 凭证编号
    private String credentialsNum;
    // 附件张数
    private String attachmentCount;
    // 摘要
    private String digest;
    // 会计科目
    private String accountingSubject;
    // 数量
    private String acount;
    // 借方金额
    private String debtorMoney;
    // 贷方金额
    private String creditorMoney;
    // 制单人
    private String creater;
    // 审核人
    private String auditor;
    // 记账人
    private String cAccounter;
    // 出纳人
    private String cashier;
    // 借方金额合计
    private String debtorMoneyTotal;
    // 贷方金额合计
    private String creditorMoneyTotal;
    // 大写
    private String upperMoney;
    // 当前页码
    private String currentPage;
    // 总页码
    private String totalPage;
    // 总数量
    private String totalAcount;

    private String pdfFileUrl;

    private Long printCount;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    // 凭证状态 4：待分册 5：已分册-待编盒 6：已编盒-待装箱 7：已归档
    private String voucherStatus;

    private String edocNo;

    private String edocType;

    private String edocCreateBy;

    private String edocPublicType;

    private String edocVoucherNo;

    private String uploadType;

    private String recordsNo;

    private String boxNo;

    //JDE、EBS
    private String sysFlag;


    //核算账套
    private String voucherAccount;

    private String erpId;

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }



    //密文
    private String sysChk;

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getVoucherAccount() {
        return voucherAccount;
    }

    public void setVoucherAccount(String voucherAccount) {
        this.voucherAccount = voucherAccount;
    }


    public String getSysChk() {
        return sysChk;
    }

    public void setSysChk(String sysChk) {
        this.sysChk = sysChk;
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

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
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

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    private List<EbsCredentialsDetailDTO> subListValue;

    public List<EbsCredentialsDetailDTO> getSubListValue() {
        return subListValue;
    }

    public void setSubListValue(List<EbsCredentialsDetailDTO> subListValue) {
        this.subListValue = subListValue;
    }

    public String getTotalAcount() {
        return totalAcount;
    }

    public void setTotalAcount(String totalAcount) {
        this.totalAcount = totalAcount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCredentialsName() {
        return credentialsName;
    }

    public void setCredentialsName(String credentialsName) {
        this.credentialsName = credentialsName;
    }

    public String getCredentialsBatchName() {
        return credentialsBatchName;
    }

    public void setCredentialsBatchName(String credentialsBatchName) {
        this.credentialsBatchName = credentialsBatchName;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(String accountingSubject) {
        this.accountingSubject = accountingSubject;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getDebtorMoney() {
        return debtorMoney;
    }

    public void setDebtorMoney(String debtorMoney) {
        this.debtorMoney = debtorMoney;
    }

    public String getCreditorMoney() {
        return creditorMoney;
    }

    public void setCreditorMoney(String creditorMoney) {
        this.creditorMoney = creditorMoney;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getcAccounter() {
        return cAccounter;
    }

    public void setcAccounter(String cAccounter) {
        this.cAccounter = cAccounter;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getDebtorMoneyTotal() {
        return debtorMoneyTotal;
    }

    public void setDebtorMoneyTotal(String debtorMoneyTotal) {
        this.debtorMoneyTotal = debtorMoneyTotal;
    }

    public String getCreditorMoneyTotal() {
        return creditorMoneyTotal;
    }

    public void setCreditorMoneyTotal(String creditorMoneyTotal) {
        this.creditorMoneyTotal = creditorMoneyTotal;
    }

    public String getUpperMoney() {
        return upperMoney;
    }

    public void setUpperMoney(String upperMoney) {
        this.upperMoney = upperMoney;
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

    private String createTimeStr;

    private String credentialsStatus;

    private String accountinLedger;

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

    private static final String EDOCCREDENTIALSPREFIX = "edocCredentials.";


    public enum CredentialsStatus implements BaseEnum {
        credentialsStatus1("1"),credentialsStatus2("2");
        private final String code;
        CredentialsStatus(String code)
        {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
        public String getName() {
            return EdocLoadConfig.get(EDOCCREDENTIALSPREFIX + this.name());
        }
        public static CredentialsStatus parse(String code) {
            CredentialsStatus result = null; // Default
            for (CredentialsStatus item : CredentialsStatus.values()) {
                if (BeanUtil.equals(item.getCode(),code)) {
                    result = item;
                    break;
                }
            }
            return result;
        }
    }

    public enum CredentialsType implements BaseEnum {
        credentialsType1("1"),credentialsType2("2"),credentialsType3("3"),credentialsType4("4");
        private final String code;
        CredentialsType(String code)
        {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
        public String getName() {
            return EdocLoadConfig.get(EDOCCREDENTIALSPREFIX + this.name());
        }
        public static CredentialsType parse(String code) {
            CredentialsType result = null; // Default
            for (CredentialsType item : CredentialsType.values()) {
                if (BeanUtil.equals(item.getCode(),code)) {
                    result = item;
                    break;
                }
            }
            return result;
        }
    }



}
