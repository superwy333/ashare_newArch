package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ArchiveMove;
import com.zynsun.platform.edoc.model.ArchiveMoveModel;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:07
 * @Modified By:
 */
public interface ArchiveMoveService extends BaseService<ArchiveMove> {

    Page<ArchiveMoveModel> queryArchiveMoveList(ArchiveMoveModel archiveMove);

    Page<ArchiveMoveModel> queryToBeWorkList(ArchiveMoveModel archiveMoveModel);

    Page<ArchiveMoveModel> queryDoneWorkList(ArchiveMoveModel archiveMoveModel);





}
