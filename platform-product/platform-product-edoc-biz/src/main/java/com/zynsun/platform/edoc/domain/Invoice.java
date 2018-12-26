package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import java.util.Date;

public class Invoice extends BaseDomain {

    /**
     * 图像-发票关联关系
     */
    @Column(name = "edoc_image_id")
    private Long edocImageId;

    public String getBitOperationResults() {
        return bitOperationResults;
    }

    public void setBitOperationResults(String bitOperationResults) {
        this.bitOperationResults = bitOperationResults;
    }

    public Long getEdocHeaderId() {

        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    @Column(name = "edoc_header_Id")
    private Long edocHeaderId;

    /**
     * 发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     */
    @Column(name = "inv_type")
    private String invType;

    /**
     * 机器编码
     */
    @Column(name = "inv_machine_no")
    private String invMachineNo;

    /**
     * 发票代码
     */
    @Column(name = "inv_code")
    private String invCode;

    /**
     * 发票号码
     */
    @Column(name = "inv_no")
    private String invNo;

    /**
     * 发票日期
     */
    @Column(name = "inv_date")
    private String invDate;

    /**
     * 金额
     */
    @Column(name = "inv_amount")
    private String invAmount;

    /**
     * 税额
     */
    @Column(name = "inv_tax")
    private String invTax;

    /**
     * 价税合计
     */
    @Column(name = "inv_total")
    private String invTotal;

    /**
     * 购方公司名称
     */
    @Column(name = "buyer_name")
    private String buyerName;

    /**
     * 购方公司税号
     */
    @Column(name = "buyer_tax_code")
    private String buyerTaxCode;

    /**
     * 销方公司税号
     */
    @Column(name = "saler_tax_code")
    private String salerTaxCode;

    /**
     * 销方公司名称
     */
    @Column(name = "saler_name")
    private String salerName;

    /**
     * 校验码
     */
    @Column(name = "check_code")
    private String checkCode;

    /**
     * 规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     */
    @Column(name = "check_status")
    private String checkStatus;

    /**
     * 校验详情（使用‘；’将校验不匹配的字段列出）
     */
    @Column(name = "check_details")
    private String checkDetails;

    /**
     * '真伪校验：''01''未校验;‘11’真；‘12’存疑',
     */
    @Column(name = "check_real")
    private String checkReal;

    /**
     * 真伪校验时间
     */
    @Column(name = "check_time")
    private String checkTime;

    /**
     * 认证状态：-1 待认证，1 认证成功，0 认证失败
     */
    @Column(name = "check_certification")
    private String checkCertification;

    /**
     * 认证时间
     */
    @Column(name = "check_certifite_time")
    private Date checkCertifiteTime;

    public String getCiphertextArea() {
        return ciphertextArea;
    }

    public void setCiphertextArea(String ciphertextArea) {
        this.ciphertextArea = ciphertextArea;
    }

    /**
     * 密文区
     */
    @Column(name = "ciphertext_area")
    private String ciphertextArea;

    /**
     * 位运算结果
     */
    @Column(name = "bit_operation_results")
    private String bitOperationResults;

    public String getCiphertextType() {
        return ciphertextType;
    }

    public void setCiphertextType(String ciphertextType) {
        this.ciphertextType = ciphertextType;
    }

    /**
     * 密文类型
     */
    @Column(name = "ciphertext_type")
    private String ciphertextType;

    /**
     * 发票认证属性
     */
    private String period;

    /**
     * 是否盖章
     */
    @Column(name = "is_stamp")
    private String isStamp;

    /**
     * 发票来源 INPUT：录入
     */
    @Column(name = "inv_source")
    private String invSource;

    /**
     * 备注
     */
    private String remarks;


    @Column(name = "ext_field1")
    private String extField1;

    //发票验真结果描述
    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    @Column(name = "ext_field5")
    private String extField5;


    @Column(name = "physical_status")
    private String physicalStatus;

    @Column(name = "inv_child_type")
    private String invChildType;

    /**
     * 实际认证金额
     */
    @Column(name = "act_certification_value")
    private String actCertificationValue;

    @Column(name = "certification_warn")
    private String certificationWarn;

    /**
     * 部门
     */
    @Column(name = "dept")
    private String dept;

    /**
     * 费用类型
     */
    @Column(name = "cost_type")
    private String costType;

    /**
     * 验真人
     */
    @Column(name = "check_real_operator")
    private String checkRealOperator;

    /**
     * 红票蓝票，默认蓝票
     */
    @Column(name = "red_or_blue")
    private String redOrBule;
    @Column(name = "buyer_bank_account")
    private String buyerBankAccount;
    @Column(name = "buyer_address")
    private String buyerAddress;
    @Column(name = "saler_bank_account")
    private String salerBankAccount;
    @Column(name = "saler_address")
    private String salerAddress;

    public String getBuyerBankAccount() {
        return buyerBankAccount;
    }

    public void setBuyerBankAccount(String buyerBankAccount) {
        this.buyerBankAccount = buyerBankAccount;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getSalerBankAccount() {
        return salerBankAccount;
    }

    public void setSalerBankAccount(String salerBankAccount) {
        this.salerBankAccount = salerBankAccount;
    }

    public String getSalerAddress() {
        return salerAddress;
    }

    public void setSalerAddress(String salerAddress) {
        this.salerAddress = salerAddress;
    }

    public String getRedOrBule() {
        return redOrBule;
    }

    public void setRedOrBule(String redOrBule) {
        this.redOrBule = redOrBule;
    }

    public String getCheckRealOperator() {
        return checkRealOperator;
    }

    public void setCheckRealOperator(String checkRealOperator) {
        this.checkRealOperator = checkRealOperator;
    }

    public String getCertificationWarn() {
        return certificationWarn;
    }

    public void setCertificationWarn(String certificationWarn) {
        this.certificationWarn = certificationWarn;
    }

    public String getInvChildType() {
        return invChildType;
    }

    public void setInvChildType(String invChildType) {
        this.invChildType = invChildType;
    }

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    /**
     * 获取图像-发票关联关系
     *
     * @return edoc_image_id - 图像-发票关联关系
     */
    public Long getEdocImageId() {
        return edocImageId;
    }

    /**
     * 设置图像-发票关联关系
     *
     * @param edocImageId 图像-发票关联关系
     */
    public void setEdocImageId(Long edocImageId) {
        this.edocImageId = edocImageId;
    }

    /**
     * 获取发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     *
     * @return inv_type - 发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     */
    public String getInvType() {
        return invType;
    }

    /**
     * 设置发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     *
     * @param invType 发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     */
    public void setInvType(String invType) {
        this.invType = invType;
    }

    /**
     * 获取机器编码
     *
     * @return inv_machine_no - 机器编码
     */
    public String getInvMachineNo() {
        return invMachineNo;
    }

    /**
     * 设置机器编码
     *
     * @param invMachineNo 机器编码
     */
    public void setInvMachineNo(String invMachineNo) {
        this.invMachineNo = invMachineNo;
    }

    /**
     * 获取发票代码
     *
     * @return inv_code - 发票代码
     */
    public String getInvCode() {
        return invCode;
    }

    /**
     * 设置发票代码
     *
     * @param invCode 发票代码
     */
    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    /**
     * 获取发票号码
     *
     * @return inv_no - 发票号码
     */
    public String getInvNo() {
        return invNo;
    }

    /**
     * 设置发票号码
     *
     * @param invNo 发票号码
     */
    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    /**
     * 获取发票日期
     *
     * @return inv_date - 发票日期
     */
    public String getInvDate() {
        return invDate;
    }

    /**
     * 设置发票日期
     *
     * @param invDate 发票日期
     */
    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    /**
     * 获取金额
     *
     * @return inv_amount - 金额
     */
    public String getInvAmount() {
        return invAmount;
    }

    /**
     * 设置金额
     *
     * @param invAmount 金额
     */
    public void setInvAmount(String invAmount) {
        this.invAmount = invAmount;
    }

    /**
     * 获取税额
     *
     * @return inv_tax - 税额
     */
    public String getInvTax() {
        return invTax;
    }

    /**
     * 设置税额
     *
     * @param invTax 税额
     */
    public void setInvTax(String invTax) {
        this.invTax = invTax;
    }

    /**
     * 获取价税合计
     *
     * @return inv_total - 价税合计
     */
    public String getInvTotal() {
        return invTotal;
    }

    /**
     * 设置价税合计
     *
     * @param invTotal 价税合计
     */
    public void setInvTotal(String invTotal) {
        this.invTotal = invTotal;
    }

    /**
     * 获取购方公司名称
     *
     * @return buyer_name - 购方公司名称
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * 设置购方公司名称
     *
     * @param buyerName 购方公司名称
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * 获取购方公司税号
     *
     * @return buyer_tax_code - 购方公司税号
     */
    public String getBuyerTaxCode() {
        return buyerTaxCode;
    }

    /**
     * 设置购方公司税号
     *
     * @param buyerTaxCode 购方公司税号
     */
    public void setBuyerTaxCode(String buyerTaxCode) {
        this.buyerTaxCode = buyerTaxCode;
    }

    /**
     * 获取销方公司税号
     *
     * @return saler_tax_code - 销方公司税号
     */
    public String getSalerTaxCode() {
        return salerTaxCode;
    }

    /**
     * 设置销方公司税号
     *
     * @param salerTaxCode 销方公司税号
     */
    public void setSalerTaxCode(String salerTaxCode) {
        this.salerTaxCode = salerTaxCode;
    }

    /**
     * 获取销方公司名称
     *
     * @return saler_name - 销方公司名称
     */
    public String getSalerName() {
        return salerName;
    }

    /**
     * 设置销方公司名称
     *
     * @param salerName 销方公司名称
     */
    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    /**
     * 获取校验码
     *
     * @return check_code - 校验码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 设置校验码
     *
     * @param checkCode 校验码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    /**
     * 获取规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     *
     * @return check_status - 规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     *
     * @param checkStatus 规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取校验详情（使用‘；’将校验不匹配的字段列出）
     *
     * @return check_details - 校验详情（使用‘；’将校验不匹配的字段列出）
     */
    public String getCheckDetails() {
        return checkDetails;
    }

    /**
     * 设置校验详情（使用‘；’将校验不匹配的字段列出）
     *
     * @param checkDetails 校验详情（使用‘；’将校验不匹配的字段列出）
     */
    public void setCheckDetails(String checkDetails) {
        this.checkDetails = checkDetails;
    }

    /**
     * 获取'真伪校验：''01''未校验;‘11’真；‘12’存疑',
     *
     * @return check_real - '真伪校验：''01''未校验;‘11’真；‘12’存疑',
     */
    public String getCheckReal() {
        return checkReal;
    }

    /**
     * 设置'真伪校验：''01''未校验;‘11’真；‘12’存疑',
     *
     * @param checkReal '真伪校验：''01''未校验;‘11’真；‘12’存疑',
     */
    public void setCheckReal(String checkReal) {
        this.checkReal = checkReal;
    }

    /**
     * 获取真伪校验时间
     *
     * @return check_time - 真伪校验时间
     */
    public String getCheckTime() {
        return checkTime;
    }

    /**
     * 设置真伪校验时间
     *
     * @param checkTime 真伪校验时间
     */
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 获取认证状态：-1 待认证，1 认证成功，0 认证失败
     *
     * @return check_certification - 认证状态：-1 待认证，1 认证成功，0 认证失败
     */
    public String getCheckCertification() {
        return checkCertification;
    }

    /**
     * 设置认证状态：-1 待认证，1 认证成功，0 认证失败
     *
     * @param checkCertification 认证状态：-1 待认证，1 认证成功，0 认证失败
     */
    public void setCheckCertification(String checkCertification) {
        this.checkCertification = checkCertification;
    }

    /**
     * 获取认证时间
     *
     * @return check_certifite_time - 认证时间
     */
    public Date getCheckCertifiteTime() {
        return checkCertifiteTime;
    }

    /**
     * 设置认证时间
     *
     * @param checkCertifiteTime 认证时间
     */
    public void setCheckCertifiteTime(Date checkCertifiteTime) {
        this.checkCertifiteTime = checkCertifiteTime;
    }

    /**
     * 获取发票认证属性
     *
     * @return period - 发票认证属性
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 设置发票认证属性
     *
     * @param period 发票认证属性
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 获取是否盖章
     *
     * @return is_stamp - 是否盖章
     */
    public String getIsStamp() {
        return isStamp;
    }

    /**
     * 设置是否盖章
     *
     * @param isStamp 是否盖章
     */
    public void setIsStamp(String isStamp) {
        this.isStamp = isStamp;
    }

    public String getInvSource() {
        return invSource;
    }

    public void setInvSource(String invSource) {
        this.invSource = invSource;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getActCertificationValue() {
        return actCertificationValue;
    }

    public void setActCertificationValue(String actCertificationValue) {
        this.actCertificationValue = actCertificationValue;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }
}