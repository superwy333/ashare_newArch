package com.zynsun.platform.edoc.vo;

/**
 * @author jary
 * @creatTime 2018/12/1 10:28 AM
 */
public class EvaluateVo extends InterReqVo {

    /**
     * 评价类型
     */
    private String type;

    /**
     * 评价信息
     */
    private String reviewMessage;

    /**
     * 评价人编号
     */
    private String reviewCode;

    /**
     * 评价来源
     */
    private String reviewSource;

    /**
     * 评价时间
     */
    private String reviewTime;


    /**
     * 评价状态
     */
    private String reviewStatus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
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

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
