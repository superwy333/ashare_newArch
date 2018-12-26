package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.model.FormAreaModel;

import java.util.List;

public interface FormAreaMapper extends IMapper<FormArea> {

    FormAreaModel selectAreaModelById(Long id);

    FormArea findAreaByCode(String areaCode);
}