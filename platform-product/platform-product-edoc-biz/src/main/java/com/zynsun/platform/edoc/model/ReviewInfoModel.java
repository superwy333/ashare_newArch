package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.openplatform.domain.PageModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Jary on 2018/11/20/020.
 */
public class ReviewInfoModel extends PageModel {

    /**
     * 单证编码
     */
    private String edocHeaderId;

    /**
     * 评价状态
     */
    private String reviewStatus;

    /**
     * 评价信息
     */
    private String reviewMessage;

    /**
     * 评价人姓名
     */
    private String reviewName;

    /**
     * 评价人代码
     */
    private String reviewCode;

    /**
     * 评价时间
     */
    private String reviewTime;

    /**
     * 是否已处理
     */
    private String isProcessed;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 扩展字段1
     */
    private String extField1;

    /**
     * 扩展字段2
     */
    private String extField2;

    /**
     * 扩展字段3
     */
    private String extField3;

    /**
     * 扩展字段4
     */
    private String extField4;

    /**
     * 扩展字段5
     */
    private String extField5;

    private String taskId;

    private String imageId;

    private String edocNo;

    private String edocType;

    private String companyName;

    private String edocTaskStatus;

    private String reviewTimeStart;

    private String reviewTimeEnd;

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    public String getReviewTimeStart() {
        return reviewTimeStart;
    }

    public void setReviewTimeStart(String reviewTimeStart) {
        this.reviewTimeStart = reviewTimeStart;
    }

    public String getReviewTimeEnd() {
        return reviewTimeEnd;
    }

    public void setReviewTimeEnd(String reviewTimeEnd) {
        this.reviewTimeEnd = reviewTimeEnd;
    }

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    private String reviewSource;

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }
}
