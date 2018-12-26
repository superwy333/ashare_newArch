package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 13:57
 **/
public class EdocScanRemarksDTO extends PageDTO {
    private Long edocHeaderId;

    private String operation;

    private String remarks;

    private Long edocImageId;

    public Long getEdocImageId() {
        return edocImageId;
    }

    public void setEdocImageId(Long edocImageId) {
        this.edocImageId = edocImageId;
    }

    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }



    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
