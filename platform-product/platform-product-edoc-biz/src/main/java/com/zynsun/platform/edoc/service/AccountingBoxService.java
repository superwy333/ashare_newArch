package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingBox;
import com.zynsun.platform.edoc.model.AccountingBoxModel;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 3:08
 * @Modified By:
 */
public interface AccountingBoxService extends BaseService<AccountingBox> {

    /**
     * 根据条件获取盒子，否则创建盒子
     * @param accountingBox
     * @return
     */
    String sectionBoxCreateOrFindBox(AccountingBox accountingBox,List<Long> ids);

    Integer saveAccountingBox(AccountingBox box);

    AccountingBox findAccountingBoxByBoxNo(String boxNo);

    Page<AccountingBoxModel> findSectionBoxs(AccountingBoxModel model);

    Integer findVolumes(String bizType,String startDate,String endDate,String companyCode);

    AccountingBoxModel findContractByBoxNo(String boxNo,String boxStatus);

    AccountingBoxModel findContractByBoxNoAlready(String boxNo);



}
