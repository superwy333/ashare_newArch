package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * Created by tomzhang on 2017/8/24.
 */
public class InvDiffColumnModel extends PageModel {

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
