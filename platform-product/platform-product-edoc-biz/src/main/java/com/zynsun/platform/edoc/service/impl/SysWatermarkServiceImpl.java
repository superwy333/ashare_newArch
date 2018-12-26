package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.platform.edoc.domain.SysWatermark;
import com.zynsun.platform.edoc.mapper.SysWatermarkMapper;
import com.zynsun.platform.edoc.model.SysWatermarkModel;
import com.zynsun.platform.edoc.service.SysWatermarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tiger on 2017/7/12.
 */
@Service
public class SysWatermarkServiceImpl extends BaseServiceImpl<SysWatermark> implements SysWatermarkService {
    @Resource
    SysWatermarkMapper sysWatermarkMapper;

    @Override
    public Page<SysWatermarkModel> selectWatermarks(SysWatermarkModel sysWatermarkModel) {
        return this.sysWatermarkMapper.selectWatermarks(sysWatermarkModel);
    }

    @Override
    public Integer createSysWatermark(SysWatermark sysWatermark)  throws Exception{
        if(sysWatermark == null){
            return null;
        }
        sysWatermark.setCreateTime(new Date());
        sysWatermark.setCreateBy(SubjectUtil.getUser().getLoginName());
        Integer insertResult = this.add(sysWatermark);
        return insertResult;
    }

    @Override
    public Integer updateSysWatermark(SysWatermark sysWatermark)  throws Exception{
        if(sysWatermark == null){
            return null;
        }
        sysWatermark.setLastModifyBy(SubjectUtil.getUser().getLoginName());
        sysWatermark.setLastModifyTime(new Date());
        Integer updateResult = this.sysWatermarkMapper.updateWatermarkById(sysWatermark);
//        Integer updateResult = this.updateByIdSelective(sysWatermark);
        return updateResult;

    }


    public void updateOtherWatermarkStatusToDisable(Long id, String type)throws Exception{
        Map<String, Object> params = new HashMap();
        params.put("id", id);
        params.put("type", type);
        params.put("user", SubjectUtil.getUser().getLoginName());
        this.sysWatermarkMapper.updateOtherWatermarkStatusToDisable(params);
    }
//============================================================================================
    @Override
    protected IMapper<SysWatermark> getBaseMapper() {
        return sysWatermarkMapper;
    }
}
