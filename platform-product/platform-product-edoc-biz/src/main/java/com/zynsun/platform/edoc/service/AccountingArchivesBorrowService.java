package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AccountingArchivesBorrow;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-06-27 13:19
 **/
public interface AccountingArchivesBorrowService extends BaseService<AccountingArchivesBorrow> {

    PageInfo<AccountingArchivesBorrowDTO> queryList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);

    Integer insertArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);

    Integer updateArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);

    List<AccountingArchivesBorrowDTO> queryArchModelList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);

    Integer updateCreateTime(Long sectionId, Date createtime);

    Integer updateSaveBackTime(Long sectionId, Date createtime);
}
