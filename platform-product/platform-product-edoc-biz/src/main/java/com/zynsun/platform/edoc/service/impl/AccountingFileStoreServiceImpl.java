package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.AccountingFileStore;
import com.zynsun.platform.edoc.dto.archive.AccountingFileStoreDTO;
import com.zynsun.platform.edoc.mapper.AccountingFileStoreMapper;
import com.zynsun.platform.edoc.model.AccountingFileStoreModel;
import com.zynsun.platform.edoc.service.AccountingFileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 档案室业务接口实现
 * @Auther: YongChen
 * @Date: Create in 2018/6/26 9:50
 * @Modified By:
 */
@Service
public class AccountingFileStoreServiceImpl extends BaseServiceImpl<AccountingFileStore> implements AccountingFileStoreService {

    @Autowired
    private AccountingFileStoreMapper accountingFileStoreMapper;

    @Override
    protected IMapper<AccountingFileStore> getBaseMapper() {
        return accountingFileStoreMapper;
    }

    @Override
    public Page<AccountingFileStoreDTO> queryFileStorePage(AccountingFileStoreModel accountingFileStoreModel) {
        return accountingFileStoreMapper.selectFileStorePage(accountingFileStoreModel);
    }
}
