package com.zynsun.platform.edoc.facade.bizManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.bizType.BizTypeDTO;
import com.zynsun.platform.edoc.model.BizTypeModelDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public interface BizTypeFacade {

    /*查询所有有效的业务类型信息*/
    public ExecuteResult<List<BizTypeDTO>> findBizTypes(BizTypeDTO dto);

    /**
     * 新增
     * @param bizTypeDTO
     * @return
     */
    public ExecuteResult<String> createBizType(BizTypeDTO bizTypeDTO);

    /**
     * 根据编码查询
     * @param bizTypeCode 编码
     * @return 结果
     */
    public ExecuteResult<BizTypeDTO> findOneBizType(String bizTypeCode) ;

    /**
     * 根据编码查询h和delete
     * @param bizTypeCode 编码
     * @return 结果
     */
    public ExecuteResult<BizTypeDTO> findOneBizType(String bizTypeCode , Integer delete) ;

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    public ExecuteResult<BizTypeModelDTO> queryBizTypeModelDTO(Long id);

    /**
     * 根据ID删除
     * @param bizTypeId
     * @return 删除成功or失败信息
     */
    public ExecuteResult<String> removeBizType(Long bizTypeId);

    /**
     * 修改
     * @param bizTypeModelDTO
     * @return
     */
    public ExecuteResult<String> modifiedBizType(BizTypeModelDTO bizTypeModelDTO);

    ExecuteResult<String> saveForm(String jsonStr);

    /**
     *  查询form_template 的 formtemp
     */
    ExecuteResult<BizTypeModelDTO> selectFromTepByBizTypeCode(String code , Integer deleted );

}
