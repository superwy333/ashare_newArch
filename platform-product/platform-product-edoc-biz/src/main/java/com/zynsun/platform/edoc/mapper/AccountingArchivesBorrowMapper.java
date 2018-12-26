package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingArchivesBorrow;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;
import com.zynsun.platform.edoc.model.AccountingArchivesBorrowModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountingArchivesBorrowMapper extends IMapper<AccountingArchivesBorrow> {

    Page<AccountingArchivesBorrowModel> queryList(@Param(value="accountingArchivesBorrowModel") AccountingArchivesBorrowModel accountingArchivesBorrowModel);


    List<AccountingArchivesBorrowModel> queryArchModelList(AccountingArchivesBorrowModel accountingArchivesBorrowModel);

    Integer updateCreateTime(@Param(value="sectionId")Long sectionId, @Param(value="createtime")Date createtime);

    Integer updateSaveBackTime(@Param(value="sectionId")Long sectionId, @Param(value="createtime")Date createtime);
}