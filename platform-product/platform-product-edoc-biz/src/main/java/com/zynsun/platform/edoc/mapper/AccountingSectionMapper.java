package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.model.AccountingSectionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AccountingSectionMapper extends IMapper<AccountingSection> {

    Page<AccountingSectionModel> findAccountingSections(@Param(value = "model") AccountingSectionModel accountingSectionModel);

    AccountingSectionModel selectByID(Long id);

    String findBookNo(Map<String, Object> map);

    List<AccountingSectionModel> findSectionsByBoxId(Long id);
}