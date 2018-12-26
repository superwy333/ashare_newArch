package com.zynsun.platform.edoc.dto.bizType;

import com.zynsun.openplatform.dto.PageDTO;

import java.util.Date;

/**
 * @Author：jerry
 * @Description：
 * @Date：Created in 2017/6/8 0002 上午 9:20
 * @Modified By：
 */
public class BillBatchDTO extends PageDTO {
    /**
     * 批次号
     */
    private String edocNo;

    /**
     * 扫描员编码
     */
    private String createBy;

    /**
     * 扫描员姓名
     */
    private String scannerName;

    /**
     * 扫描时间
     */
    private Date createTime;

    /**
     * 批次下扫描任务数
     */
    private Integer totalNum;

    /**
     * 业务类型编码
     */
    private String bizTypeCode;

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getScannerName() {
        return scannerName;
    }

    public void setScannerName(String scannerName) {
        this.scannerName = scannerName;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getBizTypeCode() {
        return bizTypeCode;
    }

    public void setBizTypeCode(String bizTypeCode) {
        this.bizTypeCode = bizTypeCode;
    }
}
