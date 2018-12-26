package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.ArchiveCompanyDTO;

import java.util.List;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/8/11 0011 下午 6:42
 * @Modified By：
 */
public interface ArchiveCompanyFacade {
    ExecuteResult<List<ArchiveCompanyDTO>> findArchiveCompanyByArchiveId(String archiveId);

    ExecuteResult<ArchiveCompanyDTO> findArchiveCompanyByCompanyName(String companyName);

}
