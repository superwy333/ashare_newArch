package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AccountingArchivesBorrow;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;
import com.zynsun.platform.edoc.mapper.AccountingArchivesBorrowMapper;
import com.zynsun.platform.edoc.mapper.AccountingArchivesMapper;
import com.zynsun.platform.edoc.model.AccountingArchivesBorrowModel;
import com.zynsun.platform.edoc.service.AccountingArchivesBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 2:10
 * @Modified By:
 */
@Service
public class AccountingArchivesBorrowServiceImpl extends BaseServiceImpl<AccountingArchivesBorrow> implements AccountingArchivesBorrowService {



    @Autowired
    private AccountingArchivesBorrowMapper accountingArchivesBorrowMapper;


    @Override
    protected IMapper<AccountingArchivesBorrow> getBaseMapper() {
        return accountingArchivesBorrowMapper;
    }

    @Override
    public PageInfo<AccountingArchivesBorrowDTO> queryList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        //Page<AccountingArchivesBorrowModel> accountingArchivesBorrowModels = accountingArchivesBorrowMapper.queryList(DozerUtil.map(accountingArchivesBorrowDTO, AccountingArchivesBorrowModel.class));
        //PageInfo<AccountingArchivesBorrowDTO> accountingArchivesBorrowDTOPageInfo = DozerUtil.mapPage(accountingArchivesBorrowModels.toPageInfo(), AccountingArchivesBorrowDTO.class);
        //return accountingArchivesBorrowDTOPageInfo;

        return null;
    }

    @Override
    public Integer insertArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        return this.add(DozerUtil.map(accountingArchivesBorrowDTO , AccountingArchivesBorrow.class));
    }

    @Override
    public Integer updateArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        return this.updateById(DozerUtil.map(accountingArchivesBorrowDTO , AccountingArchivesBorrow.class));
    }

    @Override
    public List<AccountingArchivesBorrowDTO> queryArchModelList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        //List<AccountingArchivesBorrowModel> accountingArchivesBorrowModels = accountingArchivesBorrowMapper.queryArchModelList(DozerUtil.map(accountingArchivesBorrowDTO, AccountingArchivesBorrowModel.class));
        //List<AccountingArchivesBorrowDTO> accountingArchivesBorrowDTOS = DozerUtil.mapList(accountingArchivesBorrowModels, AccountingArchivesBorrowDTO.class);
        //return accountingArchivesBorrowDTOS;

        return null;
    }

    @Override
    public Integer updateCreateTime(Long sectionId, Date createtime){
        //return accountingArchivesBorrowMapper.updateCreateTime(sectionId, createtime);
        return null;
    }

    @Override
    public Integer updateSaveBackTime(Long sectionId, Date createtime){
        //return accountingArchivesBorrowMapper.updateSaveBackTime(sectionId, createtime);
        return null;
    }

}
