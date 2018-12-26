package com.zynsun.platform.edoc.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 产品模板接口
 * @author Liangjiawei
 * @date 2018/5/28 9:46
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
public class EdocHeaderReq implements Serializable{
    /**
     * 影像编号
     */
    @XmlElement(name = "EDOC_NO")
    private String edocNo;

    /**
     * 凭证号
     */
    @XmlElement(name = "EDOC_VOUCHER_NO")
    private String edocVoucherNo;

    /**
     * 业务类型（报账单/合同等）
     */
    @XmlElement(name = "EDOC_TYPE")
    private String edocType;

    /**
     * 公司代码
     */
    @XmlElement(name = "COMPANY_CODE")
    private String companyCode;

    /**
     * 公司名称
     */
    @XmlElement(name = "COMPANY_NAME")
    private String companyName;

    /**
     * 影像任务的业务状态（具体视不通业务而定）
     */
    @XmlElement(name = "EDOC_TASK_STATUS")
    private String edocTaskStatus;

    /**
     * 实物状态（实体单证的状态）
     */
    @XmlElement(name = "EDOC_PHYSICAL_STATUS")
    private String edocPhysicalStatus;

    /**
     * 是否匹配（客户端上传影像时有可能出现影像不匹配的情况）
     */
    @XmlElement(name = "EDOC_IS_MATCHED")
    private String edocIsMatched;

    /**
     * 系统来源（区分影像任务创建的来源，包括内部系统手工创建/客户端上传/第三方系统来源等）
     */
    @XmlElement(name = "EDOC_SOURCE")
    private String edocSource;

    /**
     * 是否锁定（影像任务锁定）
     */
    @XmlElement(name = "IS_LOCKED")
    private String isLocked;

    /**
     * json格式任务详情
     */
    @XmlElement(name = "JSON_DATA")
    private String jsonData;

    @XmlElement(name = "CREATE_BY")
    private String createBy;





    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEdocNo() { return edocNo; }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getEdocVoucherNo() {
        return edocVoucherNo;
    }

    public void setEdocVoucherNo(String edocVoucherNo) {
        this.edocVoucherNo = edocVoucherNo;
    }

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
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

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }

    public String getEdocPhysicalStatus() {
        return edocPhysicalStatus;
    }

    public void setEdocPhysicalStatus(String edocPhysicalStatus) {
        this.edocPhysicalStatus = edocPhysicalStatus;
    }

    public String getEdocIsMatched() {
        return edocIsMatched;
    }

    public void setEdocIsMatched(String edocIsMatched) {
        this.edocIsMatched = edocIsMatched;
    }

    public String getEdocSource() {
        return edocSource;
    }

    public void setEdocSource(String edocSource) {
        this.edocSource = edocSource;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
