package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.SysWatermark;
import com.zynsun.platform.edoc.model.SysWatermarkModel;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SysWatermarkMapper extends IMapper<SysWatermark> {

    Page<SysWatermarkModel> selectWatermarks(@Param(value = "sysWatermarkModel") SysWatermarkModel sysWatermarkModel);

    public void updateOtherWatermarkStatusToDisable(@Param(value = "params") Map<String, Object> params);

    public int updateWatermarkById(@Param(value = "sysWatermark") SysWatermark sysWatermark);
}