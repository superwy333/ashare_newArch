package com.zynsun.platform.edoc.facade.bizManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.bizType.BillTypeDTO;
import com.zynsun.platform.edoc.model.BillTypeModelDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface BillTypeFacade {

    /*查询所有有效的单证类型信息*/
    public ExecuteResult<List<BillTypeDTO>> findBillTypes(BillTypeDTO dto);

    /*保存单证类型信息*/
    public ExecuteResult<Boolean> saveBillType(BillTypeDTO dto);

    /*根据id查询单证类型信息*/
    public ExecuteResult<BillTypeModelDTO> findBillTypeById(Long id);

    /**
     * @Description：删除单证信息
     * @Author：PeidongWang
     * @Param：id
     * @Date：2017/6/3 0003 下午 8:37
     */
    public ExecuteResult<String> removeBillType(Long id);

    /**
     * 根据编码查询单证类型
     *
     * @param billTypeCode
     * @return
     */
    public ExecuteResult<BillTypeDTO> findOneBillType(String billTypeCode);

    public ExecuteResult<Long> createBillType(BillTypeDTO billTypeDTO);

    public ExecuteResult<Long> editBillType(BillTypeDTO billTypeDTO);

    /**
     * @Description：[根据业务类型编码查询单证类型]
     * @Author：PeidongWang
     * @Param：bizTypeCode
     * @Date：2017/6/27 15:19
     */
    public ExecuteResult<List<BillTypeDTO>> findBillTypesByBizCode(String bizTypeCode);
}
