package com.zynsun.platform.edoc.facade.watermarkManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.watermark.SysWatermarkDTO;

/**
 * Created by Tiger on 2017/7/12.
 */
public interface SysWatermarkFacade {

    ExecuteResult<PageInfo<SysWatermarkDTO>> selectWatermarks(SysWatermarkDTO sysWatermarkDTO);

    ExecuteResult<Integer> createSysWatermark(SysWatermarkDTO sysWatermarkDTO);

    ExecuteResult<Integer> updateSysWatermark(SysWatermarkDTO sysWatermarkDTO);

    /**
     * 将此ID的水印设置为有效，同时把同类型中其它的设置为失效
     * @param id
     * @return
     */
    ExecuteResult<Integer> intoEffect(Long id);

    ExecuteResult<Integer> delete(Long id);

    ExecuteResult<SysWatermarkDTO> selectWatermarkById(Long id);

    ExecuteResult<SysWatermarkDTO> selectWatermark(SysWatermarkDTO sysWatermarkDTO);
}
