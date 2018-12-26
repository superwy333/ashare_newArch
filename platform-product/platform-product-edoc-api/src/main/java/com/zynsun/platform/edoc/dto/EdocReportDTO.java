package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description: Edoc报表DTO
 * @author: Wy
 * @create: 2018-07-26 13:34
 **/
public class EdocReportDTO extends PageDTO {

    private String type;

    private String operation;

    private String statusFlag;

    private String operator;

    private Integer num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
