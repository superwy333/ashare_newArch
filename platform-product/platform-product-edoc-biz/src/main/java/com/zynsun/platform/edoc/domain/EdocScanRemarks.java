package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.platform.edoc.annotation.Validata;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:00
 **/

@Table(name = "edoc_scan_remarks")
@Validata
public class EdocScanRemarks extends BaseDomain {

    @Column(name = "edoc_header_id")
    private Long edocHeaderId;
    @Column(name = "operation")
    private String operation;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "edoc_image_id")
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
