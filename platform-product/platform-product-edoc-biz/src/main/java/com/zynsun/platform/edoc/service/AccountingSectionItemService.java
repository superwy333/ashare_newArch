package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.domain.AccountingSectionItem;

import java.util.List;

/**
 * Created by WZH on 2017/7/28.
 */
public interface AccountingSectionItemService extends BaseService<AccountingSectionItem> {

    String saveAccountingSectionItems(List<Long> ids,AccountingSection accountingSection);

    List<Long> findBillHeaderIdsBySectionIds(List<String> sectionIds);

    List<Long> findBillHeaderIdsBySectionId(String id);

    void deleteSectionItemBySectionId(Long id);

    String editAccountingSectionItems(List<Long> ids,AccountingSection accountingSection);

    Integer deleteItemByBillHeaderIdAndSectionId(Long billHeaderId,Long sectionId);



}
