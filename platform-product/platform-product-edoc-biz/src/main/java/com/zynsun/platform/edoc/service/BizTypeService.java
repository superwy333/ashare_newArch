package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.BizType;
import com.zynsun.platform.edoc.domain.BizTypeStyle;
import com.zynsun.platform.edoc.model.BizTypeModel;
import com.zynsun.platform.edoc.model.BizTypeModelDTO;

import java.util.List;

/**
 * Created by Administrator on 2017-05-31 .
 */
public interface BizTypeService extends BaseService<BizType> {

    List<BizType> findBizTypeByRoleId(List<Long> roleIds);


    /*查询所有有效的业务类型信息*/
    List<BizType> findBizTypes(BizType bizType);

    /**
     * 根据代码查询
     * @param bizTypeCode 代码
     * @return
     */
    public BizType queryByCode(String bizTypeCode) ;

    /**
     * 新增业务类型及关联的单证类型
     * @param bizTypeStyleList
     * @return
     */
    public int addBizAndBills(BizType bizType, List<BizTypeStyle> bizTypeStyleList);

    /**
     * 删除业务类型及关联的单证类型
     * @param bizTypeId
     * @return
     */
    public int deleteBizAndBills(Long bizTypeId);

    /**
     * 根据id查询业务类型及其关联的单证类型
     * @param id
     * @return
     */
    public BizTypeModel queryBizTypeModelById(Long id);

    /**
     * 修改
     * @param bizType
     * @param bizTypeStyleList
     * @return
     */
    public int modifiedBizType(BizType bizType, List<BizTypeStyle> bizTypeStyleList);


    /**
     *  查询form_template 的 formtemp
     */
    BizTypeModelDTO selectFromTepByBizTypeCode(String code , Integer deleted );

}
