package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingLoc;
import com.zynsun.platform.edoc.dto.archive.AccountingLocDTO;
import com.zynsun.platform.edoc.model.AccountingLocModel;
import org.apache.ibatis.annotations.Param;

public interface AccountingLocMapper extends IMapper<AccountingLoc> {
    Page<AccountingLocDTO> selectLocPage(@Param("accountingLocModel") AccountingLocModel accountingLocModel);
}