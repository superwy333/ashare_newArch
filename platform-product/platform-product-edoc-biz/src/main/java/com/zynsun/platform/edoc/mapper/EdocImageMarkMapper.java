package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocImageMark;
import com.zynsun.platform.edoc.model.EdocImageMarkModel;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:39
 **/
public interface EdocImageMarkMapper extends IMapper<EdocImageMark> {

    Page<EdocImageMarkModel> queryMarkRecordsPageList(@Param("edocImageMarkModel")EdocImageMarkModel edocImageMarkModel);





}
