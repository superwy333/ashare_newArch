package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingBox;
import com.zynsun.platform.edoc.domain.AccountingBoxItem;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 3:12
 * @Modified By:
 */
public interface AccountingBoxItemService extends BaseService<AccountingBoxItem> {

    String saveSectionBoxItems(List<Long> sections, AccountingBox accountingBox);

    List<String> findSectionIdByAccountingBoxId(Long id);

    void deleteItemsByBoxId(Long boxId);

    Integer deleteSectionItems(String sectionId,String boxId);




}
