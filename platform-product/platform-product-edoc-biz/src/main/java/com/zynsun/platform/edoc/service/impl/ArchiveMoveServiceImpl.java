package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.ArchiveMove;
import com.zynsun.platform.edoc.mapper.ArchiveMoveMapper;
import com.zynsun.platform.edoc.model.ArchiveMoveModel;
import com.zynsun.platform.edoc.service.ArchiveMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:08
 * @Modified By:
 */
@Service
public class ArchiveMoveServiceImpl extends BaseServiceImpl<ArchiveMove> implements ArchiveMoveService {

    @Autowired
    private ArchiveMoveMapper archiveMoveMapper;

    @Override
    protected IMapper<ArchiveMove> getBaseMapper() {
        return archiveMoveMapper;
    }

    @Override
    public Page<ArchiveMoveModel> queryArchiveMoveList(ArchiveMoveModel archiveMove) {
        return archiveMoveMapper.queryArchiveMoveList(archiveMove);
    }

    @Override
    public Page<ArchiveMoveModel> queryToBeWorkList(ArchiveMoveModel archiveMoveModel) {
        return archiveMoveMapper.queryToBeWorkList(archiveMoveModel);
    }

    @Override
    public Page<ArchiveMoveModel> queryDoneWorkList(ArchiveMoveModel archiveMoveModel) {
        return archiveMoveMapper.queryDoenWorkList(archiveMoveModel);
    }



}
