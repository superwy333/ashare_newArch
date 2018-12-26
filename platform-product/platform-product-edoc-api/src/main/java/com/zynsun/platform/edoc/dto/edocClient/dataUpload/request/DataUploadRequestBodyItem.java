package com.zynsun.platform.edoc.dto.edocClient.dataUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadRequestBodyItem implements Serializable {

    /**
     * 序号
     */
    @XmlElement(name = "ITEM_NO")
    @JsonProperty(value = "ITEM_NO")
    private String itemNo;

    /**
     * 本地唯一标识
     */
    @XmlElement(name = "GUID")
    @JsonProperty(value = "GUID")
    private String guid;

    /**
     * 采集代码
     * <p>
     * 	11 新建扫描（待扫描）
     * 	31 重扫
     * 	32 补扫
     * 	33 替换
     */
    @XmlElement(name = "SCAN_CODE")
    @JsonProperty(value = "SCAN_CODE")
    private String scanCode;

    /**
     * 单证编号
     */
    @XmlElement(name = "BILL_HEADER_ID")
    @JsonProperty(value = "BILL_HEADER_ID")
    private String edocHeaderId;

    /***
     * 此影像是附件的时候，此为必填项
     */
    @XmlElement(name = "INV_SVR_GUID")
    @JsonProperty(value = "INV_SVR_GUID")
    private String invSvrId;

    /**
     * 单证类型代码
     */
    @XmlElement(name = "DOC_TYPE_CODE")
    @JsonProperty(value = "DOC_TYPE_CODE")
    private String docTypeCode;

    /**
     * 采集时间
     */
    @XmlElement(name = "SCAN_TIME")
    @JsonProperty(value = "SCAN_TIME")
    private String scanTime;

    /**
     * 采集部门代码
     */
    @XmlElement(name = "DEPT_CODE")
    @JsonProperty(value = "DEPT_CODE")
    private String deptCode;

    /**
     * 页码
     */
    @XmlElement(name = "PAGE_NUMBER")
    @JsonProperty(value = "PAGE_NUMBER")
    private String pageNumber;

    /**
     * 影像字节码
     */
    @XmlElement(name = "DOC_BYTEDATA")
    @JsonProperty(value = "DOC_BYTEDATA")
    private String docBytedata;

    /**
     * 影像类别
     */
    @XmlElement(name = "DOC_FORMAT")
    @JsonProperty(value = "DOC_FORMAT")
    private String docFormat;

    /**
     * 扫描仪类型
     */
    @XmlElement(name = "SCANNER_MODEL")
    @JsonProperty(value = "SCANNER_MODEL")
    private String scannerModel;

    /**
     * 属性集合
     */
    @XmlElement(name = "FIELDS")
    @JsonProperty(value = "FIELDS")
    private DataUploadRequestBodyItemFields fields;

    /**
     * 影像发票信息
     */
    @XmlElement(name = "INVOICE")
    @JsonProperty(value = "INVOICE")
    private DataUploadRequestBodyItemInvoice invoice;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public String getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(String edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getInvSvrId() {
        return invSvrId;
    }

    public void setInvSvrId(String invSvrId) {
        this.invSvrId = invSvrId;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getDocBytedata() {
        return docBytedata;
    }

    public void setDocBytedata(String docBytedata) {
        this.docBytedata = docBytedata;
    }

    public String getDocFormat() {
        return docFormat;
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }

    public String getScannerModel() {
        return scannerModel;
    }

    public void setScannerModel(String scannerModel) {
        this.scannerModel = scannerModel;
    }

    public DataUploadRequestBodyItemFields getFields() {
        return fields;
    }

    public void setFields(DataUploadRequestBodyItemFields fields) {
        this.fields = fields;
    }

    public DataUploadRequestBodyItemInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(DataUploadRequestBodyItemInvoice invoice) {
        this.invoice = invoice;
    }
}
