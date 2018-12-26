package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.dto.TreeDTO;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.AccountingLocDTO;

import java.util.List;

public interface AccountingLocFacade {

    ExecuteResult<PageInfo<AccountingLocDTO>> findLocListByPage(AccountingLocDTO accountingLocDTO);

    ExecuteResult<String> createLoc(AccountingLocDTO accountingLocDTO);

    ExecuteResult<AccountingLocDTO> findLocById(Long id);

    ExecuteResult<String> editLoc(AccountingLocDTO accountingLocDTO);

    ExecuteResult<String> deleteLocById(Long id);

    ExecuteResult<List<TreeDTO>> queryAllStore();
}
