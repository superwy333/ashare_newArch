package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingFileStore;
import com.zynsun.platform.edoc.dto.archive.AccountingFileStoreDTO;
import com.zynsun.platform.edoc.model.AccountingFileStoreModel;

/**
 * @Description: 档案室业务接口
 * @Auther: YongChen
 * @Date: Create in 2018/6/26 9:50
 * @Modified By:
 */
public interface AccountingFileStoreService extends BaseService<AccountingFileStore> {
    Page<AccountingFileStoreDTO> queryFileStorePage(AccountingFileStoreModel accountingFileStoreModel);
}
