package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.ArchiveMoveDTO;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:03
 * @Modified By:
 */
public interface ArchiveMoveFacade {


    ExecuteResult<PageInfo<ArchiveMoveDTO>> findArchiveMoveList(ArchiveMoveDTO archiveMoveDTO);

    ExecuteResult<String> addArchiveMoveRecord(ArchiveMoveDTO archiveMoveDTO,String companyId);

    ExecuteResult<PageInfo<ArchiveMoveDTO>> queryAllWorkList(ArchiveMoveDTO archiveMoveDTO);

    ExecuteResult<PageInfo<ArchiveMoveDTO>> queryDoneWorkList(ArchiveMoveDTO archiveMoveDTO);

    ExecuteResult<String> archiveMoveCheck(String id, String businessKey,String taskDefinitionKey,String passOrReject);

    ExecuteResult<String> updateArchiveMove(ArchiveMoveDTO archiveMoveDTO);

    ExecuteResult<ArchiveMoveDTO> queryArchiveMoveById(Long id);



}
