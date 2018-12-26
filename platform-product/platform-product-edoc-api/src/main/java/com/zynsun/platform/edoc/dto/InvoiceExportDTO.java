package com.zynsun.platform.edoc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zynsun.openplatform.dto.PageDTO;
import com.zynsun.platform.edoc.dto.scanTask.InvoiceItemDTO;

import java.util.Date;
import java.util.List;

/**
 * @description: 发票报表Excel导出DTO
 * @author: xihao.ding
 * @create: 2018-07-24 17:11
 **/
public class InvoiceExportDTO extends PageDTO {
    //费用凭证编号
    private String credentialsNum;
    //经办人
    private String createByEdoc;

    /**
     * 图像-发票关联关系
     */
    private Long edocImageId;


    private Long edocHeaderId;

    /**新希望发票报表需要的字段 start**/
    private String edocNo;
    private String edocType;
    private String costType;
    private String dept;
    private String itemName;
    private String itemTaxRate;
    private String certificationWarn;
    private List<InvoiceItemDTO> invoiceItemDTOList;
    /**新希望发票报表需要的字段 end**/

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

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

    /**
     * 备注
     */
    private String remarks;


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

    /**
     * 明细项
     */
    private Long item_invId;

    private String item_lineNo;

    private String item_itemName;

    /***
     * 型号
     */
    private String item_itemSpec;

    /***
     * 单价
     */
    private String item_itemPrice;

    /**
     * 数量
     */
    private String item_itemQuantity;


    /**
     *税额
     */
    private String item_itemTax;

    /***
     * 税率
     */
    private String item_itemTaxRate;

    /***
     *金额
     */
    private String item_itemAmount;

    private String item_unit;

    private String item_remarks;

    private String item_extField1;

    private String item_extField2;

    private String item_extField3;

    private String item_extField4;

    private String item_extField5;
    private String item_itemTotal;

    /**
     * 凭证抓取日期
     */
    private String creDate;

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getItem_lineNo() {
        return item_lineNo;
    }

    public void setItem_lineNo(String item_lineNo) {
        this.item_lineNo = item_lineNo;
    }

    public String getItem_itemName() {
        return item_itemName;
    }

    public void setItem_itemName(String item_itemName) {
        this.item_itemName = item_itemName;
    }

    public String getItem_itemSpec() {
        return item_itemSpec;
    }

    public void setItem_itemSpec(String item_itemSpec) {
        this.item_itemSpec = item_itemSpec;
    }

    public String getItem_itemPrice() {
        return item_itemPrice;
    }

    public void setItem_itemPrice(String item_itemPrice) {
        this.item_itemPrice = item_itemPrice;
    }

    public String getItem_itemQuantity() {
        return item_itemQuantity;
    }

    public void setItem_itemQuantity(String item_itemQuantity) {
        this.item_itemQuantity = item_itemQuantity;
    }

    public String getItem_itemTax() {
        return item_itemTax;
    }

    public void setItem_itemTax(String item_itemTax) {
        this.item_itemTax = item_itemTax;
    }

    public String getItem_itemTaxRate() {
        return item_itemTaxRate;
    }

    public void setItem_itemTaxRate(String item_itemTaxRate) {
        this.item_itemTaxRate = item_itemTaxRate;
    }

    public String getItem_itemAmount() {
        return item_itemAmount;
    }

    public void setItem_itemAmount(String item_itemAmount) {
        this.item_itemAmount = item_itemAmount;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public String getItem_remarks() {
        return item_remarks;
    }

    public void setItem_remarks(String item_remarks) {
        this.item_remarks = item_remarks;
    }

    public String getItem_extField1() {
        return item_extField1;
    }

    public void setItem_extField1(String item_extField1) {
        this.item_extField1 = item_extField1;
    }

    public String getItem_extField2() {
        return item_extField2;
    }

    public void setItem_extField2(String item_extField2) {
        this.item_extField2 = item_extField2;
    }

    public String getItem_extField3() {
        return item_extField3;
    }

    public void setItem_extField3(String item_extField3) {
        this.item_extField3 = item_extField3;
    }

    public String getItem_extField4() {
        return item_extField4;
    }

    public void setItem_extField4(String item_extField4) {
        this.item_extField4 = item_extField4;
    }

    public String getItem_extField5() {
        return item_extField5;
    }

    public void setItem_extField5(String item_extField5) {
        this.item_extField5 = item_extField5;
    }

    public String getItem_itemTotal() {
        return item_itemTotal;
    }

    public void setItem_invId(Long item_invId) {
        this.item_invId = item_invId;
    }

    public void setItem_itemTotal(String item_itemTotal) {
        this.item_itemTotal = item_itemTotal;
    }


    public Long getItem_invId() {
        return item_invId;
    }

    public String getCertificationWarn() {
        return certificationWarn;
    }

    public void setCertificationWarn(String certificationWarn) {
        this.certificationWarn = certificationWarn;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTaxRate() {
        return itemTaxRate;
    }

    public void setItemTaxRate(String itemTaxRate) {
        this.itemTaxRate = itemTaxRate;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    public String getInvChildType() {
        return invChildType;
    }

    public void setInvChildType(String invChildType) {
        this.invChildType = invChildType;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invDateStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invDateEnd;

    private String physicalStatus;

    private List<ItemDTO> invItem;

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
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

    public String getCiphertextType() {
        return ciphertextType;
    }

    public void setCiphertextType(String ciphertextType) {
        this.ciphertextType = ciphertextType;
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

    public Long getEdocHeaderId() {

        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getInvSource() {
        return invSource;
    }

    public void setInvSource(String invSource) {
        this.invSource = invSource;
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

    public String getCreateByEdoc() {
        return createByEdoc;
    }

    public void setCreateByEdoc(String createByEdoc) {
        this.createByEdoc = createByEdoc;
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

    public List<ItemDTO> getInvItem() {
        return invItem;
    }

    public void setInvItem(List<ItemDTO> invItem) {
        this.invItem = invItem;
    }

    public String getActCertificationValue() {
        return actCertificationValue;
    }

    public void setActCertificationValue(String actCertificationValue) {
        this.actCertificationValue = actCertificationValue;
    }

    public List<InvoiceItemDTO> getInvoiceItemDTOList() {
        return invoiceItemDTOList;
    }

    public void setInvoiceItemDTOList(List<InvoiceItemDTO> invoiceItemDTOList) {
        this.invoiceItemDTOList = invoiceItemDTOList;
    }
}
