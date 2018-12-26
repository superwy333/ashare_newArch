package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BillAccountInfo;
import com.zynsun.platform.edoc.model.BillAccountModel;
import com.zynsun.platform.edoc.service.basic.EdocBaseService;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 10:50 2017/12/27
 * @Modified By:
 */
public interface BillAccountService extends EdocBaseService<BillAccountInfo> {

    BillAccountModel addBillAccount(BillAccountModel billAccountModel);

    Integer editBillAccount(BillAccountModel billAccountModel);

    void delBilLAccount(BillAccountModel billAccountModel);

    BillAccountInfo queryBillAccountByBusinessKey(String businessKey);

    /**
     * 根据实体属性查询分页数据
     * @param billAccountModel
     * @return
     */
    Page<BillAccountModel> queryBillAccountPageList(BillAccountModel billAccountModel);

    /**
     * 根据主键ID进行查询
     * @param ids
     * @return
     */
    List<BillAccountModel> queryByIds(List<Long> ids);


}
