package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingBox;
import com.zynsun.platform.edoc.model.AccountingBoxModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountingBoxMapper extends IMapper<AccountingBox> {

    List<AccountingBoxModel> findAccountingBoxByIds(@Param(value = "ids") List<Long> ids);

    AccountingBoxModel findAccountingBoxByBoxNo(@Param(value = "boxNo") String boxNo, @Param(value = "boxStatus") String boxStatus);

    AccountingBoxModel findAccountingBoxByBoxNoAlreadyArchive(@Param(value = "boxNo") String boxNo);

    Page<AccountingBoxModel> findSectionBoxs(@Param(value = "accountingBoxModel") AccountingBoxModel accountingBoxModel);

    Page<AccountingBoxModel> findAccountingBoxs(@Param(value = "accountingBoxModel") AccountingBoxModel accountingBoxModel);
}