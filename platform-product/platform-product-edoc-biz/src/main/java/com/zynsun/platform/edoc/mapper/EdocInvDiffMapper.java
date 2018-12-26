package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocInvDiff;
import com.zynsun.platform.edoc.model.InvDiffColumnModel;
import org.apache.ibatis.annotations.Param;

public interface EdocInvDiffMapper extends IMapper<EdocInvDiff> {

    Page<InvDiffColumnModel> selectByPage(@Param(value = "invDiffColumnModel") InvDiffColumnModel invDiffColumnModel);

}