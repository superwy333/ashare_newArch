package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocScanRemarks;
import com.zynsun.platform.edoc.model.EdocScanRemarksModel;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:10
 **/
public interface EdocScanRemarksService extends BaseService<EdocScanRemarks> {

    Page<EdocScanRemarksModel> queryByPage(EdocScanRemarksModel edocScanRemarksModel);

}
