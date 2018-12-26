package com.zynsun.platform.edoc.facade.scanTaskManage;


import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.*;


import java.util.List;

/**
 * Created by WZH on 2017/7/28.
 */

public interface AccountingSectionFacade {

    ExecuteResult<PageInfo<AccountingSectionDTO>> findAccountingSection(AccountingSectionDTO accountingSectionDTO);

    ExecuteResult<Integer> saveAccountingSection(List<Long> ids, AccountingSectionDTO accountingSectionDTO);

    ExecuteResult<List<AccountingSectionDTO>> findAccountingSectionsByIds(List<Long> ids);

    ExecuteResult<AccountingSectionDTO> findByRecordNo(String recordNo);

    ExecuteResult<Integer> sectionBoxing(AccountingBoxDTO accountingBoxDTO, List<Long> ids);

    ExecuteResult<Integer> archive(List<Long> ids, AccountingArchivesDTO accountingArchivesDTO);

    ExecuteResult<List<AccountingSectionDTO>> findSectionByBoxId(Long id);

    ExecuteResult<Integer> findVolumesByBizType(String bizType,String startDate,String endDate,String companyCode);

    ExecuteResult<AccountingSectionDTO> findSectionById(Long id);

    ExecuteResult<Integer> deleteSectionById(Long id);

    ExecuteResult<Integer> editAccountingSection(AccountingSectionDTO accountingSectionDTO);

    ExecuteResult<AccountingSectionDTO> addSectionBoxItem(String recordNo,Long boxId);

    ExecuteResult<List<Long>> findBillHeaderIdsBySectionId(String sectionId);

    ExecuteResult<List<EdocHeaderDTO>> findEdocHeaderBySectionId(String sectionId);






}
