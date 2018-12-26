package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocScanRemarksDTO;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:03
 **/
public interface EdocScanRemarksFacade {

    ExecuteResult<String> saveEdocScanRemarks(EdocScanRemarksDTO edocScanRemarksDTO);

    ExecuteResult<PageInfo<EdocScanRemarksDTO>> queryImageHis(EdocScanRemarksDTO edocScanRemarksDTO);


}
