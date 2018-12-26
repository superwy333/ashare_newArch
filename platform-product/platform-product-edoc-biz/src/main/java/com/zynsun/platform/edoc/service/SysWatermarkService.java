package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.SysWatermark;
import com.zynsun.platform.edoc.model.SysWatermarkModel;

/**
 * Created by ghc on 2017/7/12.
 */
public interface SysWatermarkService extends BaseService<SysWatermark> {

    /**
    *
    */
    public Page<SysWatermarkModel> selectWatermarks(SysWatermarkModel sysWatermarkModel);

    public Integer createSysWatermark(SysWatermark sysWatermark) throws Exception;

    public Integer updateSysWatermark(SysWatermark sysWatermark) throws Exception;

    /**
     * 指定类型中，除指定ID外的其它水印都设置为失效
     * @param id
     * @param type
     * @throws Exception
     */
    public void updateOtherWatermarkStatusToDisable(Long id, String type)throws Exception;
}
