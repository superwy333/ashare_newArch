package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingBoxItem;

import java.util.List;

public interface AccountingBoxItemMapper extends IMapper<AccountingBoxItem> {

    List<String> findEdocIdByAccountingBoxId(Long id);

    List<String> findSectionIdByAccountingBoxId(Long id);
}