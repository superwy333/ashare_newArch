package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.model.BillTypeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillTypeMapper extends IMapper<BillType> {
    /****
     * 根据业务类型查询对应的单证类型
     * @param bizId
     * @return
     */
    List<BillType> findBillTypeByBizId(@Param(value = "bizId") Long bizId);

    /**
     * 根据编码查询单证类型
     * @param billTypeCode
     * @return
     */
    BillType selectByBillTypeCode(String billTypeCode);

    /**
     * 根据主键id查询详细信息
     * @param id
     * @return
     */
    public BillTypeModel selectBillTypeModel(Long id);

    /**
     * @Description：[根据业务编码查询]
     * @Author：PeidongWang
     * @Param：bizTypeCode
     * @Date：2017/6/27 15:27
     */
    public List<BillType> selectBillTypesByBizCode(@Param(value = "bizTypeCode") String bizTypeCode);

}