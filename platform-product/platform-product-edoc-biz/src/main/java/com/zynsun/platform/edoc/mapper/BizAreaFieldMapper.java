package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.model.BizAreaFieldModel;

import java.util.List;

public interface BizAreaFieldMapper extends IMapper<BizAreaField> {

    List<BizAreaFieldModel> selectFieldByBizIdAndAreaId(BizAreaField record);

    Integer deleteBizAreaField(BizAreaField record);

}