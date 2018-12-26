package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.BillAccountModelDTO;
import exception.BusinessException;
import exception.ParamInvalidException;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author Administrator
 * @create 2017/12/27
 * @modify
 */
public interface BillAccountFacade{


    /**
     * 创建报账单
     * @param billAccountModelDTO
     * @return
     * @throws ParamInvalidException
     */
    @Compensable
    ExecuteResult<BillAccountModelDTO> addBillAccount(BillAccountModelDTO billAccountModelDTO) throws ParamInvalidException, BusinessException;

    /**
     * 编辑报账单
     * @param billAccountModelDTO
     * @return
     */
    ExecuteResult<String> editBillAccount(BillAccountModelDTO billAccountModelDTO);

    /**
     * 根据流程主键查询报账单
     * @param businessKey
     * @return
     */
    ExecuteResult<BillAccountModelDTO> queryBillAccountByBusinessKey(String businessKey);

    /**
     * 根据报账单各属性查询分页数据
     * @param billAccountModelDTO
     * @return
     */
    ExecuteResult<PageInfo<BillAccountModelDTO>> queryBillAccountPageList(BillAccountModelDTO billAccountModelDTO);


    /**
     * 根据主键ID查询报账单信息
     * @param id
     * @return
     */
    ExecuteResult<BillAccountModelDTO> findBillAccountById(Long id);


    ExecuteResult<Long> getEdocHeaderIdByAccountId(Long id,Integer edocLevel);


}
