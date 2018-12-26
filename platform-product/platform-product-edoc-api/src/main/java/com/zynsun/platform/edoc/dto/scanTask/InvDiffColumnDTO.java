package com.zynsun.platform.edoc.dto.scanTask;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * 发票修改记录
 */
public class InvDiffColumnDTO extends PageDTO {

    private Long invId;

    private String invColumn;

    private String oldVal;

    private String newVal;

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public String getInvColumn() {
        return invColumn;
    }

    public void setInvColumn(String invColumn) {
        this.invColumn = invColumn;
    }

    public String getOldVal() {
        return oldVal;
    }

    public void setOldVal(String oldVal) {
        this.oldVal = oldVal;
    }

    public String getNewVal() {
        return newVal;
    }

    public void setNewVal(String newVal) {
        this.newVal = newVal;
    }

}
