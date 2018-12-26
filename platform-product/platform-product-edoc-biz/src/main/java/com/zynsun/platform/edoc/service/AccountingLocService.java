package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingLoc;
import com.zynsun.platform.edoc.dto.archive.AccountingLocDTO;
import com.zynsun.platform.edoc.model.AccountingLocModel;

/**
 * @Description: 档案库位业务接口
 * @Auther: YongChen
 * @Date: Create in 2018/6/26 14:50
 * @Modified By:
 */
public interface AccountingLocService extends BaseService<AccountingLoc> {
    Page<AccountingLocDTO> queryLocPage(AccountingLocModel accountingLocModel);
}
