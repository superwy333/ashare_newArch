package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingArchives;
import com.zynsun.platform.edoc.model.AccountingArchivesModel;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 2:10
 * @Modified By:
 */
public interface AccountingArchivesService extends BaseService<AccountingArchives> {

    Integer saveAccountingArchives(AccountingArchives accountingArchives);

    /*Page<AccountingArchivesModel> findAccountingArchives(AccountingArchivesModel accountingArchivesModel);*/

    AccountingArchives findAccountingArchivesByFileNo(AccountingArchives accountingArchives);

    String boxNoIsExist(List<Long> ids);

    String archive(List<Long> ids, AccountingArchives accountingArchives);

}
