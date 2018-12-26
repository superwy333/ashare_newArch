package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-10 13:14
 **/
@Table(name = "operation_record")
public class OperationRecord extends BaseDomain {

    @Column(name = "record_id")
    private long recordId;
    @Column(name = "record_operation")
    private String recordOperation;
    @Column(name = "record_operator")
    private String recordOperator;
    @Column(name = "record_operation_time")
    private String recordOperationTime;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "record_operation_code")
    private String recordOperationCode;
    @Column(name = "record_status_flag")
    private String recordStatusFlag;
    @Column(name = "record_table")
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
