package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocScanRemarks;
import com.zynsun.platform.edoc.model.EdocScanRemarksModel;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:06
 **/
public interface EdocScanRemarksMapper extends IMapper<EdocScanRemarks> {

    Page<EdocScanRemarksModel> selectByPage(@Param(value = "edocScanRemarksModel") EdocScanRemarksModel edocScanRemarksModel);


}
