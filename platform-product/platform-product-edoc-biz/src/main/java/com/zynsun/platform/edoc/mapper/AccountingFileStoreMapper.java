package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingFileStore;
import com.zynsun.platform.edoc.dto.archive.AccountingFileStoreDTO;
import com.zynsun.platform.edoc.model.AccountingFileStoreModel;
import org.apache.ibatis.annotations.Param;

public interface AccountingFileStoreMapper extends IMapper<AccountingFileStore> {
    Page<AccountingFileStoreDTO> selectFileStorePage(@Param("accountingFileStoreModel") AccountingFileStoreModel accountingFileStoreModel);
}