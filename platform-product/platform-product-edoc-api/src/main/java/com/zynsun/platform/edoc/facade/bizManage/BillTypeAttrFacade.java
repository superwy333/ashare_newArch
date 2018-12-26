package com.zynsun.platform.edoc.facade.bizManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.bizType.BillTypeAttrDTO;

import java.util.List;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/13 10:15
 * @Modified By：
 */
public interface BillTypeAttrFacade {

    public ExecuteResult<BillTypeAttrDTO> createBillTypeAttr(BillTypeAttrDTO billTypeAttrDTO);

    /**
     * 根据编码查询单证类型
     * @param code
     * @return
     */
    public ExecuteResult<BillTypeAttrDTO> findOneBillTypeAttr(String code);

    /**
     * 根据单证类型id查询所有属性
     * @param billTypeId
     * @return
     */
    public ExecuteResult<List<BillTypeAttrDTO>> findAttrsByBillTypeId(Long billTypeId);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public ExecuteResult<BillTypeAttrDTO> findAttrsById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    public ExecuteResult<String> removeAttr(Long id);
}
