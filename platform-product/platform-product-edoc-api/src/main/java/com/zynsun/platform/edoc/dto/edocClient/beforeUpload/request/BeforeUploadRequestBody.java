package com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端开始上传请求参数
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BeforeUploadRequestBody implements Serializable {

    /**
     * 用户代码
     */
    @XmlElement(name = "USER_CODE")
    @JsonProperty(value = "USER_CODE")
    private String userCode;

    /**
     * 父任务流水号
     */
    @XmlElement(name = "PARENT_BILL_HEADER_ID")
    @JsonProperty(value = "PARENT_BILL_HEADER_ID")
    private String parentId;

    @XmlElement(name = "SCAN_FLAG")
    @JsonProperty(value = "SCAN_FLAG")
    private String scanFlag;

    /**
     * 单编证号
     */
    @XmlElement(name = "EDOC_NO")
    @JsonProperty(value = "EDOC_NO")
    private String edocNo;

    /**
     * 业务类型代码
     */
    @XmlElement(name = "CATEGORY_CODE")
    @JsonProperty(value = "CATEGORY_CODE")
    private String categoryCode;

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

    /***
     * 扫描人名称
     */
    @XmlElement(name = "SCANNER")
    @JsonProperty(value = "SCANNER")
    private String scanner;

    /**
     * 影像总页数
     */
    @XmlElement(name = "TOTAL_COUNT")
    @JsonProperty(value = "TOTAL_COUNT")
    private Integer totalCount;

    /**
     * 扩展业务属性集合
     */
    @XmlElement(name = "FIELDS")
    @JsonProperty(value = "FIELDS")
    private BeforeUploadRequestBodyCategories extCategories;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public String getScanner() {
        return scanner;
    }

    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BeforeUploadRequestBodyCategories getExtCategories() {
        return extCategories;
    }

    public void setExtCategories(BeforeUploadRequestBodyCategories extCategories) {
        this.extCategories = extCategories;
    }

    public String getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(String scanFlag) {
        this.scanFlag = scanFlag;
    }
}
