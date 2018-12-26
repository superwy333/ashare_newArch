package com.zynsun.platform.edoc.dto.bizType;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public class BillBatchAPReportDTO extends BillBatchReportDTO {
    private List<InvListAPReportDTO> claims;
    public List<InvListAPReportDTO> getClaims() {
        return claims;
    }
    public void setClaims(List<InvListAPReportDTO> claims) {
        this.claims = claims;
    }
}
