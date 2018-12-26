package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.AccountingLoc;
import com.zynsun.platform.edoc.dto.archive.AccountingLocDTO;
import com.zynsun.platform.edoc.mapper.AccountingLocMapper;
import com.zynsun.platform.edoc.model.AccountingLocModel;
import com.zynsun.platform.edoc.service.AccountingLocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 档案库位业务接口实现
 * @Auther: YongChen
 * @Date: Create in 2018/6/26 14:50
 * @Modified By:
 */
@Service
public class AccountingLocServiceImpl extends BaseServiceImpl<AccountingLoc> implements AccountingLocService {

    @Autowired
    private AccountingLocMapper accountingLocMapper;

    @Override
    protected IMapper<AccountingLoc> getBaseMapper() {
        return accountingLocMapper;
    }

    @Override
    public Page<AccountingLocDTO> queryLocPage(AccountingLocModel accountingLocModel) {
        return accountingLocMapper.selectLocPage(accountingLocModel);
    }
}
