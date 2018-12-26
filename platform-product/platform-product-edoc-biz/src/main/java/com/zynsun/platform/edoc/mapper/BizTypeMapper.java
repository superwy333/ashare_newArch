package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BizType;
import com.zynsun.platform.edoc.model.BizTypeModel;
import com.zynsun.platform.edoc.model.BizTypeModelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizTypeMapper extends IMapper<BizType> {

    List<BizType> findBizsByRoleIds(@Param(value = "roleIds") List<Long> roleIds);

    /*查询所有的业务类型信息*/
    public List<BizType> findBizTypes();

    /**
     * 根据业务类型编码查询
     * @param bizTypeCode
     * @return
     */
    public BizType selectByBizTypeCode(String bizTypeCode);

    /**
     * 根据id查询业务类型及其关联的单证类型
     * @param id
     * @return
     */
    public BizTypeModel selectBizTypeModel(Long id);

    BizTypeModelDTO selectFromTepByBizTypeCode(@Param(value = "code") String code , @Param(value = "deleted")Integer deleted );


}