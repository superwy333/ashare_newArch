package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Jary on 2018/11/20/020.
 */
@Table(name = "review_info")
public class ReviewInfo extends BaseDomain {

    /**
     * 单证编码
     */
    @Column(name = "edoc_header_id")
    private String edocHeaderId;

    /**
     * 评价状态
     */
    @Column(name = "review_status")
    private String reviewStatus;

    /**
     * 评价信息
     */
    @Column(name = "review_message")
    private String reviewMessage;

    /**
     * 评价人姓名
     */
    @Column(name = "review_name")
    private String reviewName;

    /**
     * 评价人代码
     */
    @Column(name = "review_code")
    private String reviewCode;

    /**
     * 评价时间
     */
    @Column(name = "review_time")
    private String reviewTime;

    /**
     * 是否已处理
     */
    @Column(name = "is_processed")
    private String isProcessed;

    /**
     * 描述
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 扩展字段1
     */
    @Column(name = "ext_field1")
    private String extField1;

    /**
     * 扩展字段2
     */
    @Column(name = "ext_field2")
    private String extField2;

    /**
     * 扩展字段3
     */
    @Column(name = "ext_field3")
    private String extField3;

    /**
     * 扩展字段4
     */
    @Column(name = "ext_field4")
    private String extField4;

    /**
     * 扩展字段5
     */
    @Column(name = "ext_field5")
    private String extField5;

    @Column(name = "review_source")
    private String reviewSource;

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public String getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(String edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(String reviewCode) {
        this.reviewCode = reviewCode;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(String isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
