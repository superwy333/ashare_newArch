package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.ArchiveHeaderDTO;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 上午 9:04
 * @Modified By:
 */
public interface ArchiveHeaderFacade {

    ExecuteResult<String> createArchiveHeader(ArchiveHeaderDTO archiveHeaderDTO);


    ExecuteResult<PageInfo<ArchiveHeaderDTO>> findArchiveHeaderList(ArchiveHeaderDTO archiveHeaderDTO);

    ExecuteResult<Boolean> deleteArchiveHeaderAndCompanyById(Long id);

    ExecuteResult<ArchiveHeaderDTO> findArchiveHeaderById(Long id);

    ExecuteResult<ArchiveHeaderDTO> findArchiveHeaderByCompanyId(String companyId);

    ExecuteResult<ArchiveHeaderDTO> findArchiveInfo(String companyId);


}
