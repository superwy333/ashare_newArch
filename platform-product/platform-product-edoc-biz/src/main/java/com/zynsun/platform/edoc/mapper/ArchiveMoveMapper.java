package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.ArchiveMove;
import com.zynsun.platform.edoc.model.ArchiveMoveModel;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:09
 * @Modified By:
 */
public interface ArchiveMoveMapper extends IMapper<ArchiveMove> {


    Page<ArchiveMoveModel> queryArchiveMoveList(@Param(value = "archiveMove")ArchiveMoveModel archiveMove);

    Page<ArchiveMoveModel> queryToBeWorkList(ArchiveMoveModel archiveMoveModel);

    Page<ArchiveMoveModel> queryDoenWorkList(ArchiveMoveModel archiveMoveModel);



}
