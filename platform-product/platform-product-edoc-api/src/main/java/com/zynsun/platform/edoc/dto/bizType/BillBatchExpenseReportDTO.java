package com.zynsun.platform.edoc.dto.bizType;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public class BillBatchExpenseReportDTO extends BillBatchReportDTO {
    private List<InvListExpenseReportDTO> claims;

    public List<InvListExpenseReportDTO> getClaims() {
        return claims;
    }

    public void setClaims(List<InvListExpenseReportDTO> claims) {
        this.claims = claims;
    }
}
