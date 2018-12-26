package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;
import com.zynsun.platform.edoc.dto.ItemDTO;

import java.util.Date;
import java.util.List;

/**
 * @description: 发票报表Excel导出Model
 * @author: xihao.ding
 * @create: 2018-07-24 17:11
 **/
public class InvoiceExportModel extends PageModel {

    /**
     * 图像-发票关联关系
     */
    private Long edocImageId;


    private Long edocHeaderId;

    /**新希望发票报表需要的字段 start**/
    private String edocNo;
    private String edocType;

    private String certificationWarn;
    /**新希望发票报表需要的字段 end**/

    /**
     * 发票类型（普票/专票/机动车发票等，详细见数据字典配置）
     */
    private String invType;

    /**
     * 机器编码
     */
    private String invMachineNo;

    /**
     * 发票代码
     */
    private String invCode;

    /**
     * 发票号码
     */
    private String invNo;

    /**
     * 发票日期
     */
    private String invDate;

    /**
     * 金额
     */
    private String invAmount;

    /**
     * 税额
     */
    private String invTax;

    /**
     * 价税合计
     */
    private String invTotal;

    /**
     * 购方公司名称
     */
    private String buyerName;

    /**
     * 购方公司税号
     */
    private String buyerTaxCode;

    /**
     * 销方公司税号
     */
    private String salerTaxCode;

    /**
     * 销方公司名称
     */
    private String salerName;

    /**
     * 校验码
     */
    private String checkCode;

    /**
     * 规则校验结果：校验状态：11：校验成功；12：校验失败；01：未校验
     */
    private String checkStatus;

    /**
     * 校验详情（使用‘；’将校验不匹配的字段列出）
     */
    private String checkDetails;

    /**
     * '真伪校验：''01''未校验;‘11’真；‘12’存疑',
     */
    private String checkReal;

    /**
     * 真伪校验时间
     */
    private String checkTime;

    /**
     * 认证状态：-1 待认证，1 认证成功，0 认证失败
     */
    private String checkCertification;

    /**
     * 认证时间
     */
    private Date checkCertifiteTime;



    /**
     * 密文区
     */
    private String ciphertextArea;

    /**
     * 位运算结果
     */
    private String bitOperationResults;


    /**
     * 密文类型
     */
    private String ciphertextType;

    /**
     * 发票认证属性
     */
    private String period;

    /**
     * 是否盖章
     */
    private String isStamp;

    /**
     * 发票来源 INPUT：录入
     */
    private String invSource;


    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    private String invChildType;

    /**
     * 实际认证金额
     */
    private String actCertificationValue;

    private Date invDateStart;

    private Date invDateEnd;

    private String physicalStatus;

    private List<ItemDTO> invItem;

    private Long invId;

    private String lineNo;

    private String itemName;

    /***
     * 型号
     */
    private String itemSpec;

    /***
     * 单价
     */
    private String itemPrice;

    /**
     * 数量
     */
    private String itemQuantity;


    /**
     *税额
     */
    private String itemTax;

    /***
     * 税率
     */
    private String itemTaxRate;

    /***
     *金额
     */
    private String itemAmount;

    private String unit;

    private String remarks;


    public Long getEdocImageId() {
        return edocImageId;
    }

    public void setEdocImageId(Long edocImageId) {
        this.edocImageId = edocImageId;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
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

    public String getCertificationWarn() {
        return certificationWarn;
    }

    public void setCertificationWarn(String certificationWarn) {
        this.certificationWarn = certificationWarn;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvMachineNo() {
        return invMachineNo;
    }

    public void setInvMachineNo(String invMachineNo) {
        this.invMachineNo = invMachineNo;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(String invAmount) {
        this.invAmount = invAmount;
    }

    public String getInvTax() {
        return invTax;
    }

    public void setInvTax(String invTax) {
        this.invTax = invTax;
    }

    public String getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(String invTotal) {
        this.invTotal = invTotal;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTaxCode() {
        return buyerTaxCode;
    }

    public void setBuyerTaxCode(String buyerTaxCode) {
        this.buyerTaxCode = buyerTaxCode;
    }

    public String getSalerTaxCode() {
        return salerTaxCode;
    }

    public void setSalerTaxCode(String salerTaxCode) {
        this.salerTaxCode = salerTaxCode;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckDetails() {
        return checkDetails;
    }

    public void setCheckDetails(String checkDetails) {
        this.checkDetails = checkDetails;
    }

    public String getCheckReal() {
        return checkReal;
    }

    public void setCheckReal(String checkReal) {
        this.checkReal = checkReal;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckCertification() {
        return checkCertification;
    }

    public void setCheckCertification(String checkCertification) {
        this.checkCertification = checkCertification;
    }

    public Date getCheckCertifiteTime() {
        return checkCertifiteTime;
    }

    public void setCheckCertifiteTime(Date checkCertifiteTime) {
        this.checkCertifiteTime = checkCertifiteTime;
    }

    public String getCiphertextArea() {
        return ciphertextArea;
    }

    public void setCiphertextArea(String ciphertextArea) {
        this.ciphertextArea = ciphertextArea;
    }

    public String getBitOperationResults() {
        return bitOperationResults;
    }

    public void setBitOperationResults(String bitOperationResults) {
        this.bitOperationResults = bitOperationResults;
    }

    public String getCiphertextType() {
        return ciphertextType;
    }

    public void setCiphertextType(String ciphertextType) {
        this.ciphertextType = ciphertextType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getIsStamp() {
        return isStamp;
    }

    public void setIsStamp(String isStamp) {
        this.isStamp = isStamp;
    }

    public String getInvSource() {
        return invSource;
    }

    public void setInvSource(String invSource) {
        this.invSource = invSource;
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

    public String getInvChildType() {
        return invChildType;
    }

    public void setInvChildType(String invChildType) {
        this.invChildType = invChildType;
    }

    public String getActCertificationValue() {
        return actCertificationValue;
    }

    public void setActCertificationValue(String actCertificationValue) {
        this.actCertificationValue = actCertificationValue;
    }

    public Date getInvDateStart() {
        return invDateStart;
    }

    public void setInvDateStart(Date invDateStart) {
        this.invDateStart = invDateStart;
    }

    public Date getInvDateEnd() {
        return invDateEnd;
    }

    public void setInvDateEnd(Date invDateEnd) {
        this.invDateEnd = invDateEnd;
    }

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public List<ItemDTO> getInvItem() {
        return invItem;
    }

    public void setInvItem(List<ItemDTO> invItem) {
        this.invItem = invItem;
    }

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemTax() {
        return itemTax;
    }

    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }

    public String getItemTaxRate() {
        return itemTaxRate;
    }

    public void setItemTaxRate(String itemTaxRate) {
        this.itemTaxRate = itemTaxRate;
    }

    public String getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
