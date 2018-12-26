package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:12
 **/
public class OperationRecordDTO  extends PageDTO {

    private long recordId;

    private String recordOperation;

    private String recordOperator;

    private String recordOperationTime;

    private String remarks;

    private String recordOperationCode;

    private String recordStatusFlag;

    private String recordTable;

    public String getRecordOperationCode() {
        return recordOperationCode;
    }

    public void setRecordOperationCode(String recordOperationCode) {
        this.recordOperationCode = recordOperationCode;
    }

    public String getRecordStatusFlag() {
        return recordStatusFlag;
    }

    public void setRecordStatusFlag(String recordStatusFlag) {
        this.recordStatusFlag = recordStatusFlag;
    }

    public String getRecordTable() {
        return recordTable;
    }

    public void setRecordTable(String recordTable) {
        this.recordTable = recordTable;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getRecordOperation() {
        return recordOperation;
    }

    public void setRecordOperation(String recordOperation) {
        this.recordOperation = recordOperation;
    }

    public String getRecordOperator() {
        return recordOperator;
    }

    public void setRecordOperator(String recordOperator) {
        this.recordOperator = recordOperator;
    }

    public String getRecordOperationTime() {
        return recordOperationTime;
    }

    public void setRecordOperationTime(String recordOperationTime) {
        this.recordOperationTime = recordOperationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
