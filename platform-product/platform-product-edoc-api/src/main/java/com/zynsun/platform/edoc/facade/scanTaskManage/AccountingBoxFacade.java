package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingBoxDTO;
import com.zynsun.platform.edoc.dto.archive.ArchiveMoveDTO;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 1:11
 * @Modified By:
 */
public interface AccountingBoxFacade {

    ExecuteResult<PageInfo<AccountingBoxDTO>> findSectionBoxs(AccountingBoxDTO accountingBoxDTO);

    ExecuteResult<AccountingBoxDTO> findAccountingBoxByBoxNo(AccountingBoxDTO accountingBoxDTO);

    ExecuteResult<AccountingBoxDTO> findAccountingBoxByBoxNoAlreadyArchive(AccountingBoxDTO accountingBoxDTO);

    ExecuteResult<AccountingBoxDTO> findAccountingBoxById(Long id);

    ExecuteResult<Integer> deleteSectionBox(Long id);

    ExecuteResult<Integer> editBox(AccountingBoxDTO accountingBoxDTO);

    ExecuteResult<Integer> deleteSectionBoxItem(String sectionId,String boxId);

    /**
     * 归档逆流程，接触单据的分册编号归档（直接干掉档案或盒子或册子）
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<String> cancelArchive(EdocHeaderDTO edocHeaderDTO);

    ExecuteResult<String> cancelArchiveBySection(Long sectionId);

    ExecuteResult<List<String>> findSectionNoByBoxId(Long id);






}
