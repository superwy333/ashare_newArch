package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.openplatform.domain.PageModel;
import com.zynsun.platform.edoc.annotation.Validata;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "Edoc_header")
@Validata
public class EdocHeader extends PageModel {

    /**
     * 影像编号
     */
    //@NotBlank(message = "影像编号不能空")
    @Column(name = "edoc_no")
    private String edocNo;

    /**
     * 凭证号
     */
    @Column(name = "edoc_voucher_no")
    private String edocVoucherNo;

    /**
     * 业务类型（报账单/合同等）
     */
    //@NotBlank(message = "业务类型不能空")
    @Column(name = "edoc_type")
    private String edocType;

    /**
     * 公司代码
     */
    @Column(name = "company_code")
    private String companyCode;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 影像任务的业务状态（具体视不通业务而定）
     */
    //@NotBlank(message = "任务状态不能空")
    @Column(name = "edoc_task_status")
    private String edocTaskStatus;

    /**
     * 实物状态（实体单证的状态）
     */
    @Column(name = "edoc_physical_status")
    private String edocPhysicalStatus;

    /**
     * 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    //@NotBlank(message = "是否匹配不能为空")
    @Column(name = "edoc_is_matched")
    private String edocIsMatched;

    /**
     * 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等）
     */
    @Column(name = "edoc_source")
    private String edocSource;

    /**
     * json格式任务详情
     */
    @Column(name = "json_data")
    private String jsonData;

    /**
     * 是否锁定（影像任务锁定）
     */
    @Column(name = "is_locked")
    private String isLocked;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 公or私
     */
    @Column(name = "public_type")
    private String publicType;

    @Column(name = "ext_field1")
    private String extField1;

    @Column(name = "ext_field2")
    private String extField2;

    @Column(name = "ext_field3")
    private String extField3;

    @Column(name = "ext_field4")
    private String extField4;

    //做影像删除标注
    //当该单据有‘标记记录’，只能删除带有标记的影像图片（删除is_replace==1的），否则可以全部都删除
    //当有标记时，把有标记的影像全删了后，就相当于的没有标记记录了
    //这样是不行的，需要在流程再次发起前一直不可以删除
    @Column(name = "ext_field5")
    private String extField5;

    @Column(name = "fonds_code")
    private String fondsCode;

    @Column(name = "upload_finished")
    private String uploadFinished;

    @Column(name = "scan_finished")
    private String scanFinished;

    @Column(name = "edoc_physical_receiver")
    private String edocPhysicalReceiver;

    @Column(name = "pdf_url")
    private String pdfUrl;

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getEdocPhysicalReceiver() {
        return edocPhysicalReceiver;
    }

    public void setEdocPhysicalReceiver(String edocPhysicalReceiver) {
        this.edocPhysicalReceiver = edocPhysicalReceiver;
    }

    public String getUploadFinished() {
        return uploadFinished;
    }

    public void setUploadFinished(String uploadFinished) {
        this.uploadFinished = uploadFinished;
    }

    public String getScanFinished() {
        return scanFinished;
    }

    public void setScanFinished(String scanFinished) {
        this.scanFinished = scanFinished;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getFdModelName() {
        return fdModelName;
    }

    public void setFdModelName(String fdModelName) {
        this.fdModelName = fdModelName;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    @Column(name = "processId")
    private String processId;

    @Column(name = "fdModelName")
    private String fdModelName;

    @Column(name = "sys_flag")
    private String sysFlag;

    /**
     * 0: 未处理 1：待认证
     */
    @Column(name = "edoc_cert_status")
    private Integer edocCertStatus;

    /**
     * 0:未处理 1.已获取费用凭证 2.已获取付款凭证 3：已获取pdf
     */
    @Column(name = "edoc_voucher_status")
    private Integer edocVoucherStatus;

    /**
     * 0:未处理 1：待归档
     */
    @Column(name = "edoc_archive_status")
    private Integer edocArchiveStatus;

    @Column(name = "upload_type")
    private String uploadType;

    /**
     * 是否需要完成扫描  0：是， 1：否
     */
    @Column(name = "scan_flag")
    private String scanFlag;

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(String scanFlag) {
        this.scanFlag = scanFlag;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public String getFondsCode() {
        return fondsCode;
    }

    public void setFondsCode(String fondsCode) {
        this.fondsCode = fondsCode;
    }

    /**
     * 获取电子影像编号
     *
     * @return edoc_no - 电子影像编号
     */
    public String getEdocNo() {
        return edocNo;
    }

    /**
     * 设置电子影像编号
     *
     * @param edocNo 电子影像编号
     */
    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    /**
     * 获取业务类型（报账单/合同等，详细见数据字典配置）
     *
     * @return biz_type - 业务类型（报账单/合同等，详细见数据字典配置）
     */
    public String getEdocType() {
        return edocType;
    }

    /**
     * 设置业务类型（报账单/合同等，详细见数据字典配置）
     *
     * @param edocType 业务类型（报账单/合同等，详细见数据字典配置）
     */
    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    /**
     * 获取影像任务的业务状态（具体视不通业务而定）
     *
     * @return edoc_task_status - 影像任务的业务状态（具体视不通业务而定）
     */
    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    /**
     * 设置影像任务的业务状态（具体视不通业务而定）
     *
     * @param edocTaskStatus 影像任务的业务状态（具体视不通业务而定）
     */
    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }


    /**
     * 获取是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     *
     * @return is_match - 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    public String getEdocIsMatched() {
        return edocIsMatched;
    }

    /**
     * 设置是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     *
     * @param edocIsMatched 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    public void setEdocIsMatched(String edocIsMatched) {
        this.edocIsMatched = edocIsMatched;
    }

    /**
     * 获取实物状态（实体单证的状态）
     *
     * @return physical_status - 实物状态（实体单证的状态）
     */
    public String getEdocPhysicalStatus() {
        return edocPhysicalStatus;
    }

    /**
     * 设置实物状态（实体单证的状态）
     *
     * @param edocPhysicalStatus 实物状态（实体单证的状态）
     */
    public void setEdocPhysicalStatus(String edocPhysicalStatus) {
        this.edocPhysicalStatus = edocPhysicalStatus;
    }

    /**
     * 获取系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     *
     * @return edoc_source - 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     */
    public String getEdocSource() {
        return edocSource;
    }

    /**
     * 设置系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     *
     * @param edocSource 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等，详细见数据字典配置）
     */
    public void setEdocSource(String edocSource) {
        this.edocSource = edocSource;
    }

    /**
     * 获取是否锁定（影像任务锁定）
     *
     * @return is_locked - 是否锁定（影像任务锁定）
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否锁定（影像任务锁定）
     *
     * @param isLocked 是否锁定（影像任务锁定）
     */
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
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

    public String getEdocVoucherNo() {
        return edocVoucherNo;
    }

    public void setEdocVoucherNo(String edocVoucherNo) {
        this.edocVoucherNo = edocVoucherNo;
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

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Integer getEdocCertStatus() {
        return edocCertStatus;
    }

    public void setEdocCertStatus(Integer edocCertStatus) {
        this.edocCertStatus = edocCertStatus;
    }

    public Integer getEdocVoucherStatus() {
        return edocVoucherStatus;
    }

    public void setEdocVoucherStatus(Integer edocVoucherStatus) {
        this.edocVoucherStatus = edocVoucherStatus;
    }

    public Integer getEdocArchiveStatus() {
        return edocArchiveStatus;
    }

    public void setEdocArchiveStatus(Integer edocArchiveStatus) {
        this.edocArchiveStatus = edocArchiveStatus;
    }


    //人人乐 start
    @Column(name = "seal")
    private String seal;
    @Column(name = "review")
    private String review;
    @Column(name = "biz_no")
    private String bizNo;
    @Column(name = "edoc_tax_code")
    private String edocTaxCode;
    @Column(name = "is_abnormal")
    private String isAbnormal;
    @Column(name = "abnormal_type")
    private String abnormalType;

    public String getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(String isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getEdocTaxCode() {
        return edocTaxCode;
    }

    public void setEdocTaxCode(String edocTaxCode) {
        this.edocTaxCode = edocTaxCode;
    }


    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    //人人乐 end
}