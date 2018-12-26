package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.ArchiveIdentifiesDTO;
import com.zynsun.platform.edoc.dto.archive.ArchiveMoveDTO;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
public interface ArchiveIdentifiesFacade {


    ExecuteResult<PageInfo<ArchiveIdentifiesDTO>> archivedIdentifiesList(ArchiveIdentifiesDTO archiveIdentifiesDTO);

    //保存鉴定结果
    ExecuteResult<String> saveArchiveIdenti(ArchiveIdentifiesDTO archiveIdentifiesDTO);
}
