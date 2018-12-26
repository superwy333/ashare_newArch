package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.ArchiveIdentifies;
import com.zynsun.platform.edoc.mapper.ArchiveIdentifiesMapper;
import com.zynsun.platform.edoc.mapper.ArchiveMoveMapper;
import com.zynsun.platform.edoc.model.ArchiveIdentifiesModel;
import com.zynsun.platform.edoc.service.ArchiveIdentifiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
@Service
public class ArchiveIdentifiesServiceImpl extends BaseServiceImpl<ArchiveIdentifies> implements ArchiveIdentifiesService {

    @Autowired
    private ArchiveMoveMapper archiveMoveMapper;

    @Autowired
    private ArchiveIdentifiesMapper archiveIdentifiesMapper;


    @Override
    protected IMapper<ArchiveIdentifies> getBaseMapper() {
        return archiveIdentifiesMapper;
    }

    @Override
    public Page<ArchiveIdentifiesModel> archivedIdentifiesList(ArchiveIdentifiesModel archiveIdentifiesModel) {
        return archiveIdentifiesMapper.archivedIdentifiesList(archiveIdentifiesModel);
    }

    @Override
    public ArchiveIdentifiesModel queryByEdocHearderId(Long edocHeaderId) {
        return archiveIdentifiesMapper.queryByEdocHearderId(edocHeaderId);
    }
}
