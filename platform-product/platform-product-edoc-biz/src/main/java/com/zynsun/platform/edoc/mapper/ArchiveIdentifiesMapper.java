package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.ArchiveIdentifies;
import com.zynsun.platform.edoc.model.ArchiveIdentifiesModel;
import com.zynsun.platform.edoc.model.ArchiveMoveModel;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
public interface ArchiveIdentifiesMapper extends IMapper<ArchiveIdentifies> {


    Page<ArchiveIdentifiesModel> archivedIdentifiesList( @Param(value = "archiveIdentifiesModel")ArchiveIdentifiesModel archiveIdentifiesModel);

    ArchiveIdentifiesModel queryByEdocHearderId(Long edocHeaderId);
}
