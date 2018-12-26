package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingArchives;
import com.zynsun.platform.edoc.model.AccountingArchivesModel;
import org.apache.ibatis.annotations.Param;

public interface AccountingArchivesMapper extends IMapper<AccountingArchives> {

    Page<AccountingArchivesModel> findAccountingArchives(@Param(value = "accountingArchivesModel") AccountingArchivesModel accountingArchivesModel);



}